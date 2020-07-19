package com.guo.servlet.car;

import com.alibaba.fastjson.JSONArray;
import com.guo.dao.BaseDao;
import com.guo.pojo.*;
import com.guo.service.car.CarService;
import com.guo.service.car.CarServiceImpl;
import com.guo.service.deliverspot.DeliverspotService;
import com.guo.service.deliverspot.DeliverspotServiceImpl;
import com.guo.service.emp.EmpService;
import com.guo.service.emp.EmpServiceImpl;
import com.guo.service.line.LineService;
import com.guo.service.line.LineServiceImpl;
import com.guo.util.BaseMethod;
import com.guo.util.Constant;
import com.sun.imageio.plugins.common.I18N;
import org.omg.PortableInterceptor.INACTIVE;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CarServlet extends HttpServlet {
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
        if (method != null && method.equals("checkLine")) {
            this.checkLine(req,resp);
        }
        if (method != null && method.equals("queryCarList")) {
            this.queryCarList(req,resp);
        }
        if (method != null && method.equals("queryCarState")) {
            this.queryCarState(req,resp);
        }
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void query(HttpServletRequest req, HttpServletResponse resp) {
        String page = req.getParameter("page");
        String limit = req.getParameter("limit");
        CarService carService = new CarServiceImpl();
        int count = carService.queryCount();
        List<CarVO> list = carService.query(Integer.parseInt(page), Integer.parseInt(limit));
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", list);
        JSONArray.toJSONString(map);
        BaseMethod.sendJSON(resp, map);

    }
    public void delete(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("carid");
        CarService carService = new CarServiceImpl();
        int i = carService.deleteByid(Integer.parseInt(id));
        Map<String, String> map = BaseMethod.getMap(i);
        BaseMethod.sendJSON(resp, map);
    }
    public void add(HttpServletRequest req, HttpServletResponse resp){
        String carno = req.getParameter("carNo");
        String cartype = req.getParameter("carType");
        String carsize = req.getParameter("carSize");
        String tonnage = req.getParameter("tonnage");
        String buytime = req.getParameter("buyTime");
        String carstate = req.getParameter("carState");
        String line = req.getParameter("line");
        LineService lineService = new LineServiceImpl();
        int lineId = lineService.queryIdByName(line);

        Car car = new Car();
        car.setCarNo(carno);
        car.setCartypeId(Integer.parseInt(cartype));
        car.setCarSize(Integer.parseInt(carsize));
        car.setTonnage(Integer.parseInt(tonnage));
        car.setBuytime(buytime);
        car.setCarstateId(Integer.parseInt(carstate));
        car.setLineId(lineId);
        CarService carService = new CarServiceImpl();
        int i = carService.add(car);
        Map<String, String> map = BaseMethod.getMap(i);
        BaseMethod.sendJSON(resp, map);


    }
    public void delBatch(HttpServletRequest req, HttpServletResponse resp){
        String[] carids = req.getParameterValues("carid");
        CarService carService = new CarServiceImpl();
        boolean flag = false;

        for (String id : carids) {
            int carid = Integer.parseInt(id);
            int i = carService.deleteByid(carid);
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
        String carid = req.getParameter("carid");
        CarService carService = new CarServiceImpl();
        List<CarVO> list = carService.queryByid(Integer.parseInt(carid));
        HashMap<String, Object> map = new HashMap<String, Object>();
        if (list != null) {
            map.put("carNo", list.get(0).getCarNo());
            map.put("carType", list.get(0).getCarType());
            map.put("carSize", list.get(0).getCarSize());
            map.put("tonnage", list.get(0).getTonnage());
            map.put("buyTime", list.get(0).getBuyTime());
            map.put("carState", list.get(0).getCarState());
            map.put("line", list.get(0).getLine());

        }
        BaseMethod.sendJSON(resp,map);
    }
    public void search(HttpServletRequest req, HttpServletResponse resp) {
        String page = req.getParameter("page");
        String limit = req.getParameter("limit");
        Map condition = req.getParameterMap();
        CarService carService = new CarServiceImpl();
        int count = carService.queryCountBysearch(condition);
        List list = carService.queryBysearch(Integer.parseInt(page), Integer.parseInt(limit), condition);
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", list);
        BaseMethod.sendJSON(resp,map);
    }
    public void update(HttpServletRequest req, HttpServletResponse resp) {
        String value = req.getParameter("value");
        String carid = req.getParameter("carid");
        String column = req.getParameter("column");
        CarServiceImpl carService = new CarServiceImpl();
        int i = carService.updateInfoById(Integer.parseInt(carid), value, column);
        Map<String, String> map = BaseMethod.getMap(i);
        BaseMethod.sendJSON(resp, map);
    }
    public void checkLine(HttpServletRequest req, HttpServletResponse resp) {
        String value = req.getParameter("value");
        LineService lineService = new LineServiceImpl();
        int i = lineService.queryIdByName(value);
        Map<String, String> map = BaseMethod.getMap(i);
        BaseMethod.sendJSON(resp, map);
    }
    public void queryCarList(HttpServletRequest req, HttpServletResponse resp) {
        CarService carService = new CarServiceImpl();
        List<CarVO> list = carService.queryCarList();
        BaseMethod.sendJSON(resp,list.toArray());
    }
    public void queryCarState(HttpServletRequest req, HttpServletResponse resp) {
        String carid = req.getParameter("carid");
        CarService carService = new CarServiceImpl();
        String state = carService.queryCarState(Integer.parseInt(carid));
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("carState", state);
        BaseMethod.sendJSON(resp,map);

    }



}
