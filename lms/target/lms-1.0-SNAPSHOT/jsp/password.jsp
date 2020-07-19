<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>password</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layuiadmin/layui/css/layui.css" >
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layuiadmin/style/admin.css" >

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layuiadmin/layui/css/modules/layer/default/layer.css?v=3.1.1">
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">修改密码</div>
                <div class="layui-card-body" pad15>

                    <div class="layui-form" lay-filter="">
                        <div class="layui-form-item">
                            <label class="layui-form-label">当前密码</label>
                            <div class="layui-input-inline">
                                <input type="password" name="oldPassword" id="oldPassword" lay-verify="required|check" lay-verType="tips" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">新密码</label>
                            <div class="layui-input-inline">
                                <input type="password" name="password" lay-verify="pass" lay-verType="tips" autocomplete="off" id="password" class="layui-input">
                            </div>
                            <div class="layui-form-mid layui-word-aux">6到10个字符</div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">确认新密码</label>
                            <div class="layui-input-inline">
                                <input type="password" name="repassword" lay-verify="repass" lay-verType="tips" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit lay-filter="dosubmit">确认修改</button>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

</body>
<script src="../js/jquery-3.5.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.js" ></script>
<script src="${pageContext.request.contextPath}/resources/layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '${pageContext.request.contextPath}/resources/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index',//主入口模块
    }).use(['index', 'useradmin', 'table', 'laydate', 'laypage', 'layer', 'form', 'upload', 'element'], function () {
        var $ = layui.$
            , form = layui.form
            , table = layui.table
            , layer = layui.layer

        form.verify({
            check: function(value, item){ //value：表单的值、item：表单的DOM对象
                var url = "${pageContext.request.contextPath}/jsp/user.do",
                    params = "method=checkpwd&oldpassword=" + value,
                    flag = '';
                $.ajaxSettings.async = false;
                $.post(url, params, function (res) {
                    if (res.result === "false") {
                        flag = false;
                    }
                    if (res.result === "sessionerror") {
                        flag = "sessionerror";
                    }
                    if (res.result === "true") {
                       flag  = true;

                    }
                });
                $.ajaxSettings.async = true;
                if (flag ===false) {
                    return '旧密码输入错误'
                }if (flag ==="sessionerror") {
                    return '身份过期，请重新登陆'
                }

            }

            ,pass: [
                /^[\S]{6,10}$/
                ,'密码必须6到10位，且不能出现空格'
            ]

            //确认密码
            ,repass: function(value){
                if(value !== $('#password').val()){
                    return '两次密码输入不一致';
                }
            }
        });

        form.on('submit(dosubmit)', function (index) {
            var params = "method=updatepwd&newpassword=" + $("#password").val();
            $.post('${pageContext.request.contextPath}/jsp/user.do', params, function (res) {
                if (res.result === "true") {

                  //  layer.msg('修改成功，即将重新登陆');
                    parent.location.reload();

                } else {
                    layer.msg('修改失败');
                }

            });

        });




    });
</script>
</html>
