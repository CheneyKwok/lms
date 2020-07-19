<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>empinfo</title>
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
                        <input type="text" name="empno" id="empmo_" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">姓名</label>
                    <div class="layui-input-block">
                        <input type="text" name="empname" id="empname_" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>

                <div class="layui-inline">
                    <label class="layui-form-label">职位</label>
                    <div class="layui-input-block" style="width:180px ">
                        <select name="position" id="position_">
                            <option value=""></option>
                            <option value="总经理">总经理</option>
                            <option value="部门经理">部门经理</option>
                            <option value="操作员">操作员</option>
                            <option value="交接员">交接员</option>
                            <option value="投递员">投递员</option>
                            <option value="司机">司机</option>
                        </select>
                    </div>
                </div>

                <div class="layui-inline" >
                    <label class="layui-form-label" style="width:100px ">联系号码</label>
                    <div class="layui-input-block">
                        <input type="text" name="tel" id="tel_" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <br>
                <div class="layui-inline">
                    <label class="layui-form-label" style="width:100px ">入职时间</label>
                    <div class="layui-input-block">
                        <input type="text" name="startworktime" id="startworktime_" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">薪资</label>
                    <div class="layui-input-block">
                        <input type="text" name="salary" id="salary_" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">身份证</label>
                    <div class="layui-input-block">
                        <input type="text" name="cardid" id="cardid_" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>

                <div class="layui-inline">
                    <label class="layui-form-label"style="width:100px ">所在配送点</label>
                    <div class="layui-input-block">
                        <input type="text" name="deliverspot" id="deliverspot_" placeholder="请输入" autocomplete="off" class="layui-input">
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

            <table id="table" lay-filter="Table"></table>


            <script type="text/html" id="buttonTpl">
                {{#  if(d.age == 18){ }}
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


<div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="name"aria-hidden="true">
    <div class="modal-dialog" style="width: 480px"  role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加员工</h4>
            </div>
            <div class="modal-body">
                <form class="layui-form layui-form-pane" action="" lay-filter="dataform" id="dataform">
                    <input type="hidden" name="method" value="add">

                    <div class="layui-form-item ">
                        <label class="layui-form-label">员工姓名</label>
                        <div class="layui-input-block">
                            <input style="width: auto" type="text" name="name" lay-verify="required|name" autocomplete="off" placeholder="请输入" class="layui-input" >
                        </div>
                    </div>


                    <div class="layui-form-item">
                        <label class="layui-form-label">性别</label>
                        <div class="layui-input-block">
                            <input type="radio" name="gender" value="1" title="男" checked="">
                            <input type="radio" name="gender" value="0" title="女">
                        </div>
                    </div>


                    <div class="layui-form-item ">
                        <label class="layui-form-label">年龄</label>
                        <div class="layui-input-block">
                            <input style="width: 80px" type="number" name="age" lay-verify="required|number" autocomplete="off" placeholder="请输入" class="layui-input" >
                        </div>
                    </div>


                    <div class="layui-form-item ">
                        <label class="layui-form-label">手机号</label>
                        <div class="layui-input-block">
                            <input style="width: auto" type="tel" name="tel" lay-verify="required|phone" autocomplete="off" placeholder="请输入" class="layui-input" >
                        </div>
                    </div>

                    <div class="layui-form-item ">
                        <div class="layui-inline">
                            <label class="layui-form-label">入职时间</label>
                            <div class="layui-input-inline">
                                <input type="text"  name="startworktime"   id="laydate-normal-cn" lay-verify="date"  class="layui-input"  placeholder="请选择">
                            </div>
                        </div>
                    </div>

                    <div class="layui-form-item ">
                        <label class="layui-form-label">薪资（元）</label>
                        <div class="layui-input-block">
                            <input style="width: auto" type="text" name="salary" lay-verify="required|number" autocomplete="off" placeholder="请输入" class="layui-input" >
                        </div>
                    </div>

                    <div class="layui-form-item ">
                        <label class="layui-form-label">邮箱</label>
                        <div class="layui-input-block">
                            <input style="width: auto" type="text" name="email" lay-verify="email" autocomplete="off" placeholder="请输入" class="layui-input" >
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">身份证</label>
                        <div class="layui-input-block">
                            <input style="width: auto" type="text" name="cardid" lay-verify="identity" placeholder="请输入" autocomplete="off" class="layui-input">
                        </div>
                    </div>



                    <div class="layui-form-item">
                        <label class="layui-form-label">职位</label>
                        <div class="layui-input-block" style="width: 162px">
                            <select name="position" lay-filter="">
                                <option value=""></option>
                                <option value="1">总经理</option>
                                <option value="2">部门经理</option>
                                <option value="3">操作员</option>
                                <option value="4">交接员</option>
                                <option value="5">投递员</option>
                                <option value="6">司机</option>

                            </select>
                        </div>
                    </div>


                    <div class="layui-form-item">
                        <label class="layui-form-label">在职状态</label>
                        <div class="layui-input-block" style="width: 162px">
                            <select name="state" lay-filter="">
                                <option value=""></option>
                                <option value="1">在职</option>
                                <option value="0">离职</option>
                            </select>
                        </div>
                    </div>

                    <div class="layui-form-item ">
                        <label class="layui-form-label">所在配送点</label>
                        <div class="layui-input-block">
                            <input style="width: auto" type="text" name="dsname" lay-verify="required|deliverspot" autocomplete="off" placeholder="请输入" class="layui-input">
                        </div>
                    </div>


                    <div class="layui-form-item ">
                        <label class="layui-form-label">员工编号</label>
                        <div class="layui-input-block">
                            <input style="width: 130px" type="text" name="empno" lay-verify="required|empnno" autocomplete="off" placeholder="请输入" class="layui-input">
                        </div>
                    </div>



                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" style="background-color: #009688" class="btn btn-primary" lay-submit lay-filter="doSubmit">提交</button>
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

                <div class="form-control-static" id="empno"></div>

            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label">员工姓名：</label>
                <div class="form-control-static" id="empname"></div>
            </div>

            <div class="form-group">
                <label class="col-sm-4 control-label" >性别：</label>
                <div class="form-control-static" id="gender"></div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label" >年龄：</label>
                <div class="form-control-static" id="age"></div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label" >职位：</label>
                <div class="form-control-static" id="position"></div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label" >联系号码：</label>
                <div class="form-control-static" id="tel"></div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label" >入职时间：</label>
                <div class="form-control-static" id="startworktime"></div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label" >薪资（元）：</label>
                <div class="form-control-static" id="salary"></div>
            </div>

            <div class="form-group">
                <label class="col-sm-4 control-label" >身份证：</label>
                <div class="form-control-static" id="cardid"></div>
            </div>

            <div class="form-group">
                <label class="col-sm-4 control-label" >邮箱：</label>
                <div class="form-control-static" id="email"></div>
            </div>

            <div class="form-group">
                <label class="col-sm-4 control-label" >所在配送点：</label>
                <div class="form-control-static" id="deliverspot"></div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label" >在职状态：</label>
                <div class="form-control-static" id="state"></div>
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
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</a>
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
            , laydate = layui.laydate
        ,element = layui.element;


        laydate.render({
            elem: '#laydate-normal-cn'
            ,theme: '#393D49'
        });




        var dialogheight = $(window).height() / 5;
        $('#addUser').css({
            'margin-top': dialogheight

        });


        //执行一个 table 实例
        table.render({
            elem: '#table'
            , url: '${pageContext.request.contextPath}/jsp/emp.do'
            , title: '员工信息'
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
                {type: 'numbers', fixed: 'left'}
                , {type: 'checkbox', fixed: 'left'}
                , {field: 'empNo', title: '员工编号', width:110, sort: true, edit: true, align:'center'}
                , {field: 'empName', title: '员工姓名', width:100, edit: true, align:'center'}
                , {field: 'gender', title: '性别', width:75, sort: true, edit: true, align:'center'}
                , {field: 'age', title: '年龄', width:70, edit: true, align:'center'}
                , {field: 'position', title: '职位', width:100, edit: true, align:'center'}
                , {field: 'tel', title: '联系号码', width:130, edit: true, align:'center'}
                , {field: 'startworktime', title: '入职时间', edit: true, align:'center',type:'date'}
                , {field: 'salary', title: '薪资（元）', width:80, edit: true, align:'center'}
                , {field: 'cardId', title: '身份证号', width:190, edit: true, align:'center'}
                , {field: 'email', title: '邮箱', width:200, edit: true, align:'center'}
                , {field: 'deliverspot', title: '所在配送点', width:100, edit: true, align:'center'}
                , {field: 'state', title: '在职状态', width:90, edit: true, align:'center'}
                , {title: '操作', toolbar: '#bar', align:'center'}
            ]]

        });



        table.on('checkbox(Table)', function (obj) {
            console.log(obj.checked); //当前是否选中状态
            console.log(obj.data); //选中行的相关数据
            // alert(obj.data.userName)
            console.log(obj.type); //如果触发的是全选，则为：all，如果触发的是单选，则为：one
        });


        //监听行工具事件
        table.on('tool(Table)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                , layEvent = obj.event; //获得 lay-event 对应的值
            if (layEvent === 'detail') {
                openDetail(data.empid);
            } else if (layEvent === 'delete') {

                layer.prompt({
                    formType: 1
                    , title: '敏感操作，请验证口令'
                }, function (value, index) {

                    if (value === '123') {
                        layer.close(index);
                        layer.confirm('真的删除这条数据么', function (index) {
                            var url = "${pageContext.request.contextPath}/jsp/emp.do";
                            var params = "method=delete&empid=" + data.empid
                            $.post(url, params, function (res) {
                                if (res.result === "true") {
                                    layer.msg("删除成功");
                                } else {
                                    layer.msg("删除失败");
                                }
                            })

                            obj.del(); //删除DOM结构
                            layer.close(index);
                            table.reload('table');

                        });

                    } else {
                        layer.close(index);
                        layer.msg('密码有误')

                    }

                });


            }
        });

        //监听单元格修改
        table.on('edit(Table)', function (obj) { //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
            console.log(obj.value); //得到修改后的值
            console.log(obj.field); //当前编辑的字段名
            console.log(obj.data); //所在行的所有相关数据

            if (obj.field ==="empNo") {
                var url = "${pageContext.request.contextPath}/jsp/emp.do",
                    params = "method=update&column=empNo&empid=" + obj.data.empid +"&value=" +obj.value;
                $.post(url, params, function (res) {
                    if (res.result === "true") {
                        layer.msg("修改成功");
                    } else {
                        layer.msg("修改失败");
                    }
                });
            }

            if (obj.field ==="empName") {
                var url = "${pageContext.request.contextPath}/jsp/emp.do",
                    params = "method=update&column=empName&empid=" + obj.data.empid +"&value=" +obj.value;
                $.post(url, params, function (res) {
                    if (res.result === "true") {
                        layer.msg("修改成功");
                    } else {
                        layer.msg("修改失败");
                    }
                });
            }

            if (obj.field ==="gender") {
                var gender = null;

                if (obj.value === "男") {
                    gender = 1 ;
                    var url = "${pageContext.request.contextPath}/jsp/emp.do",
                        params = "method=update&column=gender&empid=" + obj.data.empid +"&value=" +gender;
                    $.post(url, params, function (res) {
                        if (res.result === "true") {
                            layer.msg("修改成功");
                        } else {
                            layer.msg("修改失败");
                        }
                    });

                }
                else if (obj.value === "女") {
                    gender = 0 ;
                    var url = "${pageContext.request.contextPath}/jsp/emp.do",
                        params = "method=update&column=gender&empid=" + obj.data.empid +"&value=" +gender;
                    $.post(url, params, function (res) {
                        if (res.result === "true") {
                            layer.msg("修改成功");
                        } else {
                            layer.msg("修改失败");
                        }
                    });

                }
                else {
                    layer.msg("输入格式错误");
                }

            }
            if (obj.field ==="age") {
                var url = "${pageContext.request.contextPath}/jsp/emp.do",
                    params = "method=update&column=age&empid=" + obj.data.empid +"&value=" +obj.value;
                $.post(url, params, function (res) {
                    if (res.result === "true") {
                        layer.msg("修改成功");
                    } else {
                        layer.msg("修改失败");
                    }
                });
            }
            if (obj.field ==="tel") {
                var url = "${pageContext.request.contextPath}/jsp/emp.do",
                    params = "method=update&column=tel&empid=" + obj.data.empid +"&value=" +obj.value;
                $.post(url, params, function (res) {
                    if (res.result === "true") {
                        layer.msg("修改成功");
                    } else {
                        layer.msg("修改失败");
                    }
                });
            }
            if (obj.field ==="startworktime") {
                var url = "${pageContext.request.contextPath}/jsp/emp.do",
                    params = "method=update&column=startworktime&empid=" + obj.data.empid +"&value=" +obj.value;
                $.post(url, params, function (res) {
                    if (res.result === "true") {
                        layer.msg("修改成功");
                    } else {
                        layer.msg("修改失败");
                    }
                });
            }
            if (obj.field ==="salary") {
                var url = "${pageContext.request.contextPath}/jsp/emp.do",
                    params = "method=update&column=salary&empid=" + obj.data.empid +"&value=" +obj.value;
                $.post(url, params, function (res) {
                    if (res.result === "true") {
                        layer.msg("修改成功");
                    } else {
                        layer.msg("修改失败");
                    }
                });
            }
            if (obj.field ==="email") {
                var url = "${pageContext.request.contextPath}/jsp/emp.do",
                    params = "method=update&column=email&empid=" + obj.data.empid +"&value=" +obj.value;
                $.post(url, params, function (res) {
                    if (res.result === "true") {
                        layer.msg("修改成功");
                    } else {
                        layer.msg("修改失败");
                    }
                });
            }
            if (obj.field ==="cardId") {
                var url = "${pageContext.request.contextPath}/jsp/emp.do",
                    params = "method=update&column=cardid&empid=" + obj.data.empid +"&value=" +obj.value;
                $.post(url, params, function (res) {
                    if (res.result === "true") {
                        layer.msg("修改成功");
                    } else {
                        layer.msg("修改失败");
                    }
                });
            }
            if (obj.field ==="position") {
                var positionId = null;

                if (obj.value === "总经理") {
                    positionId = 1;
                    var url = "${pageContext.request.contextPath}/jsp/emp.do",
                        params = "method=update&column=position&empid=" + obj.data.empid + "&value=" + positionId;
                    $.post(url, params, function (res) {
                        if (res.result === "true") {
                            layer.msg("修改成功");
                        } else {
                            layer.msg("修改失败");
                        }
                    });

                } else if (obj.value === "部门经理") {
                    positionId = 2;
                    var url = "${pageContext.request.contextPath}/jsp/emp.do",
                        params = "method=update&column=position&empid=" + obj.data.empid + "&value=" + positionId;
                    $.post(url, params, function (res) {
                        if (res.result === "true") {
                            layer.msg("修改成功");
                        } else {
                            layer.msg("修改失败");
                        }
                    });

                } else if (obj.value === "操作员") {
                    positionId = 3;
                    var url = "${pageContext.request.contextPath}/jsp/emp.do",
                        params = "method=update&column=position&empid=" + obj.data.empid + "&value=" + positionId;
                    $.post(url, params, function (res) {
                        if (res.result === "true") {
                            layer.msg("修改成功");
                        } else {
                            layer.msg("修改失败");
                        }
                    });

                } else if (obj.value === "交接员") {
                    positionId = 4;
                    var url = "${pageContext.request.contextPath}/jsp/emp.do",
                        params = "method=update&column=position&empid=" + obj.data.empid + "&value=" + positionId;
                    $.post(url, params, function (res) {
                        if (res.result === "true") {
                            layer.msg("修改成功");
                        } else {
                            layer.msg("修改失败");
                        }
                    });

                } else if (obj.value === "投递员") {
                    positionId = 5;
                    var url = "${pageContext.request.contextPath}/jsp/emp.do",
                        params = "method=update&column=position&empid=" + obj.data.empid + "&value=" + positionId;
                    $.post(url, params, function (res) {
                        if (res.result === "true") {
                            layer.msg("修改成功");
                        } else {
                            layer.msg("修改失败");
                        }
                    });

                } else if (obj.value === "司机") {
                    positionId = 6;
                    var url = "${pageContext.request.contextPath}/jsp/emp.do",
                        params = "method=update&column=position&empid=" + obj.data.empid + "&value=" + positionId;
                    $.post(url, params, function (res) {
                        if (res.result === "true") {
                            layer.msg("修改成功");
                        } else {
                            layer.msg("修改失败");
                        }
                    });

                } else {
                    layer.msg("输入格式错误");
                }



            }
            if (obj.field ==="state") {
                var flag = null;
                if (obj.value === "在职") {
                    flag = 1 ;
                    var url = "${pageContext.request.contextPath}/jsp/emp.do",
                        params = "method=update&column=state&empid=" + obj.data.empid +"&value=" +flag;
                    $.post(url, params, function (res) {
                        if (res.result === "true") {
                            layer.msg("修改成功");
                        } else {
                            layer.msg("修改失败");
                        }
                    });

                }
                else if (obj.value === "离职") {
                    flag = 0 ;
                    var url = "${pageContext.request.contextPath}/jsp/emp.do",
                        params = "method=update&column=state&empid=" + obj.data.empid +"&value=" +flag;
                    $.post(url, params, function (res) {
                        if (res.result === "true") {
                            layer.msg("修改成功");
                        } else {
                            layer.msg("修改失败");
                        }
                    });

                }
                else {
                    layer.msg("输入格式错误");
                }


            }
            if (obj.field ==="deliverspot") {

                var url = "${pageContext.request.contextPath}/jsp/emp.do",
                    data = "method=checkds&deliverspot=" + obj.value;
                $.post("${pageContext.request.contextPath}/jsp/user.do",data,function (res) {
                    if (res.result === "true") {
                        params = "method=update&column=deliverspot&empid=" + obj.data.empid + "&value=" + obj.value;
                        $.post(url, params, function (res) {
                            if (res.result === "true") {
                                layer.msg("修改成功");
                            } else {
                                layer.msg("修改失败");
                            }
                        });
                    } else {
                        layer.msg("没有该配送点");
                    }

                })



            }


        });


        //批量删除，添加事件
        var active = {
            batchdel: function () {
                var checkStatus = table.checkStatus('table')
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
                        delBatch();

                    } else {
                        layer.close(index);
                        layer.msg('密码有误')

                    }

                });
            }
            , add: function (obj) {

                //$("#dataform")[0].reset();
                $('#add').modal('show');

            }
        }


        //批量删除
        function delBatch() {
            var checkStatus = table.checkStatus('table');
            var data = checkStatus.data;
            var params = "";
            // layer.msg(data.userId)
            // var ids =[];
            $.each(data, function (i, item) {
                if (i === 0) {
                    // ids.push(item.userId);
                    params += "method=deleteBatch&empid=" + item.empid;
                } else {
                    params += "&empid=" + item.empid;
                }
                // layer.msg(params);
            });
            // params += "method=delete&userid="+ids;
            layer.confirm('确定删除所选数据？', function (index) {
                $.post('${pageContext.request.contextPath}/jsp/emp.do', params, function (res) {

                    if (res.result === 'true') {

                        table.reload('table');
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
            $.post('${pageContext.request.contextPath}/jsp/emp.do', params, function (res) {
                if (res.result === "true") {
                    $("#add").modal('hide');
                    layer.msg('添加成功');
                    table.reload('table');
                } else {
                    layer.msg('添加失败');
                }

            });

        });

        //验证
        form.verify({
            name: function (value) {
                if (value.length > 10) {
                    return "用户名过长";
                }
            }
           , empno: function (value) {


            }
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
        function openDetail(obj) {
            layer.open({
                type: 1 //Page层类型
                , area: ['500px', '690px']
                , title: '员工详细信息'
                , shade: 0.6 //遮罩透明度
                , maxmin: true //允许全屏最小化
                , anim: 3 //0-6的动画形式，-1不开启
                , scrollbar: false
                , content: $("#layercontent")
                , success: function () {

                    var params = "method=detail&empid=" + obj,
                        url = "${pageContext.request.contextPath}/jsp/emp.do";
                    $.post(url, params, function (res) {
                        $("#empno").text(res.empno);
                        $("#empname").text(res.empname);
                        $("#age").text(res.age);
                        $("#cardid").text(res.cardid);
                        $("#email").text(res.email);
                        $("#gender").text(res.gender);
                        $("#position").text(res.position);
                        $("#salary").text(res.salary);
                        $("#startworktime").text(res.startworktime);
                        $("#state").text(res.state);
                        $("#tel").text(res.tel);
                        $("#deliverspot").text(res.deliverspot);

                    });
                }
            });
        };


        //搜索
        $("#dosearch").click(function () {

            var url = "${pageContext.request.contextPath}/jsp/emp.do";
            table.reload('table',{
                url:url,
                where: {
                    method:"search",
                    'e.name':$('#empname_').val(),
                    'p.name':$("#position_").val(),
                    'emp_no':$('#empmo_').val(),
                    'd.name':$('#deliverspot_').val(),
                    'gender':$('#gender').val(),
                    'age':$('#age').val(),
                    'e.tel':$('#tel_').val(),
                    'startworktime':$('#startworktime_').val(),
                    'salary':$('#salary_').val(),
                    'email':$('#email_').val(),
                    'cardid':$('#cardid_').val(),
                    'state':$('#state_').val(),

                }
            })
        });





        $('.layui-btn.layuiadmin-btn-admin').on('click', function () {

            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
</script>

<style>
    .layui-table-cell .layui-form-checkbox[lay-skin="primary"]{
        top: 50%;
        transform: translateY(-50%);
    }
</style>

</body>
</html>