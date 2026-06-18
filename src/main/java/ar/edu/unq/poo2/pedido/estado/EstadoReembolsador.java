package ar.edu.unq.poo2.pedido.estado;

import ar.edu.unq.poo2.pedido.Pedido;

import java.util.HashMap;
import java.util.Map;

public abstract class EstadoReembolsador extends EstadoPedido{
    @Override
    public void cancelar(Pedido pedido){
        pedido.generarNotaDeCredito(extrasAReembolsar(pedido));
        pedido.setEstadoActual(new EstadoCancelado());
    }

    protected Map<String, Double> extrasAReembolsar(Pedido pedido) {
        return new HashMap<>();
    }
}
