<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/mdui/css/mdui.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/animate.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/antd/p__list__table-list.47a670a0.chunk.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/antd/vendors_layouts__BasicLayout_p__account__center_p__account__settings_p__editor__flow_p__editor__koni_6c045b5b.43072984.chunk.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/antd/vendors_p__account__settings_p__editor__flow_p__editor__koni_p__editor__mind_p__form__advanced-form__ae2e896d.f61224a9.chunk.css" />

</head>
<body class="mdui-drawer-body-left mdui-appbar-with-toolbar mdui-theme-primary-indigo mdui-theme-accent-pink mdui-loaded">
<%--头--%>

    <%@include file="common/header.jsp"%>
<%--导航--%>

   <%@include file="common/sider.jsp"%>


<%--主体内容--%>
<div class="mdui-container doc-container doc-no-cover">
    <div style="min-height: 80%;">
        <main class="ant-layout-content ant-pro-basicLayout-content ant-pro-basicLayout-has-header">
            <div class="ant-pro-basicLayout-children-content-wrap">
                <div class="ant-pro-page-header-wrap">

                    <div class="ant-pro-page-header-wrap-page-header-warp">
                        <div class="ant-pro-grid-content">
                            <div class="ant-page-header has-breadcrumb ant-page-header-ghost">
                                <div class="ant-breadcrumb"><span><span class="ant-breadcrumb-link"><a
                                        href="frame.jsp">首页</a></span><span
                                        class="ant-breadcrumb-separator">/</span></span><span><span
                                        class="ant-breadcrumb-link"><span>用户管理</span></span><span
                                        class="ant-breadcrumb-separator">/</span></span><span><span
                                        class="ant-breadcrumb-link"><span>用户信息</span></span><span
                                        class="ant-breadcrumb-separator">/</span></span></div>
                                <div class="ant-page-header-heading"><span class="ant-page-header-heading-title">用户信息列表</span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="ant-pro-grid-content">
                        <div class="ant-pro-page-header-wrap-children-content">
                            <div class="ant-pro-table" id="ant-design-pro-table">
