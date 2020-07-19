package com.guo.dao.deliverspot;

import com.guo.pojo.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface DeliverspotDao {

    //修改配送点名根据配送点id
    public int updateNameByid(Connection connection, int id, String name) throws SQLException;

    //查询配送点id根据配送点名
    public List<DeliverSpotVo> queryIdByname(Connection connection, String name) throws SQLException;

    //查询价格参数根据id
    public List<DeliverSpotVo> queryParams(Connection connection, int id)throws SQLException;

    //查询配送点列表
    public List<DeliverSpotVo> query(Connection connection, int start, int rows) throws SQLException;

    //查询配送点数量
    public int queryCount(Connection connection) throws SQLException;

    //查询配送点根据搜索条件
    public List<DeliverSpotVo> queryBysearch(Connection connection, int start, int rows, Map<String, String[]> condition) throws SQLException;

    //查询配送点数量根据搜索条件

    public int queryCountBysearch(Connection connection,Map<String,String[]> condition) throws SQLException;

    //查询省份列表
    public List<Province> queryProvince(Connection connection) throws SQLException;

    //查询城市列表根据省份id
    public List<City> queryCity(Connection connection, int id) throws SQLException;


    //修改配送点信息根据id
    public int updateInfoById(Connection connection, int id, String value, String column)  throws SQLException;

    //删除配送点根据id
    public int deleteByid(Connection connection, int id) throws SQLException;

    //添加配送点
    public int add(Connection connection, DeliverSpot deliverSpot) throws SQLException;
}
