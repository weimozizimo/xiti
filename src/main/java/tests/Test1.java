package tests;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.util.JacksonFeatureSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JacksonJsonParser;
import tests.exception.RpcException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Test1 {

    private final static Logger log = LoggerFactory.getLogger(Test1.class);

    public static void main(String[] args) throws RpcException, InterruptedException {
        //fastJson
        User user = new User();
        user.setUsername2("username");
        user.setEmail("email");
        user.setSex("男");

        Thread hello = new Thread(new Runner(Thread.currentThread()));
        hello.start();

        String s = JSONObject.toJSONString(user);
        TimeUnit.SECONDS.sleep(3);
        System.out.println(s);





    }


    static class Runner implements Runnable{

        private Thread previous;

        public Runner(Thread previous) {
            this.previous = previous;
        }

        @Override
        public void run() {
            try {
                previous.join();
                System.out.println("hello");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
