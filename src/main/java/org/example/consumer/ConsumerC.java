package org.example.consumer;

import com.rabbitmq.client.*;
import org.example.connect.MQConnection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class ConsumerC {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = MQConnection.getConnection();
        Channel channel = connection.createChannel();
        channel.queueBind("smsQ", "fanout", "");
        channel.basicConsume("smsQ", new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String tag, Envelope envelope, AMQP.BasicProperties props, byte[] body) {
                String msg = new String(body, StandardCharsets.UTF_8);
                System.out.println(msg);
            }
        });
    }
}
