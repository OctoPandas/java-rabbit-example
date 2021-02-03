package org.example.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.example.connect.MQConnection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ProducerB {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = MQConnection.getConnection();
        Channel channel = connection.createChannel();
        // `confirmSelect` will add unique identifiers for later messages to be checked
        channel.confirmSelect();
        channel.basicPublish("", "myQ", null, "string".getBytes());
        if (channel.waitForConfirms()) System.out.println("Published successfully.");
        else System.out.println("Publish failed.");
        channel.close();
        connection.close();
    }
}
