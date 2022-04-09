package other;

import org.springframework.boot.web.servlet.FilterRegistrationBean;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
*@Description try-with-resource的使用方式，try()中声明io流对象，可以无需再finally中关闭io流
*@Author weiyifei
*@date 2022/3/13
*/
public class TryCatchResourceTest {

    public static void main(String[] args) {

        String path = TryCatchResourceTest.class.getResource("/").getPath();


        //这里注意,如果不声明FileReader而是将其包裹在BufferReader的构造方法内，这样编译器编译生成的時候只会调用外层的的close方法
        //由于本质上外层装饰器的close是调用内部包裹的流的close方法，那么如果装饰器在进行前面的额外处理的时候报错，那么内部的流的close方法就有可能不会被调用
        //所以我们一定要对内部流进行单独声明，这样编译器才会对内部流进行单独的close操作，保证了内部流
        try(FileOutputStream outputStream = new FileOutputStream("");
                GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream)){
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
