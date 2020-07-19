package com.guo.service.line;

import com.guo.pojo.Line;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface LineService {


    //查询线路列表
    public List<Line> query(int start, int rows) ;

    //查询线路数量
    public int queryCount() ;

    //查询线路根据搜索条件
    public List<Line> queryBysearch(int start, int rows, Map<String, String[]> condition);

    //查询线路数量根据搜索条件

    public int queryCountBysearch(Map<String, String[]> condition) ;

    //查询线路id根据线路名
    public int queryIdByName( String name) ;

    //查询线路根据iid
    public List<Line> queryByid(int id);

    //修改线路信息根据d
    public int updateInfoByid( int id, String no,String column) ;

    //删除线路根据id
    public int deleteByid(int id) ;

    //添加线路
    public int add(Line line) ;

}
