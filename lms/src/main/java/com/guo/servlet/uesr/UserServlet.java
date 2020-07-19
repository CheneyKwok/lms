package com.guo.servlet.uesr;

import com.guo.dao.BaseDao;
import com.guo.pojo.*;
import com.guo.service.deliverspot.DeliverspotService;
import com.guo.service.deliverspot.DeliverspotServiceImpl;
import com.guo.service.emp.EmpService;
import com.guo.service.emp.EmpServiceImpl;
import com.guo.service.permission.PermissionServiceImpl;
import com.guo.service.user.UserService;
import com.guo.service.user.UserServiceImpl;
import com.guo.util.BaseMethod;
import com.guo.util.Constant;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String method = req.getParameter("method");
        if (method!=null && method.equals("updatepwd")) {
            this.updatePwd(req,resp);
        }
        if (method!=null && method.equals("checkpwd")) {
            this.checkPwd(req,resp);
        }
        if (method!= null && method.equals("query")) {
            this.query(req,resp);
        }
        if (method != null && method.equals("deleteBatch")) {
            this.deleteBatch(req,resp);
        }
        if (method != null && method.equals("adduser")) {
            this.add(req, resp);
        }
        if (method != null && method.equals("checkds")) {
            this.checkDs(req, resp);
        }
        if (method != null && method.equals("delete")) {
            this.delete(req, resp);
        }
        if (method != null && method.equals("detail")) {
            this.detail(req, resp);
        }
        if (method != null && method.equals("search")) {
            this.queryBysearch(req, resp);
        }
        if (method != null && method.equals("updateName")) {
            this.updateName(req, resp);
        }
        if (method != null && method.equals("updateEmpno")) {
            this.updateEmpno(req, resp);
        }
        if (method != null && method.equals("updatePermission")) {
            this.updatePermission(req, resp);
        }
        if (method != null && method.equals("updateDeliverspot")) {
            this.updateDeliverspot(req, resp);
        }

    }



    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void updatePwd(HttpServletRequest req, HttpServletResponse resp) {
        Object o = req.getSession().getAttribute(Constant.USER_SESSION);
        String newpassword = req.getParameter("newpassword");
        boolean flag = false;
        Map<String, String> resultMap = new HashMap<String, String>();
        if (o != null && !StringUtils.isNullOrEmpty(newpassword)) {
            UserService userService = new UserServiceImpl();
            flag = userService.updatePwd(((User) o).getUserId(), newpassword);
            if (flag) {
                resultMap.put("result", "true");
               // resultMap.put("URL", "/lms/login.jsp");
                req.getSession().removeAttribute(Constant.USER_SESSION);
            } else {
                resultMap.put("result", "false");
            }

        } else {
            resultMap.put("result", "error");
        }
        BaseMethod.sendJSON(resp,resultMap);

    }
    public void checkPwd(HttpServletRequest req, HttpServletResponse resp) {
        Object o = req.getSession().getAttribute(Constant.USER_SESSION);
        String oldpassword = req.getParameter("oldpassword");
        Map<String, String> resultMap = new HashMap<String,String>();
        if (o == null) {
            resultMap.put("result","sessionerror");
        } else if (StringUtils.isNullOrEmpty(oldpassword)) {
            resultMap.put("result","error");
        } else {
            String password = ((User) o).getPassword();
            if (password.equals(oldpassword)) {
                resultMap.put("result", "true");
            } else {
                resultMap.put("result", "false");
            }
        }

       BaseMethod.sendJSON(resp,resultMap);
    }
    public void query(HttpServletRequest req, HttpServletResponse resp) {
        String page = req.getParameter("page");
        String limit = req.getParameter("limit");
        int currentPage = Integer.parseInt(page);
        int limits = Integer.parseInt(limit);
        UserService userService = new UserServiceImpl();
        int usercount = userService.getUsercount();
        List<UserVO> userlist = userService.getUsetlist(currentPage,limits);
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", usercount);
        map.put("data", userlist);
        BaseMethod.sendJSON(resp,map);
//        try {
//            resp.setContentType("application/json");
//            PrintWriter writer = resp.getWriter();
//            GsonBuilder builder = new GsonBuilder();
//            Gson gson = builder.create();
//            writer.write(gson.toJson(map));
//            writer.flush();
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
    public void queryBysearch(HttpServletRequest req, HttpServletResponse resp) {
        String page = req.getParameter("page");
        String limit = req.getParameter("limit");
        Map<String,String[]> condition = req.getParameterMap();
        int currentPage = Integer.parseInt(page);
        int limits = Integer.parseInt(limit);
        UserService userService = new UserServiceImpl();
        int usercount = userService.getUsercountBysearch(condition);
        List<UserVO> userlist = userService.getUsetlistBysearch(currentPage,limits,condition);
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", usercount);
        map.put("data", userlist);
        BaseMethod.sendJSON(resp,map);

    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) {
        String userid = req.getParameter("userid");
        UserService userService = new UserServiceImpl();
        int i = userService.delUserByid(Integer.parseInt(userid));
        HashMap<String, String> map = new HashMap<String, String>();
        if (i > 0) {
            map.put(Constant.RESULT, "true");
        } else {
            map.put(Constant.RESULT, "false");
        }
        BaseMethod.sendJSON(resp,map);


    }

    public void deleteBatch(HttpServletRequest req, HttpServletResponse resp) {

        String[] userids = req.getParameterValues("userid");
        boolean flag = false;

        for (String i : userids) {
            int id = Integer.parseInt(i);
            UserService userService = new UserServiceImpl();
            int rs = userService.delUserByid(id);
            if (rs > 0) {
                flag = true;
            } else {
                flag = false;
                break;
            }
        }
        HashMap<String, String> map = new HashMap<String, String>();
        if (flag) {
            map.put("result", "true");
        } else {
            map.put("result", "false");
        }

      BaseMethod.sendJSON(resp,map);

    }
    public void add(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String per = req.getParameter("permission");
        String dsname = req.getParameter("dsname");
        String empno = req.getParameter("empno");
        int permission = Integer.parseInt(per);
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql1 = "select deliverspot_id from deliverspot where name = ?";
        String sql2 = "select emp_id from employee where emp_no = ?";
        Object[] params1 = {dsname};
        Object[] params2 = {empno};
        int dsid = 0;
        int empid= 0;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            ResultSet rs1 = BaseDao.execute(connection, pstm, rs, sql1, params1);
            ResultSet rs2 = BaseDao.execute(connection, pstm, rs, sql2, params2);
            if (rs1.next()) {
                dsid = rs1.getInt("deliverspot_id");
            }else {
                System.out.println("deliverspot-id-false");
            }

            if (rs2.next()) {
                empid = rs2.getInt("emp_id");
            }else {
                System.out.println("emp-id-false");
            }

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                System.out.println("addrollback=======");
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection, pstm, rs);
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setPermissionId(permission);
        user.setDeliverspotId(dsid);
        user.setEmpId(empid);
        UserService userService = new UserServiceImpl();
        int add = userService.addUser(user);
        Map<String, String> map = new HashMap<String, String>();
        if (add > 0) {
            map.put(Constant.RESULT, "true");
        } else {
            map.put(Constant.RESULT, "false");
        }
        BaseMethod.sendJSON(resp,map);
    }
    public void checkDs(HttpServletRequest req, HttpServletResponse resp) {
        String ds = req.getParameter("deliverspot");
        UserService userService = new UserServiceImpl();
        int i = userService.checkDs(ds);
        Map<String, String> map = new HashMap<String, String>();
        if (i > 0) {
            map.put(Constant.RESULT, "true");
        } else {
            map.put(Constant.RESULT, "false");
        }
        BaseMethod.sendJSON(resp, map);
    }

    public void detail(HttpServletRequest req, HttpServletResponse resp) {
        String userid = req.getParameter("userid");
        UserService userService = new UserServiceImpl();
        List<UserVO> userlist = userService.detailUser(Integer.parseInt(userid));
        HashMap<String, Object> map = new HashMap<String, Object>();
//        System.out.println(userlist.get(0).getUserEmpno());
        if (userlist != null) {
            map.put("userName",userlist.get(0).getUserName());
            map.put("userEmpno",userlist.get(0).getUserEmpno());
            map.put("userDeliverspot",userlist.get(0).getUserDeliverspot());
            map.put("userPermission",userlist.get(0).getUserPermission());
        }
        BaseMethod.sendJSON(resp,map);
    }

    public void updateName(HttpServletRequest req, HttpServletResponse resp) {
        String userid = req.getParameter("userid");
        String value = req.getParameter("value");
        UserService userService = new UserServiceImpl();
        int i = userService.updateUserNameByid(Integer.parseInt(userid), value);
        HashMap<String, String> map = new HashMap<String, String>();
        if (i > 0) {
            map.put(Constant.RESULT, "true");
        } else {
            map.put(Constant.RESULT, "false");
        }
        BaseMethod.sendJSON(resp, map);
    }

    public void updateEmpno(HttpServletRequest req, HttpServletResponse resp) {
        String userid = req.getParameter("userid");
        String value = req.getParameter("value");
        EmpService empService = new EmpServiceImpl();
        UserService userService = new UserServiceImpl();
        int noid = userService.queryNoidByuserid(Integer.parseInt(userid));
        int i = empService.updateNameByid(noid, value);
        HashMap<String, String> map = new HashMap<String, String>();
        if (i > 0) {
            map.put(Constant.RESULT, "true");
        } else {
            map.put(Constant.RESULT, "false");
        }
        BaseMethod.sendJSON(resp, map);
    }

    public void updateDeliverspot(HttpServletRequest req, HttpServletResponse resp) {
        String userid = req.getParameter("userid");
        String value = req.getParameter("value");
        DeliverspotService deliverspotService = new DeliverspotServiceImpl();
        List<DeliverSpotVo> list = deliverspotService.queryIdByname(value);
        UserService userService = new UserServiceImpl();
        int i = userService.updateDeliversportIdByid(Integer.parseInt(userid), list.get(0).getDeliverspotId());
        HashMap<String, String> map = new HashMap<String, String>();
        if (i > 0) {
            map.put(Constant.RESULT, "true");
        } else {
            map.put(Constant.RESULT, "false");
        }
        BaseMethod.sendJSON(resp, map);
    }

    public void updatePermission(HttpServletRequest req, HttpServletResponse resp) {
        String userid = req.getParameter("userid");
        String value = req.getParameter("value");
        PermissionServiceImpl permissionService = new PermissionServiceImpl();
        List<PermissionVO> list = permissionService.queryIdByname(value);
        UserService userService = new UserServiceImpl();
        int i = userService.updatePermissionIdByid(Integer.parseInt(userid), list.get(0).getPermissionId());
        HashMap<String, String> map = new HashMap<String, String>();
        if (i > 0) {
            map.put(Constant.RESULT, "true");
        } else {
            map.put(Constant.RESULT, "false");
        }
        BaseMethod.sendJSON(resp, map);

    }




}
