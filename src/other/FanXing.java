package other;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FanXing {
    public static void main(String[] args) {
        Map<Integer,String> map = new HashMap<>();
        for(int i = 0 ; i < 100000 ; i ++){
            map.put(i, UUID.randomUUID().toString());
        }
    }
}
