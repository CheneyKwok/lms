package com.guo.dao.receipt;





import com.guo.pojo.Receipt;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ReceiptDao {
    //查询交接单列表
    public List<Receipt> queryJoin(Connection connection, int start, int rows) throws SQLException;

    //查询投递单列表
    public List<Receipt> queryDeliver(Connection connection, int start, int rows) throws SQLException;

    //查询交接单数量
    public int queryJoinCount(Connection connection) throws SQLException;

    //查询投递单数量
    public int queryDeliverCount(Connection connection) throws SQLException;


    //查询交接单根据搜索条件
    public List<Receipt> queryJoinBysearch(Connection connection, int start, int rows, Map<String, String[]> condition) throws SQLException;

    //查询交接单数量根据搜索条件

    public int queryJoinCountBysearch(Connection connection, Map<String, String[]> condition) throws SQLException;

    //查询订单id根据编号
    public List<Receipt> queryIdByno(Connection connection, String no) throws SQLException;

    //查询订单根据iid
    public List<Receipt> queryByid(Connection connection, int id)throws  SQLException;


    //删除订单根据id
    public int deleteByid(Connection connection, int id) throws SQLException;

    //添加交接单
    public int join(Connection connection, Receipt receipt) throws SQLException;

    //添加投递单
    public int deliver(Connection connection, Receipt receipt) throws SQLException;
}
