package ar.edu.unq.poo2.pedido.estado;

import ar.edu.unq.poo2.pedido.Pedido;

public class EstadoEnviado extends EstadoPedido{
    @Override
    public void entregar(Pedido pedido){
        pedido.setEstadoActual(new EstadoEntregado());
    }

    @Override
    public void cancelar(Pedido pedido){
        // TODO: Reembolsar precio del producto, pero no del envió. Tal vez se puede implementar template aca.
        super.cancelar(pedido);
    }
}
