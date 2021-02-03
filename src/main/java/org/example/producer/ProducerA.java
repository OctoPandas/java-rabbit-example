package org.example.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.example.connect.MQConnection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ProducerA {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = MQConnection.getConnection();
        Channel channel = connection.createChannel();
        channel.basicPublish("", "myQ", null, "fake".getBytes());
        channel.close();
        connection.close();
    }
}
