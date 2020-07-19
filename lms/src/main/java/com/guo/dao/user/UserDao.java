package com.guo.dao.user;

import com.guo.pojo.User;
import com.guo.pojo.UserVO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface UserDao {
    //得到登录用户
    public User getLoginUser(Connection connection, String username) throws SQLException;

    //修改用户密码
    public int updatePwd(Connection connection, int id, String password) throws SQLException;

    //查询所有用户
    public List<UserVO> getUserlist(Connection connection,int start,int rows) throws SQLException;

    //查询用户总数
    public int getUsercount(Connection connection)throws SQLException;

    //查询用户根据搜索条件
    public List<UserVO> getUserlistBysearch(Connection connection, int start, int rows, Map<String, String[]> condition) throws SQLException;

    //查询用户总数根据搜索条件

    public int getUsercountBysearch(Connection connection,Map<String,String[]> condition) throws SQLException;

    //查询编号id根据用户id
    public int queryNoidByuserid(Connection connection,int userid)throws  SQLException;

    //查询权限id根据用户id
    public int queryPermissionidByuserid(Connection connection,int userid)throws  SQLException;

    //查询配送点id根据用户id
    public int queryDeliversportidByuserid(Connection connection,int userid)throws  SQLException;

    //删除用户根据id
    public int delUserByid(Connection connection, int id)throws SQLException;

    //添加用户
    public int addUser(Connection connection, User user)throws SQLException;

    //更新用户登录名根据id
    public int updateNameByid(Connection connection,int id, String name)throws SQLException;

    //更新配送点id根据id
    public int updateDeliversportIdByid(Connection connection,int userid, int did)throws SQLException;

    //更新权限id根据id
    public int updatePermissionIdByid(Connection connection,int userid, int pid)throws SQLException;


    //验证是否存在配送点
    public int checkDs(Connection connection, String ds)throws SQLException;

    //验证是否存在该员工通过编号
    public int checkEmp(Connection connection,String empno)throws SQLException;

    //查看用户根据id
    public List<UserVO> detailUser(Connection connection,int id)throws SQLException;




}
