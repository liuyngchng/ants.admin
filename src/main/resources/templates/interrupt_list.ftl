
<div><label class="col-sm-2 control-label admin-select-label">
        首页->
        <a href="javascript:getPage('/task_list')">任务查询</a>->
        <a href="javascript:get_task_detail(${id!})">任务详请</a>->中断详情
    </label></div>
<div class="ibox float-e-margins backstage-right-div">
    <input id="contextParam" type="hidden" value="/"/>
    <div class="ibox-content" >
        <div style="display: none" class="form-group backstage-form-group">
            <label class="col-sm-2 control-label backstage-select-label">时间:</label>
            <div class="col-sm-10 backstage-col-sm-10">
                <input id="startTime" class="laydate-icon form-control layer-date backstage-date"
                       readonly="readonly" placeholder="开始时间" name="create_time">
                <input id="endTime" class="laydate-icon form-control layer-date backstage-date" readonly="readonly"
                       placeholder="结束时间" name="finish_time">
            </div>
        </div>
        <div class="hr-line-dashed backstage-hr-line-dashed"></div>
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
                field: 'time',
                title: '时间'
            },{
                field: 'ip',
                title: 'IP'
            }, {
                field: 'operator',
                title: '操作者'
            }, {
                field: 'note',
                title: '备注'
            }]
    })
</script>
