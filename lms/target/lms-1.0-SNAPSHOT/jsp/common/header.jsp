<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/mdui/css/mdui.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/mdui/js/mdui.js" ></script>

</head>

<header class="mdui-appbar mdui-appbar-fixed">
    <div class="mdui-toolbar mdui-color-black">
        <span class="mdui-btn mdui-btn-icon mdui-ripple mdui-ripple-white" mdui-drawer="{target: '#main-drawer', swipe: true}"><i class="mdui-icon material-icons">menu</i></span>
        <a  class="mdui-typo-headline mdui-hidden-xs">物流管理系统后台</a>

        <div class="mdui-toolbar-spacer"></div>

        <span class="mdui-btn mdui-btn-icon mdui-ripple mdui-ripple-white"
              mdui-tooltip="{content: '账号：${sessionScope.userSession.username}'}"><i class="mdui-icon material-icons">face</i></span>
        <span class="mdui-btn mdui-btn-icon mdui-ripple mdui-ripple-white"
              mdui-tooltip="{content: '退出'}" onclick="loginout()"><i class="mdui-icon material-icons">arrow_drop_down</i>
        </span>

    </div>
</header>

<script>
    function loginout() {

        window.location.href="${pageContext.request.contextPath}/jsp/loginout.do";

    }

</script>
</html>
