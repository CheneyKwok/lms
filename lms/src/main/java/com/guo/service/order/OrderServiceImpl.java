package com.guo.service.order;

import com.guo.dao.BaseDao;

import com.guo.dao.order.OrderDao;
import com.guo.dao.order.OrderDaoImpl;

import com.guo.pojo.Order;
import com.guo.pojo.OrderVO;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

   private OrderDao orderDao;
   public OrderServiceImpl() {
        orderDao = new OrderDaoImpl();
   }


    public List<OrderVO> query(int currentPage, int rows) {
        Connection connection = null;
        List<OrderVO> list = null;
        int start = 0;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            start = (currentPage - 1) * rows;
            list = orderDao.query(connection, start, rows);
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
            i = orderDao.queryCount(connection);
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

    public List<OrderVO> queryBysearch(int currentPage, int rows, Map<String, String[]> condition) {
        Connection connection = null;
        List<OrderVO> list = null;
        int start = 0;
        try {
            start = (currentPage - 1) *rows;
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
             list = orderDao.queryBysearch(connection, start, rows, condition);
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
            count = orderDao.queryCountBysearch(connection,condition);
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

    public List<OrderVO> queryIdByno(String no) {
        Connection connection = null;
        List<OrderVO> list = null;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            list = orderDao.queryIdByno(connection, no);
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

    public List<OrderVO> queryByid(int id) {
        Connection connection = null;
        List<OrderVO> list = null;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            list = orderDao.queryByid(connection, id);
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

        return list;
    }



    public int updateInfoById(int id, String value, String column)  {
        Connection connection = null;
        int i = 0;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            i = orderDao.updateInfoById(connection, id,value,column);
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
            i = orderDao.deleteByid(connection, id);
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

    public int add(Order order) {
        Connection connection = null;
        int i = 0;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            i = orderDao.add(connection, order);
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
    public void test() {

    }
}
