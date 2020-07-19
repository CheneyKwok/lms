package com.guo.servlet.order;


import com.guo.pojo.Order;
import com.guo.pojo.OrderVO;
import com.guo.pojo.User;
import com.guo.service.order.OrderService;
import com.guo.service.order.OrderServiceImpl;
import com.guo.util.BaseMethod;
import com.guo.util.Constant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


public class OrderServlet extends HttpServlet {
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
        OrderService orderService = new OrderServiceImpl();
        int count = orderService.queryCount();
        List<OrderVO> list = orderService.query(Integer.parseInt(page), Integer.parseInt(limit));
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        ArrayList<Object> l = new ArrayList<Object>();
        Order order = new Order();
        order.setPrice("100");
        l.add(order);
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", list);
        map.put("totalRow", l);
        BaseMethod.sendJSON(resp, map);

    }
    public void delete(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("orderid");
        OrderService orderService = new OrderServiceImpl();
        int i = orderService.deleteByid(Integer.parseInt(id));
        Map<String, String> map = BaseMethod.getMap(i);
        BaseMethod.sendJSON(resp, map);
    }
    public void add(HttpServletRequest req, HttpServletResponse resp){
        String orderNo= BaseMethod.getNo();
        String sendName = req.getParameter("sendName");
        String sendAddress = req.getParameter("sendAddress");
        String sendTel = req.getParameter("sendTel");
        String receiveName = req.getParameter("receiveName");
        String receiveAddress = req.getParameter("receiveAddress");
        String receiveTel = req.getParameter("receiveTel");
        String price = req.getParameter("price");
        String volume = req.getParameter("volume");
        String weight = req.getParameter("weight");
        String remark = req.getParameter("remark");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = sdf.format(new Date());
        int orderStateId = 1;
       User user = (User)req.getSession().getAttribute(Constant.USER_SESSION);
        int deliverspotId = user.getDeliverspotId();
        Order o = new Order();
        o.setOrderNo(orderNo);
        o.setSendname(sendName);
        o.setSendaddress(sendAddress);
        o.setSendtel(sendTel);
        o.setReceivename(receiveName);
        o.setReceiveaddress(receiveAddress);
        o.setReceivetel(receiveTel);
        o.setPrice(price);
        o.setVolume(volume);
        o.setWeight(weight);
        o.setRemark(remark);
        o.setCreatetime(createTime);
        o.setOrderstateId(orderStateId);
        o.setDeliverspotId(deliverspotId);
        OrderService orderService = new OrderServiceImpl();
        int i = orderService.add(o);
        Map<String, String> map = BaseMethod.getMap(i);
        BaseMethod.sendJSON(resp, map);


    }
    public void delBatch(HttpServletRequest req, HttpServletResponse resp){
        String[] orderids = req.getParameterValues("orderid");
        OrderService orderService = new OrderServiceImpl();
        boolean flag = false;

        for (String id : orderids) {
            int carid = Integer.parseInt(id);
            int i = orderService.deleteByid(carid);
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
        String orderid = req.getParameter("orderid");
        OrderService orderService = new OrderServiceImpl();
        List<OrderVO> list = orderService.queryByid(Integer.parseInt(orderid));
        HashMap<String, Object> map = new HashMap<String, Object>();
        if (list != null) {
            map.put("orderNo", list.get(0).getOrderNo());
            map.put("createTime", list.get(0).getCreateTime());
            map.put("orderState", list.get(0).getOrderState());
            map.put("sendName", list.get(0).getSendName());
            map.put("sendAddress", list.get(0).getSendAddress());
            map.put("sendTel", list.get(0).getSendTel());
            map.put("receiveName", list.get(0).getReceiveName());
            map.put("receiveAddress", list.get(0).getReceiveAddress());
            map.put("receiveTel", list.get(0).getReceiveTel());
            map.put("volume", list.get(0).getVolume());
            map.put("weight", list.get(0).getWeight());
            map.put("remark", list.get(0).getRemark());
            map.put("price", list.get(0).getPrice());

        }
        BaseMethod.sendJSON(resp,map);
    }
    public void search(HttpServletRequest req, HttpServletResponse resp) {
        String page = req.getParameter("page");
        String limit = req.getParameter("limit");
        Map condition = req.getParameterMap();
        OrderService orderService = new OrderServiceImpl();

        int count = orderService.queryCountBysearch(condition);
        List list = orderService.queryBysearch(Integer.parseInt(page), Integer.parseInt(limit), condition);
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", list);
        BaseMethod.sendJSON(resp,map);
    }
    public void update(HttpServletRequest req, HttpServletResponse resp) {
        String value = req.getParameter("value");
        String orderid = req.getParameter("orderid");
        String column = req.getParameter("column");
        OrderService orderService = new OrderServiceImpl();

        int i = orderService.updateInfoById(Integer.parseInt(orderid), value, column);
        Map<String, String> map = BaseMethod.getMap(i);
        BaseMethod.sendJSON(resp, map);
    }


}
