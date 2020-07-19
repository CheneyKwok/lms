package com.guo.service.car;



import com.guo.pojo.Car;
import com.guo.pojo.CarVO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface CarService {

    //查询车辆列表
    public List<CarVO> query(int currentPage, int rows) ;

    //查询车辆数量
    public int queryCount();

    //查询车辆根据搜索条件
    public List<CarVO> queryBysearch(int currentPage, int rows, Map<String, String[]> condition);

    //查询车辆数量根据搜索条件

    public int queryCountBysearch(Map<String, String[]> condition);


    //查询车辆id根据编号
    public List<CarVO> queryIdByno(String no) ;

    //查询车辆根据iid
    public List<CarVO> queryByid(int id);

    //查询空闲车辆list
    public List<CarVO> queryCarList();

    //查询车辆状态根据id
    public String queryCarState(int id);


    //修改车辆信息根据车辆id
    public int updateInfoById(int id, String value, String column) ;

    //删除车辆根据id
    public int deleteByid(int id);

    //添加车辆
    public int add(Car car);

}
