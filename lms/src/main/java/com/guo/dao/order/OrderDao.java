package com.guo.dao.order;



import com.guo.pojo.Order;
import com.guo.pojo.OrderVO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface OrderDao {
    //查询订单列表
    public List<OrderVO> query(Connection connection, int start, int rows) throws SQLException;

    //查询订单数量
    public int queryCount(Connection connection) throws SQLException;

    //查询订单根据搜索条件
    public List<OrderVO> queryBysearch(Connection connection, int start, int rows, Map<String, String[]> condition) throws SQLException;

    //查询订单数量根据搜索条件

    public int queryCountBysearch(Connection connection, Map<String, String[]> condition) throws SQLException;

    //查询订单id根据编号
    public List<OrderVO> queryIdByno(Connection connection, String no) throws SQLException;

    //查询订单根据id
    public List<OrderVO> queryByid(Connection connection, int id)throws  SQLException;

    //修改订单信息根据车辆id
    public int updateInfoById(Connection connection, int id, String value, String column)  throws SQLException;

    //删除订单根据id
    public int deleteByid(Connection connection, int id) throws SQLException;

    //添加订单
    public int add(Connection connection, Order order) throws SQLException;

}
