package other;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class GnericsType {
    public static void main(String[] args) {
        Method method = Testx.class.getDeclaredMethods()[0];
        System.out.println(method);
        Type[] types = method.getGenericParameterTypes();
        for (Type type:types
             ) {
            System.out.println(type);
            System.out.println(type instanceof GenericArrayType);
            System.out.println(type instanceof ParameterizedType);
        }
    }
}
class Testx<T>{
    public void show(List<String>[] pTypeArray,T[] vTypeArray,List<String> list,String[] strings,int[] ints){

    }
}
