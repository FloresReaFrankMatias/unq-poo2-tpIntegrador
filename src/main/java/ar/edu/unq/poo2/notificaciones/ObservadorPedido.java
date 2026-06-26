package ar.edu.unq.poo2.notificaciones;

import ar.edu.unq.poo2.pedido.Pedido;
import ar.edu.unq.poo2.pedido.estado.EstadoPedido;

public interface ObservadorPedido {
    default void alConfirmar(Pedido pedido,EstadoPedido estAnterior,EstadoPedido estActual) {}
    default void alEnviar(Pedido pedido,EstadoPedido estAnterior,EstadoPedido estActual) {}
    default void alEntregar(Pedido pedido,EstadoPedido estAnterior,EstadoPedido estActual) {}
    default void alCancelar(Pedido pedido,EstadoPedido estAnterior,EstadoPedido estActual) {}
    default void alPreparar(Pedido pedido,EstadoPedido estAnterior,EstadoPedido estActual) {}
}
