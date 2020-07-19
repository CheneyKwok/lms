package com.guo.dao.car;

import com.guo.pojo.Car;
import com.guo.pojo.CarVO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface CarDao {
    //查询车辆列表
    public List<CarVO> query(Connection connection, int start, int rows) throws SQLException;

    //查询车辆数量
    public int queryCount(Connection connection) throws SQLException;

    //查询车辆根据搜索条件
    public List<CarVO> queryBysearch(Connection connection, int start, int rows, Map<String, String[]> condition) throws SQLException;

    //查询车辆数量根据搜索条件

    public int queryCountBysearch(Connection connection,Map<String,String[]> condition) throws SQLException;

    //查询车辆id根据编号
    public List<CarVO> queryIdByno(Connection connection, String no) throws SQLException;

    //查询车辆根据iid
    public List<CarVO> queryByid(Connection connection, int id)throws  SQLException;

    //查询空闲车辆list
    public List<CarVO> queryCarList(Connection connection)throws SQLException;

    //查询车辆状态根据id
    public String queryCarState(Connection connection, int id)throws  SQLException;

    //修改车辆信息根据车辆id
    public int updateInfoById(Connection connection, int id, String value, String column)  throws SQLException;

    //删除车辆根据id
    public int deleteByid(Connection connection, int id) throws SQLException;

    //添加车辆
    public int add(Connection connection, Car car) throws SQLException;

}
