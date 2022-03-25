package ru.fedusiv.utils.rabbit;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class FileSenderImpl implements FileSender {

    private ConnectionFactory connectionFactory;

    public FileSenderImpl() {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
    }

    @Override
    public void send(MultipartFile[] files) {
        try (Connection connection = connectionFactory.newConnection();
                Channel channel = connection.createChannel()) {

            for (MultipartFile file: files) {
                channel.basicPublish("", "files_flow0", null, file.getBytes());
            }
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
