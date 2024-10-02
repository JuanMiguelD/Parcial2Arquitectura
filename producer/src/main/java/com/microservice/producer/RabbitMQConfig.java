package com.microservice.producer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitMQConfig {

    
    @Bean
    public Queue colaPedidos() {
        return new Queue("colaPedidos88", true);
    }
    
}

