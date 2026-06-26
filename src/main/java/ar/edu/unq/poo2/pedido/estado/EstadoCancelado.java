package ar.edu.unq.poo2.pedido.estado;

import ar.edu.unq.poo2.notificaciones.ObservadorPedido;
import ar.edu.unq.poo2.pedido.Pedido;

public class EstadoCancelado extends EstadoPedido{
    @Override
    public void cancelar(Pedido pedido){}

    @Override
    public void notificarTransicion(Pedido pedido, ObservadorPedido observador, EstadoPedido estAnt){
        observador.alCancelar(pedido,estAnt, this);
    }
}
