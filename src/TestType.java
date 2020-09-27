import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class TestType <K extends Comparable & Serializable,V> {
    K key;
    V value;

    public static void main(String[] args) throws NoSuchFieldException {
        //获取字段的类型
        Field key = TestType.class.getDeclaredField("key");
        Field value = TestType.class.getDeclaredField("value");
        assert key.getGenericType() instanceof TypeVariable;
        assert value.getGenericType() instanceof TypeVariable;
        TypeVariable keyType = (TypeVariable)key.getGenericType();
        TypeVariable valueType = (TypeVariable)value.getGenericType();
        //getName方法
        System.out.println(keyType.getName());
        System.out.println(valueType.getName());
        //getGenericDeclaration方法
        System.out.println(keyType.getGenericDeclaration());
        System.out.println(valueType.getGenericDeclaration());
        //getBounds方法
        System.out.println("K的边界");
        for (Type type:keyType.getBounds()
             ) {
            System.out.println(type);
        }
        System.out.println("V的边界");
        for (Type type:valueType.getBounds()
             ) {
            System.out.println(type);
        }
    }
}
