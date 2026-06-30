package ar.edu.unq.poo2.pedido.estado;

import ar.edu.unq.poo2.notificaciones.ObservadorPedido;
import ar.edu.unq.poo2.pedido.Pedido;

import java.util.HashMap;

public class EstadoEnviado extends EstadoPedido{
    @Override
    public void entregar(Pedido pedido){
        pedido.setEstadoActual(new EstadoEntregado());
    }

    @Override
    public void enviar(Pedido pedido){}

    @Override
    public void cancelar(Pedido pedido){
        pedido.reponerStock();
        pedido.generarNotaDeCredito(new HashMap<>());
        pedido.setEstadoActual(new EstadoCancelado());
    }

    @Override
    public void notificarTransicion(Pedido pedido, ObservadorPedido observador){
        observador.alEnviar(pedido);
    }
}
