<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>物流管理系统</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layuiadmin/style/admin.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layuiadmin/style/login.css" media="all">
</head>
<body>

<div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login" style="display: none;">

    <div class="layadmin-user-login-main">
        <div class="layadmin-user-login-box layadmin-user-login-header">
            <h2>物流管理系统</h2>
            <p>Logistics Management System</p>
        </div>
        <div class="layadmin-user-login-box layadmin-user-login-body layui-form">
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-username" for="LAY-user-login-username"></label>
                <input type="text" name="username" id="LAY-user-login-username" lay-verify="required|name" placeholder="用户名" class="layui-input">
            </div>
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-login-password"></label>
                <input type="password" name="password" id="LAY-user-login-password" lay-verify="required|pass" placeholder="密码" class="layui-input">
            </div>
<%--            <div class="layui-form-item">--%>
<%--                <div class="layui-row">--%>
<%--                    <div class="layui-col-xs7">--%>
<%--                        <label class="layadmin-user-login-icon layui-icon layui-icon-vercode" for="LAY-user-login-vercode"></label>--%>
<%--                        <input type="text" name="vercode" id="LAY-user-login-vercode" lay-verify="required" placeholder="图形验证码" class="layui-input">--%>
<%--                    </div>--%>
<%--                    <div class="layui-col-xs5">--%>
<%--                        <div style="margin-left: 10px;">--%>
<%--                            <img src="https://www.oschina.net/action/user/captcha" class="layadmin-user-login-codeimg" id="LAY-user-get-vercode">--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
            <div class="layui-form-item">
                <button class="layui-btn layui-btn-fluid" lay-submit lay-filter="LAY-user-login-submit">登 入</button>
            </div>

        </div>
    </div>





    <!--<div class="ladmin-user-login-theme">
      <script type="text/html" template>
        <ul>
          <li data-theme=""><img src="{{ layui.setter.base }}style/res/bg-none.jpg"></li>
          <li data-theme="#03152A" style="background-color: #03152A;"></li>
          <li data-theme="#2E241B" style="background-color: #2E241B;"></li>
          <li data-theme="#50314F" style="background-color: #50314F;"></li>
          <li data-theme="#344058" style="background-color: #344058;"></li>
          <li data-theme="#20222A" style="background-color: #20222A;"></li>
        </ul>
      </script>
    </div>-->

</div>

<script src="${pageContext.request.contextPath}/resources/layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '${pageContext.request.contextPath}/resources/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'user'], function(){
        var $ = layui.$
            ,setter = layui.setter
            ,admin = layui.admin
            ,form = layui.form
            ,router = layui.router()
            ,search = router.search;

        form.render();

        //提交
        form.on('submit(LAY-user-login-submit)', function(obj){
            var url = "${pageContext.request.contextPath}/login.do",
                params = "username="+obj.field.username+"&password="+obj.field.password;
            $.post(url,params,function (res) {
                if (res.result === "true") {
                    layer.msg('登入成功', {
                        offset: '440px'
                        , icon: 1
                        , time: 1000
                    }, function () {
                        location.href = '${pageContext.request.contextPath}/jsp/home.jsp'; //后台主页
                    });
                } else if(res.result === "passfalse") {
                    layer.msg('密码错误', {
                        offset: '440px'
                        , icon: 2
                        , time: 1000
                    });
                }else if(res.result === "namefalse") {
                    layer.msg('用户名错误', {
                        offset: '400px'
                        , icon: 2
                        , time: 1000
                    });
                }

            });

            form.verify({
                name: [
                    /^[\S]{1,10}$/
                    , '用户名过长'
                ]

                //我们既支持上述函数式的方式，也支持下述数组的形式
                //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
                ,pass: [
                    /^[\S]{6,10}$/
                    ,'密码必须6到10位，且不能出现空格'
                ]
            });




            // location.href = "${pageContext.request.contextPath}/login.do?username="+obj.field.username+"&password="+obj.field.password;

            // //请求登入接口
            // admin.req({
            //   url: layui.setter.base + 'json/user/login.js' //实际使用请改成服务端真实接口
            //   ,data: obj.field
            //   ,done: function(res){
            //
            //     //请求成功后，写入 access_token
            //     layui.data(setter.tableName, {
            //       key: setter.request.tokenName
            //       ,value: res.data.access_token
            //     });
            //
            //     //登入成功的提示与跳转
            //     layer.msg('登入成功', {
            //       offset: '15px'
            //       ,icon: 1
            //       ,time: 1000
            //     }, function(){
            //       location.href = '../'; //后台主页
            //     });
            //   }
            // });

        });




    });
</script>
</body>
</html>