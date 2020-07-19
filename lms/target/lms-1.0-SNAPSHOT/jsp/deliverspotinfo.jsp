<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>deliverspotinfo</title>
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
                    <label class="layui-form-label" style="width:100px ">站点名称</label>
                    <div class="layui-input-block">
                        <input type="text" id="dName" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>



                <div class="layui-inline" >
                    <label class="layui-form-label" style="width:100px ">省份</label>
                    <div class="layui-input-block">
                        <input type="text" id="pName" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>

                <div class="layui-inline" >
                    <label class="layui-form-label" style="width:100px ">城市</label>
                    <div class="layui-input-block">
                        <input type="text" id="cName" placeholder="请输入" autocomplete="off" class="layui-input">
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



        </div>
    </div>
</div>


<div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="name"aria-hidden="true">
    <div class="modal-dialog" style="width: 600px"  role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">新增配送点</h4>
            </div>
            <div class="modal-body">
                <form class="layui-form " action="" lay-filter="dataform" id="dataform">
                    <input type="hidden" name="method" value="add">

                    <div class="layui-form-item ">
                        <label class="layui-form-label" style="width: 170px;text-align: center">站点名称</label>
                        <div class="layui-input-block">
                            <input style="width: 170px" type="text" name="deliverspotName" lay-verify="required" autocomplete="off" placeholder="请输入" class="layui-input" >
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label" style="width: 170px;text-align: center">站点地址</label>
                        <div class="layui-input-inline" style="width: 120px">
                            <select name="province_id" id="province" lay-filter="province">
                                <option value="">请选择省</option>

                            </select>
                        </div>
                        <div class="layui-input-inline"  style="width: 120px">
                            <select name="city_id" id="city">
                                <option value="">请选择市</option>

                            </select>
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label" style="width: 170px;text-align: center">联系电话</label>
                        <div class="layui-input-block">
                            <input style="width: 150px" type="text" name="tel" lay-verify="required" autocomplete="off" placeholder="请输入" class="layui-input" >

                        </div>
                    </div>


                    <div class="layui-form-item ">
                        <label class="layui-form-label" style="width: 170px;text-align: center">首重范围（Kg）</label>
                        <div class="layui-input-block">
                            <input style="width: 110px" type="text" name="startscope" lay-verify="required|number" autocomplete="off" placeholder="请输入" class="layui-input" >
                        </div>
                    </div>

                    <div class="layui-form-item ">
                        <label class="layui-form-label" style="width: 170px;text-align: center">首重价格（￥）</label>
                        <div class="layui-input-block">
                            <input style="width: 110px" type="text" name="startprice" lay-verify="required|" autocomplete="off" placeholder="请输入" class="layui-input" >
                        </div>
                    </div>

                    <div class="layui-form-item ">
                         <label class="layui-form-label" style="width: 170px;text-align: center">次重价格系数（￥）</label>
                         <div class="layui-input-block">
                         <input style="width: 110px" type="text" name="secondprice" lay-verify="required|" autocomplete="off" placeholder="请输入" class="layui-input" >
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
                <label class="col-sm-4 control-label" >车辆编号：</label>

                <div class="form-control-static" id="carNo_"></div>

            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label">车辆类型：</label>
                <div class="form-control-static" id="carType_"></div>
            </div>

            <div class="form-group">
                <label class="col-sm-4 control-label" >容积（m3）：</label>
                <div class="form-control-static" id="carSize_"></div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label" >吨位（t）：</label>
                <div class="form-control-static" id="tonnage_"></div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label" >购买时间：</label>
                <div class="form-control-static" id="buyTime_"></div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label" >联系号码：</label>
                <div class="form-control-static" id="tel"></div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label" >车辆状态：</label>
                <div class="form-control-static" id="carState_"></div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label" >运营线路：</label>
                <div class="form-control-static" id="line_"></div>
            </div>


        </form>
    </div>
</div>




