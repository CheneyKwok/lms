package com.guo.dao.receipt;

import com.guo.dao.BaseDao;
import com.guo.dao.order.OrderDao;
import com.guo.pojo.DeliverSpotVo;
import com.guo.pojo.Order;
import com.guo.pojo.OrderVO;
import com.guo.pojo.Receipt;
import com.guo.service.deliverspot.DeliverspotService;
import com.guo.service.deliverspot.DeliverspotServiceImpl;
import com.guo.util.BaseMethod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ReceiptDaoImpl implements ReceiptDao {


    public List<Receipt> queryJoin(Connection connection, int start, int rows) throws SQLException  {
        PreparedStatement pstm =null;
        ResultSet rs = null;
        ArrayList<Receipt> list = new ArrayList<Receipt>();
        if (connection != null) {
            String sql = "select receipt_id,car_no,createtime,`depart-time`,receipt_no ,flag " +
                    "from receipt where flag = 0 limit ?,? ";
            Object[] params = {start,rows};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            while (rs.next()) {
                Receipt receipt = new Receipt();
                receipt.setReceiptId(rs.getInt("receipt_id"));
                receipt.setCarNo(rs.getString("car_no"));
                receipt.setCreatetime(rs.getString("createtime"));
                receipt.setDepartTime(rs.getString("depart-time"));
                receipt.setReceiptNo(rs.getString("receipt_no"));
                list.add(receipt);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return list;
    }

    public List<Receipt> queryDeliver(Connection connection, int start, int rows) throws SQLException {
        PreparedStatement pstm =null;
        ResultSet rs = null;
        ArrayList<Receipt> list = new ArrayList<Receipt>();
        if (connection != null) {
            String sql = "select receipt_id,\n" +
                    "       receipt_no,\n" +
                    "       createtime,\n" +
                    "       d.name,\n" +
                    "       e.name\n" +
                    "from receipt r\n" +
                    "         inner join deliverspot d on r.deliverspot_id = d.deliverspot_id\n" +
                    "         inner join employee e on r.emp_id = e.emp_id where r.flag = 1 limit ?,?  ";
            Object[] params = {start,rows};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            while (rs.next()) {
                Receipt receipt = new Receipt();
                receipt.setReceiptId(rs.getInt("receipt_id"));
                receipt.setDeliverspot(rs.getString("d.name"));
                receipt.setCreatetime(rs.getString("createtime"));
                receipt.setEmpName(rs.getString("e.name"));
                receipt.setReceiptNo(rs.getString("receipt_no"));
                list.add(receipt);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return list;
    }

    public int queryJoinCount(Connection connection) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int execute  = 0;
        if (connection != null) {
            String sql = "select count(1) count  from receipt where flag = 0 ";
            Object[] params = {};
            rs= BaseDao.execute(connection, pstm, rs,sql, params);
            if (rs.next()) {
                execute = rs.getInt("count");
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return execute;
    }

    public int queryDeliverCount(Connection connection) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int execute  = 0;
        if (connection != null) {
            String sql = "select count(1) count from receipt where flag = 1";
            Object[] params = {};
            rs= BaseDao.execute(connection, pstm, rs,sql, params);
            if (rs.next()) {
                execute = rs.getInt("count");
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return execute;
    }

    public List<Receipt> queryJoinBysearch(Connection connection, int start, int rows, Map<String, String[]> condition) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Receipt> list = new ArrayList<Receipt>();
        if (connection != null) {
            String sql = "select receipt_id,car_no,`depart-time`,createtime,receipt_no from receipt where 1 = 1 and flag = 0 ";
            ArrayList<Object> arraylist = new ArrayList<Object>();
            StringBuilder sb = new StringBuilder(sql);
            //遍历map
            Set<String> keySet = condition.keySet();

            for (String key : keySet) {

                if ("page".equals(key) || "limit".equals(key) || "method".equals(key) ||"startTime".equals(key) || "endTime".equals(key)|| "flag".equals(key)) {
                    continue;
                }

                String value = condition.get(key)[0];
                if (value != null && !"".equals(value)) {
                    sb.append(" and " + key + " like ? ");
                    arraylist.add("%" + value + "%");
                }
            }
            for (String key : keySet) {

                String value = condition.get(key)[0];
                if ("startTime".equals(key) && value != null && !"".equals(value)) {
                    sb.append(" and createtime > ? and  createtime < ?");
                    arraylist.add(value);
                    for (String s : keySet) {
                        if ("endTime".equals(s)) {
                            String value_ = condition.get(s)[0];
                            arraylist.add(value_);
                        }
                    }
                }
            }
            sb.append(" limit ? , ? ");
            arraylist.add(start);
            arraylist.add(rows);
            Object[] params = arraylist.toArray();
          //  System.out.println(sb.toString());

//            Object[] params = {start, rows};
            rs = BaseDao.execute(connection, pstm, rs, sb.toString(), params);

            while (rs.next()) {
                Receipt receipt = new Receipt();
                receipt.setReceiptId(rs.getInt("receipt_id"));
                receipt.setCarNo(rs.getString("car_no"));
                receipt.setCreatetime(rs.getString("createtime"));
                receipt.setDepartTime(rs.getString("depart-time"));
                receipt.setReceiptNo(rs.getString("receipt_no"));
                list.add(receipt);
            }

            BaseDao.closeResource(null, pstm, rs);
        }

        return list;
    }

    public int queryJoinCountBysearch(Connection connection, Map<String, String[]> condition) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int count = 0;
        if (connection != null) {
            String sql = "select count(1) count, receipt_id,createtime,receipt_no from receipt where 1 = 1 and flag = 0  ";
            Object[] params = null;
            ArrayList<Object> list = new ArrayList<Object>();
            StringBuilder sb = new StringBuilder(sql);
            Set<String> keySet = condition.keySet();
            for (String key : keySet) {
                if ("method".equals(key) || "page".equals(key) || "limit".equals(key)||"startTime".equals(key) || "endTime".equals(key)|| "flag".equals(key)) {
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
                            list.add(value_);
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

    public List<Receipt> queryIdByno(Connection connection, String no) throws SQLException {
        return null;
    }

    public List<Receipt> queryByid(Connection connection, int id) throws SQLException {
        return null;
    }

    public int updateInfoById(Connection connection, int id, String value, String column) throws SQLException {
        return 0;
    }

    public int deleteByid(Connection connection, int id) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int execute = 0;
        if (connection != null) {
            String sql = "delete from receipt where  receipt_id = ?";
            Object[] params = {id};
            execute = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, rs);
        }
        return execute;
    }

    public int join(Connection connection, Receipt r) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int execute = 0;
        if (connection != null) {
            String sql = "insert into receipt\n" +
                    "(car_no, createtime, `depart-time`, receipt_no,flag)\n" +
                    "values (?,?,?,?,?)";
            Object[] params = {r.getCarNo(), r.getCreatetime(), r.getDepartTime(), r.getReceiptNo(),r.getFlag()};
            execute = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, rs);
        }
        return execute;
    }

    public int deliver(Connection connection, Receipt r) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int execute = 0;
        if (connection != null) {
            String sql = "insert into receipt\n" +
                    "( createtime, receipt_no,deliverspot_id,emp_id,flag)\n" +
                    "values (?,?,?,?,?)";
            Object[] params = {r.getCreatetime(), r.getReceiptNo(),r.getDeliverspotId(),r.getEmpId(),r.getFlag()};
            execute = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, rs);
        }
        return execute;
    }
}





