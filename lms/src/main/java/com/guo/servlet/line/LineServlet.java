package com.guo.servlet.line;


import com.guo.pojo.Line;
import com.guo.pojo.User;
import com.guo.service.line.LineService;
import com.guo.service.line.LineServiceImpl;

import com.guo.util.BaseMethod;
import com.guo.util.Constant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


public class LineServlet extends HttpServlet {
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
        LineService lineService = new LineServiceImpl();
        int count = lineService.queryCount();
        List<Line> list = lineService.query(Integer.parseInt(page), Integer.parseInt(limit));
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", list);
        BaseMethod.sendJSON(resp, map);

    }
    public void delete(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("lineid");
        LineService lineService = new LineServiceImpl();
        int i = lineService.deleteByid(Integer.parseInt(id));
        Map<String, String> map = BaseMethod.getMap(i);
        BaseMethod.sendJSON(resp, map);
    }
    public void add(HttpServletRequest req, HttpServletResponse resp){

        String lineNo = req.getParameter("lineNo");
        String lineName = req.getParameter("lineName");
        String length = req.getParameter("length");
        Line line = new Line();
        line.setLineNo(lineNo);
        line.setName(lineName);
        line.setLength(Integer.parseInt(length));
        LineService lineService = new LineServiceImpl();
        int i = lineService.add(line);
        Map<String, String> map = BaseMethod.getMap(i);
        BaseMethod.sendJSON(resp, map);


    }
    public void delBatch(HttpServletRequest req, HttpServletResponse resp){
        String[] lineids = req.getParameterValues("lineid");
        LineService lineService = new LineServiceImpl();
        boolean flag = false;

        for (String id : lineids) {
            int lineid = Integer.parseInt(id);
            int i = lineService.deleteByid(lineid);
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
        String lineid = req.getParameter("lineid");
        LineService lineService = new LineServiceImpl();

        List<Line> list = lineService.queryByid(Integer.parseInt(lineid));
        HashMap<String, Object> map = new HashMap<String, Object>();
        if (list != null) {
            map.put("lineNo", list.get(0).getLineNo());
            map.put("lineName", list.get(0).getName());
            map.put("length", list.get(0).getLength());


        }
        BaseMethod.sendJSON(resp,map);
    }
    public void search(HttpServletRequest req, HttpServletResponse resp) {
        String page = req.getParameter("page");
        String limit = req.getParameter("limit");
        Map condition = req.getParameterMap();
        LineService lineService = new LineServiceImpl();

        int count = lineService.queryCountBysearch(condition);
        List list = lineService.queryBysearch(Integer.parseInt(page), Integer.parseInt(limit), condition);
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", list);
        BaseMethod.sendJSON(resp,map);
    }
    public void update(HttpServletRequest req, HttpServletResponse resp) {
        String value = req.getParameter("value");
        String lineid = req.getParameter("lineid");
        String column = req.getParameter("column");
        LineService lineService = new LineServiceImpl();


        int i = lineService.updateInfoByid(Integer.parseInt(lineid), value, column);
        Map<String, String> map = BaseMethod.getMap(i);
        BaseMethod.sendJSON(resp, map);
    }


}
