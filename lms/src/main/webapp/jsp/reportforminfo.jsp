<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>reportforminfo</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layuiadmin/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layuiadmin/style/admin.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css"/>
    `
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/layuiadmin/layui/css/modules/layer/default/layer.css?v=3.1.1">
</head>


<body>

<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
            <div class="layui-form-item">



                <div class="layui-inline">
                    <label class="layui-form-label" style="width: 120px">日期范围</label>
                    <div class="layui-input-inline" style="width:200px;">
                        <input type="text" id="start" placeholder="2020-03-25 00:00:00" autocomplete="off"
                               class="layui-input">
                    </div>
                    <div class="layui-form-mid">-</div>
                    <div class="layui-input-inline" style="width: 200px;">
                        <input type="text" id="end" placeholder="2020-04-25 00:00:00" autocomplete="off"
                               class="layui-input">
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
                <button class="layui-btn layuiadmin-btn-admin" data-type="batchdel">批量删除</button>

            </div>

            <table id="table" lay-filter="Table"></table>



        </div>
    </div>
</div>




<div style="display: none" id="layercontent">
    <div class="wrapper wrapper-content animated fadeInRight ibox-content">
        <form class="form-horizontal m-t" id="signupForm" style="padding-left:20px;">
            <div class="form-group">

                <div class="form-group">
                    <label class="col-sm-5 control-label">订单编号：</label>
                    <div class="form-control-static " id="orderNo"></div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">下单时间：</label>
                    <div class="form-control-static col-sm-10" id="createTime"></div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">订单状态：</label>
                    <div class="form-control-static col-sm-10" id="orderState_"></div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">寄件人姓名：</label>
                    <div class="form-control-static col-sm-3" id="sendName"></div>
                    <label class="col-sm-3 control-label">寄件人电话：</label>
                    <div class="form-control-static col-sm-4" id="sendTel"></div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">寄件人地址：</label>
                    <div class="form-control-static col-sm-10" id="sendAddress"></div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">收件人姓名：</label>
                    <div class="form-control-static col-sm-4" id="receiveName"></div>
                    <label class="col-sm-2 control-label">收件人电话：</label>
                    <div class="form-control-static col-sm-4" id="receiveTel"></div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">收件人地址：</label>
                    <div class="form-control-static col-sm-10" id="receiveAddress"></div>
                </div>


                <div class="form-group">
                    <label class="col-sm-2 control-label">体积（m3）：</label>
                    <div class="form-control-static col-sm-4" id="volume"></div>
                    <label class="col-sm-2 control-label">重量(kg)：</label>
                    <div class="form-control-static col-sm-4" id="weight"></div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">费用（元）：</label>
                    <div class="form-control-static col-sm-9" id="price"></div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">备注：</label>
                    <div class="form-control-static col-sm-10" id="remark"></div>
                </div>
            </div>


        </form>
    </div>
</div>






<script type="text/html" id="bar">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>

    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</a>
</script>


<script src="../js/jquery-3.5.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.js"></script>
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
            , element = layui.element;


        laydate.render({
            elem: '#laydate-normal-cn'
            , theme: '#393D49'
        });


        var dialogheight = $(window).height() / 5;
        $('#addUser').css({
            'margin-top': dialogheight

        });


        //执行一个 table 实例
        table.render({
            elem: '#table'
            , url: '${pageContext.request.contextPath}/jsp/order.do'
            , title: '报表信息'
            , page: true
            , toolbar: false
            , where: {method: "query"}
            , skin: 'line'
            , even: false //开启隔行背景
            // ,height:437
            // ,height:'full-509.6'
            , limit: 10
            , limits: [10, 20, 40]
            ,totalRow: true
            , cols: [[
                {type: 'numbers', fixed: 'left',}
                , {type: 'checkbox', fixed: 'left',totalRowText: '合计'}
                , {field: 'orderNo', title: '订单编号', sort: true, align: 'center'}
                , {field: 'createTime', title: '创建时间',  align: 'center'}
                , {field: 'price', title: '价格', edit: true, align: 'center',totalRow: true}
                , {title: '操作', fixed: 'right', width: 300, toolbar: '#bar', align: 'center'}
            ]]

        });




        table.on('checkbox(Table)', function (obj) {
            console.log(obj.checked); //当前是否选中状态
            console.log(obj.data); //选中行的相关数据
            // alert(obj.data.userName)
            console.log(obj.type); //如果触发的是全选，则为：all，如果触发的是单选，则为：one
        });


        //监听行事件
        table.on('tool(Table)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                , layEvent = obj.event; //获得 lay-event 对应的值
            if (layEvent === 'detail') {
                openDetail(data.orderId);
            }
            else if (layEvent === 'delete') {
                layer.prompt({
                    formType: 1
                    , title: '敏感操作，请验证口令'
                }, function (value, index) {

                    if (value === '123') {
                        layer.close(index);
                        layer.confirm('真的删除这条数据么', function (index) {
                            var url = "${pageContext.request.contextPath}/jsp/order.do";
                            var params = "method=delete&orderid=" + data.orderId
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
            else if (layEvent === 'join') {
                if (data.orderState === "已交接" || data.orderState === "已投递" || data.orderState === "已签收") {
                    layer.msg("订单已交接",{icon: 7,anim:6, time: 2000,});
                } else {
                    //$.ajaxSettings.async = false;
                    var params = "method=queryCarList";
                    $.post('${pageContext.request.contextPath}/jsp/car.do', params, function (res) {
                        $.each(res, function (index, item) {
                            // alert(item.carNo)
                            $('#carList').append(new Option(item.carNo, item.carId));// 下拉菜单里添加元素
                        });
                        layui.form.render("select");


                    });

                    layer.open({
                        type: 1 //Page层类型
                        , area: ['400px', '310px']
                        , title: '订单交接'
                        ,btn: ['确定', '取消']
                        ,skin: 'demo-class'
                        , shade: 0.6 //遮罩透明度
                        , maxmin: true //允许全屏最小化
                        , anim: 5 //0-6的动画形式，-1不开启
                        , scrollbar: false
                        , content: $("#layer-join")
                        ,success:function () {
                            $('#form-join')[0].reset();
                            $("#lo").text("待选择")


                        }
                        , yes: function (index, d) {
                            if ($('#carList option:selected').text() === "请选择" ) {
                                layer.msg("请选择车辆",{icon: 7,anim:6, time: 1800,})
                            }else if ($('#depart-time').val() === "") {
                                layer.msg("请选择发车时间", {icon: 7, anim: 6, time: 1800,});
                            } else {
                                // alert($('#depart-time').val());
                                var params = "method=join&carno=" + $('#carList option:selected').text() + "&departtime="
                                    + $("#depart-time").val() + "&orderid=" + data.orderId + "&flag=0" + "&carid=" + $("#carList option:selected").val();
                                $.post("${pageContext.request.contextPath}/jsp/receipt.do", params, function (res) {

                                    if (res.result === "true") {
                                        layer.msg("发车成功");
                                        table.reload('table');
                                    } else {
                                        layer.msg("发车失败");
                                    }


                                });
                                layer.close(index);

                            }
                        }
                        ,end:function () {
                            $("#carList").empty();
                            $("#carList").prepend("<option value=\"待选择\">请选择</option>")

                        }



                    });



                }


            }
            else if (layEvent === 'deliver') {

                if (data.orderState === "已投递") {
                    layer.msg("订单已投递",{icon: 7,anim:6, time: 2000,});
                }else if (data.orderState === "未处理") {
                    layer.msg("请先交接订单",{icon: 7,anim:6, time: 2000,});
                }else if (data.orderState === "已签收") {
                    layer.msg("订单已签收",{icon: 7,anim:6, time: 2000,});
                } else {
                    layer.confirm('是否投递订单?', {
                        title: ['信息', 'background:#009688']
                        , skin: 'demo-class'
                        , btn: ['是', '否']
                    }, function (index) {
                        var params = "method=deliver&orderid=" + data.orderId + "&flag=1"
                        $.post("${pageContext.request.contextPath}/jsp/receipt.do", params, function (res) {
                            if (res.result === "true") {
                                layer.msg("投递成功");
                                table.reload('table');
                            } else {
                                layer.msg("投递失败");
                            }
                            layer.close(index);
                        });


                    });


                }
            }
        });

        //监听edit事件
        table.on('edit(Table)', function (obj) { //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
            console.log(obj.value); //得到修改后的值
            console.log(obj.field); //当前编辑的字段名
            console.log(obj.data); //所在行的所有相关数据

            if (obj.field === "sendName") {
                var url = "${pageContext.request.contextPath}/jsp/order.do",
                    params = "method=update&column=sendName&orderid=" + obj.data.orderId + "&value=" + obj.value;
                $.post(url, params, function (res) {
                    if (res.result === "true") {
                        layer.msg("修改成功");
                    } else {
                        layer.msg("修改失败");
                    }
                });

            }

            if (obj.field === "sendAddress") {
                var url = "${pageContext.request.contextPath}/jsp/order.do",
                    params = "method=update&column=sendAddress&orderid=" + obj.data.orderId + "&value=" + obj.value;
                $.post(url, params, function (res) {
                    if (res.result === "true") {
                        layer.msg("修改成功");
                    } else {
                        layer.msg("修改失败");
                    }
                });
            }

            if (obj.field === "receiveName") {
                var url = "${pageContext.request.contextPath}/jsp/order.do",
                    params = "method=update&column=receiveName&orderid=" + obj.data.orderId + "&value=" + obj.value;
                $.post(url, params, function (res) {
                    if (res.result === "true") {
                        layer.msg("修改成功");
                    } else {
                        layer.msg("修改失败");
                    }
                });
            }

            if (obj.field === "receiveAddress") {
                var url = "${pageContext.request.contextPath}/jsp/order.do",
                    params = "method=update&column=receiveAddress&orderid=" + obj.data.orderId + "&value=" + obj.value;
                $.post(url, params, function (res) {
                    if (res.result === "true") {
                        layer.msg("修改成功");
                    } else {
                        layer.msg("修改失败");
                    }
                });
            }

            if (obj.field === "deliverSpot") {

                var url = "${pageContext.request.contextPath}/jsp/order.do",
                    data = "method=checkds&deliverspot=" + obj.value;
                $.post("${pageContext.request.contextPath}/jsp/user.do", data, function (res) {
                    if (res.result === "true") {
                        params = "method=update&column=deliverspot&orderid=" + obj.data.orderId + "&value=" + obj.value;
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

                // $("#dataform")[0].reset();
                $('#add').modal('show');

            }
            , join:function (obj) {
                var checkStatus = table.checkStatus('table')
                    , checkData = checkStatus.data; //得到选中的数据

                if (checkData.length === 0) {
                    return layer.msg('请选择数据');
                }
                function f (value, index, ar) {
                    if (value.orderState === "未处理") {
                        return true;
                    }else {
                        return false;
                    }
                };
                if (checkData.every(f)) {
                    joinBatch();
                }else{
                    return layer.msg('请勿选择非可交接订单',{icon:7,anim:6,time:1900});
                }


            }
            , deliver: function (obj) {
                var checkStatus = table.checkStatus('table')
                    , checkData = checkStatus.data; //得到选中的数据
                if (checkData.length === 0) {
                    return layer.msg('请选择数据');
                }
                function f (value, index, ar) {
                    if (value.orderState === "已交接") {
                        return true;
                    }else {
                        return false;
                    }
                };
                if (checkData.every(f)) {
                    deliverBatch();
                }else{
                    return layer.msg('请勿选择非可投递订单',{icon:7,anim:6,time:1900});
                }


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
                    params += "method=deleteBatch&orderid=" + item.orderId;
                } else {
                    params += "&orderid=" + item.orderId;
                }
            });
            // params += "method=delete&userid="+ids;
            layer.confirm('确定删除所选数据？', function (index) {
                $.post('${pageContext.request.contextPath}/jsp/order.do', params, function (res) {

                    if (res.result === 'true') {

                        table.reload('table');
                        layer.msg("删除成功");
                    } else if (res.result === 'false') {
                        layer.msg("删除失败")
                    }

                });

            });

        }

        //批量交接
        function joinBatch() {
            var params = "method=queryCarList";
            $.post('${pageContext.request.contextPath}/jsp/car.do', params, function (res) {
                $.each(res, function (index, item) {
                    // alert(item.carNo)
                    $('#carList').append(new Option(item.carNo, item.carId));// 下拉菜单里添加元素
                });
                layui.form.render("select");


            });

            layer.open({
                type: 1 //Page层类型
                , area: ['400px', '310px']
                , title: '订单交接'
                ,btn: ['确定', '取消']
                ,skin: 'demo-class'
                , shade: 0.6 //遮罩透明度
                , maxmin: true //允许全屏最小化
                , anim: 5 //0-6的动画形式，-1不开启
                , scrollbar: false
                , content: $("#layer-join")
                ,success:function () {
                    $('#form-join')[0].reset();
                    $("#lo").text("待选择")


                }
                , yes: function (index, d) {
                    if ($('#carList option:selected').text() === "请选择" ) {
                        layer.msg("请选择车辆",{icon: 7,anim:6, time: 1800,})
                    }else if ($('#depart-time').val() === "") {
                        layer.msg("请选择发车时间", {icon: 7, anim: 6, time: 1800,});
                    } else {
                        var checkStatus = table.checkStatus('table');
                        var data = checkStatus.data;
                        var params = "";


                        $.each(data, function (i, item) {
                            if (i === 0) {

                                params += "method=joinBatch&&carno=" + $('#carList option:selected').text() + "&departtime="
                                    + $("#depart-time").val()  + "&flag=0" + "&carid=" + $("#carList option:selected").val() + "&orderid=" + item.orderId;
                            } else {
                                params += "&orderid=" + item.orderId;
                            }
                        });
                        $.post("${pageContext.request.contextPath}/jsp/receipt.do", params, function (res) {

                            if (res.result === "true") {
                                layer.msg("发车成功");
                                table.reload('table');
                            } else {
                                layer.msg("发车失败");
                            }


                        });
                        layer.close(index);

                    }
                }
                ,end:function () {
                    $("#carList").empty();
                    $("#carList").prepend("<option value=\"待选择\">请选择</option>")

                }



            });


        }


        function deliverBatch() {

            layer.confirm('是否投递所选订单?', {
                title: ['信息', 'background:#009688']
                , skin: 'demo-class'
                , btn: ['是', '否']
            }, function (index) {
                var checkStatus = table.checkStatus('table');
                var data = checkStatus.data;
                var params = "";
                $.each(data, function (i, item) {
                    if (i === 0) {
                        params += "method=deliverBatch&flag=1&orderid=" + item.orderId;
                    } else {
                        params += "&orderid=" + item.orderId;
                    }
                });
                $.post("${pageContext.request.contextPath}/jsp/receipt.do", params, function (res) {
                    if (res.result === "true") {
                        layer.msg("投递成功");
                        table.reload('table');
                    } else {
                        layer.msg("投递失败");
                    }
                    layer.close(index);
                });


            });


        }





        //添加订单
        form.on('submit(doSubmit)', function (index) {
            // var params = form.val('dataform');
            var params = $('#dataform').serialize()
            $.post('${pageContext.request.contextPath}/jsp/order.do', params, function (res) {
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
            carlist: function (value) {
                alert(value)
                if ($("#carList option:selected").text() === "请选择") {
                    return "请选择车辆";
                }
            }
            , carNo: [
                /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/
                , "车牌号格式错误"
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


        //查看
        function openDetail(obj) {
            layer.open({
                type: 1 //Page层类型
                , area: ['750px', '620px']
                , title: '订单详细信息'
                , skin: 'demo-class'

                , shade: 0.6 //遮罩透明度
                , maxmin: true //允许全屏最小化
                , anim: 3 //0-6的动画形式，-1不开启
                , scrollbar: false
                , content: $("#layercontent")
                , success: function () {

                    var params = "method=detail&orderid=" + obj,
                        url = "${pageContext.request.contextPath}/jsp/order.do";
                    $.post(url, params, function (res) {
                        $("#orderNo").text(res.orderNo);
                        $("#createTime").text(res.createTime);
                        $("#orderState_").text(res.orderState);
                        $("#sendName").text(res.sendName);
                        $("#sendTel").text(res.sendTel);
                        $("#sendAddress").text(res.sendAddress);
                        $("#receiveName").text(res.receiveName);
                        $("#receiveAddress").text(res.receiveAddress);
                        $("#receiveTel").text(res.receiveTel);
                        $("#volume").text(res.volume);
                        $("#weight").text(res.weight);
                        $("#price").text(res.price);
                        $("#remark").text(res.remark);


                    });
                }
            });
        };


        //搜索
        $("#dosearch").click(function () {

            var url = "${pageContext.request.contextPath}/jsp/order.do";
            table.reload('table', {
                url: url,
                where: {
                    method: "search",

                    'order_no': $("#orderNo_").val(),
                    'o2.name': $('#orderState').val(),
                    'd.name': $('#deliverspot').val(),
                    'startTime': $('#start').val(),
                    'endTime': $('#end').val(),


                }
            })
        });


        form.on('select(carList)', function (data) {
            if (data.value === "待选择") {
                $("#lo").text("待选择");
            } else {
                var params  = "method=queryCarState&carid="+data.value;
                $.post("${pageContext.request.contextPath}/jsp/car.do", params, function (res) {
                    $("#lo").text(res.carState)
                });

            }



        });


        laydate.render({
            elem: '#start'
            , type: 'datetime'
        });

        laydate.render({
            elem: '#end'
            , type: 'datetime'
        });
        laydate.render({
            elem: '#depart-time'
            , type: 'datetime'
        });


        $('.layui-btn.layuiadmin-btn-admin').on('click', function () {

            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
</script>

<style>
    .layui-table-cell .layui-form-checkbox[lay-skin="primary"] {
        top: 50%;
        transform: translateY(-50%);
    }
</style>

</body>
</html>