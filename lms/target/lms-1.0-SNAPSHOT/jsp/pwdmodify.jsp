<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/mdui/css/mdui.css" />


    <link rel="stylesheet" href="${pageContext.request.contextPath}/antd/umi.bee3e6e5.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/antd/analytics.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>

</head>
<body>

<div class="ant-page-header has-breadcrumb ant-page-header-ghost">
    <div class="ant-breadcrumb">
        <span>
          <span class="ant-breadcrumb-link"><a href="/">首页</a></span>
          <span class="ant-breadcrumb-separator">/</span>
        </span>
        <span>
            <span class="ant-breadcrumb-link"><a href="/">系统管理</a></span>
            <span class="ant-breadcrumb-separator">/</span>
        </span>
        <span>
            <span class="ant-breadcrumb-link"><a href="/">密码修改</a></span>
            <span class="ant-breadcrumb-separator">/</span>
        </span>
    </div>
    <div class="ant-page-header-heading"><span class="ant-page-header-heading-title">密码修改</span></div>
    <div class="ant-page-header-content">
        <div class="ant-pro-page-header-wrap-detail">
            <div class="ant-pro-page-header-wrap-main">
                <div class="ant-pro-page-header-wrap-row">
                    <div class="ant-pro-page-header-wrap-content"></div>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="ant-pro-grid-content">
    <div class="ant-pro-page-header-wrap-children-content">
        <div class="ant-card">
            <div class="ant-card-body">


                    <div style="width:400px" class="mdui-textfield mdui-textfield-floating-label" id="textfield">
<%--                        <label class="mdui-textfield-label">请输入原密码</label>--%>
                        <input class="mdui-textfield-input" type="password" name="oldpassword" id="oldpassword" placeholder="请输入原密码" maxlength="10" required/>
                        <div class="mdui-textfield-error" id="error"></div>
<%--                        <div class="mdui-textfield-helper"></div>--%>
                    </div>

                    <div style="width:400px" class="mdui-textfield mdui-textfield-floating-label"  id="ntextfield">
<%--                        <label class="mdui-textfield-label">请输入6~10位数字或字母的新密码</label>--%>
                        <input class="mdui-textfield-input" type="password" name="newpassword" id="newpassword"  maxlength="10" placeholder="请输入新密码(6~10位数字或字母)" required/>
                        <div class="mdui-textfield-error" id="nerror" ></div>
<%--                        <div class="mdui-textfield-helper"id="helper">请输入至少 6 位数字或字母</div>--%>
                    </div>

                    <div style="width:400px" class="mdui-textfield mdui-textfield-floating-label" id="rtextfield">
<%--                        <label class="mdui-textfield-label">请再次输入密码</label>--%>
                        <input class="mdui-textfield-input" type="password" name="rnewpassword" id="rnewpassword" placeholder="请再次输入密码" maxlength="10" required/>
                        <div class="mdui-textfield-error" id="rerror"></div>
<%--                        <div class="mdui-textfield-helper">请输入至少 6 位数字或字母</div>--%>
                    </div>

                    <i>${message}</i>
                    <div class="ant-row ant-form-item" style="margin-top: 32px;">
                        <div class="ant-col ant-form-item-control ant-col-xs-24 ant-col-xs-offset-0 ant-col-sm-10 ant-col-sm-offset-7">
                            <div class="ant-form-item-control-input">
                                <div class="ant-form-item-control-input-content">
                                    <button  class="mdui-btn mdui-btn-raised mdui-ripple mdui-color-pink-accent " type="button" id="valid" >
                                        提交</button>
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    <button class="mdui-btn mdui-btn-raised mdui-ripple" type="button" id="reset">
                                        重置 </button>
                                </div>
                            </div>
                        </div>
                    </div>



            </div>
        </div>
    </div>
