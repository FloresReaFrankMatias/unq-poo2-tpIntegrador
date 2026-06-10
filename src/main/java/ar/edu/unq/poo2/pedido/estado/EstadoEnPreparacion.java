package ar.edu.unq.poo2.pedido.estado;

import ar.edu.unq.poo2.pedido.Pedido;

public class EstadoEnPreparacion extends EstadoPedido{
    @Override
    public void enviar(Pedido pedido){
        pedido.setEstadoActual(new EstadoEnviado());
    }

    @Override
    public void cancelar(Pedido pedido){
        // TODO: Reponer stock
        // TODO: Reembolsar precio del productos y del envió.
        super.cancelar(pedido);
    }
}
