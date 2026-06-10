package ar.edu.unq.poo2.pedido.estado;

import ar.edu.unq.poo2.item.Item;
import ar.edu.unq.poo2.pedido.Pedido;

public abstract class EstadoPedido {
    public void agregarItem(Pedido pedido, Item item){
        operacionInvalida();
    }

    public void quitarItem(Pedido pedido, Item item){
        operacionInvalida();
    }

    public void confirmar(Pedido pedido){
        operacionInvalida();
    }

    public void cancelar(Pedido pedido){
        pedido.setEstadoActual(new Cancelado());
    }

    public void preparar(Pedido pedido){
        operacionInvalida();
    }

    public void enviar(Pedido pedido){
        operacionInvalida();
    }

    public void entregar(Pedido pedido){
        operacionInvalida();
    }

    private void operacionInvalida(){
        throw new OperacionInvalidaParaEstadoException();
    }
}
