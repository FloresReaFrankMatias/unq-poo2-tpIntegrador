package ar.edu.unq.poo2.pedido.estado;

import ar.edu.unq.poo2.item.Item;
import ar.edu.unq.poo2.pedido.Inventario;
import ar.edu.unq.poo2.pedido.Pedido;

public class EstadoBorrador extends EstadoPedido {
    @Override
    public void confirmar(Pedido pedido){
        pedido.setEstadoActual(new EstadoConfirmado());
        // TODO: Decrementar stock.
    }

    @Override
    public void verificarAgregarItem(Pedido pedido, Item item){}

    @Override
    public void verificarQuitarItem(Pedido pedido, Item item){}
}
