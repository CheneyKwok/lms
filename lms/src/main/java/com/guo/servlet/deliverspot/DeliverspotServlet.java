package com.guo.servlet.deliverspot;

import com.guo.pojo.City;
import com.guo.pojo.DeliverSpot;
import com.guo.pojo.DeliverSpotVo;

import com.guo.pojo.Province;
import com.guo.service.deliverspot.DeliverspotService;
import com.guo.service.deliverspot.DeliverspotServiceImpl;

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

public class DeliverspotServlet extends HttpServlet {
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
        if (method != null && method.equals("search")) {
            this.search(req,resp);
        }
        if (method != null && method.equals("update")) {
            this.update(req,resp);
        }
        if (method != null && method.equals("queryProvince")) {
            this.queryProvince(req,resp);
        }
        if (method != null && method.equals("queryCity")) {
            this.queryCity(req,resp);
        }




    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void query(HttpServletRequest req, HttpServletResponse resp) {
        String page = req.getParameter("page");
        String limit = req.getParameter("limit");
        DeliverspotService deliverspotService = new DeliverspotServiceImpl();
        int count = deliverspotService.queryCount();
        List<DeliverSpotVo> list = deliverspotService.query(Integer.parseInt(page), Integer.parseInt(limit));
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", list);
        BaseMethod.sendJSON(resp, map);

    }
    public void delete(HttpServletRequest req, HttpServletResponse resp) {
        String deliverspotid = req.getParameter("deliverspotid");
        DeliverspotService deliverspotService = new DeliverspotServiceImpl();
        int i = deliverspotService.deleteByid(Integer.parseInt(deliverspotid));
        Map<String, String> map = BaseMethod.getMap(i);
        BaseMethod.sendJSON(resp, map);
    }
    public void add(HttpServletRequest req, HttpServletResponse resp){
        String deliverspotName = req.getParameter("deliverspotName");
        String province_id = req.getParameter("province_id");
        String city_id = req.getParameter("city_id");
        String tel = req.getParameter("tel");
        String startscope = req.getParameter("startscope");
        String startprice = req.getParameter("startprice");
        String secondprice = req.getParameter("secondprice");
        DeliverspotService deliverspotService = new DeliverspotServiceImpl();

        DeliverSpot d = new DeliverSpot();
        d.setName(deliverspotName);
        d.setProvinceId(Integer.parseInt(province_id));
        d.setCityId(Integer.parseInt(city_id));
        d.setTel(tel);
        d.setStartscope(startscope);
        d.setStartprice(startprice);
        d.setSceondprice(secondprice);
        int i = deliverspotService.add(d);
        Map<String, String> map = BaseMethod.getMap(i);
        BaseMethod.sendJSON(resp, map);


    }
    public void delBatch(HttpServletRequest req, HttpServletResponse resp){
        String[] deliverspotids = req.getParameterValues("deliverspotid");
        DeliverspotService deliverspotService = new DeliverspotServiceImpl();
        boolean flag = false;

        for (String id : deliverspotids) {
            int did = Integer.parseInt(id);
            int i = deliverspotService.deleteByid(did);
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
    public void search(HttpServletRequest req, HttpServletResponse resp) {
        String page = req.getParameter("page");
        String limit = req.getParameter("limit");
        Map condition = req.getParameterMap();
        DeliverspotService deliverspotService = new DeliverspotServiceImpl();
        int count = deliverspotService.queryCountBysearch(condition);
        List list = deliverspotService.queryBysearch(Integer.parseInt(page), Integer.parseInt(limit), condition);
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
        DeliverspotService deliverspotService = new DeliverspotServiceImpl();
        int i = deliverspotService.updateInfoById(Integer.parseInt(empid), value, column);
        Map<String, String> map = BaseMethod.getMap(i);
        BaseMethod.sendJSON(resp, map);
    }
    public void queryProvince(HttpServletRequest req, HttpServletResponse resp) {
        DeliverspotService deliverspotService = new DeliverspotServiceImpl();
        List<Province> list = deliverspotService.queryProvince();
        BaseMethod.sendJSON(resp,list.toArray());
    }
    public void queryCity(HttpServletRequest req, HttpServletResponse resp) {
        String pid = req.getParameter("pid");
        DeliverspotService deliverspotService = new DeliverspotServiceImpl();
        List<City> list = deliverspotService.queryCity(Integer.parseInt(pid));
        BaseMethod.sendJSON(resp,list.toArray());
    }




}
