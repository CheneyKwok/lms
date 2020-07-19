<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html >
<head>
    <meta charset="utf-8">
    <title>物流管理系统</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layuiadmin/style/admin.css" media="all">


</head>
<body class="layui-layout-body">

<div id="LAY_app">
    <div class="layui-layout layui-layout-admin">
        <div class="layui-header">
            <!-- 头部区域 -->
            <ul class="layui-nav layui-layout-left">
                <li class="layui-nav-item layadmin-flexible" lay-unselect>
                    <a href="javascript:;" layadmin-event="flexible" title="侧边伸缩">
                        <i class="layui-icon layui-icon-shrink-right" id="LAY_app_flexible"></i>
                    </a>
                </li>
                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="http://www.layui.com/admin/" target="_blank" title="前台">
                        <i class="layui-icon layui-icon-website"></i>
                    </a>
                </li>
                <li class="layui-nav-item" lay-unselect>
                    <a href="javascript:;" layadmin-event="refresh" title="刷新">
                        <i class="layui-icon layui-icon-refresh-3"></i>
                    </a>
                </li>

            </ul>
            <ul class="layui-nav layui-layout-right" lay-filter="layadmin-layout-right">


                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="javascript:;" layadmin-event="fullscreen">
                        <i class="layui-icon layui-icon-screen-full"></i>
                    </a>
                </li>
                <li class="layui-nav-item" lay-unselect>
                    <a href="javascript:;">
                        <cite>guo</cite>
                    </a>
                    <dl class="layui-nav-child">
<%--                        <dd><a lay-href="set/user/info.html">基本资料</a></dd>--%>
                        <dd><a lay-href="${pageContext.request.contextPath}/jsp/password.jsp">修改密码</a></dd>
                        <hr>
                        <dd  style="text-align: center;"><a href="${pageContext.request.contextPath}/jsp/loginout.do">退出</a></dd>
                    </dl>
                </li>

                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="javascript:;" layadmin-event="about"><i class="layui-icon layui-icon-more-vertical"></i></a>
                </li>
                <li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm" lay-unselect>
                    <a href="javascript:;" layadmin-event="more"><i class="layui-icon layui-icon-more-vertical"></i></a>
                </li>
            </ul>
        </div>

        <!-- 侧边菜单 -->
        <div class="layui-side layui-side-menu">
            <div class="layui-side-scroll">
                <div class="layui-logo" >
                    <span>物流管理系统</span>
                </div>

                <ul class="layui-nav layui-nav-tree" lay-shrink="all" id="LAY-system-side-menu" lay-filter="layadmin-system-side-menu">
<%--                    <li  class="layui-nav-item layui-nav-itemed" >--%>
<%--                        <a href="javascript:;"lay-direction="2" lay-href="${pageContext.request.contextPath}/jsp/admininfo.jsp">--%>
<%--                            <i class="layui-icon layui-icon-home"></i>--%>
<%--                            <cite >用户管理</cite>--%>
<%--                        </a>--%>
<%--&lt;%&ndash;                        <dl class="layui-nav-child">&ndash;%&gt;--%>
<%--&lt;%&ndash;                            <dd >&ndash;%&gt;--%>
<%--&lt;%&ndash;                                <a lay-href="${pageContext.request.contextPath}/jsp/admininfo.jsp">用户信息</a>&ndash;%&gt;--%>
<%--&lt;%&ndash;                            </dd>&ndash;%&gt;--%>
<%--&lt;%&ndash;                        </dl>&ndash;%&gt;--%>
<%--                    </li>--%>
                    <li class="layui-nav-item">
                        <a href="javascript:;"  lay-direction="2" lay-href="${pageContext.request.contextPath}/jsp/empinfo.jsp">
                            <i class="layui-icon layui-icon-component"></i>
                            <cite>员工管理</cite>
                        </a>
                    </li>
                    <li class="layui-nav-item">
                        <a href="javascript:;"  lay-direction="2" lay-href="${pageContext.request.contextPath}/jsp/carinfo.jsp">
                            <i class="layui-icon layui-icon-template"></i>
                            <cite>车辆管理</cite>
                        </a>

                    </li>
                    <li class="layui-nav-item">
                        <a href="javascript:;"  lay-direction="2" lay-href="${pageContext.request.contextPath}/jsp/deliverspotinfo.jsp">
                            <i class="layui-icon layui-icon-template"></i>
                            <cite>配送点管理</cite>
                        </a>

                    </li>

                    <li class="layui-nav-item">
                        <a href="javascript:;" lay-direction="2">
                            <i class="layui-icon layui-icon-app"></i>
                            <cite>订单管理</cite>
                        </a>
                        <dl class="layui-nav-child">
                            <dd >
                                <a href="javascript:;" lay-href="${pageContext.request.contextPath}/jsp/orderinfo.jsp">订单信息</a>
                            </dd>
                            <dd >
                                <a href="javascript:;" lay-href="${pageContext.request.contextPath}/jsp/receipt0info.jsp">交接单信息</a>
                            </dd>
                            <dd >
                                <a href="javascript:;" lay-href="${pageContext.request.contextPath}/jsp/receipt1info.jsp">投递单信息</a>
                            </dd>

                        </dl>
                    </li>
                    <li class="layui-nav-item">
                        <a href="javascript:;" lay-direction="2"lay-href="${pageContext.request.contextPath}/jsp/lineinfo.jsp">
                            <i class="layui-icon layui-icon-app"></i>
                            <cite>线路管理</cite>
                        </a>

                    </li>
