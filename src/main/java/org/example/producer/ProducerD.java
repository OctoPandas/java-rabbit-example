package org.example.producer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.example.connect.MQConnection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Fanout exchanges: broadcast single datagram to multiple binders
 */
public class ProducerD {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = MQConnection.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("fanout", BuiltinExchangeType.FANOUT);
        channel.basicPublish("fanout", "", null, "fanout".getBytes());
        channel.close();
        connection.close();
    }
}
