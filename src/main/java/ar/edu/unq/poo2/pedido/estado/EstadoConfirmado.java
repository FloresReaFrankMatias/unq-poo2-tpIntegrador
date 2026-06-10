package ar.edu.unq.poo2.pedido.estado;

import ar.edu.unq.poo2.pedido.Pedido;

public class EstadoConfirmado extends EstadoPedido{
    @Override
    public void preparar(Pedido pedido){
        pedido.setEstadoActual(new EstadoEnPreparacion());
    }

    @Override
    public void cancelar(Pedido pedido){
        // TODO: Reponer stock.
        super.cancelar(pedido);
    }
}
