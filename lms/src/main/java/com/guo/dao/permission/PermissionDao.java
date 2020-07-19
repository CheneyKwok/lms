package com.guo.dao.permission;

import com.guo.pojo.DeliverSpotVo;
import com.guo.pojo.PermissionVO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PermissionDao {

    //查询权限id根据权限名
    public List<PermissionVO> queryIdByname(Connection connection, String name) throws SQLException;
}
