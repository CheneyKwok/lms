package com.guo.service.deliverspot;


import com.guo.dao.BaseDao;
import com.guo.dao.deliverspot.DeliverspotDao;
import com.guo.dao.deliverspot.DeliverspotDaoImpl;
import com.guo.pojo.*;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DeliverspotServiceImpl implements DeliverspotService {

    private DeliverspotDao deliverspotDao;
    public DeliverspotServiceImpl(){
       deliverspotDao = new DeliverspotDaoImpl();
    }


    public int updateNameByid(int id, String name) {
        Connection connection = null;
        int i = 0;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            i = deliverspotDao.updateNameByid(connection, id, name);
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
        return i;
    }

    public List<DeliverSpotVo> queryIdByname(String name) {
        Connection connection = null;
        List<DeliverSpotVo> list = null;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            list = deliverspotDao.queryIdByname(connection, name);
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

    public List<DeliverSpotVo> queryParams(int id) {
        Connection connection = null;
        List<DeliverSpotVo> list = null;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            list = deliverspotDao.queryParams(connection, id);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                System.out.println("D-queryParams==rollback");
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection, null, null);
        }
        return list;
    }

    public List<DeliverSpotVo> query(int currentPage,int rows) {
        Connection connection = null;
        List<DeliverSpotVo> list = null;
        int start = 0;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            start = (currentPage - 1) * rows;
            list = deliverspotDao.query(connection, start, rows);
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

    public int queryCount() {
        Connection connection = null;
        int i = 0;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            i = deliverspotDao.queryCount(connection);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                System.out.println("rollback");
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection, null, null);
        }

        return i;

    }

    public List<DeliverSpotVo> queryBysearch(int currentPage, int rows, Map<String, String[]> condition) {
        Connection connection = null;
        List<DeliverSpotVo> list = null;
        int start = 0;
        try {
            start = (currentPage - 1) *rows;
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            list = deliverspotDao.queryBysearch(connection, start, rows, condition);
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

    public int queryCountBysearch(Map<String, String[]> condition) {
        Connection connection = null;
        int count = 0;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            count = deliverspotDao.queryCountBysearch(connection,condition);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                System.out.println("rollback============");
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection, null, null);
        }
        return count;
    }

    public List<Province> queryProvince() {
        Connection connection = null;
        List<Province> list = null;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            list = deliverspotDao.queryProvince(connection);
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

    public List<City> queryCity(int id) {
        Connection connection = null;
        List<City> list = null;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            list = deliverspotDao.queryCity(connection,id);
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


    public int updateInfoById(int id, String value, String column) {
        Connection connection = null;
        int i = 0;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            i = deliverspotDao.updateInfoById(connection, id,value,column);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                System.out.println("update==rollback====");
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection, null, null);
        }
        return i;

    }

    public int deleteByid(int id) {
        Connection connection = null;
        int i = 0;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            i = deliverspotDao.deleteByid(connection, id);
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
        return i;
    }

    public int add(DeliverSpot d) {
        Connection connection = null;
        int i = 0;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            i = deliverspotDao.add(connection, d);
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

        return i;
    }
    @Test
    public void t() {
        String name = "上海";
        List<DeliverSpotVo> list = queryIdByname(name);
        System.out.println(list.get(0).getDeliverspotId());
    }

}
