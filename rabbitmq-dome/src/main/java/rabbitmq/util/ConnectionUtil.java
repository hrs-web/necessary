package rabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionUtil {
    /**
     * 建立与RabbitMQ的连接
     * @return
     * @throws IOException
     * @throws TimeoutException
     */
    public static Connection getConnection() throws IOException, TimeoutException {
        // 定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 设置服务地址
        factory.setHost("192.168.182.100");
        // 端口
        factory.setPort(5672);
        // 设置账号信息，用户名，密码，vhost
        factory.setVirtualHost("/youxiang");
        factory.setUsername("youxiang");
        factory.setPassword("youxiang");
        // 通过工厂获取连接
        Connection connection = factory.newConnection();
        return connection;
    }
}
