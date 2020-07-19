package com.guo.service.order;

import com.guo.pojo.Order;
import com.guo.pojo.OrderVO;

import java.util.List;
import java.util.Map;

public interface OrderService {

    //查询订单列表
    public List<OrderVO> query(int currentPage, int rows) ;

    //查询订单数量
    public int queryCount();

    //查询订单根据搜索条件
    public List<OrderVO> queryBysearch(int currentPage, int rows, Map<String, String[]> condition);

    //查询订单数量根据搜索条件

    public int queryCountBysearch(Map<String, String[]> condition);


    //查询订单id根据编号
    public List<OrderVO> queryIdByno(String no) ;

    //查询订单根据id
    public List<OrderVO> queryByid(int id);



    //修改订单信息根据车辆id
    public int updateInfoById(int id, String value, String column) ;

    //删除订单根据id
    public int deleteByid(int id);

    //添加订单
    public int add(Order order);

}
