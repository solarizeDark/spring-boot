package ru.fedusiv.files_server.rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
@Slf4j
public class FilesConsumerImpl implements FilesConsumer {

    private ConnectionFactory connectionFactory;

    public FilesConsumerImpl() {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
    }

    private DeliverCallback deliverCallback = (consumerTag, delivery) -> {
        FileOutputStream fileOutputStream = new FileOutputStream("file.txt");
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        log.info("<Body size>" + delivery.getBody().length);
        bufferedOutputStream.write(delivery.getBody());
        bufferedOutputStream.flush();
    };

    @Override
    public void consume() throws IOException, TimeoutException {

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.basicConsume("files_flow0", true, deliverCallback, consumerTag -> {});

    }

    @EventListener(ApplicationReadyEvent.class)
    public void start() throws IOException, TimeoutException {
        log.info("<======STARTED======>");
        this.consume();
    }

}
