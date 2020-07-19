package com.guo.dao.line;

import com.guo.pojo.Car;
import com.guo.pojo.CarVO;
import com.guo.pojo.Line;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface LineDao {
    //查询线路列表
    public List<Line> query(Connection connection, int start, int rows) throws SQLException;

    //查询线路数量
    public int queryCount(Connection connection) throws SQLException;

    //查询线路根据搜索条件
    public List<Line> queryBysearch(Connection connection, int start, int rows, Map<String, String[]> condition) throws SQLException;

    //查询线路数量根据搜索条件

    public int queryCountBysearch(Connection connection, Map<String, String[]> condition) throws SQLException;

    //查询线路id根据线路名
    public int queryIdByName(Connection connection, String name) throws SQLException;

    //查询线路根据iid
    public List<Line> queryByid(Connection connection, int id)throws  SQLException;

    //修改线路信息根据d
    public int updateInfoByid(Connection connection, int id, String no,String column) throws SQLException;

    //删除线路根据id
    public int deleteByid(Connection connection, int id) throws SQLException;

    //添加线路
    public int add(Connection connection, Line line) throws SQLException;

}
