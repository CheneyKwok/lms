package com.guo.dao.line;

import com.guo.dao.BaseDao;


import com.guo.pojo.Line;
import com.guo.util.BaseMethod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LineDaoImpl implements LineDao {


    public int queryIdByName(Connection connection, String name) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs =null;
        int id = 0;
        if (connection != null) {
            String sql = "select line_id from line where name = ? ";
            Object[] params = {name};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            if (rs.next()) {
                id = rs.getInt("line_id");
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return id;
    }

    public List<Line> query(Connection connection , int start , int rows) throws SQLException {
        PreparedStatement pstm =null;
        ResultSet rs = null;
        ArrayList<Line> list = new ArrayList<Line>();
        if (connection != null) {
            String sql = "select *\n" +
                    "from line limit ?,? ; ";
            Object[] params = {start,rows};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            while (rs.next()) {
                Line line = new Line();
                line.setLineId(rs.getInt("line_id"));
                line.setLineNo(rs.getString("line_no"));
                line.setName(rs.getString("name"));
                line.setLength(rs.getInt("length"));
                list.add(line);
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
            String sql = "select count(1) count from line";
            Object[] params = {};
            rs= BaseDao.execute(connection, pstm, rs,sql, params);
            if (rs.next()) {
                execute = rs.getInt("count");
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return execute;
    }

    public List<Line> queryBysearch(Connection connection, int start, int rows, Map<String, String[]> condition) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Line> list = new ArrayList<Line>();
        if (connection != null) {
            String sql = "select * from line where 1 =1 ";
            ArrayList<Object> arraylist = new ArrayList<Object>();
            StringBuilder sb = new StringBuilder(sql);
            //遍历map
            Set<String> keySet = condition.keySet();

            for (String key : keySet) {

                if ("page".equals(key) || "limit".equals(key) || "method".equals(key) ) {
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
            System.out.println(sb.toString());

//            Object[] params = {start, rows};
            rs = BaseDao.execute(connection, pstm, rs, sb.toString(), params);

            while (rs.next()) {
                Line line = new Line();
                line.setLineId(rs.getInt("line_id"));
                line.setLineNo(rs.getString("line_no"));
                line.setName(rs.getString("name"));
                line.setLength(rs.getInt("length"));
                list.add(line);
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
            String sql = "select count(1) count from line where 1 = 1 ";
            Object[] params = null;
            ArrayList<Object> list = new ArrayList<Object>();
            StringBuilder sb = new StringBuilder(sql);
            Set<String> keySet = condition.keySet();
            for (String key : keySet) {
                if ("method".equals(key) || "page".equals(key) || "limit".equals(key)||"startTime".equals(key) || "endTime".equals(key)) {
                    continue;
                }
                String value = condition.get(key)[0];
                if (value != null && !"".equals(value)) {
                    sb.append(" and " + key + " like ? ");
                    list.add("%" + value + "%");
                }
            }

            for (String key : keySet) {

                String value = condition.get(key)[0];
                if ("startTime".equals(key) && value != null && !"".equals(value)) {
                    sb.append(" and createtime > ? and  createtime < ? ");
                    list.add("'"+value+"'");
                    for (String s : keySet) {
                        if ("endTime".equals(s)) {
                            String value_ = condition.get(s)[0];
                            list.add("'"+value_+"'");
                        }
                    }
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


    public List<Line> queryByid(Connection connection, int id) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Line> list = new ArrayList<Line>();
        if (connection != null) {
            String sql = "select * from line where  line_id = ? ";
            Object[] params = {id};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            while (rs.next()) {
                Line line = new Line();
                line.setLineId(rs.getInt("line_id"));
                line.setLineNo(rs.getString("line_no"));
                line.setName(rs.getString("name"));
                line.setLength(rs.getInt("length"));
                list.add(line);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return list;
    }

    public int updateInfoByid(Connection connection, int id, String value, String column) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "";
        Object[] params = {};
        int execute = 0;
        if (connection != null) {
            if (column.equals("lineNo")) {
                sql = "update line set line_no = ? where line_id = ?";
                params = new Object[]{value,id};
            }
            else if (column.equals("lineName")) {
                sql = "update line set name = ? where line_id = ?";
                params = new Object[]{value,id};
            }else if (column.equals("length")) {
                sql = "update line set length = ? where line_id = ?";
                params = new Object[]{Integer.parseInt(value),id};
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
            String sql = "delete from line where  line_id = ?";
            Object[] params = {id};
            execute = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, rs);
        }
        return execute;
    }

    public int add(Connection connection, Line line) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int execute = 0;
        if (connection != null) {
            String sql = "insert into line (line_no,name,length) values(?,?,?)";
            Object[] params = {line.getLineNo(),line.getName(),line.getLength()};
            execute = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, rs);
        }
        return execute;
    }
}
