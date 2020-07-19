package com.guo.dao.user;

import com.guo.dao.BaseDao;
import com.guo.pojo.User;
import com.guo.pojo.UserVO;
import com.guo.util.BaseMethod;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {

    public User getLoginUser(Connection connection, String username) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        User user = null;

        if (connection != null) {
            String sql = "select * from lm_user where username = ?";
            Object[] params = {username};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setPermissionId(rs.getInt("permission_id"));
                user.setDeliverspotId(rs.getInt("deliverspot_id"));
                user.setEmpId(rs.getInt("emp_id"));
            }
            BaseDao.closeResource(null, pstm, rs);
        }

        return user;
    }

    public int updatePwd(Connection connection, int id, String password) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int execute = 0;

        if (connection != null) {
            String sql = "update lm_user set password = ? where user_id = ?";
            Object[] params = {password, id};
            execute = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, rs);
        }
        return execute;
    }

    public int delUserByid(Connection connection, int id) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int execute = 0;
        if (connection != null) {
            String sql = "delete from lm_user where user_id = ?";
            Object[] params = {id};
            execute = BaseDao.execute(connection, pstm, sql, params);
        }
        BaseDao.closeResource(null, pstm, rs);

        return execute;
    }

    public List<UserVO> getUserlist(Connection connection, int start, int rows) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<UserVO> list = new ArrayList<UserVO>();
        if (connection != null) {
            String sql = "select  u.user_id,u.username, p.name, u.emp_id ,e.deliverspot_id, e.emp_no,d.name from lm_user u\n" +
                    "    left join permission p\n" +
                    "        on u.permission_id = p.permission_id\n" +
                    "    inner join employee e\n" +
                    "        on u.emp_id = e.emp_id\n" +
                    "    inner join deliverspot d\n" +
                    "        on u.deliverspot_id = d.deliverspot_id limit ?,?;";
            Object[] params = {start, rows};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            while (rs.next()) {
                UserVO userVO = new UserVO();
                userVO.setUserId(rs.getInt("u.user_id"));
                userVO.setUserEmpno(rs.getString("e.emp_no"));
                userVO.setUserName(rs.getString("u.username"));
                userVO.setUserPermission(rs.getString("p.name"));
                userVO.setUserDeliverspot(rs.getString("d.name"));
//                System.out.println(rs.getString("u.user_id"));
                list.add(userVO);
            }
            BaseDao.closeResource(null, pstm, rs);
        }

        return list;
    }

    public List<UserVO> getUserlistBysearch(Connection connection, int start, int rows, Map<String, String[]> condition) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<UserVO> list = new ArrayList<UserVO>();
        if (connection != null) {
            String sql = "select  u.user_id,u.username, p.name pname, u.emp_id ,e.deliverspot_id, e.emp_no,d.name dname from lm_user u\n" +
                    "    left join permission p\n" +
                    "        on u.permission_id = p.permission_id\n" +
                    "    inner join employee e\n" +
                    "        on u.emp_id = e.emp_id\n" +
                    "    inner join deliverspot d\n" +
                    "        on u.deliverspot_id = d.deliverspot_id where 1 = 1 ";
            ArrayList<Object> arraylist = new ArrayList<Object>();
            StringBuilder sb = new StringBuilder(sql);
            //遍历map
            Set<String> keySet = condition.keySet();

            for (String key : keySet) {
                if ("page".equals(key) || "limit".equals(key) ||"method".equals(key)) {
                    continue;
                }

                String value = condition.get(key)[0];
                if (value != null && !"".equals(value)) {
                    sb.append(" and " + key + " like ? ");
                    arraylist.add("%" + value + "%");
                }
            }
            sb.append(" limit ?,? ");
            arraylist.add(start);
            arraylist.add(rows);
            Object[] params = arraylist.toArray();
            System.out.println(arraylist);
            System.out.println(sb.toString());
//            Object[] params = {start, rows};
            rs = BaseDao.execute(connection, pstm, rs, sb.toString(), params);

            while (rs.next()) {
                UserVO userVO = new UserVO();
                userVO.setUserId(rs.getInt("u.user_id"));
                userVO.setUserEmpno(rs.getString("e.emp_no"));
                userVO.setUserName(rs.getString("u.username"));
                userVO.setUserPermission(rs.getString("pname"));
                userVO.setUserDeliverspot(rs.getString("dname"));
//                System.out.println(rs.getString("u.user_id"));
                list.add(userVO);

            }

            BaseDao.closeResource(null, pstm, rs);
        }

        return list;
    }

    public int getUsercount(Connection connection) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int count = 0;
        if (connection != null) {
            String sql = "select count(1) as count\n" +
                    "from lm_user u\n" +
                    "         left join permission p\n" +
                    "                   on u.permission_id = p.permission_id\n" +
                    "         inner join employee e\n" +
                    "                    on u.emp_id = e.emp_id\n" +
                    "         inner join deliverspot d\n" +
                    "                    on u.deliverspot_id = d.deliverspot_id ";
            Object[] params = {};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            if (rs.next()) {
                count = rs.getInt("count");
            }
            BaseDao.closeResource(null, pstm, rs);

        }
        return count;
    }

    public int getUsercountBysearch(Connection connection, Map<String, String[]> condition) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int count = 0;
        if (connection != null) {
            String sql = "select count(1) count, u.user_id,u.username, p.name pname, u.emp_id ,e.deliverspot_id, e.emp_no,d.name dname from lm_user u\n" +
                    "    left join permission p\n" +
                    "        on u.permission_id = p.permission_id\n" +
                    "    inner join employee e\n" +
                    "        on u.emp_id = e.emp_id\n" +
                    "    inner join deliverspot d\n" +
                    "        on u.deliverspot_id = d.deliverspot_id where 1 = 1  ";
            ArrayList<Object> list = new ArrayList<Object>();
            StringBuilder sb = new StringBuilder(sql);
            //遍历map
            Set<String> keySet = condition.keySet();

            for (String key : keySet) {
                if ("page".equals(key) || "limit".equals(key)||"method".equals(key)) {
                    continue;
                }

                String value = condition.get(key)[0];
                if (value != null && !"".equals(value)) {
                    sb.append(" and " + key + " like ? ");
                    list.add("%" + value + "%");
                }
            }

            Object[] params = list.toArray();

            rs = BaseDao.execute(connection, pstm, rs, sb.toString(), params);
            if (rs.next()) {
                count = rs.getInt("count");
            }
            BaseDao.closeResource(null, pstm, rs);

        }
        return count;
    }

    public int addUser(Connection connection, User user) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int execute = 0;
        if (connection != null) {
            String sql = " insert into lm_user(username, password, permission_id, deliverspot_id, emp_id) " +
                    "values (?, ?, ?, ?, ?)";
            Object[] params = {user.getUsername(), user.getPassword(), user.getPermissionId(),
                    user.getDeliverspotId(), user.getEmpId()};

            execute = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, rs);
        }
        return execute;
    }

    public int checkDs(Connection connection, String ds) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int count = 0;
        if (connection != null) {

            String sql = "select count(1) count from deliverspot where name = ?";
            Object[] params = {ds};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            if (rs.next()) {
                count = rs.getInt("count");
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return count;
    }

    public int checkEmp(Connection connection, String empno) throws SQLException {
        return 0;
    }

    public List<UserVO> detailUser(Connection connection, int id) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<UserVO> list = new ArrayList<UserVO>();
        if (connection != null) {
            String sql = "select u.user_id, u.username, d.name, p.name, e.emp_no\n" +
                    "from lm_user u\n" +
                    "         inner join deliverspot d on u.deliverspot_id = d.deliverspot_id\n" +
                    "         inner join permission p on u.permission_id = p.permission_id\n" +
                    "         inner join employee e on u.emp_id = e.emp_id\n" +
                    "where user_id = ?";
            Object[] params = {id};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            if (rs.next()) {
                UserVO userVO = new UserVO();
                userVO.setUserName(rs.getString("u.username"));
                userVO.setUserEmpno(rs.getString("e.emp_no"));
                userVO.setUserPermission(rs.getString("p.name"));
                userVO.setUserDeliverspot(rs.getString("d.name"));
                list.add(userVO);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return list;
    }

    public int queryNoidByuserid(Connection connection, int userid) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int noid = 0;
        if (connection != null) {
            String sql = "select emp_id from lm_user where user_id = ?";
            Object[] params = {userid};
           rs = BaseDao.execute(connection, pstm, rs,sql, params);
            if (rs.next()) {
                noid = rs.getInt("emp_id");
                BaseDao.closeResource(null, pstm, rs);
            }
        }
        return noid;
    }

    public int queryPermissionidByuserid(Connection connection, int userid) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int pid = 0;
        if (connection != null) {
            String sql = "select permission_id from lm_user where user_id = ?";
            Object[] params = {userid};
            rs = BaseDao.execute(connection, pstm, rs,sql, params);
            if (rs.next()) {
                pid = rs.getInt("permission_id");
                BaseDao.closeResource(null, pstm, rs);
            }
        }
        return pid;
    }

    public int queryDeliversportidByuserid(Connection connection, int userid) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int did = 0;
        if (connection != null) {
            String sql = "select deliverspot_id from lm_user where user_id = ?";
            Object[] params = {userid};
            rs = BaseDao.execute(connection, pstm, rs,sql, params);
            if (rs.next()) {
                did = rs.getInt("deliverspot_id");
                BaseDao.closeResource(null, pstm, rs);
            }
        }
        return did;
    }

    public int updateNameByid(Connection connection, int id, String name) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int i = 0;
        if (connection != null) {
            String sql = "update lm_user set username = ? where user_id = ?";
            Object[] params = {name, id};
            i = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, rs);

        }
        return i;
    }

    public int updateDeliversportIdByid(Connection connection, int userid, int did) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int i = 0;
        if (connection != null) {
            String sql = "update lm_user set deliverspot_id = ? where user_id = ?";
            Object[] params = {did, userid};
            i = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, rs);

        }
        return i;
    }

    public int updatePermissionIdByid(Connection connection, int userid, int pid) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int i = 0;
        if (connection != null) {
            String sql = "update lm_user set permission_id = ? where user_id = ?";
            Object[] params = {pid, userid};
            i = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, rs);

        }
        return i;
    }
}
