
<div><label class="col-sm-2 control-label backstage-select-label">首页->任务查询</label></div>
<div class="ibox float-e-margins backstage-right-div">
    <input id="contextParam" type="hidden" value="/"/>
    <div class="ibox-content">
        <form method="get" class="form-horizontal">

            <div class="form-group backstage-form-group">
                <label class="col-sm-2 control-label backstage-select-label">文件名称:</label>
                <div class="col-sm-10 backstage-col-sm-10">
                    <input placeholder="请输入文件名称" class="form-control backstage-input"
                           type="text" id="task_name" name="task_name" value="${task_name!}">
                </div>
            </div>

            <div class="hr-line-dashed backstage-hr-line-dashed"></div>
            <div class="form-group backstage-form-group">
                <label class="col-sm-2 control-label backstage-select-label">时间:</label>
                <div class="col-sm-10 backstage-col-sm-10">
                    <input id="startTime" class="laydate-icon form-control layer-date backstage-date"
                           readonly="readonly" placeholder="开始时间" name="startTime" value="${create_time_start!}" />
                    <input id="endTime" class="laydate-icon form-control layer-date backstage-date" readonly="readonly"
                           placeholder="结束时间" name="endTime" value="${create_time_end!}"/>
                </div>
            </div>
            <div class="hr-line-dashed backstage-hr-line-dashed"></div>

            <div class="form-group backstage-form-group">
                <label class="col-sm-2 control-label backstage-select-label">任务状态:</label>
                <div class="col-sm-10 backstage-col-sm-10">
                    <select class="form-control m-b backstage-select" name="status" id="status">
                        <option value="">-- 全部 --</option>
                        <option value="0">初始化</option>
                        <option value="1">提交成功</option>
                        <option value="2">发送中</option>
                        <option value="3">已停止</option>
                        <option value="4">已取消</option>
                        <option value="5">发送失败</option>
                        <option value="6">发送成功</option>
                        <option value="7">投递成功</option>
<#--                        <option value="8">已删除</option>-->
                    </select>
                </div>
            </div>
            <div class="hr-line-dashed backstage-hr-line-dashed"></div>

            <div class="form-group backstage-form-group">
                <label class="col-sm-2 control-label backstage-select-label">任务类型:</label>
                <div class="col-sm-10 backstage-col-sm-10">
                    <select class="form-control m-b backstage-select" name="task_type" id="task_type">
                        <option value="">-- 全部 --</option>
                        <option value="0">接收</option>
                        <option value="1">发送</option>
                    </select>
                </div>
            </div>
            <div class="hr-line-dashed backstage-hr-line-dashed"></div>

            <div class="form-group backstage-form-group">
                <label class="col-sm-2 control-label backstage-select-label">发送端IP:</label>
                <div class="col-sm-10 backstage-col-sm-10">
                    <input placeholder="请输入发送端IP" class="form-control backstage-input"
                           type="text" id="origin_ip" name="origin_ip" value="${origin_ip!}">
                </div>
            </div>
            <div class="hr-line-dashed backstage-hr-line-dashed"></div>
            <div class="form-group backstage-form-group">
                <label class="col-sm-2 control-label backstage-select-label">接收端传输服务IP:</label>
                <div class="col-sm-10 backstage-col-sm-10">
                    <input placeholder="请输入接收端传输服务IP" class="form-control backstage-input" type="target_ip" id="target_ip"
                           name="target_ip" value="${target_ip!}">
                </div>
            </div>
            <div class="hr-line-dashed backstage-hr-line-dashed"></div>

            <div class="form-group backstage-form-group">
                <div class="col-sm-2 backstage-select-label">
                    <button type="button" class="btn btn-danger backstage-button" onclick="search()">查&nbsp;&nbsp;&nbsp;询</button>
                </div>
                <div class="col-sm-2 backstage-select-label">
                    <button type="button" class="btn btn-danger backstage-button" onclick="resetSearch()">重置查询条件</button>
                </div>
            </div>
            <div class="hr-line-dashed backstage-hr-line-dashed"></div>
        </form>
    </div>
    <div class="ibox-content">
        <div class="bootstrap-table table-responsive backstage-table-div">
            <div class="fixed-table-container">
                <table id="table"></table>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>
