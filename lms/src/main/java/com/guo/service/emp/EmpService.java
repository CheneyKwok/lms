package com.guo.service.emp;

import com.guo.pojo.EmpVO;
import com.guo.pojo.Employee;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface EmpService {

    //查询员工列表
    public List<EmpVO> query(int currentPage,int rows) ;

    //查询员工数量
    public int queryCount();

    //查询员工根据搜索条件
    public List<EmpVO> queryBysearch(int currentPage, int rows, Map<String, String[]> condition);

    //查询员工数量根据搜索条件

    public int queryCountBysearch(Map<String,String[]> condition);


    //查询员工id根据编号
    public List<EmpVO> queryIdByno(String no) ;

    //查询员工根据iid
    public List<EmpVO> queryByid(int id);

    //修改编号名根据员工id
    public int updateNameByid( int id, String no);

    //修改员工信息根据车辆id
    public int updateInfoById(int id, String value, String column) ;

    //删除用户根据id
    public int deleteByid(int id);

    //添加员工
    public int add(Employee emp);

}
