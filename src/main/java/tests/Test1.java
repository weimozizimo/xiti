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

public class Test1 {

    private final static Logger log = LoggerFactory.getLogger(Test1.class);

    public static void main(String[] args) throws RpcException {
        //fastJson
        User user = new User();
        user.setUsername2("username");
        user.setEmail("email");
        user.setSex("男");

        String s = JSONObject.toJSONString(user);
        System.out.println(s);




    }
}
