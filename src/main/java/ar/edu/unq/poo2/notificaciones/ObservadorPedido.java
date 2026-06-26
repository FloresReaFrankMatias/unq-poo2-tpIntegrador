package ar.edu.unq.poo2.notificaciones;

import ar.edu.unq.poo2.pedido.Pedido;

public interface ObservadorPedido {
    default void alConfirmar(Pedido pedido) {}
    default void alEnviar(Pedido pedido) {}
    default void alEntregar(Pedido pedido) {}
    default void alCancelar(Pedido pedido) {}
    default void alPreparar(Pedido pedido) {}
}
