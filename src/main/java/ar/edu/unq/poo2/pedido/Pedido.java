package ar.edu.unq.poo2.pedido;

import ar.edu.unq.poo2.item.Item;
import ar.edu.unq.poo2.pedido.estado.EstadoPedido;

import java.util.List;

public class Pedido {
    EstadoPedido estadoActual;
    List<Item> contenidoDePedido;
    // Envio envio;

    public void setEstadoActual(EstadoPedido estadoPedido){
        estadoActual = estadoPedido;
    }

    public void agregarItem(Item item){
        estadoActual.agregarItem(this, item);
    }

    public void quitarItem(Item item){
        estadoActual.agregarItem(this, item);
    }

    public void confirmar(){
        estadoActual.confirmar(this);
    }

    public void cancelar(){
        estadoActual.cancelar(this);
    }

    public void preparar(){
        estadoActual.preparar(this);
    }

    public void enviar(){
        estadoActual.enviar(this);
    }

    public void entregar(){
        estadoActual.entregar(this);
    }
}
