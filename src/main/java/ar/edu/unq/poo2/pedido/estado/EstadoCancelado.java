package ar.edu.unq.poo2.pedido.estado;

import ar.edu.unq.poo2.pedido.Pedido;
import ar.edu.unq.poo2.pedido.observadores.ObservadorPedido;

public class EstadoCancelado extends EstadoPedido{
    @Override
    public void cancelar(Pedido pedido){}

    @Override
    public void notificarTransicion(Pedido pedido, ObservadorPedido observador){
        observador.alCancelar(pedido);
    }
}
