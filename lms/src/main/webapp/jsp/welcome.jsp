<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <h1>欢迎</h1>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/lay/css/layui.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js"></script>
<body>
<div class="mdui-container">

    <div class="mdui-dialog" id="dialog">
        <div class="mdui-dialog-title">Are you sure?</div>
        <div class="mdui-dialog-content">You'll lose all photos and media!</div>
        <div class="mdui-dialog-actions">
            <button class="mdui-btn mdui-ripple" mdui-dialog-cancel>cancel</button>
            <button class="mdui-btn mdui-ripple" mdui-dialog-confirm>erase</button>
        </div>
    </div>

    <button id="open" class="mdui-btn mdui-color-pink-accent">open</button>

</div>
</body>
<link rel="stylesheet" href="${pageContext.request.contextPath}/mdui/css/mdui.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/mdui/js/mdui.js" ></script>
<script>

    var inst = new mdui.Dialog('#dialog');

    // method
    document.getElementById('open').addEventListener('click', function () {
        inst.open();
    });

</script>

</html>
