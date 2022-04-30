package rabbitmq.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbitmq.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者
 */
public class Send {
    private final static String QUEUE_NAME = "test_work_queue";

    public static void main(String[] argv) throws IOException, TimeoutException {
        // 获取到连接
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中创建通道，使用通道才能完成消息相关的操作
        Channel channel = connection.createChannel();
        // 声明（创建）队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        for (int i=0;i<50;i++){
            // 消息内容
            String message = "task...."+i;
            // 向指定的队列中发送消息
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());

            System.out.println("[x] Send '" + message + "'");
        }
        // 关闭通道和连接
        channel.close();
        connection.close();
    }
}
