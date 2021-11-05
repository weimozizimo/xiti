package other;

import java.io.Serializable;
import java.util.ArrayList;

public class Men {
    public static void main(String[] args) {

        Serializable serializable = get("1", new ArrayList<>());
    }

    public static <T> T get(T t1,T t2){
        return t2;
    }
}
