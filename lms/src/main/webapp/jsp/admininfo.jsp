<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>admininfo</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layuiadmin/layui/css/layui.css" >
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layuiadmin/style/admin.css" >
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css"/>`
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layuiadmin/layui/css/modules/layer/default/layer.css?v=3.1.1">
</head>

<body >

<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto" >
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">编号</label>
                    <div class="layui-input-block">
                        <input type="text" name="userEmpno" id="userempno_" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">登录名</label>
                    <div class="layui-input-block">
                        <input type="text" name="userName" id="username_" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">配送点</label>
                    <div class="layui-input-block">
                        <input type="text" name="userDeliverspot" id="userds_" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label" >权限</label>
                    <div class="layui-input-block">
                        <select name="userPermission" id="userpermission_">
                            <option value=""></option>
                            <option value="配送点管理员">配送点管理员</option>
                            <option value="配送点操作员">配送点操作员</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">
                        <button class="layui-btn layuiadmin-btn-admin" id="dosearch">
                            <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
                        </button>
                    </label>
                </div>
            </div>
        </div>





        <div class="layui-card-body">
            <div style="padding-bottom: 10px;">
                <button class="layui-btn layuiadmin-btn-admin" data-type="batchdel">删除</button>
                <button class="layui-btn layuiadmin-btn-admin" data-type="add">添加</button>
            </div>

            <table id="usertable" lay-filter="userTable"></table>


            <script type="text/html" id="buttonTpl">
                {{#  if(d.check == true){ }}
                <button class="layui-btn layui-btn-xs">已审核</button>
                {{#  } else { }}
                <button class="layui-btn layui-btn-primary layui-btn-xs">未审核</button>
                {{#  } }}
            </script>
            <script type="text/html" id="table-useradmin-admin">
                <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
                {{#  if(d.role == '超级管理员'){ }}
                <a class="layui-btn layui-btn-disabled layui-btn-xs"><i class="layui-icon layui-icon-delete"></i>删除</a>
                {{#  } else { }}
                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
                {{#  } }}
            </script>
        </div>
    </div>
</div>


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

                                <option value=""></option>
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
                        <button type="button" class="btn btn-primary" style="background-color: #009688" lay-submit lay-filter="doSubmit">提交</button>
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

                <p class="form-control-static" id="empno"></p>

            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label">用户登录名：</label>
                <div class="form-control-static" id="username"></div>
            </div>

            <div class="form-group">
                <label class="col-sm-4 control-label" >用户权限：</label>
                <div class="form-control-static" id="permission"></div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label" >所在配送点：</label>
                <div class="form-control-static" id="deliverspot"></div>
            </div>

            <%--            <div class="form-group">--%>
            <%--                <label class="col-sm-2 control-label">状态：</label>--%>
            <%--                <div class="label label-primary">正常</div>--%>
            <%--            </div>--%>

        </form>
    </div>
</div>




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



        var dialogheight = $(window).height() / 5;
        $('#addUser').css({
            'margin-top': dialogheight

        });


        //执行一个 table 实例
        table.render({
            elem: '#usertable'
            , url: '${pageContext.request.contextPath}/jsp/user.do'
            , title: '用户信息'
            , page: true
            , toolbar: false //开启工具栏
            , where: {method: "query"}
            , skin: 'line'
            , even: false //开启隔行背景
            // ,height:437
            // ,height:'full-509.6'
            , limit: 10
            , limits: [10, 20, 40]
            , cols: [[
                {type: 'numbers', fixed:'left'}
                , {type: 'checkbox', fixed:'left'}
                , {field: 'userEmpno', title: '员工编号', sort: true, edit: true, align:'center'}
                , {field: 'userName', title: '用户登录名', edit: true, align:'center'}
                , {field: 'userPermission', title: '用户权限', sort: true, edit: true, align:'center'}
                , {field: 'userDeliverspot', title: '所在配送点', edit: true, align:'center'}
                , {title: '操作', toolbar: '#bar', align:'center'}
            ]]

        });


        table.on('checkbox(userTable)', function (obj) {
            console.log(obj.checked); //当前是否选中状态
            console.log(obj.data); //选中行的相关数据
            // alert(obj.data.userName)
            console.log(obj.type); //如果触发的是全选，则为：all，如果触发的是单选，则为：one
        });

        // //监听头工具栏事件
        // table.on('toolbar(userTable)', function(obj){
        //     var checkStatus = table.checkStatus(obj.config.id);
        //     var data = checkStatus.data; //获取选中的数据
        //
        //
        //     switch(obj.event){
        //         case 'addUser':
        //
        //             $("#dataform")[0].reset();
        //             $('#addUser').modal('show');
        //             break;
        //
        //         case 'delUsers':
        //             if(data.length === 0){
        //                 layer.msg('请选择一行');
        //             } else {
        //                 delUsers();
        //             }
        //             break;
        //     }
        // });


        //监听行工具事件
        table.on('tool(userTable)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                , layEvent = obj.event; //获得 lay-event 对应的值
            if (layEvent === 'detail') {
                openDetailUser(data.userId);
            } else if (layEvent === 'delUser') {
                layer.confirm('真的删除这条数据么', function (index) {
                    var url = "${pageContext.request.contextPath}/jsp/user.do";
                    var params = "method=delete&userid=" + data.userId
                    $.post(url, params, function (res) {
                        if (res.result === "true") {
                            layer.msg("删除成功");
                        } else {
                            layer.msg("删除失败");
                        }
                    })

                    obj.del(); //删除DOM结构
                    layer.close(index);
                    table.reload('usertable');

                });
            } else if (layEvent === 'edit') {
                layer.msg('编辑操作');
            }
        });

        //监听单元格事件
        table.on('edit(userTable)', function (obj) { //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
            console.log(obj.value); //得到修改后的值
            console.log(obj.field); //当前编辑的字段名
            console.log(obj.data); //所在行的所有相关数据

            if (obj.field ==="userName") {
                var url = "${pageContext.request.contextPath}/jsp/user.do",
                    params = "method=updateName&userid=" + obj.data.userId +"&value=" +obj.value;
                $.post(url, params, function (res) {
                    if (res.result === "true") {
                        layer.msg("修改成功");
                    } else {
                        layer.msg("修改失败");
                    }
                });
            }

            if (obj.field ==="userEmpno") {
                var url = "${pageContext.request.contextPath}/jsp/user.do",
                    params = "method=updateEmpno&userid=" + obj.data.userId +"&value=" +obj.value;
                $.post(url, params, function (res) {
                    if (res.result === "true") {
                        layer.msg("修改成功");
                    } else {
                        layer.msg("修改失败");
                    }
                });
            }

            if (obj.field ==="userDeliverspot") {
                var url = "${pageContext.request.contextPath}/jsp/user.do",
                    params = "method=updateDeliverspot&userid=" + obj.data.userId +"&value=" +obj.value;
                $.post(url, params, function (res) {
                    if (res.result === "true") {
                        layer.msg("修改成功");
                    } else {
                        layer.msg("修改失败");
                    }
                });
            }

            if (obj.field ==="userPermission") {
                var url = "${pageContext.request.contextPath}/jsp/user.do",
                    params = "method=updatePermission&userid=" + obj.data.userId +"&value=" +obj.value;
                $.post(url, params, function (res) {
                    if (res.result === "true") {
                        layer.msg("修改成功");
                    } else {
                        layer.msg("修改失败");
                    }
                });
            }


        });


        //删除，添加事件
        var active = {
            batchdel: function () {
                var checkStatus = table.checkStatus('usertable')
                    , checkData = checkStatus.data; //得到选中的数据

                if (checkData.length === 0) {
                    return layer.msg('请选择数据');
                }

                layer.prompt({
                    formType: 1
                    , title: '敏感操作，请验证口令'
                }, function (value, index) {

                    if (value === '123') {
                        layer.close(index);
                        delUsers();

                    } else {
                        layer.close(index);
                        layer.msg('密码有误')

                    }

                });
            }
            , add: function (obj) {

                $("#dataform")[0].reset();
                $('#addUser').modal('show');

            }
        }


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

                        table.reload('usertable');
                        layer.msg("删除成功");
                    } else if (res.result === 'false') {
                        layer.msg("删除失败")
                    }

                });

            });

        }


        //添加用户
        form.on('submit(doSubmit)', function (index) {
            // var params = form.val('dataform');
            var params = $('#dataform').serialize()
            $.post('${pageContext.request.contextPath}/jsp/user.do', params, function (res) {
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
            , password: [
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
                , area: ['420px', '310px']
                , title: '用户详细信息'
                , shade: 0.6 //遮罩透明度
                , maxmin: true //允许全屏最小化
                , anim: 3 //0-6的动画形式，-1不开启
                , scrollbar: false
                , content: $("#layercontent")
                , success: function () {

                    var params = "method=detail&userid=" + obj,
                        url = "${pageContext.request.contextPath}/jsp/user.do";
                    $.post(url, params, function (res) {
                        console.log(res)
                        $("#empno").text(res.userEmpno);
                        $("#username").text(res.userName);
                        $("#permission").text(res.userPermission);
                        $("#deliverspot").text(res.userDeliverspot);

                    });
                }
            });
        };


        //搜索
        $("#dosearch").click(function () {

            var url = "${pageContext.request.contextPath}/jsp/user.do";
            table.reload('usertable',{
                url:url,
                where: {
                    method:"search",
                    username:$('#username_').val(),
                    'p.name':$("#userpermission_").val(),
                     'e.emp_no':$('#userempno_').val(),
                    'd.name':$('#userds_').val()

                }
            })
        });





        $('.layui-btn.layuiadmin-btn-admin').on('click', function () {

            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
</script>


</body>
</html>


