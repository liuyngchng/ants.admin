package ants.admin.model;

/**
 * Created by richard on 3/20/19.
 */
public class TaskSearchDto {

    /**
     * 数据库主键
     */
    private int id;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 任务开始时间
     */
    private String createTimeStart;

    /**
     * 完成时间
     */
    private String createTimeEnd;

    /**
     * 任务状态
     */
    private String status;

    /**
     * 任务类型
     */
    private String taskType;

    /**
     * 发送者IP
     */
    private String originIp;

    /**
     * 接收者Ip
     */
    private String targetIp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * 页码，从1开始
     */
    private int pageNo;         //start from 1;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(String createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public String getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(String createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getOriginIp() {
        return originIp;
    }

    public void setOriginIp(String originIp) {
        this.originIp = originIp;
    }

    public String getTargetIp() {
        return targetIp;
    }

    public void setTargetIp(String targetIp) {
        this.targetIp = targetIp;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
}
