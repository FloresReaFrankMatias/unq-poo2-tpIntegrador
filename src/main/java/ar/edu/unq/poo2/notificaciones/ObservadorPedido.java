package ar.edu.unq.poo2.notificaciones;

import ar.edu.unq.poo2.pedido.Pedido;
import ar.edu.unq.poo2.pedido.estado.EstadoPedido;

public abstract class ObservadorPedido {
    public void alConfirmar(Pedido pedido) {}
    public void alEnviar(Pedido pedido) {}
    public void alEntregar(Pedido pedido) {}
    public void alCancelar(Pedido pedido) {}
}
