package ar.edu.unq.poo2.pedido.estado;

import ar.edu.unq.poo2.notificaciones.ObservadorPedido;
import ar.edu.unq.poo2.pedido.Pedido;

public class EstadoEntregado extends EstadoPedido{
    @Override
    public void entregar(Pedido pedido){}

    @Override
    public void notificarTransicion(Pedido pedido, ObservadorPedido observador){
        observador.alEntregar(pedido);
    }
}
