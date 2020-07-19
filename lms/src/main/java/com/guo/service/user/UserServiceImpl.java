package com.guo.service.user;

import com.guo.dao.BaseDao;
import com.guo.dao.user.UserDao;
import com.guo.dao.user.UserDaoImpl;
import com.guo.pojo.User;
import com.guo.pojo.UserVO;
import org.junit.Test;

import javax.swing.plaf.basic.BasicEditorPaneUI;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    //业务层调Dao层，引入Dao层..私有进来
    private UserDao userDao;
    public UserServiceImpl(){
        userDao = new UserDaoImpl();
    }

    public User login(String uesrname, String password) {
        Connection connection = null;
        User user = null;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
             user = userDao.getLoginUser(connection, uesrname);
             connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                System.out.println("rollback============");
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection, null, null);
        }

        return user;
    }

    public boolean updatePwd(int id, String password) {
         Connection connection = null;
         boolean flag = false;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            if (userDao.updatePwd(connection, id, password) > 0) {
                flag = true;
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                System.out.println("rollback============");
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return flag;
    }

    public int getUsercount() {
        Connection connection = null;
        int usercount = 0;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
          usercount = userDao.getUsercount(connection);
          connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                System.out.println("rollback============");
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection, null, null);
        }
        return usercount;
    }

    public int getUsercountBysearch(Map<String, String[]> condition) {
        Connection connection = null;
        int usercount = 0;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            usercount = userDao.getUsercountBysearch(connection,condition);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                System.out.println("rollback============");
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection, null, null);
        }
        return usercount;
    }


    public List<UserVO> getUsetlist(int currentPage,int rows) {
        Connection connection = null;
        List<UserVO> userlist = null;
        int start = 0;
        try {
            connection = BaseDao.getConnection();
            start = (currentPage - 1) * rows;
            userlist = userDao.getUserlist(connection,start,rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection, null, null);
        }
        return userlist;
    }

    public List<UserVO> getUsetlistBysearch(int currentPage, int rows, Map<String, String[]> condition) {
        Connection connection = null;
        List<UserVO> userlist = null;
        int start = 0;
        try {
            connection = BaseDao.getConnection();
            start = (currentPage - 1) * rows;
            connection.setAutoCommit(false);
            userlist = userDao.getUserlistBysearch(connection,start,rows,condition);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                System.out.println("rollback====");
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection, null, null);
        }
        return userlist;
    }

    public int delUserByid(int id) {
        Connection connection = null;
        int rs = 0;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            rs = userDao.delUserByid(connection,id);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                System.out.println("rollback============");
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection, null, null);
        }
        return rs;

    }

    public int addUser(User user) {
        Connection connection = null;
        int i =0;
        try {
            connection = BaseDao.getConnection();
            //开启JDBC事务
            connection.setAutoCommit(false);
             i = userDao.addUser(connection, user);
             connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                System.out.println("rollback============");
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection, null, null);
        }
        return i;
    }

    public int updateUserNameByid(int id,String name) {
        Connection connection = null;
        int i = 0;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            i = userDao.updateNameByid(connection, id, name);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                System.out.println("rollback===");
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection, null, null);
        }
        return i;
    }

    public int updateDeliversportIdByid(int userid, int did) {
        Connection connection = null;
        int i = 0;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            i = userDao.updateDeliversportIdByid(connection, userid, did);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                System.out.println("rollback===");
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection, null, null);
        }
        return i;
    }
    @Test
    public void test() {
        int userid = 201;
        int did = 3;
        int i = updatePermissionIdByid(userid, did);
        System.out.println(i);
    }

    public int updatePermissionIdByid(int userid, int pid) {
        Connection connection = null;
        int i = 0;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            i = userDao.updatePermissionIdByid(connection, userid, pid);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                System.out.println("rollback===");
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection, null, null);
        }
        return i;
    }

    public int checkDs(String ds) {
        Connection connection = null;
        int i = 0;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            i = userDao.checkDs(connection, ds);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                System.out.println("rollback=======");
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection, null, null);

        }
        return i;

    }

    public int checkEmp(String empno) {
        return 0;
    }

    public List<UserVO> detailUser(int id) {
        Connection connection = null;
        List<UserVO> list = null;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            list = userDao.detailUser(connection,id);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                System.out.println("rollback=======");
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }finally {
                BaseDao.closeResource(connection, null, null);
            }
        }
        return list;
    }

    public int queryNoidByuserid(int userid) {
        Connection connection = null;
        int i = 0;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            i = userDao.queryNoidByuserid(connection, userid);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                System.out.println("rollback=======");
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection, null, null);

        }
        return i;
    }


    public int queryPermissionidByuserid(int userid) {
        Connection connection = null;
        int i = 0;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            i = userDao.queryPermissionidByuserid(connection, userid);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                System.out.println("rollback=======");
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection, null, null);

        }
        return i;
    }

    public int queryDeliversportidByuserid(int userid) {
        Connection connection = null;
        int i = 0;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            i = userDao.queryDeliversportidByuserid(connection, userid);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                System.out.println("rollback=======");
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection, null, null);

        }
        return i;
    }
}
