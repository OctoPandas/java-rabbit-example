package org.example.consumer;

import com.rabbitmq.client.*;
import org.example.connect.MQConnection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * No ACK confirmation mechanism
 */
public class ConsumerA {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = MQConnection.getConnection();
        Channel channel = connection.createChannel();
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String tag, Envelope envelope, AMQP.BasicProperties props, byte[] body) {
                String msg = new String(body, StandardCharsets.UTF_8);
                System.out.println(msg);
            }
        };
        channel.basicConsume("myQ", true, consumer);
        // Do not close channel here, or the process will end.
    }
}
