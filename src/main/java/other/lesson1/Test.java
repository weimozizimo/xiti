package other.lesson1;


import cn.hutool.json.JSONObject;

public class Test {
    public static void main(String[] args) {
//        System.out.println(InsttanceFacotry.key);
//        InsttanceFacotry.getInstance();
        try{
            JSONObject jsonObject = new JSONObject();
            Integer code = jsonObject.getInt("code");
            if(code!=0){

            }
        }catch (Exception e){
          e.printStackTrace();
        }
    }
}
