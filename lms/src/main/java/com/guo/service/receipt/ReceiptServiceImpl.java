package com.guo.service.receipt;

import com.guo.dao.BaseDao;
import com.guo.dao.receipt.ReceiptDao;
import com.guo.dao.receipt.ReceiptDaoImpl;
import com.guo.pojo.OrderVO;
import com.guo.pojo.Receipt;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ReceiptServiceImpl implements ReceiptService {
    private ReceiptDao receiptDao;
    public ReceiptServiceImpl() {
        receiptDao = new ReceiptDaoImpl();
    }

    public int join(Receipt receipt) {
        Connection connection = null;
        int i = 0;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            i = receiptDao.join(connection, receipt);
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

    public int deliver(Receipt receipt) {
        Connection connection = null;
        int i = 0;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            i = receiptDao.deliver(connection, receipt);
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

    public List<Receipt> queryJoin(int currentPage, int rows) {
        Connection connection = null;
        List<Receipt> list = null;
        int start = 0;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            start = (currentPage - 1) * rows;
            list = receiptDao.queryJoin(connection, start, rows);
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

    public List<Receipt> queryJoinBysearch(int currentPage, int rows, Map<String, String[]> condition) {
        Connection connection = null;
        List<Receipt> list = null;
        int start = 0;
        try {
            start = (currentPage - 1) *rows;
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            list = receiptDao.queryJoinBysearch(connection, start, rows, condition);
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

    public int queryJoinCountBysearch(Map<String, String[]> condition) {
        Connection connection = null;
        int count = 0;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            count = receiptDao.queryJoinCountBysearch(connection,condition);
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

    public List<Receipt> queryDeliver(int currentPage, int rows) {
        Connection connection = null;
        List<Receipt> list = null;
        int start = 0;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            start = (currentPage - 1) * rows;
            list = receiptDao.queryDeliver(connection, start, rows);
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

    public int queryJoinCount() {
        Connection connection = null;
        int i = 0;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            i = receiptDao.queryJoinCount(connection);
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

    public int queryDeliverCount() {
        Connection connection = null;
        int i = 0;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            i = receiptDao.queryDeliverCount(connection);
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

    public int deleteByid(int id) {
        Connection connection = null;
        int i = 0;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            i = receiptDao.deleteByid(connection, id);
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
}
