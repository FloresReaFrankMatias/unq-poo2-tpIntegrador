package ar.edu.unq.poo2.pedido.estado;

import ar.edu.unq.poo2.pedido.Pedido;

public class EstadoConfirmado extends EstadoPedido{
    @Override
    public void preparar(Pedido pedido){
        //pedido.setEstadoActual(new EstadoEnPreparacion());
    }
}
