package ar.edu.unq.poo2.pedido.estado;

import ar.edu.unq.poo2.pedido.Inventario;
import ar.edu.unq.poo2.pedido.Pedido;

public class EstadoCancelado extends EstadoPedido{
    @Override
    public void cancelar(Pedido pedido, Inventario inventario){}
}
