package org.example.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.example.connect.MQConnection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Enable transaction to ensure integrity (also confirm the delivery successfully)
 */
public class ProducerC {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = null;
        Channel channel = null;
        try {
            connection = MQConnection.getConnection();
            channel = connection.createChannel();
            channel.txSelect(); // enable transaction support
            channel.basicPublish("", "myQ", null, "transaction".getBytes());
            if (Math.random() > 0.5) throw new RuntimeException();
            channel.txCommit(); // commit transaction
        } catch (IOException e) {
            e.printStackTrace();
            if (channel != null) channel.txRollback();
        } finally {
            if (channel != null) channel.close();
            if (connection != null) connection.close();
        }
    }
}