<%--                                <div class="ant-pro-table-search" style="height: 55px;">--%>
<%--                                    <div>--%>
<%--                                        <form class="ant-form ant-form-horizontal">--%>
<%--                                            <div class="ant-row ant-row-start"--%>
<%--                                                 style="margin-left: -8px; margin-right: -8px;">--%>
<%--                                                <div class="ant-col ant-col-xs-24 ant-col-sm-12 ant-col-md-12 ant-col-lg-8 ant-col-xl-8 ant-col-xxl-6"--%>
<%--                                                     style="padding-left: 8px; padding-right: 8px;">--%>
<%--                                                    <div class="ant-row ant-form-item">--%>
<%--                                                        <div class="ant-col ant-form-item-label"><label for="name" class=""--%>
<%--                                                                                                       >查询条件</label>--%>
<%--                                                        </div>--%>
<%--                                                        <div class="ant-col ant-form-item-control">--%>
<%--                                                            <div class="ant-form-item-control-input">--%>
<%--                                                                <div class="ant-form-item-control-input-content"><input--%>
<%--                                                                        placeholder="请输入" type="text" id="name"--%>
<%--                                                                        class="ant-input" value=""></div>--%>
<%--                                                                <div class="ant-form-item-control-input-content"><input--%>
<%--                                                                        placeholder="请输入" type="text" id=""--%>
<%--                                                                        class="ant-input" value=""></div>--%>
<%--                                                            </div>--%>
<%--                                                        </div>--%>
<%--                                                    </div>--%>
<%--                                                </div>--%>
<%--                                                <div class="ant-col ant-pro-table-search-option ant-col-xs-24 ant-col-sm-12 ant-col-md-12 ant-col-lg-8 ant-col-xl-8 ant-col-xxl-6"--%>
<%--                                                     style="padding-left: 8px; padding-right: 8px;">--%>
<%--                                                    <div class="ant-row ant-form-item">--%>
<%--                                                        <div class="ant-col ant-form-item-control">--%>
<%--                                                            <div class="ant-form-item-control-input">--%>
<%--                                                                <div class="ant-form-item-control-input-content">--%>
<%--                                                                    <button type="submit" class="ant-btn ant-btn-primary">--%>
<%--                                                                        <span>查 询</span></button>--%>
<%--                                                                    <button type="button" class="ant-btn"--%>
<%--                                                                            style="margin-left: 8px;"><span>重 置</span>--%>
<%--                                                                    </button>--%>
<%--                                                                </div>--%>
<%--                                                            </div>--%>
<%--                                                        </div>--%>
<%--                                                    </div>--%>
<%--                                                </div>--%>
<%--                                            </div>--%>
<%--                                        </form>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
    <div class="layui-form layui-card-header layuiadmin-card-header-auto">
        <div class="layui-form-item" id="dataform_">
            <div class="layui-inline">
                <label class="layui-form-label">ID</label>
                <div class="layui-input-block">
                    <input type="hidden" name="method" value="search" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-block">
                    <input type="text" name="username" id="userna"placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">编号</label>
                <div class="layui-input-block">
                    <input type="text" name="email" placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-block">
                    <select name="sex">
                        <option value="0">不限</option>
                        <option value="1">男</option>
                        <option value="2">女</option>
                    </select><div class="layui-unselect layui-form-select"><div class="layui-select-title"><input type="text" placeholder="请选择" value="不限" readonly="" class="layui-input layui-unselect" name=""><i class="layui-edge"></i></div><dl class="layui-anim layui-anim-upbit" style=""><dd lay-value="0" class="layui-this">不限</dd><dd lay-value="1" class="">男</dd><dd lay-value="2" class="">女</dd></dl></div>
                </div>
            </div>
            <div class="layui-inline">
                <button class="layui-btn layuiadmin-btn-useradmin" id="dosearch" lay-submit="" lay-filter="LAY-user-front-search">
                    <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
                </button>
            </div>
        </div>
    </div>
                                <div class="ant-card" style="height: 100%;">
                                    <div class="ant-card-body" style="padding: 0px;">
                                        <div class="ant-pro-table-toolbar">
                                            <div class="ant-pro-table-toolbar-title">查询表格</div>
                                            <div class="ant-pro-table-toolbar-option">
                                                <div class="ant-pro-table-toolbar-item">

                                                </div>

                                            </div>
                                        </div>

                                        <div class="ant-table-wrapper">
                                            <div class="ant-spin-nested-loading">
                                                <div class="ant-spin-container">
                                                    <div class="ant-table ant-table-middle">
                                                        <div class="ant-table-container">



                                                            <table class="layui-hide" id="usertable" lay-filter="userTable"></table>



                                                        </div>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>



</div>



<%@include file="common/footer.jsp"%>

<div class="modal fade" id="addUser" tabindex="-1" role="dialog" aria-labelledby="name"aria-hidden="true">
    <div class="modal-dialog" style="width: 400px"  role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加用户</h4>
            </div>
            <div class="modal-body">
                <form class="layui-form layui-form-pane" action="" lay-filter="dataform" id="dataform">
                    <input type="hidden" name="method" value="adduser">

                    <div class="layui-form-item ">
                        <label class="layui-form-label">用户登录名</label>
                        <div class="layui-input-block">
                            <input style="width: auto" type="text" name="username" lay-verify="required|username" autocomplete="off" placeholder="请输入用户名" class="layui-input" >
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">密码</label>
                        <div class="layui-input-block">
                            <input style="width: 190px" type="password" name="password" lay-verify="required|password" placeholder="请输入6~10位数字或字母" autocomplete="off" class="layui-input">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">权限</label>
                        <div class="layui-input-block" style="width: 162px">
                            <select name="permission" lay-filter="">

                                <option value="2">配送点管理员</option>
                                <option value="3">配送点操作员</option>

                            </select>
                        </div>
                    </div>

                    <div class="layui-form-item ">
                        <label class="layui-form-label">所在配送点</label>
                        <div class="layui-input-block">
                            <input style="width: auto" type="text" name="dsname" lay-verify="required|deliverspot" autocomplete="off" placeholder="请输入所在配送点" class="layui-input">
                        </div>
                    </div>


                    <div class="layui-form-item ">
                        <label class="layui-form-label">员工编号</label>
                        <div class="layui-input-block">
                            <input style="width: 130px" type="text" name="empno" lay-verify="required|empnno" autocomplete="off" placeholder="请输入编号" class="layui-input">
                        </div>
                    </div>

                    <%--        <div class="layui-form-item">--%>
                    <%--            <label class="layui-form-label">单选框</label>--%>
                    <%--            <div class="layui-input-block">--%>
                    <%--                <input type="radio" name="sex" value="男" title="男" checked="">--%>
                    <%--                <input type="radio" name="sex" value="女" title="女">--%>
                    <%--            </div>--%>
                    <%--        </div>--%>

                    <div class="modal-footer">
                        <button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" lay-submit lay-filter="doSubmit">提交</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div style="display: none" id="layercontent">
    <div class="wrapper wrapper-content animated fadeInRight ibox-content">
        <form class="form-horizontal m-t" id="signupForm" style="padding-left:20px;">
            <div class="form-group" >
                <label class="col-sm-4 control-label" >员工编号：</label>

                    <p class="form-control-static" id="empno">D2E15465</p>

            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label">用户登录名：</label>
                <div class="form-control-static" id="username">guozhicheng</div>
            </div>

            <div class="form-group">
                <label class="col-sm-4 control-label" >用户权限：</label>
                <div class="form-control-static" id="permission">配送点操作员</div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label" >所在配送点：</label>
                <div class="form-control-static" id="deliverspot">合肥</div>
            </div>

