package ar.edu.unq.poo2.pedido.estado;

import ar.edu.unq.poo2.pedido.Pedido;
import ar.edu.unq.poo2.pedido.observadores.ObservadorPedido;

public class EstadoEntregado extends EstadoPedido{
    @Override
    public void entregar(Pedido pedido){}

    @Override
    public void notificarTransicion(Pedido pedido, ObservadorPedido observador){
        observador.alEntregar(pedido);
    }
}
