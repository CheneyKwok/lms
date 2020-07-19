package com.guo.dao.emp;

import com.guo.pojo.EmpVO;
import com.guo.pojo.Employee;
import com.guo.pojo.UserVO;
import com.guo.util.Constant;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface EmpDao {

    //查询员工列表
    public List<EmpVO> query(Connection connection, int start, int rows) throws SQLException;

    //查询员工数量
    public int queryCount(Connection connection) throws SQLException;

    //查询员工根据搜索条件
    public List<EmpVO> queryBysearch(Connection connection, int start, int rows, Map<String, String[]> condition) throws SQLException;

    //查询员工数量根据搜索条件

    public int queryCountBysearch(Connection connection,Map<String,String[]> condition) throws SQLException;

    //查询员工id根据编号
    public List<EmpVO> queryIdByno(Connection connection, String no) throws SQLException;

    //查询员工根据iid
    public List<EmpVO> queryByid(Connection connection, int id)throws  SQLException;


    //修改编号名根据员工id
    public int updateNameByid(Connection connection, int id, String no) throws SQLException;

    //修改员工信息根据车辆id
    public int updateInfoById(Connection connection, int id, String value, String column)  throws SQLException;

    //删除用户根据id
    public int deleteByid(Connection connection, int id) throws SQLException;

    //添加员工
    public int add(Connection connection, Employee emp) throws SQLException;

}
