package com.microservice.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PedidoListener {

    private final ProcesarPedidoService procesarPedidoService;

    public PedidoListener(ProcesarPedidoService procesarPedidoService) {
        this.procesarPedidoService = procesarPedidoService;
    }

    @RabbitListener(queues = "colaPedidos88") // Asegúrate de que el nombre de la cola coincide
    public void recibirPedido(String mensaje) {
        try {
            procesarPedidoService.procesarMensaje(mensaje);
        } catch (Exception e) {
            e.printStackTrace(); // Maneja el error de acuerdo a tus necesidades
        }
    }
}
