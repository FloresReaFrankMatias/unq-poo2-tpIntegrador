package ar.edu.unq.poo2.pedido.estado;

import ar.edu.unq.poo2.pedido.Inventario;
import ar.edu.unq.poo2.pedido.Pedido;

import java.util.HashMap;
import java.util.Map;

public class EstadoReembolsador extends EstadoPedido{
    @Override
    public void cancelar(Pedido pedido){
        pedido.generarNotaDeCredito(extrasAReembolsar(pedido));
        super.cancelar(pedido);
    }

    protected Map<String, Double> extrasAReembolsar(Pedido pedido) {
        return new HashMap<>();
    }
}
