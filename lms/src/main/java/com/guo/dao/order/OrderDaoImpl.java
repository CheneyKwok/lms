package com.guo.dao.order;

import com.guo.dao.BaseDao;
import com.guo.pojo.DeliverSpotVo;
import com.guo.pojo.Order;
import com.guo.pojo.OrderVO;
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

public class OrderDaoImpl implements OrderDao {

    public List<OrderVO> query(Connection connection , int start , int rows) throws SQLException {
        PreparedStatement pstm =null;
        ResultSet rs = null;
        ArrayList<OrderVO> list = new ArrayList<OrderVO>();
        if (connection != null) {
            String sql = "select order_id," +
                    "       order_no,\n" +
                    "       price,\n" +
                    "       sendname,\n" +
                    "       createtime,\n" +
                    "       sendaddress,\n" +
                    "       receivename,\n" +
                    "       receiveaddress,\n" +
                    "       o2.name,\n" +
                    "       d.name\n" +
                    "from `order` o\n" +
                    "         inner join orderstate o2 on o.orderstate_id = o2.orderstate_id\n" +
                    "         inner join deliverspot d on o.deliverspot_id = d.deliverspot_id\n" +
                    " limit ? ,? ";
            Object[] params = {start,rows};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            while (rs.next()) {
                OrderVO orderVO = new OrderVO();
                orderVO.setOrderId(rs.getInt("order_id"));
                orderVO.setOrderNo(rs.getString("order_no"));
                orderVO.setPrice(rs.getString("price"));
                orderVO.setSendName(rs.getString("sendname"));
                orderVO.setSendAddress(rs.getString("sendaddress"));
                orderVO.setReceiveName(rs.getString("receivename"));
                orderVO.setReceiveAddress(rs.getString("receiveaddress"));
                orderVO.setOrderState(rs.getString("o2.name"));
                orderVO.setDeliverSpot(rs.getString("d.name"));
                orderVO.setCreateTime(rs.getString("createtime"));

                list.add(orderVO);
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
                    "from `order` o\n" +
                    "         inner join orderstate o2 on o.orderstate_id = o2.orderstate_id\n" +
                    "         inner join deliverspot d on o.deliverspot_id = d.deliverspot_id ";
            Object[] params = {};
            rs= BaseDao.execute(connection, pstm, rs,sql, params);
            if (rs.next()) {
                execute = rs.getInt("count");
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return execute;
    }

    public List<OrderVO> queryBysearch(Connection connection, int start, int rows, Map<String, String[]> condition) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<OrderVO> list = new ArrayList<OrderVO>();
        if (connection != null) {
            String sql = "select " +
                    "       order_id,\n" +
                    "       order_no,\n" +
                    "       createtime,\n" +
                    "       sendname,\n" +
                    "       sendaddress,\n" +
                    "       receivename,\n" +
                    "       receiveaddress,\n" +
                    "       o2.name,\n" +
                    "       d.name\n" +
                    "from `order` o\n" +
                    "         inner join orderstate o2 on o.orderstate_id = o2.orderstate_id\n" +
                    "         inner join deliverspot d on o.deliverspot_id = d.deliverspot_id\n where 1 =1 ";
            ArrayList<Object> arraylist = new ArrayList<Object>();
            StringBuilder sb = new StringBuilder(sql);
            //遍历map
            Set<String> keySet = condition.keySet();

            for (String key : keySet) {

                if ("page".equals(key) || "limit".equals(key) || "method".equals(key) ||"startTime".equals(key) || "endTime".equals(key)) {
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
            System.out.println(sb.toString());

//            Object[] params = {start, rows};
                rs = BaseDao.execute(connection, pstm, rs, sb.toString(), params);

                while (rs.next()) {
                    OrderVO orderVO = new OrderVO();
                    orderVO.setOrderId(rs.getInt("order_id"));
                    orderVO.setOrderNo(rs.getString("order_no"));
                    orderVO.setSendName(rs.getString("sendname"));
                    orderVO.setSendAddress(rs.getString("sendaddress"));
                    orderVO.setReceiveName(rs.getString("receivename"));
                    orderVO.setReceiveAddress(rs.getString("receiveaddress"));
                    orderVO.setCreateTime(rs.getString("createtime"));
                    orderVO.setOrderState(rs.getString("o2.name"));
                    orderVO.setDeliverSpot(rs.getString("d.name"));

                    list.add(orderVO);
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
                    "        order_id,\n" +
                    "        order_no,\n" +
                    "        sendname,\n" +
                    "        sendaddress,\n" +
                    "        receivename,\n" +
                    "        receiveaddress,\n" +
                    "        o2.name,\n" +
                    "        d.name\n" +
                    " from `order` o\n" +
                    "          inner join orderstate o2 on o.orderstate_id = o2.orderstate_id\n" +
                    "          inner join deliverspot d on o.deliverspot_id = d.deliverspot_id where 1 = 1 ";
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
                    list.add(value);
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

    public List<OrderVO> queryIdByno(Connection connection, String no) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<OrderVO> list = null;
        if (connection != null) {
            String sql = "select order_id from `order` where order_no = ?";
            Object[] params = {no};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            list= new ArrayList<OrderVO>();
            while (rs.next()) {
                OrderVO orderVO = new OrderVO();
                orderVO.setOrderId(rs.getInt("order_id"));
                list.add(orderVO);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return list;
    }

    public List<OrderVO> queryByid(Connection connection, int id) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<OrderVO> list = new ArrayList<OrderVO>();
        if (connection != null) {
            String sql = "select order_id,\n" +
                    "       order_no,\n" +
                    "       car_id,\n" +
                    "       createtime,\n" +
                    "       sendtel,\n" +
                    "       sendname,\n" +
                    "       sendaddress,\n" +
                    "       receivename,\n" +
                    "       receiveaddress,\n" +
                    "       receivetel,\n" +
                    "       volume,\n" +
                    "       weight,\n" +
                    "       price,\n" +
                    "       remark,\n" +
                    "       o2.name,\n" +
                    "       d.name\n" +
                    "from `order` o\n" +
                    "         inner join orderstate o2 on o.orderstate_id = o2.orderstate_id\n" +
                    "         inner join deliverspot d on o.deliverspot_id = d.deliverspot_id where  order_id = ? ";
            Object[] params = {id};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            while (rs.next()) {
                OrderVO orderVO = new OrderVO();
                orderVO.setOrderId(rs.getInt("order_id"));
                orderVO.setCarId(rs.getInt("car_id"));
                orderVO.setOrderNo(rs.getString("order_no"));
                orderVO.setSendName(rs.getString("sendname"));
                orderVO.setSendAddress(rs.getString("sendaddress"));
                orderVO.setSendTel(rs.getString("sendtel"));
                orderVO.setReceiveName(rs.getString("receivename"));
                orderVO.setReceiveAddress(rs.getString("receiveaddress"));
                orderVO.setReceiveTel(rs.getString("receivetel"));
                orderVO.setOrderState(rs.getString("o2.name"));
                orderVO.setDeliverSpot(rs.getString("d.name"));
                orderVO.setVolume(rs.getString("volume"));
                orderVO.setWeight(rs.getString("weight"));
                orderVO.setRemark(rs.getString("remark"));
                orderVO.setPrice(rs.getString("price"));
                orderVO.setCreateTime(rs.getString("createtime"));


                list.add(orderVO);
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
           if (column.equals("sendName")) {
                sql = "update `order` set sendname = ? where order_id = ?";
                params = new Object[]{value,id};
            }
            else if (column.equals("sendAddress")) {
                sql = "update `order` set sendaddress = ? where order_id = ?";
                params = new Object[]{value,id};
            }
           else if (column.equals("orderState")) {
               sql = "update `order` set orderstate_id = ? where order_id = ?";
               params = new Object[]{Integer.parseInt(value),id};
           } else if (column.equals("carId")) {
               sql = "update `order` set car_id = ? where order_id = ?";
               params = new Object[]{Integer.parseInt(value), id};
           }else if (column.equals("receiveName")) {
                sql = "update `order` set receivename = ? where order_id = ?";
                params = new Object[]{value,id};
            }else if (column.equals("receiveAddress")) {
                sql = "update `order` set receiveaddress = ? where order_id = ?";
                params = new Object[]{value,id};
            }else if (column.equals("deliverspot")) {
                DeliverspotService deliverspotService = new DeliverspotServiceImpl();
                List<DeliverSpotVo> list = deliverspotService.queryIdByname(value);
                sql = "update `order` set deliverspot_id = ? where order_id = ?";
                params = new Object[]{list.get(0).getDeliverspotId(),id};
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
            String sql = "delete from `order` where  order_id = ?";
            Object[] params = {id};
            execute = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, rs);
        }
        return execute;
    }

    public int add(Connection connection, Order o) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int execute = 0;
        if (connection != null) {
            String sql = "insert into `order`\n" +
                    "(order_no, sendname, sendaddress, sendtel, receivename, receiveaddress, receivetel," +
                    "orderstate_id,price,volume,weight,remark,createtime,deliverspot_id)\n" +
                    "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            float price = BaseMethod.getPrice(o.getWeight(), o.getDeliverspotId());
            Object[] params = {o.getOrderNo(),o.getSendname(),o.getSendaddress(),o.getSendtel(),o.getReceivename(),o.getReceiveaddress(),
            o.getReceivetel(),o.getOrderstateId(),price,o.getVolume(),o.getWeight(),o.getRemark(),o.getCreatetime(),o.getDeliverspotId()};
            execute = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, rs);
        }
        return execute;
    }
}





