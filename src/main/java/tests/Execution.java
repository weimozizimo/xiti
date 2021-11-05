package tests;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

public class Execution extends HashMap<String,JSONObject> {

    public JSONObject getVariable(String key){
       return this.get(key);
    }
    public void setVariable(String key,JSONObject value){
        this.put(key,value);
    }
}
