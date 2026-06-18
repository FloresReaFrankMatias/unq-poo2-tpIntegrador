package ar.edu.unq.poo2.pedido.estado;

import ar.edu.unq.poo2.pedido.Pedido;
import ar.edu.unq.poo2.pedido.observadores.ObservadorPedido;

public class EstadoConfirmado extends EstadoPedido{
    @Override
    public void confirmar(Pedido pedido){}

    @Override
    public void preparar(Pedido pedido){
        //pedido.setEstadoActual(new EstadoEnPreparacion());
    }
    
    @Override
    public void cancelar(Pedido pedido){
        pedido.reponerStock();
        pedido.setEstadoActual(new EstadoCancelado());
    }

    @Override
    public void notificarTransicion(Pedido pedido, ObservadorPedido observador){
        observador.alConfirmar(pedido);
    }
}
