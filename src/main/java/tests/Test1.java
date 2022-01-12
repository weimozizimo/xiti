package tests;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.INTERNAL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import other.Men;
import tests.exception.RpcException;

import java.util.*;
import java.util.stream.Collectors;

public class Test1 {

    private final static Logger log = LoggerFactory.getLogger(Test1.class);

    public static void main(String[] args) throws RpcException {
        Map<Integer, Integer> map = new HashMap<>();
        Integer i = map.put(1, 1);
        if(i!=null){
            map.put(1,++i);
        }
        map.forEach((k,v)-> System.out.println(v));
    }
}
