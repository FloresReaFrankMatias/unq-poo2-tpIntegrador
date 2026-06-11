package ar.edu.unq.poo2.pedido;

import ar.edu.unq.poo2.item.Item;
import ar.edu.unq.poo2.pedido.estado.EstadoPedido;

import java.util.List;

public class Pedido {
    EstadoPedido estadoActual;
    List<Item> contenidoDePedido;
    // Envio envio;

    public List<Item> getContenidoDePedido() {
        return contenidoDePedido;
    }

    public void setEstadoActual(EstadoPedido estadoPedido){
        estadoActual = estadoPedido;
    }

    public void agregarItem(Item item){
        estadoActual.verificarAgregarItem(this, item);
        contenidoDePedido.add(item);
    }

    public void quitarItem(Item item){
        estadoActual.verificarAgregarItem(this, item);
        contenidoDePedido.add(item);
    }

    public void confirmar(Inventario inventario){
        estadoActual.confirmar(this, inventario);
    }

    public void cancelar(Inventario inventario){
        estadoActual.cancelar(this, inventario);
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
