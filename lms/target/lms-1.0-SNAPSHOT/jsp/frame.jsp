<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <title>物流管理系统</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/antd/umi.bee3e6e5.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/antd/analytics.js"></script>

</head>


<body class="mdui-drawer-body-left mdui-appbar-with-toolbar mdui-theme-primary-indigo mdui-theme-accent-pink mdui-loaded">
<%--头--%>
<%@include file="common/header.jsp"%>
<%--导航--%>

<%@include file="common/sider.jsp"%>

<%--主体内容--%>
<div class="mdui-container doc-container doc-no-cover">
    <div style="min-height: 730px;">

</div>
<%--尾部--%>
<%@include file="common/footer.jsp"%>
</body>


</html>