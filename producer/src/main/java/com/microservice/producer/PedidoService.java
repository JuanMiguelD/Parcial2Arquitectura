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

    @CircuitBreaker(name = "pedidosCircuitBreaker",fallbackMethod = "fallbackEnvioPedido")
     public ResponseEntity<String> enviarPedido(Pedido pedido) {
        
        /*
        throw new RuntimeException("Simulación de fallo en el envío de pedido");
         */   
        
        System.out.println("\n \n \n \n \n Circuit breaker activado. Deja pasar ejecutado.");
        rabbitTemplate.convertAndSend("colaPedidos88", pedido);
        return ResponseEntity.ok("Pedido enviado exitosamente");
        
    }

    public ResponseEntity<String> fallbackEnvioPedido(Pedido pedido, Throwable t) {
        System.out.println("\n \n \n \n \n Circuit breaker activado. Fallback ejecutado.");

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Error en el envío de pedido.");
    }


}