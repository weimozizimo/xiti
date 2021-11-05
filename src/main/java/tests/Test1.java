package tests;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tests.exception.RpcException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Test1 {

    private final static Logger log = LoggerFactory.getLogger(Test1.class);

    public static void main(String[] args) throws RpcException {

        JSONObject jsonObject = JSONObject.parseObject("{\n" +
                "      \"area_ids\": [\n" +
                "        {\n" +
                "         \"area_id\":\"bab9e10b-ecf8-4446-9a0a-fdddca6be41f\"\n" +
                "        },\n" +
                "        {\n" +
                "         \"area_id\":\"4fae926f-286a-4227-9cfc-c8d274e57e3f\"\n" +
                "        },\n" +
                "        {\n" +
                "         \"area_id\":\"4fae926f-286a-4227-9cfc-c8d274e57e3f2\"\n" +
                "        }\n" +
                "      ]\n" +
                "}");
        log.info("请求报文为:{}",jsonObject);
        Execution execution = new Execution();

        //拿到areaIds
        JSONArray areaIds = jsonObject.getJSONArray("area_ids");
        log.info("areaIds is {}",areaIds.toString());

        for(int i = 0 ; i < areaIds.size();i++){
            JSONObject json = (JSONObject) areaIds.get(i);
            String areaId = json.getString("area_id");
            log.info("the {} areaId is {}",i,areaId);
            if(StringUtils.isEmpty(areaId)){
                throw new RpcException("参数校验异常，areaId不得为空");
            }
        }

        JSONObject paramJson = new JSONObject();

        String groupId = UUID.randomUUID().toString();

        paramJson.put("groupId",groupId);
        paramJson.put("areaIds",areaIds);

        log.info("groupId is {}",groupId);
        log.info("paramJson ids {}",paramJson);

        execution.setVariable("paramJson",paramJson);

    }
}
