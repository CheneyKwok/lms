<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>lineinfo</title>
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
                    <label class="layui-form-label"  style="width: 110px">线路编号</label>
                    <div class="layui-input-block">
                        <input type="text"  id="lineno_" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>



                <div class="layui-inline">
                    <label class="layui-form-label"  style="width: 110px">线路名称</label>
                    <div class="layui-input-block">
                        <input type="text"  id="linename_" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label" style="width: 110px">线路里程</label>
                    <div class="layui-input-block">
                        <input type="text" id="linelength" placeholder="请输入" autocomplete="off" class="layui-input">
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
    <div class="modal-dialog" style="width: 500px"  role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加线路</h4>
            </div>
            <div class="modal-body">
                <form class="layui-form " action="" lay-filter="dataform" id="dataform">
                    <input type="hidden" name="method" value="add">

                    <div class="layui-row">
                        <div class="layui-col-xs3">
                            <label class="layui-form-label" style="width: 90px">线路编号</label>
                        </div>
                        <div class="">
                            <input style="width: 200px" type="text" name="lineNo" placeholder="请输入" autocomplete="off"
                                   class="form-control">

                        </div>
                    </div>



                    <div class="layui-col-xs3">
                        <label class="layui-form-label" style="width: 90px">线路名称</label>
                    </div>
                    <div class="layui-col-xs4">
                        <input style="width: 200px" type="text" name="lineName" placeholder="请输入" autocomplete="off"
                               class="form-control">
                    </div>

                    <div class="layui-row">
                        <div class="layui-col-xs3">
                            <label class="layui-form-label" style="width: 90px">线路里程</label>
                        </div>
                        <div class="layui-col-xs4">
                            <input style="width: 200px" type="text" name="length" placeholder="请输入" autocomplete="off"
                                   class="form-control">

                        </div>
                    </div>




                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" style="background-color: #009688" class="btn btn-primary" lay-submit lay-filter="doSubmit">确定</button>
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

                <div class="form-group">
                    <label class="col-sm-3 control-label">线路编号：</label>
                    <div class="form-control-static " id="lineNo"></div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">线路名称：</label>
                    <div class="form-control-static col-sm-9" id="lineName"></div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">线路里程：</label>
                    <div class="form-control-static col-sm-9"id="length"></div>
                </div>

            </div>


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
            , url: '${pageContext.request.contextPath}/jsp/line.do'
            , title: '线路信息'
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
                , {field: 'lineNo', title: '线路编号',  sort: true, edit: true, align:'center'}
                , {field: 'name', title: '线路名称',  edit: true, align:'center'}
                , {field: 'length', title: '线路里程（km）', edit: true, align:'center'}
                , {title: '操作', toolbar: '#bar', align:'center'}
            ]]

        });





        //监听行工具事件
        table.on('tool(Table)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                , layEvent = obj.event; //获得 lay-event 对应的值
            if (layEvent === 'detail') {
                openDetail(data.lineId);
            } else if (layEvent === 'delete') {

                layer.prompt({
                    formType: 1
                    , title: '敏感操作，请验证口令'
                }, function (value, index) {

                    if (value === '123') {
                        layer.close(index);
                        layer.confirm('真的删除这条数据么', function (index) {
                            var url = "${pageContext.request.contextPath}/jsp/line.do";
                            var params = "method=delete&lineid=" + data.lineId
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

        //监听edit事件
        table.on('edit(Table)', function (obj) { //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
            console.log(obj.value); //得到修改后的值
            console.log(obj.field); //当前编辑的字段名
            console.log(obj.data); //所在行的所有相关数据

            if (obj.field ==="lineNo") {
                var url = "${pageContext.request.contextPath}/jsp/line.do",
                    params = "method=update&column=lineNo&lineid=" + obj.data.lineId +"&value=" +obj.value;
                $.post(url, params, function (res) {
                    if (res.result === "true") {
                        layer.msg("修改成功");
                    } else {
                        layer.msg("修改失败");
                    }
                });

            }

            if (obj.field ==="name                 ") {
                var url = "${pageContext.request.contextPath}/jsp/line.do",
                    params = "method=update&column=lineName&lineid=" + obj.data.lineId +"&value=" +obj.value;
                $.post(url, params, function (res) {
                    if (res.result === "true") {
                        layer.msg("修改成功");
                    } else {
                        layer.msg("修改失败");
                    }
                });
            }

            if (obj.field ==="length") {
                var url = "${pageContext.request.contextPath}/jsp/line.do",
                    params = "method=update&column=length&lineid=" + obj.data.lineId +"&value=" +obj.value;
                $.post(url, params, function (res) {
                    if (res.result === "true") {
                        layer.msg("修改成功");
                    } else {
                        layer.msg("修改失败");
                    }
                });
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

                 $("#dataform")[0].reset();
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
                    params += "method=deleteBatch&lineid=" + item.lineId;
                } else {
                    params += "&lineid=" + item.lineId;
                }
                // layer.msg(params);
            });
            // params += "method=delete&userid="+ids;
            layer.confirm('确定删除所选数据？', function (index) {
                $.post('${pageContext.request.contextPath}/jsp/line.do', params, function (res) {

                    if (res.result === 'true') {

                        table.reload('table');
                        layer.msg("删除成功");
                    } else if (res.result === 'false') {
                        layer.msg("删除失败")
                    }

                });

            });

        }


        //添加订单
        form.on('submit(doSubmit)', function (index) {
            // var params = form.val('dataform');
            var params = $('#dataform').serialize()
            $.post('${pageContext.request.contextPath}/jsp/line.do', params, function (res) {
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
            , carNo: [
                /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/
                ,"车牌号格式错误"
            ]

            , line: function (value) {
                var msg = "";
                var url = "${pageContext.request.contextPath}/jsp/car.do"
                var params = "method=checkLine&value=" + value;
                $.ajaxSettings.async = false;
                $.post(url, params, function (data) {
                    if (data.result === "false") {
                        msg = "没有该线路";
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
                , area: ['520px', '350px']
                , title: '线路详细信息'
                , shade: 0.6 //遮罩透明度
                , maxmin: true //允许全屏最小化
                , anim: 3 //0-6的动画形式，-1不开启
                , scrollbar: false
                , content: $("#layercontent")
                , success: function () {

                    var params = "method=detail&lineid=" + obj,
                        url = "${pageContext.request.contextPath}/jsp/line.do";
                    $.post(url, params, function (res) {
                        $("#lineNo").text(res.lineNo);
                        $("#lineName").text(res.lineName);
                        $("#length").text(res.length);



                    });
                }
            });
        };


        //搜索
        $("#dosearch").click(function () {

            var url = "${pageContext.request.contextPath}/jsp/line.do";
            table.reload('table',{
                url:url,
                where: {
                    method:"search",

                    'line_no':$("#lineno_").val(),
                    'name':$('#linename_').val(),
                    'length':$('#linelength').val(),


                }
            })
        });


        laydate.render({
            elem: '#start'
            ,type: 'datetime'
        });

        laydate.render({
            elem: '#end'
            ,type: 'datetime'
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