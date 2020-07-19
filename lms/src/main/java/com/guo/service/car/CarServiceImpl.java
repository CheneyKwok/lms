package com.guo.service.car;

import com.guo.dao.BaseDao;

import com.guo.dao.car.CarDao;
import com.guo.dao.car.CarDaoImpl;
import com.guo.pojo.Car;
import com.guo.pojo.CarVO;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class CarServiceImpl implements CarService {

    private CarDao carDao;
    public CarServiceImpl(){
        carDao = new CarDaoImpl();
    }


    public List<CarVO> query(int currentPage, int rows) {
        Connection connection = null;
        List<CarVO> list = null;
        int start = 0;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            start = (currentPage - 1) * rows;
            list = carDao.query(connection, start, rows);
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
            i = carDao.queryCount(connection);
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

    public List<CarVO> queryBysearch(int currentPage, int rows, Map<String, String[]> condition) {
        Connection connection = null;
        List<CarVO> list = null;
        int start = 0;
        try {
            start = (currentPage - 1) *rows;
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
             list = carDao.queryBysearch(connection, start, rows, condition);
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
            count = carDao.queryCountBysearch(connection,condition);
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

    public List<CarVO> queryIdByno(String no) {
        Connection connection = null;
        List<CarVO> list = null;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            list = carDao.queryIdByno(connection, no);
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

    public List<CarVO> queryByid(int id) {
        Connection connection = null;
        List<CarVO> list = null;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            list = carDao.queryByid(connection, id);
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

    public List<CarVO> queryCarList(){
        Connection connection = null;
        List<CarVO> list = null;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            list = carDao.queryCarList(connection);
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

    public String queryCarState(int id) {
        Connection connection = null;
        String state = null;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            state = carDao.queryCarState(connection, id);
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

        return state;
    }


    public int updateInfoById(int id, String value, String column)  {
        Connection connection = null;
        int i = 0;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            i = carDao.updateInfoById(connection, id,value,column);
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
            i = carDao.deleteByid(connection, id);
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

    public int add(Car car) {
        Connection connection = null;
        int i = 0;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            i = carDao.add(connection, car);
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

        int i = updateInfoById(1, "8888", "carNo");
        System.out.println(i);
    }
}
