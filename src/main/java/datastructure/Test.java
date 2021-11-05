package datastructure;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class Test {
    public static void main(String[] args) throws InterruptedException {

        String jsonStr = "{\n" +
                "  \"provinceList\": [\n" +
                "    {\n" +
                "      \"provinceId\": 1\n" +
                "    }\n" +
                "  ],\n" +
                "  \"areaList\": [\n" +
                "    {\n" +
                "      \"provinceId\": 1,\n" +
                "      \"areaId\": 1\n" +
                "    }\n" +
                "  ],\n" +
                "  \"nodeList\": [\n" +
                "    {\n" +
                "      \"provinceId\": 1,\n" +
                "      \"areaId\": 1,\n" +
                "      \"nodeId\": 1\n" +
                "    }\n" +
                "  ]\n" +
                "}";



        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        JSONArray provinces = jsonObject.getJSONArray("provinceList");
        if(provinces!=null&&!provinces.isEmpty()){
            for(int i = 0 ; i < provinces.size() ; i++){
                JSONObject province = (JSONObject)provinces.get(i);
                String s = province.get("provinceId").toString();
                if(s==null){
                    throw new RuntimeException("参数校验异常，provinceId不得为空");
                }
            }
        }else{
            throw new RuntimeException("参数校验异常,provinceList不得为空");
        }
    }

    public static Double fetchPrice(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(Math.random()<0.3){
            throw new RuntimeException("fetch price failed!");
        }
        return 5 + Math.random()*20;
    }
}
