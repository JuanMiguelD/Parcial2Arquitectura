package com.microservice.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class PedidoService {

    private final RabbitTemplate rabbitTemplate;

    public PedidoService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @CircuitBreaker(name = "rabbitmqCB", fallbackMethod = "fallbackEnvioPedido")
    public ResponseEntity<String> enviarPedido(Pedido pedido) {
        rabbitTemplate.convertAndSend("colaPedidos88", pedido);
        return ResponseEntity.ok("Pedido enviado exitosamente");
    }

    public ResponseEntity<String> fallbackEnvioPedido(Pedido pedido, Throwable t) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Error en el envío de pedido.");
    }
}
