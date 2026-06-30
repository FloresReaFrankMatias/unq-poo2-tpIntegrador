package ar.edu.unq.poo2.pedido.estado;

import ar.edu.unq.poo2.notificaciones.ObservadorPedido;
import ar.edu.unq.poo2.pedido.Pedido;

import java.util.HashMap;
import java.util.Map;

public class EstadoEnPreparacion extends EstadoPedido{
    @Override
    public void preparar(Pedido pedido){}

    @Override
    public void enviar(Pedido pedido){
        pedido.setEstadoActual(new EstadoEnviado());
    }

    @Override
    public void cancelar(Pedido pedido){
        pedido.reponerStock();
        Map<String, Double> extras = Map.of("Envió", pedido.getCostoEnvio());
        pedido.generarNotaDeCredito(extras);
        pedido.setEstadoActual(new EstadoCancelado());
    }

    @Override
    public void notificarTransicion(Pedido pedido, ObservadorPedido observador){
        observador.alPreparar(pedido);
    }
}
