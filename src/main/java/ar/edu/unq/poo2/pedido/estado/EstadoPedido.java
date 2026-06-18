package ar.edu.unq.poo2.pedido.estado;

import ar.edu.unq.poo2.item.Item;
import ar.edu.unq.poo2.pedido.Pedido;
import ar.edu.unq.poo2.pedido.observadores.ObservadorPedido;

public abstract class EstadoPedido {
    public void verificarAgregarItem(Pedido pedido, Item item){
        lanzarOperacionInvalida();
    }

    public void verificarQuitarItem(Pedido pedido, Item item){
        lanzarOperacionInvalida();
    }

    public void confirmar(Pedido pedido){
        lanzarOperacionInvalida();
    }

    public void cancelar(Pedido pedido){
        lanzarOperacionInvalida();
    }

    public void preparar(Pedido pedido){
        lanzarOperacionInvalida();
    }

    public void enviar(Pedido pedido){
        lanzarOperacionInvalida();
    }

    public void entregar(Pedido pedido){
        lanzarOperacionInvalida();
    }

    private void lanzarOperacionInvalida(){
        throw new OperacionInvalidaParaEstadoException();
    }

    public void notificarTransicion(Pedido pedido, ObservadorPedido observador){}
}
