package com.guo.servlet.uesr;

import com.guo.pojo.User;
import com.guo.service.user.UserService;
import com.guo.service.user.UserServiceImpl;
import com.guo.util.BaseMethod;
import com.guo.util.Constant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserService userService = new UserServiceImpl();
        User user = userService.login(username, password);
        Map<String, String> map = new HashMap<String, String>();
        if (user != null ) {
            if (user.getPassword().equals(password)) {

                req.getSession().setAttribute(Constant.USER_SESSION, user);


                map.put(Constant.RESULT, "true");
            } else {
                map.put(Constant.RESULT, "passfalse");
            }
        } else {
           map.put(Constant.RESULT, "namefalse");

        }
        BaseMethod.sendJSON(resp,map);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
