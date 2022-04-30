package rabbitmq.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbitmq.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

// 生产者，订阅模式（fanout类型）
public class Send {
    private final static String EXCHANGE_NAME = "fanout_exchange_test";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取连接
        Connection connection = ConnectionUtil.getConnection();
        // 获取通道
        Channel channel = connection.createChannel();

        // 声明交换机exchange，指定类型为fanout
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");

        // 消息内容
        String message = "Hello everyone";
        // 发布消息到Exchange
        channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes());
        System.out.println(" [生产者] Send '" + message + "'");

        channel.close();
        connection.close();
    }
}
