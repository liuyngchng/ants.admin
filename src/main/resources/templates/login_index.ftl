<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>login</title>
<#--    <script type="text/javascript" src="/static/js/jquery.min.js" ></script>-->
<#--    <script type="text/javascript" src="/static/js/bootstrap.min.js" ></script>-->
<#--    <script type="text/javascript" src="/static/js/jquery-ui-1.10.4.min.js" ></script>-->
<#--    <script type="text/javascript" src="/static/js/content.js" ></script>-->
<#--    <!-- 日期插件 &ndash;&gt;-->
<#--    <script type="text/javascript" src="/static/js/plugins/layer/laydate/laydate.js"></script>-->
<#--    <!-- bootstrap table &ndash;&gt;-->
<#--    <script type="text/javascript" src="/static/js/plugins/bootstrap-table/bootstrap-table.js"></script>-->
<#--    <script type="text/javascript" src="/static/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>-->
<#--    <link href="/static/js/plugins/bootstrap-table/bootstrap-table.css" rel="stylesheet">-->
<#--    <!--menu&ndash;&gt;-->
<#--    <script type="text/javascript" src="/static/js/plugins/metisMenu/jquery.metisMenu.js"></script>-->
<#--    <script type="text/javascript" src="/static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>-->
<#--    <script type="text/javascript" src="/static/js/hAdmin.js"></script>-->

<#--    <script type="text/javascript" src="/static/js/emergency.js"></script>-->

<#--    <!--一些常用的函数&ndash;&gt;-->
<#--    <script type="text/javascript" src="/static/js/common.js"></script>-->

<#--    <link href="/static/css/animate.css" rel="stylesheet">-->
<#--    <link href="/static/css/bootstrap.min.css" rel="stylesheet">-->
<#--    <link href="/static/css/font-awesome.min.css" rel="stylesheet">-->
<#--    <link href="/static/css/login.css" rel="stylesheet">-->
<#--    <link href="/static/css/style.css" rel="stylesheet">-->
<#--    <link href="/static/css/main.css" rel="stylesheet">-->
<#--    <link href="/static/css/patton/patton.css" rel="stylesheet">-->
<#--    <link href="/static/css/emergency.css" rel="stylesheet">-->
<#--    <link href="/static/css/jquery-ui.css" rel="stylesheet">-->
<#--    <link href="/static/css/canvas.css" rel="stylesheet">-->
    <style type="text/css">
        #footer {
            position: absolute;
            bottom: 0;
            width: 100%;
            height: 60px;/*脚部的高度*/
            /*background: #6cf;*/
            clear:both;
        }
    </style>
</head>
<body>
    <div style="padding: 100px 100px 10px;">
        <h2 align="center">

            <form method="post" action="/login" class="bs-example bs-example-form" role="form">


                <div class="input-group input-group-lg">
<#--                    <label class="col-sm-2 control-label backstage-select-label">用户名:</label>-->
                    <span class="label label-default" style="font-family: 'Microsoft Sans Serif'">用户名:</span>
                    <input type="text" name="name" class="form-control" style="height: 20px;" placeholder="请输入用户名">
                </div><br>

                <div class="input-group input-group-lg">
                    <span class="label label-default" style="font-family: 'Microsoft Sans Serif'">密&nbsp;&nbsp;&nbsp;&nbsp;码:</span>
                    <input type="password" name="password" class="form-control" style="height: 20px;" placeholder="请输入密码">
                </div><br>
                <div class="input-group input-group-lg">
                    <span class="input-group-addon"> </span>
                    <input type="submit" class="form-control" style="font-family: 'Microsoft Sans Serif';" value="登&nbsp;&nbsp;录">
                </div><br>
               <div style="font-family: 'Microsoft Sans Serif'; color: #bd362f;">${info!"<br/>"}</div>
            </form>
        </h2>
    </div>
   <div id="footer" style="text-align: center">
       <a class="l-column-center" target="_blank" href="http://www.miitbeian.gov.cn/" style="font-family: 'Microsoft Sans Serif';"> 京 ICP 备 1234 号 </a>
   </div>
</body>
</html>