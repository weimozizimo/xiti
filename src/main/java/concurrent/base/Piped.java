package concurrent.base;

import javax.crypto.spec.PSource;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
*@Description 管道输入/输出流
*@Author weiyifei
*@date 2022/2/3
*/
public class Piped {

    public static void main(String[] args) throws IOException {
        PipedWriter out = new PipedWriter();
        PipedReader in = new PipedReader();
        //将输入流输出流连接，否则在使用的时候会抛出IOException
        out.connect(in);
        Thread printThread = new Thread(new Print(in), "PrintThread");
        printThread.start();
        int receive = 0;
        try {
            while ((receive=System.in.read())!=-1){

                    out.write(receive);
            }
        } finally {
            out.close();
        }

    }

    static class Print implements Runnable{

        private PipedReader in;

        public Print(PipedReader in) {
            this.in = in;
        }

        @Override
        public void run() {
            int receive = 0 ;
            char[] buf = new char[255];
            try {
                while ((receive = in.read(buf))!=-1){
                    System.out.println((String.valueOf(buf)));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
