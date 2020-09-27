import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

public class GenericsType {
    private Box<Number> box;

    private Box<Number>.Node<String> node;
    private Person person;


    public static void main(String[] args) throws NoSuchFieldException {
        Field f = GenericsType.class.getDeclaredField("box");
        Field f2 = GenericsType.class.getDeclaredField("person");
        Field f3 = GenericsType.class.getDeclaredField("node");
        System.out.println(f2.getGenericType());
        System.out.println(f2.getGenericType() instanceof  ParameterizedType);
        System.out.println(f.getGenericType());
        System.out.println(f.getGenericType() instanceof ParameterizedType);
        ParameterizedType pType = (ParameterizedType) f.getGenericType();
        System.out.println(pType.getActualTypeArguments());
        for (Type type:pType.getActualTypeArguments()
             ) {
            System.out.println(type);
        }
        ParameterizedType p2 = (ParameterizedType)f3.getGenericType();
        //getOwnerType得意思就是如果目前这个类是一个内部类，那么返回他的拥有则
        //比如Map.Entity的拥有者就是Map接口
        System.out.println(p2.getOwnerType());
    }



}
class Box<T extends Number>{

    Number get(T s){
        return s;
    }

    class Node<T extends String>{
        private String left;
        private String right;
    }

}
class Person {
    public Person(){

    }
    String getName(String name){
        return name;
    }
}