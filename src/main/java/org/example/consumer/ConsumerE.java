package org.example.consumer;

import com.rabbitmq.client.*;
import org.example.connect.MQConnection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * Direct routing with routing key
 */
public class ConsumerE {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = MQConnection.getConnection();
        Channel channel = connection.createChannel();
        channel.queueBind("emailQ", "direct", "email");
        channel.basicConsume("emailQ", true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                String msg = new String(body, StandardCharsets.UTF_8);
                System.out.println(msg);
            }
        });
    }
}
