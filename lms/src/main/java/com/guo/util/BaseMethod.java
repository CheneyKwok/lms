package com.guo.util;

import com.alibaba.fastjson.JSONArray;
import com.guo.pojo.DeliverSpotVo;
import com.guo.service.deliverspot.DeliverspotService;
import com.guo.service.deliverspot.DeliverspotServiceImpl;
import net.sf.json.util.JSONUtils;
import org.junit.Test;
import org.omg.PortableInterceptor.INACTIVE;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class BaseMethod {
    public static void sendJSON(HttpServletResponse resp,Object obj) {
        try {
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            writer.write(JSONArray.toJSONString(obj));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> getMap(int i) {
        Map<String, String> map = new HashMap<String, String>();
        if (i > 0) {
            map.put(Constant.RESULT, "true");
        } else {
            map.put(Constant.RESULT, "false");
        }
        return map;
    }


    public static synchronized String getNo() {
        Random random = new Random();

        // 随机数的量 自由定制，这是9位随机数
        Integer r = random.nextInt(9000);

        // 返回  13位时间
        Long timeMillis = System.currentTimeMillis();

        // 返回  17位时间
        DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        String timeStr = sdf.format(new Date());

        return timeStr + r;
    }

    public static float getPrice(String weight, int d_id) {
        DeliverspotService deliverspotService = new DeliverspotServiceImpl();
    List<DeliverSpotVo> list = deliverspotService.queryParams(d_id);
//        int startscope = Integer.parseInt(list.get(0).getStartscope());
//        int startprice = Integer.parseInt(list.get(0).getStartprice());
//        int sceondprice = Integer.parseInt(list.get(0).getSceondprice());
        float startscope = Float.parseFloat(list.get(0).getStartscope());
        float startprice = Float.parseFloat(list.get(0).getStartprice());
        float sceondprice = Float.parseFloat(list.get(0).getSceondprice());


        float price = (Float.parseFloat(weight) - startscope  ) *  sceondprice +  startprice;
        return price;

    }
    @Test
    public void t() {
        float price = getPrice("10", 101);

        System.out.println(price);
    }



    }

