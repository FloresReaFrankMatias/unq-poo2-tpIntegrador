package ar.edu.unq.poo2.pedido;

import java.util.List;

import ar.edu.unq.poo2.item.Item;
import ar.edu.unq.poo2.pago.MedioPago;
import ar.edu.unq.poo2.pedido.estado.EstadoPedido;

public class Pedido {
    EstadoPedido estadoActual;
    List<Item> contenidoDePedido;
    // Envio envio;
    private MedioPago medioPago;
    
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
    
    public MedioPago getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(MedioPago medioPago) {
        this.medioPago = medioPago;
    }
}