<script type="text/html" id="bar">
    <a class="layui-btn layui-btn-blue layui-btn-xs" lay-event="detail">修改</a>
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
            , url: '${pageContext.request.contextPath}/jsp/deliverspot.do'
            , title: '配送点信息'
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
                , {field: 'deliverspotName', title: '站点名称',  sort: true, edit: true, align:'center'}
                , {field: 'address', title: '所在地址',  edit: true, align:'center'}
                , {field: 'tel', title: '联系电话', edit: true, align:'center'}

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
                openDetail(data.deliverspotId);
            } else if (layEvent === 'delete') {

                layer.prompt({
                    formType: 1
                    , title: '敏感操作，请验证口令'
                }, function (value, index) {

                    if (value === '123') {
                        layer.close(index);
                        layer.confirm('真的删除这条数据么', function (index) {
                            var url = "${pageContext.request.contextPath}/jsp/deliverspot.do";
                            var params = "method=delete&deliverspotid=" + data.deliverspotId
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

        //监听单元格事件
        table.on('edit(Table)', function (obj) { //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
            console.log(obj.value); //得到修改后的值
            console.log(obj.field); //当前编辑的字段名
            console.log(obj.data); //所在行的所有相关数据

            if (obj.field ==="carNo") {
                var vg = /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/;
                if (!vg.test(obj.value)) {
                    layer.msg("格式输入错误");
                } else {
                    var url = "${pageContext.request.contextPath}/jsp/car.do",
                        params = "method=update&column=carNo&carid=" + obj.data.carId +"&value=" +obj.value;
                    $.post(url, params, function (res) {
                        if (res.result === "true") {
                            layer.msg("修改成功");
                        } else {
                            layer.msg("修改失败");
                        }
                    });

                }

            }

            if (obj.field ==="carSize") {
                var url = "${pageContext.request.contextPath}/jsp/car.do",
                    params = "method=update&column=carSize&carid=" + obj.data.carId +"&value=" +obj.value;
                $.post(url, params, function (res) {
                    if (res.result === "true") {
                        layer.msg("修改成功");
                    } else {
                        layer.msg("修改失败");
                    }
                });
            }

            if (obj.field ==="line") {
                var url = "${pageContext.request.contextPath}/jsp/car.do",
                    data = "method=checkLine&value=" + obj.value;
                $.post(url, data, function (res) {
                    if (res.result === "true") {
                        var params = "method=update&column=line&carid=" + obj.data.carId + "&value=" + obj.value;
                        $.post(url, params, function (res) {
                            if (res.result === "true") {
                                layer.msg("修改成功");
                            } else {
                                layer.msg("修改失败");
                            }
                        });
                    } else {
                        layer.msg("没有该线路");
                    }

                });

            }

            if (obj.field ==="carType") {
                var carTypeId = null;

                if (obj.value === "大型") {
                    carTypeId = 1 ;
                    var url = "${pageContext.request.contextPath}/jsp/car.do",
                        params = "method=update&column=carType&carid=" + obj.data.carId +"&value=" +carTypeId;
                    $.post(url, params, function (res) {
                        if (res.result === "true") {
                            layer.msg("修改成功");
                        } else {
                            layer.msg("修改失败");
                        }
                    });
                }
                else if (obj.value === "皮卡") {
                    carTypeId = 2 ;
                    var url = "${pageContext.request.contextPath}/jsp/car.do",
                        params = "method=update&column=carType&carid=" + obj.data.carId +"&value=" +carTypeId;
                    $.post(url, params, function (res) {
                        if (res.result === "true") {
                            layer.msg("修改成功");
                        } else {
                            layer.msg("修改失败");
                        }
                    });
                }
                else if (obj.value === "小型") {
                    carTypeId = 3;
                    var url = "${pageContext.request.contextPath}/jsp/car.do",
                        params = "method=update&column=carType&carid=" + obj.data.carId +"&value=" +carTypeId;
                    $.post(url, params, function (res) {
                        if (res.result === "true") {
                            layer.msg("修改成功");
                        } else {
                            layer.msg("修改失败");
                        }
                    });
                } else {
                    layer.msg("输入类型错误")
                    table.reload('table');

                }

            }

            if (obj.field ==="tonnage") {
                var url = "${pageContext.request.contextPath}/jsp/car.do",
                    params = "method=update&column=tonnage&carid=" + obj.data.carId +"&value=" +obj.value;
                $.post(url, params, function (res) {
                    if (res.result === "true") {
                        layer.msg("修改成功");
                    } else {
                        layer.msg("修改失败");
                    }
                });
            }

            if (obj.field ==="buyTime") {
                var url = "${pageContext.request.contextPath}/jsp/car.do",
                    params = "method=update&column=buyTime&carid=" + obj.data.carId +"&value=" +obj.value;
                $.post(url, params, function (res) {
                    if (res.result === "true") {
                        layer.msg("修改成功");
                    } else {
                        layer.msg("修改失败");
                    }
                });
            }

            if (obj.field ==="carState") {
                var carStateId = null;

                if (obj.value === "空闲") {
                    carStateId = 1 ;
                    var url = "${pageContext.request.contextPath}/jsp/car.do",
                        params = "method=update&column=carState&carid=" + obj.data.carId +"&value=" +carStateId;
                    $.post(url, params, function (res) {
                        if (res.result === "true") {
                            layer.msg("修改成功");
                        } else {
                            layer.msg("修改失败");
                        }
                    });
                }
                else if (obj.value === "维修") {
                    carStateId = 2 ;
                    var url = "${pageContext.request.contextPath}/jsp/car.do",
                        params = "method=update&column=carState&carid=" + obj.data.carId +"&value=" +carStateId;
                    $.post(url, params, function (res) {
                        if (res.result === "true") {
                            layer.msg("修改成功");
                        } else {
                            layer.msg("修改失败");
                        }
                    });
                }
                else if (obj.value === "途中") {
                    carStateId = 3;
                    var url = "${pageContext.request.contextPath}/jsp/car.do",
                        params = "method=update&column=carState&carid=" + obj.data.carId +"&value=" +carStateId;
                    $.post(url, params, function (res) {
                        if (res.result === "true") {
                            layer.msg("修改成功");
                        } else {
                            layer.msg("修改失败");
                        }
                    });
                } else {
                    layer.msg("输入类型错误")
                    table.reload('table');

                }

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
                var params = "method=queryProvince";
                $.post('${pageContext.request.contextPath}/jsp/deliverspot.do', params, function (res) {
                    $.each(res, function (index, item) {
                        // alert(item.carNo)
                        $('#province').append(new Option(item.provinceName, item.provinceId));// 下拉菜单里添加元素

                    });
                    layui.form.render("select");


                });



                $('#add').modal('show');

            }
        }

        form.on("select(province)", function (data) {
            $("#city").empty();
            $("#city").prepend("<option value=\"\">请选择市</option>")
            var params  = "method=queryCity&pid=" + data.value;
            $.post("${pageContext.request.contextPath}/jsp/deliverspot.do", params, function (res) {
                $.each(res, function (index, item) {

                    $('#city').append(new Option(item.cityName, item.cityId));// 下拉菜单里添加元素

                });
                layui.form.render("select");


            });

        });


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
                    params += "method=deleteBatch&deliverspotid=" + item.deliverspotId;
                } else {
                    params += "&deliverspotid=" + item.deliverspotId;
                }
                // layer.msg(params);
            });
            // params += "method=delete&userid="+ids;
            layer.confirm('确定删除所选数据？', function (index) {
                $.post('${pageContext.request.contextPath}/jsp/deliverspot.do', params, function (res) {

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
            $.post('${pageContext.request.contextPath}/jsp/deliverspot.do', params, function (res) {
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
                , area: ['450px', '490px']
                , title: '车辆详细信息'
                , shade: 0.6 //遮罩透明度
                , maxmin: true //允许全屏最小化
                , anim: 3 //0-6的动画形式，-1不开启
                , scrollbar: false
                , content: $("#layercontent")
                , success: function () {

                    var params = "method=detail&carid=" + obj,
                        url = "${pageContext.request.contextPath}/jsp/car.do";
                    $.post(url, params, function (res) {
                        $("#carNo_").text(res.carNo);
                        $("#carType_").text(res.carType);
                        $("#carSize_").text(res.carSize);
                        $("#tonnage_").text(res.tonnage);
                        $("#buyTime_").text(res.buyTime);
                        $("#carState_").text(res.carState);
                        $("#line_").text(res.line);


                    });
                }
            });
        };


        //搜索
        $("#dosearch").click(function () {

            var url = "${pageContext.request.contextPath}/jsp/deliverspot.do";
            table.reload('table',{
                url:url,
                where: {
                    method:"search",

                    'd.name':$("#dName").val(),
                    'p.name':$("#pName").val(),
                    'c.name':$("#cName").val(),



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