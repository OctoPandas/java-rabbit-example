package org.example.producer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.example.connect.MQConnection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Publish messages to exchange with routing key
 */
public class ProducerE {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = MQConnection.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("direct", BuiltinExchangeType.DIRECT);
        channel.basicPublish("direct", "email", null, "direct".getBytes());
        channel.close();
        connection.close();
    }
}
