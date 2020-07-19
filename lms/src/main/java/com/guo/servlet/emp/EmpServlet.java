package com.guo.servlet.emp;

import com.guo.pojo.DeliverSpotVo;
import com.guo.pojo.EmpVO;
import com.guo.pojo.Employee;
import com.guo.service.deliverspot.DeliverspotService;
import com.guo.service.deliverspot.DeliverspotServiceImpl;
import com.guo.service.emp.EmpService;
import com.guo.service.emp.EmpServiceImpl;
import com.guo.util.BaseMethod;
import com.guo.util.Constant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EmpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method != null && method.equals("query")) {
            this.query(req,resp);
        }
        if (method != null && method.equals("delete")) {
            this.delete(req,resp);
        }
        if (method != null && method.equals("add")) {
            this.add(req,resp);
        }
        if (method != null && method.equals("deleteBatch")) {
            this.delBatch(req,resp);
        }
        if (method != null && method.equals("detail")) {
            this.detail(req,resp);
        }
        if (method != null && method.equals("search")) {
            this.search(req,resp);
        }
        if (method != null && method.equals("update")) {
            this.update(req,resp);
        }

    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void query(HttpServletRequest req, HttpServletResponse resp) {
        String page = req.getParameter("page");
        String limit = req.getParameter("limit");
        EmpService empService = new EmpServiceImpl();
        int count = empService.queryCount();
        List<EmpVO> emplist = empService.query(Integer.parseInt(page), Integer.parseInt(limit));
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", emplist);
        BaseMethod.sendJSON(resp, map);

    }
    public void delete(HttpServletRequest req, HttpServletResponse resp) {
        String empid = req.getParameter("empid");
        EmpService empService = new EmpServiceImpl();
        int i = empService.deleteByid(Integer.parseInt(empid));
        Map<String, String> map = BaseMethod.getMap(i);
        BaseMethod.sendJSON(resp, map);
    }
    public void add(HttpServletRequest req, HttpServletResponse resp){
        String empno = req.getParameter("empno");
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        String age = req.getParameter("age");
        String tel = req.getParameter("tel");
        String startworktime = req.getParameter("startworktime");
        String salary = req.getParameter("salary");
        String email = req.getParameter("email");
        String cardid = req.getParameter("cardid");
        String position = req.getParameter("position");
        String state = req.getParameter("state");
        String dsname = req.getParameter("dsname");
        DeliverspotService deliverspotService = new DeliverspotServiceImpl();
        List<DeliverSpotVo> dslist = deliverspotService.queryIdByname(dsname);
        Employee employee = new Employee();
        employee.setEmpNo(empno);
        employee.setName(name);
        employee.setGender(Integer.parseInt(gender));
        employee.setAge(Integer.parseInt(age));
        employee.setTel(tel);
        employee.setStartworktime(startworktime);
        employee.setSalary(Integer.parseInt(salary));
        employee.setEmail(email);
        employee.setCardid(cardid);
        employee.setPositionId(Integer.parseInt(position));
        employee.setFlag(Integer.parseInt(state));
        employee.setDeliverspotId(dslist.get(0).getDeliverspotId());
        EmpService empService = new EmpServiceImpl();
        int i = empService.add(employee);
        Map<String, String> map = BaseMethod.getMap(i);
        BaseMethod.sendJSON(resp, map);


    }
    public void delBatch(HttpServletRequest req, HttpServletResponse resp){
        String[] empids = req.getParameterValues("empid");
        EmpService empService = new EmpServiceImpl();
        boolean flag = false;

        for (String id : empids) {
            int empid = Integer.parseInt(id);
            int i = empService.deleteByid(empid);
            if (i > 0) {
                flag = true;
            } else {
                flag = false;
                break;
            }
        }

        HashMap<String, String> map = new HashMap<String, String>();
        if (flag = true) {
            map.put(Constant.RESULT, "true");
        } else {
            map.put(Constant.RESULT, "false");
        }
        BaseMethod.sendJSON(resp,map);
    }
    public void detail(HttpServletRequest req, HttpServletResponse resp) {
        String empid = req.getParameter("empid");
        EmpService empService = new EmpServiceImpl();
        List<EmpVO> list = empService.queryByid(Integer.parseInt(empid));
        HashMap<String, Object> map = new HashMap<String, Object>();
        if (list != null) {
            map.put("empno", list.get(0).getEmpNo());
            map.put("empname", list.get(0).getEmpName());
            map.put("gender", list.get(0).getGender());
            map.put("age", list.get(0).getAge());
            map.put("tel", list.get(0).getTel());
            map.put("startworktime", list.get(0).getStartworktime());
            map.put("salary", list.get(0).getSalary());
            map.put("email", list.get(0).getEmail());
            map.put("cardid", list.get(0).getCardId());
            map.put("position", list.get(0).getPosition());
            map.put("state", list.get(0).getState());
            map.put("deliverspot", list.get(0).getDeliverspot());
        }
        BaseMethod.sendJSON(resp,map);
    }
    public void search(HttpServletRequest req, HttpServletResponse resp) {
        String page = req.getParameter("page");
        String limit = req.getParameter("limit");
        Map condition = req.getParameterMap();
        EmpService empService = new EmpServiceImpl();
        int count = empService.queryCountBysearch(condition);
        List list = empService.queryBysearch(Integer.parseInt(page), Integer.parseInt(limit), condition);
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", list);
        BaseMethod.sendJSON(resp,map);
    }
    public void update(HttpServletRequest req, HttpServletResponse resp) {
        String value = req.getParameter("value");
        String empid = req.getParameter("empid");
        String column = req.getParameter("column");
        EmpService empService = new EmpServiceImpl();
        int i = empService.updateInfoById(Integer.parseInt(empid), value, column);
        Map<String, String> map = BaseMethod.getMap(i);
        BaseMethod.sendJSON(resp, map);
    }


}
