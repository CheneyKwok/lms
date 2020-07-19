package com.guo.dao.emp;

import com.guo.dao.BaseDao;
import com.guo.pojo.DeliverSpotVo;
import com.guo.pojo.EmpVO;
import com.guo.pojo.Employee;
import com.guo.pojo.UserVO;
import com.guo.service.deliverspot.DeliverspotService;
import com.guo.service.deliverspot.DeliverspotServiceImpl;
import com.guo.service.line.LineService;
import com.guo.service.line.LineServiceImpl;
import com.guo.util.BaseMethod;
import com.sun.org.apache.regexp.internal.RESyntaxException;
import org.junit.Test;
import org.omg.CORBA.OBJ_ADAPTER;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EmpDaoImpl implements EmpDao {

    public List<EmpVO> query(Connection connection , int start , int rows) throws SQLException {
        PreparedStatement pstm =null;
        ResultSet rs = null;
        ArrayList<EmpVO> list = new ArrayList<EmpVO>();
        if (connection != null) {
            String sql = "select e.emp_id, emp_no,\n" +
                    "       e.name,\n" +
                    "       (case gender\n" +
                    "            when 1 then '男'\n" +
                    "            when 0 then '女'\n" +
                    "           end) gender,\n" +
                    "       age,\n" +
                    "       e.tel,\n" +
                    "       startworktime,\n" +
                    "       salary,\n" +
                    "       email,\n" +
                    "       cardid,\n" +
                    "       p.name,\n" +
                    "       (case e.flag\n" +
                    "           when 1 then '在职'\n" +
                    "           when 0 then '离职'\n" +
                    "           end) state,\n" +
                    "       d.name\n" +
                    "from employee e\n" +
                    "         inner join position p on e.position_id = p.position_id\n" +
                    "         inner join deliverspot d on e.deliverspot_id = d.deliverspot_id limit ?,? ";
            Object[] params = {start,rows};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            while (rs.next()) {
                EmpVO empVO = new EmpVO();
                empVO.setEmpid(rs.getInt("e.emp_id"));
                empVO.setEmpNo(rs.getString("emp_no"));
                empVO.setEmpName(rs.getString("e.name"));
                empVO.setGender(rs.getString("gender"));
                empVO.setAge(rs.getInt("age"));
                empVO.setTel(rs.getString("e.tel"));
                empVO.setStartworktime(rs.getString("startworktime"));
                empVO.setSalary(rs.getString("salary"));
                empVO.setEmail(rs.getString("email"));
                empVO.setCardId(rs.getString("cardid"));
                empVO.setPosition(rs.getString("p.name"));
                empVO.setState(rs.getString("state"));
                empVO.setDeliverspot(rs.getString("d.name"));
                list.add(empVO);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return list;
    }

    public int queryCount(Connection connection) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int execute  = 0;
        if (connection != null) {
            String sql = "select count(1) count\n" +
                    "from employee e\n" +
                    "         inner join position p on e.position_id = p.position_id\n" +
                    "         inner join deliverspot d on e.deliverspot_id = d.deliverspot_id";
            Object[] params = {};
          rs= BaseDao.execute(connection, pstm, rs,sql, params);
            if (rs.next()) {
                execute = rs.getInt("count");
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return execute;
    }

    public List<EmpVO> queryBysearch(Connection connection, int start, int rows, Map<String, String[]> condition) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<EmpVO> list = new ArrayList<EmpVO>();
        if (connection != null) {
            String sql = "select e.name,\n" +
                    "       emp_no,\n" +
                    "       case gender when 1 then '男' when 0 then '女' end   gender,\n" +
                    "       age,\n" +
                    "       e.tel,\n" +
                    "       startworktime,\n" +
                    "       salary,\n" +
                    "       email,\n" +
                    "       cardid,\n" +
                    "       p.name,\n" +
                    "       case e.flag when 1 then '在职' when 0 then '离职' end state,\n" +
                    "       d.name\n" +
                    "from employee e\n" +
                    "         inner join deliverspot d on e.deliverspot_id = d.deliverspot_id\n" +
                    "         inner join position p on e.position_id = p.position_id\n" +
                    "where 1 = 1 ";
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

//            Object[] params = {start, rows};
            rs = BaseDao.execute(connection, pstm, rs, sb.toString(), params);

            while (rs.next()) {
                EmpVO empVO = new EmpVO();
                empVO.setEmpNo(rs.getString("emp_no"));
                empVO.setEmpName(rs.getString("e.name"));
                empVO.setGender(rs.getString("gender"));
                empVO.setAge(rs.getInt("age"));
                empVO.setTel(rs.getString("e.tel"));
                empVO.setStartworktime(rs.getString("startworktime"));
                empVO.setSalary(rs.getString("salary"));
                empVO.setEmail(rs.getString("email"));
                empVO.setCardId(rs.getString("cardid"));
                empVO.setPosition(rs.getString("p.name"));
                empVO.setState(rs.getString("state"));
                empVO.setDeliverspot(rs.getString("d.name"));
                list.add(empVO);

            }

            BaseDao.closeResource(null, pstm, rs);
        }

        return list;
    }

    public int queryCountBysearch(Connection connection, Map<String, String[]> condition) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int count = 0;
        if (connection != null) {
            String sql = "select count(1) count,e.name,\n" +
                    "                    emp_no,\n" +
                    "                    case gender when 1 then '男' when 0 then '女' end gender,\n" +
                    "                    age,\n" +
                    "                    e.tel,\n" +
                    "                    startworktime,\n" +
                    "                    salary,\n" +
                    "                    email,\n" +
                    "                    cardid,\n" +
                    "                    p.name,\n" +
                    "                    case e.flag when 1 then '在职' when 0 then '离职' end state,\n" +
                    "                    d.name\n" +
                    "             from employee e\n" +
                    "                      inner join deliverspot d on e.deliverspot_id = d.deliverspot_id\n" +
                    "                      inner join position p on e.position_id = p.position_id\n" +
                    "             where 1 = 1 ";
            Object[] params = null;
            ArrayList<Object> list = new ArrayList<Object>();
            StringBuilder sb = new StringBuilder(sql);
            Set<String> keySet = condition.keySet();
            for (String key : keySet) {
                if ("method".equals(key) || "page".equals(key) || "limit".equals(key)) {
                    continue;
                }
                String value = condition.get(key)[0];
                if (value != null && !"".equals(value)) {
                    sb.append(" and " + key + " like ? ");
                    list.add("%" + value + "%");
                }
            }
            params = list.toArray();

            rs = BaseDao.execute(connection, pstm, rs, sb.toString(), params);
            if (rs.next()) {
                count = rs.getInt("count");
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return count;
    }

    public List<EmpVO> queryIdByno(Connection connection, String no) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<EmpVO> list = null;
        if (connection != null) {
            String sql = "select emp_id from employee where emp_no = ?";
            Object[] params = {no};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            list= new ArrayList<EmpVO>();
            while (rs.next()) {
                EmpVO empVO = new EmpVO();
                empVO.setEmpid(rs.getInt("emp_id"));
                list.add(empVO);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return list;
    }

    public List<EmpVO> queryByid(Connection connection, int id) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<EmpVO> list = new ArrayList<EmpVO>();
        if (connection != null) {
            String sql = "select e.name,\n" +
                    "       emp_no,\n" +
                    "       case gender when 1 then '男' when 0 then '女' end gender,\n" +
                    "       age,\n" +
                    "       e.tel,\n" +
                    "       startworktime,\n" +
                    "       salary,\n" +
                    "       email,\n" +
                    "       cardid,\n" +
                    "       p.name,\n" +
                    "       case e.flag when 1 then '在职' when 0 then '离职' end state,\n" +
                    "       d.name\n" +
                    "from employee e\n" +
                    "         inner join deliverspot d on e.deliverspot_id = d.deliverspot_id\n" +
                    "         inner join position p on e.position_id = p.position_id\n" +
                    "where e.emp_id = ? ";
            Object[] params = {id};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            while (rs.next()) {
                EmpVO empVO = new EmpVO();
                empVO.setEmpName(rs.getString("e.name"));
                empVO.setEmpNo(rs.getString("emp_no"));
                empVO.setGender(rs.getString("gender"));
                empVO.setAge(rs.getInt("age"));
                empVO.setTel(rs.getString("e.tel"));
                empVO.setStartworktime(rs.getString("startworktime"));
                empVO.setSalary(rs.getString("salary"));
                empVO.setEmail(rs.getString("email"));
                empVO.setCardId(rs.getString("cardid"));
                empVO.setPosition(rs.getString("p.name"));
                empVO.setState(rs.getString("state"));
                empVO.setDeliverspot(rs.getString("d.name"));
                list.add(empVO);

            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return list;
    }

    public int updateNameByid(Connection connection, int id, String no) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int i = 0;
        if (connection != null) {
            String sql = "update employee set emp_no  =  ? where emp_id = ?";
            Object[] params = {no, id};
            i = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, rs);
        }
        return i;
    }

    public int updateInfoById(Connection connection, int id, String value, String column) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "";
        Object[] params = {};
        int execute = 0;
        if (connection != null) {
            if (column.equals("empNo")) {
                sql = "update employee set emp_no = ? where emp_id = ?";
                params = new Object[]{value,id};
            } else if (column.equals("empName")) {
                sql = "update employee set name = ? where emp_id = ?";
                params = new Object[]{value,id};
            }
            else if (column.equals("cardid")) {
                sql = "update employee set cardid = ? where emp_id = ?";
                params = new Object[]{value,id};
            }
            else if (column.equals("gender")) {
                sql = "update employee set gender = ? where emp_id = ?";
                params = new Object[]{Integer.parseInt(value),id};
            }
            else if (column.equals("age")) {
                sql = "update employee set age = ? where emp_id = ?";
                params = new Object[]{Integer.parseInt(value),id};
            }
            else if (column.equals("tel")) {
                sql = "update employee set tel = ? where emp_id = ?";
                params = new Object[]{value,id};
            }
            else if (column.equals("startworktime")) {
                sql = "update employee set startworktime = ? where emp_id = ?";
                params = new Object[]{value,id};
            }
            else if (column.equals("salary")) {
                sql = "update employee set salary = ? where emp_id = ?";
                params = new Object[]{Integer.parseInt(value),id};
            }
            else if (column.equals("email")) {
                sql = "update employee set email = ? where emp_id = ?";
                params = new Object[]{value,id};
            }
            else if (column.equals("position")) {
                sql = "update employee set position_id = ? where emp_id = ?";
                params = new Object[]{Integer.parseInt(value),id};
            }
            else if (column.equals("state")) {
                sql = "update employee set flag = ? where emp_id = ?";
                params = new Object[]{Integer.parseInt(value),id};
            }
            else if (column.equals("deliverspot")) {
                DeliverspotService deliverspotService = new DeliverspotServiceImpl();
                List<DeliverSpotVo> list = deliverspotService.queryIdByname(value);
                int d_id = list.get(0).getDeliverspotId();
                sql = "update employee set deliverspot_id = ? where emp_id = ?";
                params = new Object[]{d_id,id};
            }

            execute = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, rs);

        }
        return execute;
    }

    public int deleteByid(Connection connection, int id) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int execute = 0;
        if (connection != null) {
            String sql = "delete from employee where  emp_id = ?";
            Object[] params = {id};
             execute = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, rs);
        }
        return execute;
    }

    public int add(Connection connection, Employee emp) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int execute = 0;
        if (connection != null) {
            String sql = "insert into employee\n" +
                    "(emp_no, name, gender, age, tel, startworktime, salary, email, cardid, position_id, flag,\n" +
                    " deliverspot_id)\n" +
                    "values (?,?,?,?,?,?,?,?,?,?,?,?)";
            Object[] params = {emp.getEmpNo(), emp.getName(), emp.getGender(), emp.getAge(), emp.getTel(), emp.getStartworktime(),
                    emp.getSalary(), emp.getEmail(), emp.getCardid(), emp.getPositionId(), emp.getFlag(), emp.getDeliverspotId()};
            execute = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, rs);
        }
        return execute;
    }
}
