package ar.edu.unq.poo2.notificaciones;

import ar.edu.unq.poo2.pedido.Pedido;
import ar.edu.unq.poo2.pedido.estado.EstadoPedido;

public interface ObservadorPedido {
    void actualizar(Pedido pedido, EstadoPedido anterior, EstadoPedido nuevo);

}
