package com.guo.dao.permission;

import com.guo.dao.BaseDao;
import com.guo.pojo.DeliverSpotVo;
import com.guo.pojo.PermissionVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PermissionDaoImpl implements PermissionDao {

    public List<PermissionVO> queryIdByname(Connection connection, String name) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<PermissionVO> list = null;
        if (connection != null) {
            String sql = "select permission_id from permission where name = ?";
            Object[] params = {name};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            list = new ArrayList<PermissionVO>();
            while (rs.next()) {
                PermissionVO permissionVO = new PermissionVO();
                permissionVO.setPermissionId(rs.getInt("permission_id"));
                list.add(permissionVO);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return list;
    }
}
