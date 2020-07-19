package com.guo.dao.car;

import com.guo.dao.BaseDao;
import com.guo.pojo.Car;
import com.guo.pojo.CarVO;
import com.guo.service.line.LineService;
import com.guo.service.line.LineServiceImpl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CarDaoImpl implements CarDao {

    public List<CarVO> query(Connection connection , int start , int rows) throws SQLException {
        PreparedStatement pstm =null;
        ResultSet rs = null;
        ArrayList<CarVO> list = new ArrayList<CarVO>();
        if (connection != null) {
            String sql = "select car_id,\n" +
                    "       car_no,\n" +
                    "       c3.name cartype,\n" +
                    "       car_size,\n" +
                    "       tonnage,\n" +
                    "       buytime,\n" +
                    "       c2.name carstate,\n" +
                    "       l.name\n" +
                    "from car c\n" +
                    "         inner join carstate c2 on c.car_state_id = c2.car_state_id\n" +
                    "         inner join cartype c3 on c.car_type_id = c3.car_type_id\n" +
                    "         inner join line l on c.line_id = l.line_id limit ? ,? ";
            Object[] params = {start,rows};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            while (rs.next()) {
                CarVO carVO = new CarVO();
                carVO.setCarId(rs.getInt("car_id"));
                carVO.setCarNo(rs.getString("car_no"));
                carVO.setCarType(rs.getString("cartype"));
                carVO.setCarSize(rs.getString("car_size"));
                carVO.setTonnage(rs.getInt("tonnage"));
                carVO.setBuyTime(rs.getString("buytime"));
                carVO.setCarState(rs.getString("carstate"));
                carVO.setLine(rs.getString("l.name"));
                list.add(carVO);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return list;
    }

    public int queryCount(Connection connection) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int execute  = 0;
        if (connection != null) {
            String sql = "select count(1) count\n" +
                    "from car c\n" +
                    "         inner join carstate c2 on c.car_state_id = c2.car_state_id\n" +
                    "         inner join cartype c3 on c.car_type_id = c3.car_type_id\n" +
                    "         inner join line l on c.line_id = l.line_id ";
            Object[] params = {};
            rs= BaseDao.execute(connection, pstm, rs,sql, params);
            if (rs.next()) {
                execute = rs.getInt("count");
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return execute;
    }

    public List<CarVO> queryBysearch(Connection connection, int start, int rows, Map<String, String[]> condition) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<CarVO> list = new ArrayList<CarVO>();
        if (connection != null) {
            String sql = "select car_id," +
                    "       car_no," +
                    "       c3.name cartype," +
                    "       car_size," +
                    "       tonnage," +
                    "       c2.name carstate," +
                    "       buytime," +
                    "       l.name " +
                    "from car c" +
                    "         inner join carstate c2 on c.car_state_id = c2.car_state_id " +
                    "         inner join cartype c3 on c.car_type_id = c3.car_type_id " +
                    "         inner join line l on c.line_id = l.line_id" +
                    " where 1 = 1 ";
            ArrayList<Object> arraylist = new ArrayList<Object>();
            StringBuilder sb = new StringBuilder(sql);
            //遍历map
            Set<String> keySet = condition.keySet();

            for (String key : keySet) {
                if ("page".equals(key) || "limit".equals(key) ||"method".equals(key)) {
                    continue;
                }

                String value = condition.get(key)[0];
                if (value != null && !"".equals(value)) {
                    sb.append(" and " + key + " like ? ");
                    arraylist.add("%" + value + "%");
                }
            }
            sb.append(" limit ? , ? ");
            arraylist.add(start);
            arraylist.add(rows);
            Object[] params = arraylist.toArray();

//            Object[] params = {start, rows};
            rs = BaseDao.execute(connection, pstm, rs, sb.toString(), params);

            while (rs.next()) {
                CarVO carVO = new CarVO();
                carVO.setCarId(rs.getInt("car_id"));
                carVO.setCarNo(rs.getString("car_no"));
                carVO.setCarType(rs.getString("cartype"));
                carVO.setCarSize(rs.getString("car_size"));
                carVO.setTonnage(rs.getInt("tonnage"));
                carVO.setBuyTime(rs.getString("buytime"));
                carVO.setCarState(rs.getString("carstate"));
                carVO.setLine(rs.getString("l.name"));
                list.add(carVO);
            }

            BaseDao.closeResource(null, pstm, rs);
        }

        return list;
    }

    public int queryCountBysearch(Connection connection, Map<String, String[]> condition) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int count = 0;
        if (connection != null) {
            String sql = "select count(1) count,\n" +
                    "                    car_id,\n" +
                    "                    car_no,\n" +
                    "                    c3.name cartype,\n" +
                    "                    car_size,\n" +
                    "                    tonnage,\n" +
                    "                    buytime,\n" +
                    "                    c2.name carstate,\n" +
                    "                    l.name\n" +
                    "             from car c\n" +
                    "                      inner join carstate c2 on c.car_state_id = c2.car_state_id\n" +
                    "                      inner join cartype c3 on c.car_type_id = c3.car_type_id\n" +
                    "                      inner join line l on c.line_id = l.line_id\n" +
                    "             where  1= 1 ";
            Object[] params = null;
            ArrayList<Object> list = new ArrayList<Object>();
            StringBuilder sb = new StringBuilder(sql);
            Set<String> keySet = condition.keySet();
            for (String key : keySet) {
                if ("method".equals(key) || "page".equals(key) || "limit".equals(key)) {
                    continue;
                }
                String value = condition.get(key)[0];
                if (value != null && !"".equals(value)) {
                    sb.append(" and " + key + " like ? ");
                    list.add("%" + value + "%");
                }
            }
            params = list.toArray();

            rs = BaseDao.execute(connection, pstm, rs, sb.toString(), params);
            if (rs.next()) {
                count = rs.getInt("count");
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return count;
    }

    public List<CarVO> queryIdByno(Connection connection, String no) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<CarVO> list = null;
        if (connection != null) {
            String sql = "select emp_id from employee where emp_no = ?";
            Object[] params = {no};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            list= new ArrayList<CarVO>();
            while (rs.next()) {
                CarVO empVO = new CarVO();
                empVO.setCarId(rs.getInt("emp_id"));
                list.add(empVO);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return list;
    }

    public List<CarVO> queryByid(Connection connection, int id) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<CarVO> list = new ArrayList<CarVO>();
        if (connection != null) {
            String sql = "select " +
                    "                    car_id,\n" +
                    "                    car_no,\n" +
                    "                    c3.name cartype,\n" +
                    "                    car_size,\n" +
                    "                    tonnage,\n" +
                    "                    buytime,\n" +
                    "                    c2.name carstate,\n" +
                    "                    l.name\n" +
                    "             from car c\n" +
                    "                      inner join carstate c2 on c.car_state_id = c2.car_state_id\n" +
                    "                      inner join cartype c3 on c.car_type_id = c3.car_type_id\n" +
                    "                      inner join line l on c.line_id = l.line_id\n" +
                    "             where  car_id = ? ";
            Object[] params = {id};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            while (rs.next()) {
                CarVO carVO = new CarVO();
                carVO.setCarId(rs.getInt("car_id"));
                carVO.setCarNo(rs.getString("car_no"));
                carVO.setCarType(rs.getString("cartype"));
                carVO.setCarSize(rs.getString("car_size"));
                carVO.setTonnage(rs.getInt("tonnage"));
                carVO.setBuyTime(rs.getString("buytime"));
                carVO.setCarState(rs.getString("carstate"));
                carVO.setLine(rs.getString("l.name"));
                list.add(carVO);

            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return list;
    }

    public List<CarVO> queryCarList(Connection connection) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<CarVO> list = new ArrayList<CarVO>();
        if (connection != null) {
            String sql = "select car_no, c.car_id\n" +
                    "from car c inner join carstate c2 on c.car_state_id = c2.car_state_id\n" +
                    "where c.car_state_id = 1 ";
            Object[] params = {};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            while (rs.next()) {
                CarVO carVO = new CarVO();

                carVO.setCarNo(rs.getString("car_no"));
                carVO.setCarId(rs.getInt("c.car_id"));

                list.add(carVO);

            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return list;
    }

    public String queryCarState(Connection connection, int id) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
       String state  = null;
        if (connection != null) {
            String sql = "select c2.name\n" +
                    "from car c inner join carstate c2 on c.car_state_id = c2.car_state_id\n" +
                    "where c.car_id = ? ";
            Object[] params = {id};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            if (rs.next()) {

                state = rs.getString("c2.name");



            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return state;
    }

    public int updateInfoById(Connection connection, int id, String value, String column) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "";
        Object[] params = {};
        int execute = 0;
        if (connection != null) {
            if (column.equals("carNo")) {
                sql = "update car set car_no = ? where car_id = ?";
                params = new Object[]{value,id};
            } else if (column.equals("carType")) {
                sql = "update car set car_type_id = ? where car_id = ?";
                params = new Object[]{Integer.parseInt(value),id};
            }
            else if (column.equals("carSize")) {
                sql = "update car set car_size = ? where car_id = ?";
                params = new Object[]{Integer.parseInt(value),id};
            }else if (column.equals("tonnage")) {
                sql = "update car set tonnage = ? where car_id = ?";
                params = new Object[]{Integer.parseInt(value),id};
            }else if (column.equals("buyTime")) {
                sql = "update car set buytime = ? where car_id = ?";
                params = new Object[]{value,id};
            }else if (column.equals("carState")) {
                sql = "update car set car_state_id = ? where car_id = ?";
                params = new Object[]{Integer.parseInt(value),id};
            } else if (column.equals("line")) {
                LineService lineService = new LineServiceImpl();
                int line_id = lineService.queryIdByName(value);
                sql = "update car set line_id = ? where car_id = ?";
                params = new Object[]{line_id,id};
            }
            execute = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, rs);

        }
        return execute;
    }



    public int deleteByid(Connection connection, int id) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int execute = 0;
        if (connection != null) {
            String sql = "delete from car where  car_id = ?";
            Object[] params = {id};
            execute = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, rs);
        }
        return execute;
    }

    public int add(Connection connection, Car car) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int execute = 0;
        if (connection != null) {
            String sql = "insert into car\n" +
                    "(car_no, car_type_id, car_size, tonnage, buytime, car_state_id, line_id)\n" +
                    "values (?,?,?,?,?,?,?)";
            Object[] params = {car.getCarNo(), car.getCartypeId(),car.getCarSize(),car.getTonnage(),
                    car.getBuytime(),car.getCarstateId(),car.getLineId()};
            execute = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, rs);
        }
        return execute;
    }
}





