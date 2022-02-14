package concurrent.threadApplyExample;

import org.aopalliance.intercept.Invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

/**
*@Description 使用动态代理构造一个Connection
*@Author weiyifei
*@date 2022/2/5
*/
public class ConnectionDriver {
    static class ConnectionHandler implements InvocationHandler{

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if(method.getName().equals("commit")){
                TimeUnit.MICROSECONDS.sleep(100);
            }
            return null;
        }
    }

    public static final Connection createConnection(){
        return (Connection) Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(),new Class<?>[]{Connection.class}
        ,new ConnectionHandler());
    }
}
