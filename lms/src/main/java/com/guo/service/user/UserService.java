package com.guo.service.user;

import com.guo.pojo.User;
import com.guo.pojo.UserVO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface UserService {
    //用户登录
    public User login(String uesrname, String password);

    //用户修改密码
    public boolean updatePwd(int id, String password);

    //用户总数
    public int getUsercount();

    //用户总数根据查询条件
    public int getUsercountBysearch(Map<String, String[]> condition);


    //查询所有用户
    public List<UserVO> getUsetlist(int currentPage,int rows);


    //查询所有用户根据查询条件
    public List<UserVO> getUsetlistBysearch(int currentPage, int rows, Map<String, String[]> condition);

    //查询编号id根据用户id
    public int queryNoidByuserid(int userid);

    //查询权限id根据用户id
    public int queryPermissionidByuserid(int userid);

    //查询配送点id根据用户id
    public int queryDeliversportidByuserid(int userid);

    //删除用户根据id
    public int delUserByid(int id);

    //添加用户
    public int addUser(User user);

    //更新用户登录名根据id
    public int updateUserNameByid(int id, String name);

    //更新配送点id根据id
    public int updateDeliversportIdByid(int userid, int did);

    //更新权限id根据id
    public int updatePermissionIdByid(int userid, int pid);

    //验证是否存在配送点
    public int checkDs(String ds);

    //验证是否存在该员工通过编号
    public int checkEmp(String empno);

    //查看用户根据id
    public List<UserVO> detailUser(int id);
}