<%--                   <li class="layui-nav-item">--%>
<%--                       <a href="javascript:;"  lay-direction="2" lay-href="${pageContext.request.contextPath}/jsp/reportforminfo.jsp">--%>
<%--                       <i class="layui-icon layui-icon-template"></i>--%>
<%--                       <cite>报表管理</cite>--%>
<%--                       </a>--%>

<%--                    </li>--%>

                    <li class="layui-nav-item">
                        <a href="javascript:;"  lay-direction="2" lay-href="${pageContext.request.contextPath}/jsp/admininfo.jsp">
                            <i class="layui-icon layui-icon-user"></i>
                            <cite>用户管理</cite>
                        </a>

                    </li>

                    <li  class="layui-nav-item">
                        <a href="javascript:;" lay-direction="2">
                            <i class="layui-icon layui-icon-set"></i>
                            <cite>设置</cite>
                        </a>
                        <dl class="layui-nav-child">
                            <dd >
                                <a href="javascript:;" lay-href="${pageContext.request.contextPath}/jsp/password.jsp">修改密码</a>
                            </dd>


                        </dl>


                        </dl>
                    </li>

                </ul>
            </div>
        </div>

        <!-- 页面标签 -->
        <div class="layadmin-pagetabs" id="LAY_app_tabs">
            <div class="layui-icon layadmin-tabs-control layui-icon-prev" layadmin-event="leftPage"></div>
            <div class="layui-icon layadmin-tabs-control layui-icon-next" layadmin-event="rightPage"></div>
            <div class="layui-icon layadmin-tabs-control layui-icon-down">
                <ul class="layui-nav layadmin-tabs-select" lay-filter="layadmin-pagetabs-nav">
                    <li class="layui-nav-item" lay-unselect>
                        <a href="javascript:;"></a>
                        <dl class="layui-nav-child layui-anim-fadein">
                            <dd layadmin-event="closeThisTabs"><a href="javascript:;">关闭当前标签页</a></dd>
                            <dd layadmin-event="closeOtherTabs"><a href="javascript:;">关闭其它标签页</a></dd>
                            <dd layadmin-event="closeAllTabs"><a href="javascript:;">关闭全部标签页</a></dd>
                        </dl>
                    </li>
                </ul>
            </div>
            <div class="layui-tab" lay-unauto lay-allowClose="true" lay-filter="layadmin-layout-tabs">
                <ul class="layui-tab-title" id="LAY_app_tabsheader">
                    <li lay-id="home/console.html" lay-attr="home/console.html" class="layui-this"><i class="layui-icon layui-icon-home"></i></li>
                </ul>
            </div>
        </div>


        <!-- 主体内容 -->
        <div class="layui-body" id="LAY_app_body">
            <div class="layadmin-tabsbody-item layui-show">
                <iframe src="test2.jsp" frameborder="5" class="layadmin-iframe"></iframe>
            </div>
        </div>


    </div>
</div>

<script src="${pageContext.request.contextPath}/resources/layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '${pageContext.request.contextPath}/resources/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use('index');
</script>
</body>
</html>