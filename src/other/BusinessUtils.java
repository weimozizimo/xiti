package other;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class BusinessUtils {


    //获取查询的加和sqlSum条件
    public static String getSqlSum(int interval) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 60; i=i+interval) {
            String str;
            if(i<10){
                str = "V0"+i+"+";
            }else{
                if(i==(60/interval-1)*interval){
                    str = "V"+String.valueOf(i)+"";
                }else {
                    str = "V"+String.valueOf(i)+"+";
                }
            }
            sb.append(str);
        }
        return sb.toString();
    }


    //获取查询sql条件
    public static String getSql(int interval) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 60; i=i+interval) {
            String str;
            if(i<10){
                str = "V0"+i+" V0"+i+",";
            }else{
                if(i==(60/interval-1)*interval){
                    str = "V"+String.valueOf(i)+" V"+String.valueOf(i);
                }else {
                    str = "V"+String.valueOf(i)+" V"+String.valueOf(i)+",";
                }
            }
            sb.append(str);
        }
        return sb.toString();
    }

    //获取查询SUM统计sql条件
    public static String getSumSql(int interval) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 60; i=i+interval) {
            String str;
            if(i<10){
                str = "ISNULL(SUM(V0"+i+"),0) V0"+i+",";
            }else{
                if(i==(60/interval-1)*interval){
                    str = "SUM(V"+String.valueOf(i)+") V"+String.valueOf(i);
                }else {
                    str = "SUM(V"+String.valueOf(i)+") V"+String.valueOf(i)+",";
                }
            }
            sb.append(str);
        }
        return sb.toString();
    }

    //获取查询SUM统计sql条件
    public static String getWaveSql(int interval) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 60; i=i+interval) {
            String str;
            if(i<10){
                str = "ISNULL(SUM(V0"+i+"),0) V0"+i+",";
            }else{
                if(i==(60/interval-1)*interval){
                    str = "SUM(V"+String.valueOf(i)+") V"+String.valueOf(i);
                }else {
                    str = "SUM(V"+String.valueOf(i)+") V"+String.valueOf(i)+",";
                }
            }
            sb.append(str);
        }
        return sb.toString();
    }

    public static String getSumSqlFromH5(int interval){
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i <24 ;i++){
            for (int j = 0; j < 60; j=j+interval) {
                String str;
                String jStr = getTimeStr(j);
                String iStr = getTimeStr(i);
                if(i==23&&j==(60/interval-1)*interval){
                    str = "SUM(V"+iStr+jStr+") V"+iStr+jStr;
                }else {
                    str = "SUM(V"+iStr+jStr+") V"+iStr+jStr+",";
                }
                sb.append(str);
            }
        }
        return sb.toString();
    }
    public static String getAvgSqlFromH5(int interval){
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i <24 ;i++){
            for (int j = 0; j < 60; j=j+interval) {
                String str;
                String jStr = getTimeStr(j);
                String iStr = getTimeStr(i);
                if(i==23&&j==(60/interval-1)*interval){
                    str = "AVG(V"+iStr+jStr+") V"+iStr+jStr;
                }else {
                    str = "AVG(V"+iStr+jStr+") V"+iStr+jStr+",";
                }
                sb.append(str);
            }
        }
        return sb.toString();
    }


    public static String getSqlSumFromH5(int interval){
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i <24 ;i++){
            for (int j = 0; j < 60; j=j+interval) {
                String str;
                String jStr = getTimeStr(j);
                String iStr = getTimeStr(i);
                if(i==23&&j==(60/interval-1)*interval){
                    str = "ISNULL(V"+iStr+jStr+",0)";
                }else {
                    str = "ISNULL(V"+iStr+jStr+",0)+";
                }
                sb.append(str);
            }
        }
        return sb.toString();
    }



    public static String getSqlFromH5(int interval){
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i <24 ;i++){
            for (int j = 0; j < 60; j=j+interval) {
                String str;
                String jStr = getTimeStr(j);
                String iStr = getTimeStr(i);
                if(i==23&&j==(60/interval-1)*interval){
                    str = "V"+iStr+jStr+" V"+iStr+jStr;
                }else {
                    str = "V"+iStr+jStr+" V"+iStr+jStr+",";
                }
                sb.append(str);
            }
        }
        return sb.toString();
    }

    public static String getSqlSumFromH60(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i <24 ;i++){
            String str;
            String iStr = getTimeStr(i);
            if(i==23){
                str = "ISNULL(V"+iStr+",0)";
            }else {
                str = "ISNULL(V"+iStr+",0)+";
            }
            sb.append(str);
        }
        return sb.toString();
    }

    //获取1分钟表最新的数据
    public static String getNewData(Map<String,Object> data,int interval){
        String reData = "";
        for (int i = 0 ; i < 60 ; i+=interval){
            String min = BusinessUtils.getTimeStr(i);
            String newData = data.get("V"+min)==null?null:data.get("V"+min).toString();
            if(newData==null){
                break;
            }
            reData = newData;
        }

        return reData;
    }

    public static String getCodeFormat(String regionId){
        if(regionId.equals("370000")){
            return "37%";
        }else {
            return regionId.substring(0,4)+"%";
        }
    }

    public static List<String> getSelTime(int interval){
        List<String> list = new ArrayList<>();
        for(int i =  0 ; i < 24 ; i ++){
            for(int j = 0 ; j < 60 ; j+=interval){
                String time = getTimeStr(i)+":"+getTimeStr(j);
                list.add(time);
            }
        }
        return list;
    }

    public static String getTimeStr(int i){
        if(i<10){
            return 0+String.valueOf(i);
        }else{
            return String.valueOf(i);
        }
    }
}
