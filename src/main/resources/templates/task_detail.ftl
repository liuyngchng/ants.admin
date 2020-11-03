
<div><label class="col-sm-2 control-label backstage-select-label">
        首页->
        <a href="javascript:getPage('/task_list')">任务查询</a>->任务详请
    </label></div>
<div class="ibox float-e-margins backstage-right-div">
    <input id="contextParam" type="hidden" value="/"/>
    <div class="ibox-content">
        <form method="get" class="form-horizontal">

            <div class="form-group backstage-form-group">
                <label class="col-sm-2 control-label backstage-select-label">文件名称:</label>
                <div class="col-sm-10 backstage-col-sm-10">
                    <input readonly="readonly" class="form-control backstage-input" type="text" value="${task_name!}">
                </div>
            </div>
            <div class="hr-line-dashed backstage-hr-line-dashed"></div>

            <div class="form-group backstage-form-group">
                <label class="col-sm-2 control-label backstage-select-label">开始时间:</label>
                <div class="col-sm-10 backstage-col-sm-10">
                    <input readonly="readonly" class="form-control backstage-input" type="text" value="${create_time!}">
                </div>
            </div>
            <div class="hr-line-dashed backstage-hr-line-dashed"></div>

            <div class="form-group backstage-form-group">
                <label class="col-sm-2 control-label backstage-select-label">结束时间:</label>
                <div class="col-sm-10 backstage-col-sm-10">
                    <input readonly="readonly" class="form-control backstage-input" type="text" value="${finish_time!}">
                </div>
            </div>
            <div class="hr-line-dashed backstage-hr-line-dashed"></div>

            <div class="form-group backstage-form-group">
                <label class="col-sm-2 control-label backstage-select-label">文件信息:</label>
                <div class="col-sm-10 backstage-col-sm-10">
                    <input readonly="readonly" class="form-control backstage-input" type="text" value="${file_info!}">
                </div>
            </div>
            <div class="hr-line-dashed backstage-hr-line-dashed"></div>

            <div class="form-group backstage-form-group">
                <label class="col-sm-2 control-label backstage-select-label">任务用时:</label>
                <div class="col-sm-10 backstage-col-sm-10">
                    <input readonly="readonly" class="form-control backstage-input" type="text" value="${used_time!}">
                </div>
            </div>
            <div class="hr-line-dashed backstage-hr-line-dashed"></div>

            <div class="form-group backstage-form-group">
                <label class="col-sm-2 control-label backstage-select-label">任务完成率:</label>
                <div class="col-sm-10 backstage-col-sm-10">
                    <input readonly="readonly" class="form-control backstage-input" type="text" value="${complete_per!}">
                </div>
            </div>
            <div class="hr-line-dashed backstage-hr-line-dashed"></div>

            <div class="form-group backstage-form-group">
                <label class="col-sm-2 control-label backstage-select-label">任务状态:</label>
                <div class="col-sm-10 backstage-col-sm-10">
                    <input readonly="readonly" class="form-control backstage-input" type="text" value="${status!}">
                </div>
            </div>
            <div class="hr-line-dashed backstage-hr-line-dashed"></div>

            <div class="form-group backstage-form-group">
                <label class="col-sm-2 control-label backstage-select-label">任务类型:</label>
                <div class="col-sm-10 backstage-col-sm-10">
                    <input readonly="readonly" class="form-control backstage-input" type="text" value="${task_type!}">
                </div>
            </div>
            <div class="hr-line-dashed backstage-hr-line-dashed"></div>

            <div class="form-group backstage-form-group">
                <label class="col-sm-2 control-label backstage-select-label">发送端IP:</label>
                <div class="col-sm-10 backstage-col-sm-10">
                    <input readonly="readonly" class="form-control backstage-input" type="text" value="${origin_ip!}">
                </div>
            </div>
            <div class="hr-line-dashed backstage-hr-line-dashed"></div>

            <div class="form-group backstage-form-group">
                <label class="col-sm-2 control-label backstage-select-label">接收端传输服务IP:</label>
                <div class="col-sm-10 backstage-col-sm-10">
                    <input readonly="readonly" class="form-control backstage-input" type="text" value="${target_ip!}">
                </div>
            </div>
            <div class="hr-line-dashed backstage-hr-line-dashed"></div>

            <div class="form-group backstage-form-group">
                <label class="col-sm-2 control-label backstage-select-label">中断信息:</label>
                <div class="col-sm-10 backstage-col-sm-10">
                    <button type="button" class="btn btn-danger backstage-button" onclick="get_page_with_data('/interrupt_list', ${id!})">中断详情</button>
                </div>
            </div>
            <div class="hr-line-dashed backstage-hr-line-dashed"></div>

            <div class="form-group backstage-form-group">
                <label class="col-sm-2 control-label backstage-select-label">BW重传率:</label>
                <div class="col-sm-10 backstage-col-sm-10">
                    <input readonly="readonly" class="form-control backstage-input" type="text" value="">
                </div>
            </div>
            <div class="hr-line-dashed backstage-hr-line-dashed"></div>

            <div class="form-group backstage-form-group">
                <label class="col-sm-2 control-label backstage-select-label">BW头压缩率:</label>
                <div class="col-sm-10 backstage-col-sm-10">
                    <input readonly="readonly" class="form-control backstage-input" type="text" value="">
                </div>
            </div>
            <div class="hr-line-dashed backstage-hr-line-dashed"></div>

            <div class="form-group backstage-form-group">
                <label class="col-sm-2 control-label backstage-select-label">SS数据帧间压缩率:</label>
                <div class="col-sm-10 backstage-col-sm-10">
                    <input readonly="readonly" class="form-control backstage-input" type="text" value="">
                </div>
            </div>
            <div class="hr-line-dashed backstage-hr-line-dashed"></div>
        </form>
    </div>
</div>
<!-- 隐藏域 -->
<div style="display: none">
    <!-- 如果点击搜索,则需要将页码制为1 -->
    <input id="ifPageOne" name="ifPageOne" value="0"/>
</div>
<script type="text/javascript">

    function get_page_with_data(url, v) {
        $.ajax({
            method: "post",
            url: url,
            data: {"id":v},
            success: function(data) {
                $("#page-wrapper").empty();
                $("#page-wrapper").html(data);
                drawCurve();
            }
        })
    }
</script>
