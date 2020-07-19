package com.guo.service.emp;

import com.guo.dao.BaseDao;
import com.guo.dao.emp.EmpDao;
import com.guo.dao.emp.EmpDaoImpl;
import com.guo.pojo.EmpVO;
import com.guo.pojo.Employee;
import com.sun.jmx.snmp.SnmpNull;
import com.sun.org.apache.xalan.internal.xsltc.trax.XSLTCSource;
import org.junit.Test;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmpServiceImpl implements EmpService {

    private EmpDao empDao;
    public EmpServiceImpl(){
        empDao = new EmpDaoImpl();
    }


    public List<EmpVO> query(int currentPage,int rows) {
        Connection connection = null;
        List<EmpVO> list = null;
        int start = 0;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            start = (currentPage - 1) * rows;
            list = empDao.query(connection, start, rows);
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
            i = empDao.queryCount(connection);
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

    public List<EmpVO> queryBysearch(int currentPage, int rows, Map<String, String[]> condition) {
        Connection connection = null;
        List<EmpVO> list = null;
        int start = 0;
        try {
            start = (currentPage - 1) *rows;
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
             list = empDao.queryBysearch(connection, start, rows, condition);
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
            count = empDao.queryCountBysearch(connection,condition);
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

    public List<EmpVO> queryIdByno(String no) {
        Connection connection = null;
        List<EmpVO> list = null;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            list = empDao.queryIdByno(connection, no);
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

    public List<EmpVO> queryByid(int id) {
        Connection connection = null;
        List<EmpVO> list = null;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            list = empDao.queryByid(connection, id);
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

    public int updateNameByid(int id, String no) {
        Connection connection = null;
        int i = 0;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
             i = empDao.updateNameByid(connection, id, no);
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

    public int updateInfoById(int id, String value, String column) {
        Connection connection = null;
        int i = 0;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            i = empDao.updateInfoById(connection, id,value,column);
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
            i = empDao.deleteByid(connection, id);
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

    public int add(Employee emp) {
        Connection connection = null;
        int i = 0;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            i = empDao.add(connection, emp);
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

        List<EmpVO> list = queryByid(1000);
        System.out.println(list.get(0).getDeliverspot());
    }
}
