package ar.edu.unq.poo2.pedido.estado;

import ar.edu.unq.poo2.pedido.Inventario;
import ar.edu.unq.poo2.pedido.Pedido;

public class EstadoEnviado extends EstadoReembolsador{
    @Override
    public void entregar(Pedido pedido){
        pedido.setEstadoActual(new EstadoEntregado());
    }
}
