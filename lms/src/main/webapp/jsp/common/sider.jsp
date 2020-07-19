<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>

<div class="mdui-drawer mdui-drawer-open" id="main-drawer">
    <div class="mdui-list" mdui-collapse="{accordion: true}" style="margin-bottom: 76px;">
        <%--用户管理--%>
        <div class="mdui-collapse-item ">
            <div class="mdui-collapse-item-header mdui-list-item mdui-ripple">
                <i class="mdui-list-item-icon mdui-icon material-icons mdui-text-color-blue">near_me</i>
                <div class="mdui-list-item-content">用户管理</div>
                <i class="mdui-collapse-item-arrow mdui-icon material-icons">keyboard_arrow_down</i>
            </div>
            <div class="mdui-collapse-item-body mdui-list">
                <a href="userlist.jsp" target="" class="mdui-list-item mdui-ripple ">用户信息</a>
                <a href="test2.jsp" target="" class="mdui-list-item mdui-ripple ">添加用户</a>
            </div>
        </div>
        <%--员工管理--%>
        <div class="mdui-collapse-item ">
            <div class="mdui-collapse-item-header mdui-list-item mdui-ripple">
                <i class="mdui-list-item-icon mdui-icon material-icons mdui-text-color-deep-orange">layers</i>
                <div class="mdui-list-item-content">员工管理</div>
                <i class="mdui-collapse-item-arrow mdui-icon material-icons">keyboard_arrow_down</i>
            </div>
            <div class="mdui-collapse-item-body mdui-list">

                <a href="lay.jsp" class="mdui-list-item mdui-ripple ">员工信息</a>
                <a href="./grid" class="mdui-list-item mdui-ripple ">新员工入职</a>
            </div>
        </div>
        <%--车辆管理--%>
        <div class="mdui-collapse-item ">
            <div class="mdui-collapse-item-header mdui-list-item mdui-ripple">
                <i class="mdui-list-item-icon mdui-icon material-icons mdui-text-color-green">widgets</i>
                <div class="mdui-list-item-content">车辆管理</div>
                <i class="mdui-collapse-item-arrow mdui-icon material-icons">keyboard_arrow_down</i>
            </div>
            <div class="mdui-collapse-item-body mdui-list">
                <a href="./ripple" class="mdui-list-item mdui-ripple ">车辆信息</a>
                <a href="./button" class="mdui-list-item mdui-ripple ">新购车辆登记</a>
            </div>
        </div>
        <%--订单管理--%>
        <div class="mdui-collapse-item ">
            <div class="mdui-collapse-item-header mdui-list-item mdui-ripple">
                <i class="mdui-list-item-icon mdui-icon material-icons mdui-text-color-brown">view_carousel</i>
                <div class="mdui-list-item-content">订单管理</div>
                <i class="mdui-collapse-item-arrow mdui-icon material-icons">keyboard_arrow_down</i>
            </div>
            <div class="mdui-collapse-item-body mdui-list">
                <a href="./collapse" class="mdui-list-item mdui-ripple ">添加订单</a>
                <a href="./headroom" class="mdui-list-item mdui-ripple ">查询订单</a>
                <a href="./headroom" class="mdui-list-item mdui-ripple ">订单签收</a>
                <a href="./headroom" class="mdui-list-item mdui-ripple ">货物交接</a>
                <a href="./headroom" class="mdui-list-item mdui-ripple ">查询交接单</a>
            </div>
        </div>
        <%--线路管理--%>
        <div class="mdui-collapse-item ">
            <div class="mdui-collapse-item-header mdui-list-item mdui-ripple">
                <i class="mdui-list-item-icon mdui-icon material-icons mdui-text-color-purple">local_mall</i>
                <div class="mdui-list-item-content">线路管理</div>
                <i class="mdui-collapse-item-arrow mdui-icon material-icons">keyboard_arrow_down</i>
            </div>
            <div class="mdui-collapse-item-body mdui-list">
                <a href="./material_icon" class="mdui-list-item mdui-ripple ">线路信息</a>
                <a href="./material_icon" class="mdui-list-item mdui-ripple ">新增线路</a>
                <a href="./material_icon" class="mdui-list-item mdui-ripple ">线路配车</a>
            </div>
        </div>
        <%--配送点管理--%>
        <div class="mdui-collapse-item ">
            <div class="mdui-collapse-item-header mdui-list-item mdui-ripple">
                <i class="mdui-list-item-icon mdui-icon material-icons mdui-text-color-purple">local_mall</i>
                <div class="mdui-list-item-content">配送点管理</div>
                <i class="mdui-collapse-item-arrow mdui-icon material-icons">keyboard_arrow_down</i>
            </div>
            <div class="mdui-collapse-item-body mdui-list">
                <a href="./material_icon" class="mdui-list-item mdui-ripple ">配送点信息</a>
                <a href="./material_icon" class="mdui-list-item mdui-ripple ">新增配送点</a>
            </div>
        </div>
        <%--报表管理--%>
        <div class="mdui-collapse-item ">
            <div class="mdui-collapse-item-header mdui-list-item mdui-ripple">
                <i class="mdui-list-item-icon mdui-icon material-icons mdui-text-color-purple">local_mall</i>
                <div class="mdui-list-item-content">报表管理</div>
                <i class="mdui-collapse-item-arrow mdui-icon material-icons">keyboard_arrow_down</i>
            </div>
            <div class="mdui-collapse-item-body mdui-list">
                <a href="./material_icon" class="mdui-list-item mdui-ripple ">查看报表</a>
            </div>
        </div>
        <%--系统管理--%>
        <div class="mdui-collapse-item ">
            <div class="mdui-collapse-item-header mdui-list-item mdui-ripple">
                <i class="mdui-list-item-icon mdui-icon material-icons mdui-text-color-purple">local_mall</i>
                <div class="mdui-list-item-content">系统管理</div>
                <i class="mdui-collapse-item-arrow mdui-icon material-icons">keyboard_arrow_down</i>
            </div>
            <div class="mdui-collapse-item-body mdui-list">
                <a href="pwdmodify.jsp" target="main" class="mdui-list-item mdui-ripple ">密码修改 </a>
            </div>
        </div>


    </div>
</div>

</html>
