package by.bsuir.mycoolsite.broker;

import com.rabbitmq.client.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class RabbitMQMessageListener {
    private static final Logger logger = LogManager.getLogger(RabbitMQMessageListener.class);
    private static final String HOST = "localhost";
    private static final int PORT = 5672;
    private static final String USERNAME = "guest";
    private static final String PASSWORD = "guest";
    private static final String QUEUE_NAME = "myQueue";

    public void startListening() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(HOST);
            factory.setPort(PORT);
            factory.setUsername(USERNAME);
            factory.setPassword(PASSWORD);

            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, StandardCharsets.UTF_8);
                    System.out.println("Received message from RabbitMQ: " + message);

                    // Здесь вы можете выполнить логику обработки полученного сообщения, в соответствии с вашими требованиями

                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            };

            channel.basicConsume(QUEUE_NAME, false, consumer);
        } catch (Exception e) {
            System.out.println("Failed to start listening to RabbitMQ messages.");
        }
    }
}