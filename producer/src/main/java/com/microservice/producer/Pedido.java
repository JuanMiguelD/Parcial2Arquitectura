package com.microservice.producer; 
import java.io.Serializable;




public class Pedido implements Serializable  {
    private static final long serialVersionUID = 1L;

    
    private String id;

    
    private String producto;

    public Pedido(String id, String descripcion) {
        this.id = id;
        this.producto = descripcion;
    }

    // Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String descripcion) {
        this.producto = descripcion;
    }
}