<%--            <div class="form-group">--%>
<%--                <label class="col-sm-2 control-label">状态：</label>--%>
<%--                <div class="label label-primary">正常</div>--%>
<%--            </div>--%>

        </form>
    </div>
</div>



<script src="../js/jquery-3.5.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>

<script type="text/html" id="bar">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
<%--    <a class="layui-btn layui-btn-xs" lay-event="edit">删除</a>--%>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delUser">删除</a>
</script>
<script type="text/html" id="toolbar">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="addUser">新建</button>
        <button class="layui-btn layui-btn-sm" lay-event="delUsers">批量删除</button>

    </div>
</script>
<script>
        layui.use(['laydate', 'laypage', 'layer', 'table', 'form', 'upload', 'element'], function(){
            var laydate = layui.laydate //日期
                ,laypage = layui.laypage //分页
                ,layer = layui.layer //弹层
                ,table = layui.table //表格
                ,form = layui.form
                ,upload = layui.upload //上传
                ,element = layui.element //元素操作




            var dialogheight =  $(window).height()/5;
            $('#addUser').css({
                'margin-top':dialogheight

            });


            //执行一个 table 实例
            var tableIns = table.render({
                elem: '#usertable'
                , url: '${pageContext.request.contextPath}/jsp/user.do'
                , title: '用户信息'
                , page: true
                , toolbar: '#toolbar' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
                , where: {method: "query"}
                , skin: 'nob' //行边框风格
                , even: false //开启隔行背景
                ,height:437
                // ,height:'full-509.6'
                ,limit:8
                ,limits:[8,16,24]
                , cols: [[
                    {type: 'numbers'}
                    , {type: 'checkbox'}
                    , {field: 'userEmpno', title: '员工编号', sort: true, edit: true}
                    , {field: 'userName', title: '用户登录名', edit: true}
                    , {field: 'userPermission', title: '用户权限', sort: true, edit: true}
                    , {field: 'userDeliverspot', title: '所在配送点', edit: true}
                    , { title: '操作', toolbar: '#bar'}
                ]]


            });



            $("#dosearch").click(function () {

                var url = "${pageContext.request.contextPath}/jsp/user.do";
                tableIns.reload({
                    url:url,
                    where: {
                        method:"search",
                        username:$('#userna').val()
                    }
                })
            })





            table.on('checkbox(userTable)', function(obj){
                console.log(obj.checked); //当前是否选中状态
                console.log(obj.data); //选中行的相关数据
                // alert(obj.data.userName)
                console.log(obj.type); //如果触发的是全选，则为：all，如果触发的是单选，则为：one
            });

            //监听头工具栏事件
            table.on('toolbar(userTable)', function(obj){
                var checkStatus = table.checkStatus(obj.config.id);
                    var data = checkStatus.data; //获取选中的数据

                var addu = new mdui.Dialog('#addUserdialog');
                switch(obj.event){
                    case 'addUser':
                       // addu.open();
                        $("#dataform")[0].reset();
                        $('#addUser').modal('show');
                        break;

                    case 'delUsers':
                        if(data.length === 0){
                            layer.msg('请选择一行');
                        } else {
                            delUsers();
                        }
                        break;
                }
            });



            //监听行工具事件
            table.on('tool(userTable)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
                var data = obj.data //获得当前行数据
                    ,layEvent = obj.event; //获得 lay-event 对应的值
                if(layEvent === 'detail'){
                    openDetailUser(data.userId);
                } else if(layEvent === 'delUser'){
                    layer.confirm('真的删除这条数据么', function(index){
                        var url = "${pageContext.request.contextPath}/jsp/user.do";
                        var params = "method=delete&userid="+data.userId
                        $.post(url,params,function (res) {
                            if (res.result === "true") {
                                layer.msg("删除成功");
                            } else {
                                layer.msg("删除失败");
                            }
                        })

                        obj.del(); //删除DOM结构
                        layer.close(index);
                        table.reload('usertable');
                        //向服务端发送删除指令
                    });
                } else if(layEvent === 'edit'){
                    layer.msg('编辑操作');
                }
            });

              //监听单元格事件
            table.on('edit(test)', function (obj) { //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
                console.log(obj.value); //得到修改后的值
                console.log(obj.field); //当前编辑的字段名
                console.log(obj.data); //所在行的所有相关数据
            });

            //批量删除
            function delUsers() {
                var checkStatus = table.checkStatus('usertable');
                var data = checkStatus.data;
                var params = "";
                // layer.msg(data.userId)
                // var ids =[];
                $.each(data, function (i, item) {
                    if (i === 0) {
                        // ids.push(item.userId);
                        params += "method=deleteBatch&userid=" + item.userId;
                    } else {
                        params += "&userid=" + item.userId;
                    }
                    // layer.msg(params);
                });
                // params += "method=delete&userid="+ids;
                layer.confirm('确定删除所选数据？', function (index) {
                    $.post('${pageContext.request.contextPath}/jsp/user.do', params, function (res) {

                        if (res.result === 'true') {
                            console.log(res.result)
                            alert(res.result)
                            table.reload('usertable');
                            layer.msg("删除成功");
                        } else if (res.result === 'false') {
                            layer.msg("删除失败")
                        }

                    });

                });

            }



            //添加用户
            form.on('submit(doSubmit)',function (index) {
                var params = form.val('dataform');
                $.post('${pageContext.request.contextPath}/jsp/user.do',params,function (res) {
                    if (res.result === "true") {
                        $("#addUser").modal('hide');
                        layer.msg('添加成功');
                        table.reload('usertable');
                    } else {
                        layer.msg('添加失败');
                    }

                });

            });

            //验证
            form.verify({
                username: function (value) {
                    if (value.length > 10) {
                        return "用户名过长";
                    }
                }
                ,password: [
                    /^[0-9A-Za-z]{6,10}$/
                    , "密码必须为6~10位数字或字母"
                ]
                , deliverspot: function (value) {
                    var msg = "";
                    var url = "${pageContext.request.contextPath}/jsp/user.do"
                    var params = "method=checkds&deliverspot=" + value;
                    $.ajaxSettings.async = false;
                    $.post(url, params, function (data) {
                        if (data.result === "false") {
                          msg = "没有该配送点";
                        }

                    });
                    $.ajaxSettings.async = true;
                    return msg;
                }
            });
            //查看用户
            function openDetailUser(obj) {
                layer.open({
                    type: 1 //Page层类型
                    ,area: ['420px', '310px']
                    ,title: '用户详细信息'
                    ,shade: 0.6 //遮罩透明度
                    ,maxmin: true //允许全屏最小化
                    ,anim: 3 //0-6的动画形式，-1不开启
                    ,scrollbar: false
                    ,content: $("#layercontent")
                    ,success: function () {

                        var params = "method=detail&userid=" + obj,
                            url = "${pageContext.request.contextPath}/jsp/user.do";
                        $.post(url,params,function (res) {
                            console.log(res)
                            $("#empno").text(res.userEmpno);
                            $("#username").text(res.userName);
                            $("#permission").text(res.userPermission);
                            $("#deliverspot").text(res.userDeliverspot);

                        });
                    }
                });
            };

            $("#doSearch").click(function () {


            });






        });



</script>


</body>
</html>
