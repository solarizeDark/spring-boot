package ru.fedusiv.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class RabbitConfiguration {

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.host}")
    private String rabbitmqHost;

    @Bean
    public ConnectionFactory connectionFactory() {

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitmqHost);

        connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");

        return connectionFactory;

    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(exchange);
    }


    @Bean
    List<Binding> bindings(TopicExchange exchange) {
        return Arrays.asList(
                BindingBuilder.bind(new Queue("files_audio", true)).to(exchange).with("files.audio.#"),
                BindingBuilder.bind(new Queue("files_video", true)).to(exchange).with("files.video.#"),
                BindingBuilder.bind(new Queue("files_text", true)).to(exchange).with("files.text.#"),
                BindingBuilder.bind(new Queue("files_image", true)).to(exchange).with("files.image.#")
        );
    }

    @Bean
    public RabbitAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

}