</div>
<!-- 隐藏域 -->
<div style="display: none">
    <!-- 如果点击搜索,则需要将页码制为1 -->
    <input id="ifPageOne" name="ifPageOne" value="0"/>
</div>
<script type="text/javascript">
    //日期控件
    var startTime = {
        elem: '#startTime',
        format: 'YYYY-MM-DD hh:mm:ss',
        istime: true,
        max: laydate.now(),
        choose: function (datas) {
            endTime.min = datas;
            endTime.start = datas;
        }
    };
    var endTime = {
        elem: '#endTime',
        format: 'YYYY-MM-DD hh:mm:ss',
        istime: true,
        max: laydate.now(),
        choose: function (datas) {
            startTime.max = datas;
        }
    };
    this.laydate(startTime);
    this.laydate(endTime);
    this.setStatus(${status!});
    this.setTaskType(${task_type!});
    <#--this.setTimeStart( ${create_time_start!});-->
    <#--this.setTimeEnd(${create_time_end!});-->

    //table
    $('#table').bootstrapTable({
        cache: false,
        method: 'post',
        contentType: 'application/x-www-form-urlencoded',
        dataType: 'json',
        url: '/data',
        undefinedText: '-',
        striped: true,
        pagination: true,
        sidePagination: 'server',
        pageSize: 25,
        pageNumber: 1,
        pageList: [],
        queryParams: queryParams,
        columns: [
            {
                field: 'id',
                title: '序号',
                width: "50px"
            }, {
                field: 'task_name',
                title: '文件名称'
            }, {
                field: 'create_time',
                title: '开始时间'
            }, {
                field: 'finish_time',
                title: '结束时间'
            }, {
                field: 'data_size',
                title: '数据量'
            }, {
                field: 'complete_per',
                title: '任务完成率（%）'
            }, {
                field: 'status',
                title: '任务状态'
            }, {
                field: 'task_type',
                title: '任务类型'
            }, {
                field: 'origin_ip',
                title: '发送端IP'
            }, {
                field: 'target_ip',
                title: '接收端传输服务IP'
            }, {
                field: 'id',
                title: '操作',
                formatter: function (value, row, index) {
                    return '<button type="button" class="btn btn-danger backstage-button" onclick="stop()">暂停</button>'+
                        '<button type="button" class="btn btn-danger backstage-button" onclick="stop()">取消</button>'+
                        '<button type="button" class="btn btn-danger backstage-button" onclick="stop()">重试</button><br/>'+
                        '<button type="button" class="btn btn-danger backstage-button" onclick="delete_task('+ value +')">删除</button>'+
                        '<button type="button" class="btn btn-danger backstage-button" onclick="get_task_detail('+ value +')">任务详情</button>'+
                        '<button type="button" class="btn btn-danger backstage-button" onclick="get_file_list('+ value +')">文件信息</button>'
                        ;
                }
            }]
    })
    //设置查询参数
    function queryParams(params) {
        if ($("#ifPageOne").val() == 1) {
            params.offset = 0;
            var obj = $('#table').bootstrapTable('getOptions');
            obj.pageNumber = 1;
            $("#ifPageOne").val(0);
        }
        //applyDate
        var startTime = $("#startTime").val();
        if (startTime != "") {
            // startTime = startTime.replace(/[^0-9]/mg, '');
        }
        var endTime = $("#endTime").val();
        if (endTime != "") {
            // endTime = endTime.replace(/[^0-9]/mg, '');
        };
        var obj = {
            taskName:$("#task_name").val(),
            createTimeStart: $("#startTime").val(),
            createTimeEnd: $("#endTime").val(),
            status: $("#status").val(),
            taskType: $("#task_type").val(),
            originIp: $("#origin_ip").val(),
            targetIp: $("#target_ip").val(),
            pageSize: params.limit,
            pageNo: params.offset / 10 + 1
        }
        return obj;
    }

    //查询按钮方法
    function search() {
        $("#ifPageOne").val(1);
        $('#table').bootstrapTable('removeAll');
        $('#table').bootstrapTable('refresh');
    }

    function resetSearch() {
        $("#task_name").val("");
        $("#startTime").val("");
        $("#status").val("");
        $("#task_type").val("");
        $("#origin_ip").val("");
        $("#target_ip").val("");
        $.ajax({
            method: "post",
            url: "/reset_search",
            data: {"id": ""},
            success: function(data) {

            }
        })
    }


    //表格时间格式化
    function tableDateFormatter(dateStr) {
        if (dateStr != null && dateStr != "" && dateStr.length == 14) {
            var strArray = dateStr.split("");
            strArray.splice(4, 0, "-");
            strArray.splice(7, 0, "-");
            strArray.splice(10, 0, " ");
            strArray.splice(13, 0, ":");
            strArray.splice(16, 0, ":");
            return strArray.join("");
        } else {
            return dateStr;
        }
    }

    Date.prototype.format = function (format) {
        var date = {
            "M+": this.getMonth() + 1,
            "d+": this.getDate(),
            "h+": this.getHours(),
            "m+": this.getMinutes(),
            "s+": this.getSeconds(),
            "q+": Math.floor((this.getMonth() + 3) / 3),
            "S+": this.getMilliseconds()
        };
        if (/(y+)/i.test(format)) {
            format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
        }
        for (var k in date) {
            if (new RegExp("(" + k + ")").test(format)) {
                format = format.replace(RegExp.$1, RegExp.$1.length == 1
                    ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
            }
        }
        return format;
    }

    function showJson(obj) {
        var trElement = $(obj).parent().parent().parent()
        var json = $(obj).attr("data-id");
        json = JSON.parse(json);
        json = JSON.stringify(json, null, 4).replace(/\n/g, "<br/>").replace(/\s/g, "&nbsp;");
        var insertElement =
            "<tr class = 'json' >" +
            "<td>J</td>" +
            "<td><a class='hideJson' href='javascript:void(0);' onclick='hideJson(this)'>隐藏</a></td>" +
            "<td colspan='19' style='width:200px;'>" + json + "</td>" +
            "</tr>";
        $(insertElement).insertAfter(trElement);
        $(obj).hide();
    }

    function setStatus(value) {
        if (value == null && value == "") {
            return;
        }
        $("#status").val(value);
    }

    function setTaskType(value) {
        if (value == null && value == "") {
            return;
        }
        $("#task_type").val(value);
    }

    function setTimeStart(value) {
        if (value == null && value == "") {
            return;
        }
        $("#create_time_start").val(value);
    }

    function setTimeEnd(value) {
        if (value == null && value == "") {
            return;
        }
        $("#create_time_end").val(value);
    }

    function delete_task(id) {
        $.ajax({
            method: "post",
            url: "/delete_task",
            data: {id: id},
            success: function(data) {
                if ('0' == data) {
                    alert('删除成功');
                } else {
                    alert('删除失败');
                }
                search();
            }
        })
    }

    function get_task_detail(id) {
        $.ajax({
            method: "post",
            url: "/task_detail",
            data: {id: id},
            success: function(data) {
                $("#page-wrapper").empty();
                $("#page-wrapper").html(data);
                drawCurve();
            }
        })
    }

    function get_file_list(id) {
        $.ajax({
            method: "post",
            url: "/file_list",
            data: {id: id},
            success: function(data) {
                $("#page-wrapper").empty();
                $("#page-wrapper").html(data);
                drawCurve();
            }
        })
    }

</script>
