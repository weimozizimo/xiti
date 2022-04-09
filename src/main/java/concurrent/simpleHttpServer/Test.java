package concurrent.simpleHttpServer;

import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        SimpleHttpServer.setPort(9091);
        SimpleHttpServer.setBasePath("C:\\Users\\10358\\Desktop\\杂七杂八\\表情包");
        SimpleHttpServer.start();
    }
}
