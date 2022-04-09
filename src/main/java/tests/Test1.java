package tests;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.util.JacksonFeatureSet;
import concurrent.base.Interrupted;
import jdk.nashorn.internal.scripts.JS;
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
        List<Integer> l1 = new ArrayList<>();
        l1.add(11112);
        l1.add(11111);
        l1.add(111113);
        l1.add(11114);
        List<Integer> l2 = new ArrayList<>();
        l2.add(11114);
        l2.add(111113);

        boolean remove = l1.removeAll(l2);
        for (Integer integer : l1) {
            System.out.println(integer);
        }

    }
}
