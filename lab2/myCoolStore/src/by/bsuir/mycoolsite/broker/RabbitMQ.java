package by.bsuir.mycoolsite.broker;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQ {
    private static final String HOST = "localhost";
    private static final int PORT = 5672;
    private static final String USERNAME = "guest";
    private static final String PASSWORD = "guest";
    private static final String QUEUE_NAME = "myQueue";

    public static Connection getConnection() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost(HOST);
        factory.setPort(PORT);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);

        return factory.newConnection();
    }

    public static void sendMessage(String message) {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(HOST);
            factory.setPort(PORT);
            factory.setUsername(USERNAME);
            factory.setPassword(PASSWORD);

            try (Connection connection = factory.newConnection();
                 Channel channel = connection.createChannel()) {
                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                System.out.println("Message sent to RabbitMQ successfully");
            }
        } catch (Exception e) {
            System.out.println("Failed to send message to RabbitMQ.");
        }
    }
}
