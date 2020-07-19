package com.guo.service.deliverspot;

import com.guo.pojo.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface DeliverspotService {

    //修改配送点名根据配送点id
    public int updateNameByid(int id, String name);

    //查询配送点id根据配送点名
    public List<DeliverSpotVo> queryIdByname(String name);

    //查询价格参数根据id
    public List<DeliverSpotVo> queryParams(int id);

    //查询配送点列表
    public List<DeliverSpotVo> query(int currentPage,int rows) ;

    //查询配送点数量
    public int queryCount();

    //查询配送点根据搜索条件
    public List<DeliverSpotVo> queryBysearch(int currentPage, int rows, Map<String, String[]> condition);

    //查询配送点数量根据搜索条件

    public int queryCountBysearch(Map<String,String[]> condition);

    //查询省份列表
    public List<Province> queryProvince();

    //查询城市列表根据省份id
    public List<City> queryCity(int id);


    //修改配送点信息根据id
    public int updateInfoById(int id, String value, String column) ;

    //删除配送点根据id
    public int deleteByid(int id);

    //添加配送点
    public int add(DeliverSpot d);


}