</div>
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
<script src="../js/jquery-3.5.0.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/mdui/js/mdui.js" ></script>
<script>
    var $$ = mdui.JQ;
    var textfield = $$('#textfield');
    var ntextfield = $$('#ntextfield');
    var rtextfield = $$('#rtextfield');

    var oldpassword = $$('#oldpassword');
    var newpassword = $$('#newpassword');
    var rnewpassword = $$('#rnewpassword');
    var error = $$('#error');
    var rerror = $$('#rerror');
    var nerror = $$('#nerror');
    var hasError = false;


    $$('#oldpassword').on('blur', function () {
        error.show();
        $.ajax({
            type: "get",
            url: "${pageContext.request.contextPath}/jsp/user.do",
            data: {method: "checkpwd", oldpassword: oldpassword.val()},
            dataType: "json",
            success: function (data) {
                if (data.result === "true") {
                    textfield.removeClass('mdui-textfield-invalid');
                    oldpassword.attr("flag", "true");
                } else if (data.result === "false") {
                    error.text('原密码输入不正确');
                    textfield.addClass('mdui-textfield-invalid');
                    oldpassword.attr("flag", "false");
                } else if (data.result === "sessionerror") {
                    error.text('身份过期，请重新登陆');
                    textfield.addClass('mdui-textfield-invalid');
                    oldpassword.attr("flag", "false");

                } else if (data.result === "error") {
                    error.text('密码不能为空');
                    textfield.addClass('mdui-textfield-invalid');
                    oldpassword.attr("flag", "false");

                }

            },
            error: function () {
                error.text('请求出错，请刷新重试');
                textfield.addClass('mdui-textfield-invalid');
                oldpassword.attr("flag", "false");

            }

        });

    }).on('focus', function () {
        // error.text('请输入原密码');
        // textfield.addClass('mdui-textfield-invalid');
        textfield.removeClass('mdui-textfield-invalid');
        oldpassword.attr("flag", "false");

    });

    $$('#newpassword').on('focus', function () {

        ntextfield.removeClass('mdui-textfield-invalid');
        newpassword.attr("flag", "false");

    }).on('blur', function () {
        nerror.show();
        ntextfield.removeClass('mdui-textfield-invalid');
        var veg = /^[0-9A-Za-z]{6,10}$/;

        if (newpassword.val() !== null && newpassword.val().length > 5 && veg.test(newpassword.val())) {
            ntextfield.removeClass('mdui-textfield-invalid');
            newpassword.attr("flag", "true");
        } else {
            nerror.text('密码格式错误');
            ntextfield.addClass('mdui-textfield-invalid');
            newpassword.attr("flag", "false");

        }


    });

    $$('#rnewpassword').on('focus', function () {
        // rerror.text('请再次输入密码');
        // rtextfield.addClass('mdui-textfield-invalid');
        rtextfield.removeClass('mdui-textfield-invalid');
        rnewpassword.attr("flag", "false");

    }).on('blur', function () {
        rerror.show();
        rtextfield.removeClass('mdui-textfield-invalid');
        var veg = /^[0-9A-Za-z]{6,10}$/;


        if (rnewpassword.val() !== null && rnewpassword.val().length > 5 &&
            veg.test(rnewpassword.val()) && newpassword.val() === rnewpassword.val()) {
            rtextfield.removeClass('mdui-textfield-invalid');
            rnewpassword.attr("flag", "true");
        } else if (rnewpassword.val() === "") {
            rerror.text('密码为空');
            rtextfield.addClass('mdui-textfield-invalid');
            rnewpassword.attr("flag", "false");
        } else {
            rerror.text('两次输入密码不一致，请重新输入');
            rtextfield.addClass('mdui-textfield-invalid');
            rnewpassword.attr("flag", "false");

        }


    });



    mdui.JQ('#valid').on('click', function(){

        if (oldpassword.attr("flag") === "true" && newpassword.attr("flag") === "true" && rnewpassword.attr("flag") === "true") {
            mdui.confirm('是否确认修改密码?', '修改密码', function () {
                $.ajax({
                    type: "get",
                    url: "${pageContext.request.contextPath}/jsp/user.do",
                    data: {method: "updatepwd", newpassword: newpassword.val()},
                    dataType: "json",
                    success: function (data) {
                        if (data.result === "true") {
                            mdui.snackbar({
                                message: '修改成功，即将重新登录...',
                                position: 'top',
                                buttonText: '${sessionScope.userSession.username}',

                                onClose: function(){
                                    parent.location.reload();

                                },
                            });
                            <%--mdui.alert('请重新登录！', '修改成功', function () {--%>
                            <%--    parent.location.reload();--%>

                            <%--    // history.go(0);--%>
                            <%--    // document.execCommand('Refresh');--%>
                            <%--    // window.location.replace(location.href='http://localhost:8080/lms/jsp/login.jsp');--%>
                            <%--    &lt;%&ndash;window.location.href='${pageContext.request.contextPath}/jsp/login.jsp';&ndash;%&gt;--%>
                            <%--    &lt;%&ndash;window.open("", "_top").close();&ndash;%&gt;--%>
                            <%--    &lt;%&ndash;window.open('${pageContext.request.contextPath}/login.jsp');&ndash;%&gt;--%>

                            <%--},{--%>
                            <%--    confirmText: '确定',--%>
                            <%--});--%>


                        } else if (data.result === "false") {
                            mdui.snackbar({
                                message: '修改失败，请重试',
                                position: 'top',
                                buttonText: '确定',

                            });
                        } else if (data.result === "error") {
                            mdui.snackbar({
                                message: '密码有问题，请重试',
                                position: 'top',
                                buttonText: '确定',

                            });
                        }

                    },
                    error: function () {
                        mdui.snackbar({
                            message: '请求出错，请刷新重试',
                            position: 'top',
                            buttonText: '确定',

                        });

                    }

                });
            }, null, {
                confirmText: '是',
                cancelText: '否',
            });
        }





    });

    $$('#reset').on('click', function () {
        oldpassword.val("");
        newpassword.val("");
        rnewpassword.val("");

        error.hide();
        nerror.hide();
        rerror.hide();

        textfield.removeClass('mdui-textfield-invalid');
        ntextfield.removeClass('mdui-textfield-invalid');
        rtextfield.removeClass('mdui-textfield-invalid');

    });






</script>

</html>
