package com.guo.service.permission;

import com.guo.dao.BaseDao;
import com.guo.dao.permission.PermissionDao;
import com.guo.dao.permission.PermissionDaoImpl;
import com.guo.pojo.DeliverSpotVo;
import com.guo.pojo.PermissionVO;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PermissionServiceImpl implements PermissionService {
    private PermissionDao permissionDao;
    public PermissionServiceImpl(){
        permissionDao = new PermissionDaoImpl();
    }

    public List<PermissionVO> queryIdByname(String name) {
        Connection connection = null;
        List<PermissionVO> list = null;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            list = permissionDao.queryIdByname(connection, name);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                System.out.println("rollback====");
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection, null, null);
        }

        return list;
    }

    @Test
    public void t() {
        String name = "配送点管理员";
        List<PermissionVO> list = queryIdByname(name);
        System.out.println(list.get(0).getPermissionId());
    }
}
