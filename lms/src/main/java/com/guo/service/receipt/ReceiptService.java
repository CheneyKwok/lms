package com.guo.service.receipt;

import com.guo.pojo.Receipt;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public interface ReceiptService {
    //添加交接单
    public int join( Receipt receipt) ;

    //添加投递单
    public int deliver( Receipt receipt) ;

    //查询交接单列表
    public List<Receipt> queryJoin(int currentPage, int rows);

    //查询交接单根据搜索条件
    public List<Receipt> queryJoinBysearch(int start, int rows, Map<String, String[]> condition);

    //查询交接单数量根据搜索条件

    public int queryJoinCountBysearch(Map<String, String[]> condition) ;

    //查询投递单列表
    public List<Receipt> queryDeliver(int currentPage, int rows);

    //查询交接单数量
    public int queryJoinCount() ;

    //查询投递单数量
    public int queryDeliverCount() ;

    //删除订单根据id
    public int deleteByid(int id) ;

}

