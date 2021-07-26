package other;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.CreditCodeUtil;
import cn.hutool.core.util.StrUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FindTest {



    public static void main(String[] args) {
        List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>());
//        List<Integer> list = new ArrayList<>();

        AtomicInteger count = new AtomicInteger(0);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ; i < 50 ; i++){
                    list.add(count.getAndIncrement());
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ; i < 50 ; i++){
                    list.add(count.getAndIncrement());
                }
            }
        }).start();
        System.out.println(list.size());
        list.forEach(System.out::println);
    }


}
