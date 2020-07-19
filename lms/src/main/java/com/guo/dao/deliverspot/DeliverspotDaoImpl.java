package com.guo.dao.deliverspot;

import com.guo.dao.BaseDao;
import com.guo.pojo.*;

import com.guo.service.deliverspot.DeliverspotService;
import com.guo.service.deliverspot.DeliverspotServiceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DeliverspotDaoImpl implements DeliverspotDao {
    public int updateNameByid(Connection connection, int id, String name) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int i = 0;
        if (connection != null) {
            String sql = "update deliverspot set name  =  ? where deliverspot_id = ?";
            Object[] params = {name, id};
            i = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, rs);
        }
        return i;
    }

    public List<DeliverSpotVo> queryIdByname(Connection connection, String name) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<DeliverSpotVo> list = null;
        if (connection != null) {
            String sql = "select deliverspot_id from deliverspot where name = ?";
            Object[] params = {name};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            list = new ArrayList<DeliverSpotVo>();
            while (rs.next()) {
                DeliverSpotVo deliverSpotVo = new DeliverSpotVo();
                deliverSpotVo.setDeliverspotId(rs.getInt("deliverspot_id"));
                list.add(deliverSpotVo);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return list;

    }

    public List<DeliverSpotVo> queryParams(Connection connection, int id) throws SQLException {
        PreparedStatement pdtm = null;
        ResultSet rs = null;
        ArrayList<DeliverSpotVo> list = new ArrayList<DeliverSpotVo>();
        if (connection != null) {
            String sql = "select startscope,\n" +
                    "       startprice,\n" +
                    "       secondprice\n" +
                    "from deliverspot\n" +
                    "where deliverspot_id = ?;";
            Object[] params = {id};
            rs = BaseDao.execute(connection, pdtm, rs, sql, params);
            while (rs.next()) {
                DeliverSpotVo d = new DeliverSpotVo();
                d.setStartscope(rs.getString("startscope"));
                d.setStartprice(rs.getString("startprice"));
                d.setSceondprice(rs.getString("secondprice"));
                list.add(d);
            }
            BaseDao.closeResource(null, pdtm, rs);
        }
        return list;
    }

    public List<DeliverSpotVo> query(Connection connection , int start , int rows) throws SQLException {
        PreparedStatement pstm =null;
        ResultSet rs = null;
        ArrayList<DeliverSpotVo> list = new ArrayList<DeliverSpotVo>();
        if (connection != null) {
            String sql = "select deliverspot_id,\n" +
                    "       d.name,\n" +
                    "       p.name,\n" +
                    "       c.name,\n" +
                    "       tel\n" +
                    "from deliverspot d\n" +
                    "         inner join province p on d.province_id = p.province_id\n" +
                    "         inner join city c on d.city_id = c.city_id  limit ?,? ";
            Object[] params = {start,rows};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            while (rs.next()) {
                DeliverSpotVo d = new DeliverSpotVo();
                d.setDeliverspotId(rs.getInt("deliverspot_id"));
                d.setDeliverspotName(rs.getString("d.name"));
                d.setAddress(rs.getString("p.name") + "-" + rs.getString("c.name"));
                d.setTel(rs.getString("tel"));
                list.add(d);
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
            String sql = "select count(1) count from deliverspot d\n" +
                    "         inner join province p on d.province_id = p.province_id\n" +
                    "         inner join city c on d.city_id = c.city_id";
            Object[] params = {};
            rs= BaseDao.execute(connection, pstm, rs,sql, params);
            if (rs.next()) {
                execute = rs.getInt("count");
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return execute;
    }

    public List<DeliverSpotVo> queryBysearch(Connection connection, int start, int rows, Map<String, String[]> condition) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<DeliverSpotVo> list = new ArrayList<DeliverSpotVo>();
        if (connection != null) {
            String sql = "select deliverspot_id,\n" +
                    "       d.name,\n" +
                    "       p.name,\n" +
                    "       c.name,\n" +
                    "       tel\n" +
                    "from deliverspot d\n" +
                    "         inner join province p on d.province_id = p.province_id\n" +
                    "         inner join city c on d.city_id = c.city_id where 1 = 1";
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
            sb.append(" limit ?,? ");
            arraylist.add(start);
            arraylist.add(rows);
            Object[] params = arraylist.toArray();

//            Object[] params = {start, rows};
            rs = BaseDao.execute(connection, pstm, rs, sb.toString(), params);

            while (rs.next()) {
                DeliverSpotVo d = new DeliverSpotVo();
                d.setDeliverspotId(rs.getInt("deliverspot_id"));
                d.setDeliverspotName(rs.getString("d.name"));
                d.setAddress(rs.getString("p.name") + "-" + rs.getString("c.name"));
                d.setTel(rs.getString("tel"));
                list.add(d);

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
            String sql = "select count(1) count ,deliverspot_id,\n" +
                    "       d.name,\n" +
                    "       p.name,\n" +
                    "       c.name,\n" +
                    "       tel\n" +
                    "from deliverspot d\n" +
                    "         inner join province p on d.province_id = p.province_id\n" +
                    "         inner join city c on d.city_id = c.city_id where 1 = 1 ";
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

    public List<Province> queryProvince(Connection connection) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Province> list = null;
        if (connection != null) {
            String sql = "select province_id, name from province;";
            Object[] params = {};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            list = new ArrayList<Province>();
            while (rs.next()) {
                Province province = new Province();
                province.setProvinceName(rs.getString("name"));
                province.setProvinceId(rs.getInt("province_id"));
                list.add(province);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return list;
    }

    public List<City> queryCity(Connection connection, int id) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<City> list = null;
        if (connection != null) {
            String sql = "select city_id, name from city where province_id = ? ;";
            Object[] params = {id};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            list = new ArrayList<City>();
            while (rs.next()) {
                City city = new City();
                city.setCityName(rs.getString("name"));
                city.setCityId(rs.getInt("city_id"));
                list.add(city);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return list;
    }


    public int updateInfoById(Connection connection, int id, String value, String column) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "";
        Object[] params = {};
        int execute = 0;
        if (connection != null) {
            if (column.equals("empNo")) {
                sql = "update employee set emp_no = ? where emp_id = ?";
                params = new Object[]{value,id};
            } else if (column.equals("empName")) {
                sql = "update employee set name = ? where emp_id = ?";
                params = new Object[]{value,id};
            }
            else if (column.equals("cardid")) {
                sql = "update employee set cardid = ? where emp_id = ?";
                params = new Object[]{value,id};
            }
            else if (column.equals("gender")) {
                sql = "update employee set gender = ? where emp_id = ?";
                params = new Object[]{Integer.parseInt(value),id};
            }
            else if (column.equals("age")) {
                sql = "update employee set age = ? where emp_id = ?";
                params = new Object[]{Integer.parseInt(value),id};
            }
            else if (column.equals("tel")) {
                sql = "update employee set tel = ? where emp_id = ?";
                params = new Object[]{value,id};
            }
            else if (column.equals("startworktime")) {
                sql = "update employee set startworktime = ? where emp_id = ?";
                params = new Object[]{value,id};
            }
            else if (column.equals("salary")) {
                sql = "update employee set salary = ? where emp_id = ?";
                params = new Object[]{Integer.parseInt(value),id};
            }
            else if (column.equals("email")) {
                sql = "update employee set email = ? where emp_id = ?";
                params = new Object[]{value,id};
            }
            else if (column.equals("position")) {
                sql = "update employee set position_id = ? where emp_id = ?";
                params = new Object[]{Integer.parseInt(value),id};
            }
            else if (column.equals("state")) {
                sql = "update employee set flag = ? where emp_id = ?";
                params = new Object[]{Integer.parseInt(value),id};
            }
            else if (column.equals("deliverspot")) {
                DeliverspotService deliverspotService = new DeliverspotServiceImpl();
                List<DeliverSpotVo> list = deliverspotService.queryIdByname(value);
                int d_id = list.get(0).getDeliverspotId();
                sql = "update employee set deliverspot_id = ? where emp_id = ?";
                params = new Object[]{d_id,id};
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
            String sql = "delete from deliverspot where  deliverspot_id = ?";
            Object[] params = {id};
            execute = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, rs);
        }
        return execute;
    }

    public int add(Connection connection, DeliverSpot d) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int execute = 0;
        if (connection != null) {
            String sql = "insert into deliverspot\n" +
                    "(name, province_id, city_id, tel, startscope, startprice, secondprice)" +
                    "values (?,?,?,?,?,?,?)";
            Object[] params = {d.getName(),d.getProvinceId(),d.getCityId(),d.getTel(),d.getStartscope(),d.getStartprice(),d.getSceondprice()};
            execute = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, rs);
        }
        return execute;
    }
}




