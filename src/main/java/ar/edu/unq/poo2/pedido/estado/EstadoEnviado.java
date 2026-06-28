package ar.edu.unq.poo2.pedido.estado;

import ar.edu.unq.poo2.notificaciones.ObservadorPedido;
import ar.edu.unq.poo2.pedido.Pedido;

public class EstadoEnviado extends EstadoReembolsador{
    @Override
    public void entregar(Pedido pedido){
        pedido.setEstadoActual(new EstadoEntregado());
    }

    @Override
    public void enviar(Pedido pedido){}

    @Override
    public void notificarTransicion(Pedido pedido, ObservadorPedido observador){
        observador.alEnviar(pedido);
    }
}
