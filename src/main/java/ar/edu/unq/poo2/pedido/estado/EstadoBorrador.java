package ar.edu.unq.poo2.pedido.estado;

import ar.edu.unq.poo2.item.Item;
import ar.edu.unq.poo2.pedido.Pedido;

public class EstadoBorrador extends EstadoPedido {
    @Override
    public void confirmar(Pedido pedido){
        validarPedidoTieneItems(pedido);
        pedido.procesarPago();
        pedido.setEstadoActual(new EstadoConfirmado());
        pedido.descontarStock();
    }

    @Override
    public void cancelar(Pedido pedido){
        pedido.setEstadoActual(new EstadoCancelado());
    }

    @Override
    public void verificarAgregarItem(Pedido pedido, Item item){}

    @Override
    public void verificarQuitarItem(Pedido pedido, Item item){}

    private void validarPedidoTieneItems(Pedido pedido){
        if (!pedido.tieneItems()){
            throw new RuntimeException();
        }
    }
}
