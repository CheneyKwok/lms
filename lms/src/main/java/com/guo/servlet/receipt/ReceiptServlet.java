package com.guo.servlet.receipt;


import com.guo.pojo.Order;
import com.guo.pojo.OrderVO;
import com.guo.pojo.Receipt;
import com.guo.pojo.User;
import com.guo.service.car.CarService;
import com.guo.service.car.CarServiceImpl;
import com.guo.service.order.OrderService;
import com.guo.service.order.OrderServiceImpl;
import com.guo.service.receipt.ReceiptService;
import com.guo.service.receipt.ReceiptServiceImpl;
import com.guo.util.BaseMethod;
import com.guo.util.Constant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


public class ReceiptServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method != null && method.equals("queryJoin")) {
            this.queryJoin(req,resp);
        }
        if (method != null && method.equals("queryDeliver")) {
            this.queryDeliver(req, resp);
        }
        if (method != null && method.equals("delete")) {
            this.delete(req,resp);
        }
        if (method != null && method.equals("join")) {
            this.join(req,resp);
        }
        if (method != null && method.equals("deliver")) {
            this.deliver(req,resp);
        }
        if (method != null && method.equals("deleteBatch")) {
            this.delBatch(req,resp);
        }
        if (method != null && method.equals("joinBatch")) {
            this.joinBatch(req,resp);
        }
        if (method != null && method.equals("deliverBatch")) {
            this.deliverBatch(req,resp);
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

    public void queryJoin(HttpServletRequest req, HttpServletResponse resp) {
        String page = req.getParameter("page");
        String limit = req.getParameter("limit");
        ReceiptService receiptService = new ReceiptServiceImpl();
        int count = receiptService.queryJoinCount();
        List<Receipt> list = receiptService.queryJoin(Integer.parseInt(page), Integer.parseInt(limit));
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", list);
        BaseMethod.sendJSON(resp, map);

    }
    public void queryDeliver(HttpServletRequest req, HttpServletResponse resp) {
        String page = req.getParameter("page");
        String limit = req.getParameter("limit");
        ReceiptService receiptService = new ReceiptServiceImpl();
        int count = receiptService.queryDeliverCount();
        List<Receipt> list = receiptService.queryDeliver(Integer.parseInt(page), Integer.parseInt(limit));
        System.out.println(list);
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", list);
        BaseMethod.sendJSON(resp, map);

    }
    public void delete(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("receiptid");
        ReceiptService receiptService = new ReceiptServiceImpl();

        int i = receiptService.deleteByid(Integer.parseInt(id));
        Map<String, String> map = BaseMethod.getMap(i);
        BaseMethod.sendJSON(resp, map);
    }
    public void join(HttpServletRequest req, HttpServletResponse resp){
        String receiptNo= BaseMethod.getNo();
        String carno = req.getParameter("carno");
        String departtime = req.getParameter("departtime");
        String carid = req.getParameter("carid");
        String flag = req.getParameter("flag");
        String orderid = req.getParameter("orderid");
        CarService carService = new CarServiceImpl();
        int carState = carService.updateInfoById(Integer.parseInt(carid), "3", "carState");
        //System.out.println(carService.queryCarState(Integer.parseInt(carid)));
        OrderService orderService = new OrderServiceImpl();
        int orderState = orderService.updateInfoById(Integer.parseInt(orderid), "2", "orderState");
        orderService.updateInfoById(Integer.parseInt(orderid), carid, "carId");
       // System.out.println(orderState);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = sdf.format(new Date());

       User user = (User)req.getSession().getAttribute(Constant.USER_SESSION);
        Receipt r = new Receipt();
        r.setCarNo(carno);
        r.setCreatetime(createTime);
        r.setReceiptNo(receiptNo);
        r.setDepartTime(departtime);
        r.setFlag(Integer.parseInt(flag));
        ReceiptService receiptService = new ReceiptServiceImpl();
        int i = receiptService.join(r);
        Map<String, String> map = BaseMethod.getMap(i);
        BaseMethod.sendJSON(resp, map);


    }
    public void deliver(HttpServletRequest req, HttpServletResponse resp) {
        String receiptNo= BaseMethod.getNo();

        String flag = req.getParameter("flag");
        String orderid = req.getParameter("orderid");

        OrderService orderService = new OrderServiceImpl();
        int orderState = orderService.updateInfoById(Integer.parseInt(orderid), "3", "orderState");
        List<OrderVO> list = orderService.queryByid(Integer.parseInt(orderid));
        CarService carService = new CarServiceImpl();
        int carState = carService.updateInfoById(list.get(0).getCarId(), "1", "carState");
        // System.out.println(orderState);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = sdf.format(new Date());

       User user = (User)req.getSession().getAttribute(Constant.USER_SESSION);

        Receipt r = new Receipt();
       r.setDeliverspotId(user.getDeliverspotId());
       r.setEmpId(user.getEmpId());
        r.setReceiptNo(receiptNo);
        r.setCreatetime(createTime);
        r.setFlag(Integer.parseInt(flag));
        ReceiptService receiptService = new ReceiptServiceImpl();
        int i = receiptService.deliver(r);
        Map<String, String> map = BaseMethod.getMap(i);
        BaseMethod.sendJSON(resp, map);


    }
    public void delBatch(HttpServletRequest req, HttpServletResponse resp){
        String[] receiptids = req.getParameterValues("receiptid");
        ReceiptService receiptService = new ReceiptServiceImpl();

        boolean flag = false;

        for (String id : receiptids) {
            int id_ = Integer.parseInt(id);
            int i = receiptService.deleteByid(id_);
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

    public void joinBatch(HttpServletRequest req, HttpServletResponse resp) {
        String[] orderids = req.getParameterValues("orderid");
        String carno = req.getParameter("carno");
        String departtime = req.getParameter("departtime");
        String carid = req.getParameter("carid");
        String flag = req.getParameter("flag");
        CarService carService = new CarServiceImpl();
        int carState = carService.updateInfoById(Integer.parseInt(carid), "3", "carState");
        OrderService orderService = new OrderServiceImpl();

        boolean i = false;
        for (String id : orderids) {
            int orderid = Integer.parseInt(id);
            int i1 = orderService.updateInfoById(orderid, "2", "orderState");
            int i3 = orderService.updateInfoById(orderid, carid, "carId");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createTime = sdf.format(new Date());
            String receiptNo= BaseMethod.getNo();

            Receipt r = new Receipt();
            r.setCarNo(carno);
            r.setCreatetime(createTime);
            r.setReceiptNo(receiptNo);
            r.setDepartTime(departtime);
            r.setFlag(Integer.parseInt(flag));
            ReceiptService receiptService = new ReceiptServiceImpl();
            int i2 = receiptService.join(r);
            if (i1 > 0 && i2 >0 && i3 >0) {
                i = true;
            } else {
                i = false;
                break;
            }
        }

        HashMap<String, String> map = new HashMap<String, String>();
        if (i = true) {
            map.put(Constant.RESULT, "true");
        } else {
            map.put(Constant.RESULT, "false");
        }
        BaseMethod.sendJSON(resp,map);


    }

    public void deliverBatch(HttpServletRequest req, HttpServletResponse resp) {


        String flag = req.getParameter("flag");
        String[] orderids = req.getParameterValues("orderid");
        OrderService orderService = new OrderServiceImpl();

        boolean i = false;
        for (String id : orderids) {
            int orderid = Integer.parseInt(id);
            int i1 = orderService.updateInfoById(orderid, "3", "orderState");

            List<OrderVO> list = orderService.queryByid(orderid);
            CarService carService = new CarServiceImpl();
            int i2 = carService.updateInfoById(list.get(0).getCarId(), "1", "carState");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createTime = sdf.format(new Date());

            User user = (User)req.getSession().getAttribute(Constant.USER_SESSION);
            String receiptNo= BaseMethod.getNo();
            Receipt r = new Receipt();
            r.setDeliverspotId(user.getDeliverspotId());
            r.setEmpId(user.getEmpId());
            r.setReceiptNo(receiptNo);
            r.setCreatetime(createTime);
            r.setFlag(Integer.parseInt(flag));
            ReceiptService receiptService = new ReceiptServiceImpl();
            int i3 = receiptService.deliver(r);
            if (i1 > 0 && i2 >0 && i3 >0) {
                i = true;
            } else {
                i = false;
                break;
            }
        }

        HashMap<String, String> map = new HashMap<String, String>();
        if (i = true) {
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
        String flag = req.getParameter("flag");
        Map condition = req.getParameterMap();
        ReceiptService receiptService = new ReceiptServiceImpl();
        int count = 0;
        List<Receipt> list = null;
        if (flag.equals("0")) {
            count = receiptService.queryJoinCountBysearch(condition);
            list = receiptService.queryJoinBysearch(Integer.parseInt(page), Integer.parseInt(limit), condition);

        }


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
