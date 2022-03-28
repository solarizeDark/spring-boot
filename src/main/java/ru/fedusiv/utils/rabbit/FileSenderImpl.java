package ru.fedusiv.utils.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Random;

@Component
@Slf4j
public class FileSenderImpl implements FileSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Override
    public void send(MultipartFile[] files) {

        Random random = new Random();

        for (MultipartFile file: files) {
            try {
                Integer randInt = random.nextInt(10) + 1;
                Message fileVal = new Message(file.getBytes());

                fileVal.getMessageProperties().setHeader("id", randInt);
                fileVal.getMessageProperties().setHeader("filename", file.getOriginalFilename());

                String type = file.getContentType();

                CorrelationData correlationData = new CorrelationData();

                correlationData.getFuture().addCallback(new ListenableFutureCallback<CorrelationData.Confirm>() {
                    @Override
                    public void onFailure(Throwable ex) {
                        log.info("Sending file via rabbit failure");
                    }

                    @Override
                    public void onSuccess(CorrelationData.Confirm result) {
                        if (result.isAck()) {
                            log.info("file successfully sent");
                        }
                    }
                });

                MessagePostProcessor mpp = message -> {
                    message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
                    return message;
                };

                if (type.matches("video.*"))
                    rabbitTemplate.convertAndSend(exchange, "files.audio", fileVal, mpp, correlationData);

                if (type.matches("audio.*"))
                    rabbitTemplate.convertAndSend(exchange, "files.audio", fileVal, mpp, correlationData);

                if (type.matches("text.*"))
                    rabbitTemplate.convertAndSend(exchange, "files.text", fileVal, mpp, correlationData);

                if (type.matches("image.*"))
                    rabbitTemplate.convertAndSend(exchange, "files.image", fileVal, mpp, correlationData);

            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

}
