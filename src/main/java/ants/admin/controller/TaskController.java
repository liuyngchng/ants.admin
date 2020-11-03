package ants.admin.controller;

import ants.admin.enums.TaskStatus;
import ants.admin.enums.TaskType;
import ants.admin.model.TaskInfo;
import ants.admin.model.Pagination;
import ants.admin.model.TaskSearchDto;
import ants.admin.model.User;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * config database https://www.cnblogs.com/liangblog/p/5228548.html
 */
@Controller
@RequestMapping("/")
public class TaskController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    @Value("${spring.datasource.url}")
    private String dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("")
    public ModelAndView index() {
        LOGGER.info("spring.datasource.url is {}", dataSource);
        this.jdbcTemplate.execute("PRAGMA journal_mode=WAL");
        LOGGER.info("open wal mode.");
        ModelAndView modelAndView =  new ModelAndView("menu");
        modelAndView.addObject("name", "whoAmI");
        modelAndView.addObject("dae", new Date());
        String sql = " create table if not exists file_info(" +
            "id integer primary key," +
            "app_id integer," +
            "file_id integer," +
            "name varchar(128)," +
            "size integer," +
            "path varchar(128)," +
            "slice_size integer," +
            "slice_total integer," +
            "slice_snd integer," +
            "md5 char(32)," +
            "create_time timestamp not null default(strftime('%Y-%m-%d %H:%M:%f', 'now' ,'localtime'))," +
            "update_time timestamp not null default(strftime('%Y-%m-%d %H:%M:%f', 'now' ,'localtime'))," +
            "del integer default 0" +
            ");";
        LOGGER.debug(sql);
        this.jdbcTemplate.execute(sql);
        String sql1 = "create table if not exists task_info(" +
            "id integer primary key," +
            "task_name varchar(128)," +
            "app_id integer," +
            "data_size integer," +
            "uid varchar(128)," +
            "origin_ip char(16)," +
            "target_ip varchar(128)," +
            "used_time integer," +
            "complete_per char(5)," +       //完成率
            "create_time timestamp not null default(strftime('%Y-%m-%d %H:%M:%f', 'now' ,'localtime'))," +
            "finish_time timestamp," +
            "push_time timestamp," +
            "update_time timestamp," +
            "interrupt_time integer," +     //中断次数
            "retry_count integer," +        //重试次数
            "retry_time integer," +         //重试总时间
            "retry_ip char(16)," +          //重试机器IP
            "slice_sended integer," +       //已发送的包书
            "slice_total integer," +        //总包数
            "restart_time timestamp," +
            "cancel_time timestamp," +
            "file_id varchar(128)," +
            "priority integer," +
            "progress integer," +
            "op_uid varchar(128)," +
            "op_type integer," +
            "task_type integer," +          //任务类型
            "status integer," +             //任务状态
            "del integer default 0" +
            ");";
        LOGGER.debug(sql1);
        this.jdbcTemplate.execute(sql1);
//        String sql2 = "insert into task_info (task_name, status, task_type,origin_ip,"+
//            "target_ip,data_size,complete_per,used_time,interrupt_time,retry_count,"+
//            "retry_time,retry_ip,finish_time,push_time,slice_sended,slice_total)"+
//            " values ('abc.pdf', 3, 2,'192.168.0.1','192.168.0.2',100, '100%',10,0,0,0,'',"+
//            " '2020-11-11 08:00:39.000','2020-11-11 08:00:39.000',10,10)";
//        LOGGER.debug(sql2);
//        this.jdbcTemplate.execute(sql2);
        return modelAndView;
    }

    @RequestMapping("task_list")
    public ModelAndView taskList(HttpServletRequest request) {
        LOGGER.info("task_list, search = {}", request.getSession().getAttribute("s"));
        ModelAndView modelAndView =  new ModelAndView("task_list");
        if (null != request.getSession().getAttribute("s")) {
            final TaskSearchDto s = (TaskSearchDto) request.getSession().getAttribute("s");
            LOGGER.debug("searchDto in session is {}", s);
            modelAndView.addObject("task_name", s.getTaskName());
            modelAndView.addObject("create_time_start", s.getCreateTimeStart());
            modelAndView.addObject("create_time_end", s.getCreateTimeEnd());
            modelAndView.addObject("status", s.getStatus());
            modelAndView.addObject("task_type", s.getTaskType());
            modelAndView.addObject("origin_ip", s.getOriginIp());
            modelAndView.addObject("target_ip", s.getTargetIp());
        }
        return modelAndView;
    }

    @RequestMapping("task_detail")
    public ModelAndView taskDetail(final int id) {
        LOGGER.info("task detail , id = {}", id);
        final TaskSearchDto dto = new TaskSearchDto();
        dto.setId(id);
        dto.setPageNo(1);
        List<Map<String, Object>> data = this.getDataList(dto);
        ModelAndView modelAndView =  new ModelAndView("task_detail");
        if (null == data || data.isEmpty()) {
            LOGGER.error("error for task_detail, id = {}", id);
            return modelAndView;
        }
        modelAndView.addObject("id", data.get(0).get("id"));
        modelAndView.addObject("task_name", data.get(0).get("task_name"));
        modelAndView.addObject("create_time", data.get(0).get("create_time"));
        modelAndView.addObject("finish_time", data.get(0).get("finish_time"));
        modelAndView.addObject("file_info", data.get(0).get("file_info"));
        modelAndView.addObject("used_time", data.get(0).get("used_time"));
        modelAndView.addObject("complete_per", data.get(0).get("complete_per"));
        modelAndView.addObject("status", data.get(0).get("status"));
        if (null != data.get(0).get("task_type")) {
            if(String.valueOf(data.get(0).get("task_type")).equals("0")) {
                modelAndView.addObject("task_type", "发送");
            } else {
                modelAndView.addObject("task_type", "接收");
            }
        }
        modelAndView.addObject("origin_ip", data.get(0).get("origin_ip"));
        modelAndView.addObject("target_ip", data.get(0).get("target_ip"));
        modelAndView.addObject("create_time", data.get(0).get("create_time"));
        return modelAndView;
    }

    /**
     * 返回file_list的静态页面
     * @return
     */
    @RequestMapping("file_list")
    public ModelAndView fileList() {
        LOGGER.info("hello, fileList");
        ModelAndView modelAndView =  new ModelAndView("file_list");
        return modelAndView;
    }

    /**
     * 返回file_list的静态页面
     * @return
     */
    @RequestMapping("interrupt_list")
    public ModelAndView interruptList(Integer id) {
        LOGGER.info("interruptList, id = {}", id);
        final ModelAndView modelAndView =  new ModelAndView("interrupt_list");
        modelAndView.addObject("id", id);
        return modelAndView;
    }

    @RequestMapping("curve_data")
    public ModelAndView curveData() {
        LOGGER.info("hello, curveData");
        ModelAndView modelAndView =  new ModelAndView("curve_data");
        modelAndView.addObject("name", "whoAmI");
        modelAndView.addObject("dae", new Date());
        return modelAndView;
    }

    @RequestMapping("data")
    @ResponseBody
    public Pagination getData(final TaskSearchDto searchDto, final HttpServletRequest request) {
        LOGGER.debug("searchDto = {}", searchDto);
        TaskSearchDto mySearch = null;
        if (TaskController.isValidSearch(searchDto)) {
            LOGGER.debug("valid searchDto");
            request.getSession().setAttribute("s", searchDto);
            mySearch = searchDto;
        } else {
            LOGGER.debug("invalid searchDto");
            if (null == request.getSession().getAttribute("s")) {
                mySearch = searchDto;
            } else {
                mySearch = (TaskSearchDto)request.getSession().getAttribute("s");
            }
        }
        String countSql = TaskController.buildCountSql(mySearch);
        int count = this.jdbcTemplate.queryForObject(countSql, Integer.class);
        Pagination pagination = new Pagination(searchDto.getPageNo(),25,count);
        if (count > 1000) {
            LOGGER.error("search count > 1000, condition = {}", searchDto.toString());
            return pagination;
        }
        pagination.setRows(
            this.getDataList(searchDto)
        );
        return pagination;
    }

    @RequestMapping("reset_search")
    @ResponseBody
    public void resetSearch(final HttpServletRequest request) {
        LOGGER.debug("reset search");
        request.getSession().removeAttribute("s");
    }

    @RequestMapping("delete_task")
    @ResponseBody
    public int deleteTask(int id) {
        LOGGER.debug("deleteTask, id = {}", id);
        StringBuilder sb = new StringBuilder("update task_info set del = 1 where id = '");
        sb.append(id);
        sb.append("'");
        try {
            LOGGER.info("deleteTask, sql = {}",sb.toString());
            this.jdbcTemplate.execute(sb.toString());
        } catch (Exception ex) {
            LOGGER.error(ex.toString());
            return 1;
        }
        return 0;
    }

    @RequestMapping("view")
    public ModelAndView view() {
        LOGGER.info("hello, myview");
        ModelAndView modelAndView =  new ModelAndView("view");
        modelAndView.addObject("name", "whoAmI");
        modelAndView.addObject("dae", new Date());
        return modelAndView;
    }

    @RequestMapping("login")
    public ModelAndView login(HttpServletRequest request) {
        if(HttpMethod.GET.name().equals(request.getMethod())) {
            return new ModelAndView("login_index");
        }
        if (null != request.getSession().getAttribute("user")) {
            final User user = (User)request.getSession().getAttribute("user");
            LOGGER.info("user login {}", user.getName());
            ModelAndView modelAndView =  new ModelAndView("index");
            modelAndView.addObject("name", user.getName());
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            modelAndView.addObject("date", sf.format(new Date()));
            return modelAndView;
        }
        final String name = request.getParameter("name");
        final String password = request.getParameter("password");
        if (!Strings.isNullOrEmpty(name) && !Strings.isNullOrEmpty(password)
                && null != User.list.get(name) && User.list.get(name).equals(password)) {
            final User user = new User();
            user.setName(name);
            user.setPsword(password);
            request.getSession().setAttribute("user", user);
            LOGGER.info("user login success {}", name);
            ModelAndView modelAndView =  new ModelAndView("menu");
            modelAndView.addObject("name", user.getName());
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            modelAndView.addObject("date", sf.format(new Date()));
            return modelAndView;
        }
        ModelAndView modelAndView =  new ModelAndView("login_index");
        if (null == User.list.get(name)) {
            modelAndView.addObject("info", "用户名不存在，请重新输入");
        } else if (!User.list.get(name).equals(password)) {
            modelAndView.addObject("info", "密码错误，请重新输入");
        }
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        modelAndView.addObject("date", sf.format(new Date()));
        return modelAndView;
    }

    @RequestMapping("logout")
    public ModelAndView logout(HttpServletRequest request) {
        LOGGER.info("user logout");
        ModelAndView modelAndView =  new ModelAndView("login_index");
        modelAndView.addObject("info", "您已退出系统，欢迎下次再来");
        request.getSession().removeAttribute("user");
        return modelAndView;
    }

    @RequestMapping("task")
    public ModelAndView doTask(HttpServletRequest request) {
        LOGGER.info("hello, task");
        final String name = String.valueOf(request.getSession().getAttribute("user.name"));
        ModelAndView modelAndView;
        if (Strings.isNullOrEmpty(name) || name.equals("null")) {
            LOGGER.info("user name is null");
            return new ModelAndView("login_index");
        }
        if (name.equals("abc")) {
            modelAndView = new ModelAndView("task");
            if (request.getMethod().toLowerCase().equals("get")) {
                LOGGER.info("http get method.");
            } else {
                request.getParameterMap().forEach((k, v) -> {
                    if (k.startsWith("_s_")) {
                        final String score = v[0];
                        final String[] keys = k.replace("_s_", "").split("_");
                        final String sql = TaskController.buildUpdateSql(keys, score);
                        LOGGER.info("update sql is {}", sql);
                        int result = this.jdbcTemplate.update(sql);
                        LOGGER.info("update result is {}", result);
                    }
                });
            }
            modelAndView.addObject("file", UploadController.fileList);
        } else {
            modelAndView = new ModelAndView("task_illegal");
        }
        return modelAndView;
    }

    /**
     * 获取数据清单.
     * @param searchDto
     * @return
     */
    private List<Map<String, Object>> getDataList(TaskSearchDto searchDto) {
        String sql = TaskController.buildSearchSql(searchDto);
        LOGGER.debug("search sql = {}", sql);
        List<Map<String,Object>> result = this.jdbcTemplate.query(sql, new RowMapper<Map<String,Object>>() {
            @Override
            public Map mapRow(ResultSet rs, int rowNum) throws SQLException {
                Map row = new HashMap();
                row.put("id", rs.getInt("id"));
                row.put("task_name", rs.getString("task_name"));
                row.put("create_time", rs.getString("create_time"));
                row.put("finish_time", rs.getString("finish_time"));
                int data_size = Integer.parseInt(rs.getString("data_size"));
                if (data_size > 0  && data_size < 1024) {
                    row.put("data_size", data_size + "B");
                } else if (data_size >= 1024 && data_size < 1024 * 1024) {
                    data_size = data_size / 1024;
                    row.put("data_size", data_size + "KB");
                } else if (data_size >= 1024 *1024 && data_size < 1024 * 1024 * 1024) {
                    data_size = data_size / (1024 * 1024);
                    row.put("data_size", data_size + "MB");
                } else if (data_size >= 1024 *1024 *1024 && data_size < 1024 * 1024 * 1024 * 1024) {
                    data_size = data_size / (1024 * 1024 * 1024);
                    row.put("data_size", data_size + "GB");
                } else {
                    row.put("data_size", data_size + "B");
                }
                final String taskName = rs.getString("task_name");
                row.put("file_type", taskName.substring(taskName.lastIndexOf(".")+1));
                row.put("complete_per", rs.getString("complete_per"));
                TaskStatus status = TaskStatus.getStatus(Integer.parseInt(rs.getString("status")));
                row.put("status", status == null ? rs.getString("status"): status.getDesc());
                row.put("task_type", "发送");
                row.put("origin_ip", rs.getString("origin_ip"));
                row.put("target_ip", rs.getString("target_ip"));
                return row;
            }
        });
        return result;
    }


    private static String buildCountSql(TaskSearchDto dto) {
        StringBuilder sb = new StringBuilder("select count(1) from task_info where 1=1 ");
        sb.append(TaskController.buildSearchCondition(dto));
        return sb.toString();
    }

    private static String buildSearchSql(TaskSearchDto dto) {
        StringBuilder sb = new StringBuilder("select * from task_info where 1=1 ");
        sb.append(TaskController.buildSearchCondition(dto));
        sb.append(" order by create_time desc ");
        sb.append("limit ");
        sb.append(dto.getPageNo() -1);
        sb.append(", 25");
        return sb.toString();
    }

    private static StringBuilder buildSearchCondition(TaskSearchDto dto) {
        final StringBuilder sb = new StringBuilder();
        if (Strings.isNullOrEmpty(dto.getStatus())) {
            sb.append("and del = 0 ");
        } else {
            final int a = Integer.parseInt(dto.getStatus());
            if (TaskStatus.DELETED.getStatus() == a) {
                sb.append("and del = 1");
            } else {
                sb.append("and status = ");
                sb.append(dto.getStatus());
            }
            sb.append(" ");
        }
        if (!Strings.isNullOrEmpty(dto.getTaskName())) {
            sb.append(" and task_name like '%");
            sb.append(dto.getTaskName());
            sb.append("%' ");
        }
        if (!Strings.isNullOrEmpty(dto.getCreateTimeStart())) {
            sb.append("and create_time >= '");
            sb.append(dto.getCreateTimeStart());
            sb.append("' ");
        }
        if (!Strings.isNullOrEmpty(dto.getCreateTimeEnd())) {
            sb.append("and create_time <= '");
            sb.append(dto.getCreateTimeEnd());
            sb.append("' ");
        }
//        if (!Strings.isNullOrEmpty(dto.getTaskType())) {
//            int a = Integer.parseInt(dto.getTaskType());
//            sb.append("and task_type = ");
//            sb.append(dto.getTaskType());
//            sb.append(" ");
//        }

        if (!Strings.isNullOrEmpty(dto.getOriginIp())) {
            sb.append("and origin_ip = '");
            sb.append(dto.getOriginIp());
            sb.append("' ");
        }
        if (!Strings.isNullOrEmpty(dto.getTargetIp())) {
            sb.append("and target_ip = '");
            sb.append(dto.getTargetIp());
            sb.append("' ");
        }
        return sb;
    }

    private static String buildUpdateSql(String[] keys, String score) {
        if (keys.length != 4) {
            return "";
        }
        StringBuilder sb = new StringBuilder("update test.test set score = '");
        sb.append(score);
        sb.append("' where true ");
        sb.append("and city = '" + keys[0] + "'");
        sb.append("and item = '" + keys[1] + "'");
        sb.append("and id1 = '" + keys[2] + "'");
        sb.append("and id2 = '" + keys[3] + "'");
        return sb.toString();
    }

    private static boolean isValidSearch(TaskSearchDto dto) {
        return !Strings.isNullOrEmpty(dto.getTaskName())
                || !Strings.isNullOrEmpty(dto.getCreateTimeStart())
                || !Strings.isNullOrEmpty(dto.getCreateTimeEnd())
                || !Strings.isNullOrEmpty(dto.getStatus())
                || !Strings.isNullOrEmpty(dto.getTaskType())
                || !Strings.isNullOrEmpty(dto.getOriginIp())
                || !Strings.isNullOrEmpty(dto.getTargetIp());
    }
}
