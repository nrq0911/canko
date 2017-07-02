package com.canko.common;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nrq on 2017/6/25.
 */
public class HttpUtils {

    public static void main(String...arg){
        Map<String,String> map =new HashMap<>();
        map.put("1","http://canko-1253396962.cosgz.myqcloud.com/context1.jpg");
        map.put("2","http://canko-1253396962.cosgz.myqcloud.com/context2.jpg");
        map.put("3","http://canko-1253396962.cosgz.myqcloud.com/context3.jpg");
        map.put("4","http://canko-1253396962.cosgz.myqcloud.com/context4.jpg");
        map.put("5","http://canko-1253396962.cosgz.myqcloud.com/context5.jpg");
        map.put("6","http://canko-1253396962.cosgz.myqcloud.com/context6.jpg");
        map.put("7","http://canko-1253396962.cosgz.myqcloud.com/context7.jpg");
        map.put("8","http://canko-1253396962.cosgz.myqcloud.com/context8.jpg");
        map.put("9","http://canko-1253396962.cosgz.myqcloud.com/context9.jpg");
        System.out.print(JSON.toJSONString(map));
    }


}
