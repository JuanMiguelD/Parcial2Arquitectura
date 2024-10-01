package com.microservice.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class ProcesarPedidoService {
    private final ObjectMapper objectMapper;

    public ProcesarPedidoService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void procesarMensaje(String mensaje) throws Exception {
        // Convertir el mensaje JSON en un objeto Pedido
        Pedido pedido = objectMapper.readValue(mensaje, Pedido.class);
        // Procesar el pedido
        System.out.println("Procesando pedido: " + pedido);
    }
}

