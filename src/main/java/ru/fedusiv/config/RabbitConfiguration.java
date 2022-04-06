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

    @Bean Queue textQueue()  { return new Queue("files_text", true); }
    @Bean Queue audioQueue() { return new Queue("files_audio", true); }
    @Bean Queue videoQueue() { return new Queue("files_video", true); }
    @Bean Queue imageQueue() { return new Queue("files_image", true); }

    @Bean Binding textBinding(TopicExchange exchange)
    { return BindingBuilder.bind(textQueue()).to(exchange).with("files.text.#"); }

    @Bean Binding audioBinding(TopicExchange exchange)
    { return BindingBuilder.bind(audioQueue()).to(exchange).with("files.audio.#"); }

    @Bean Binding videoBinding(TopicExchange exchange)
    { return BindingBuilder.bind(videoQueue()).to(exchange).with("files.video.#"); }

    @Bean Binding imageBinding(TopicExchange exchange)
    { return BindingBuilder.bind(imageQueue()).to(exchange).with("files.image.#"); }

    @Bean
    public RabbitAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

}
