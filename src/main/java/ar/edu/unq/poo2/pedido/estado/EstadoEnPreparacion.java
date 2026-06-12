package ar.edu.unq.poo2.pedido.estado;

import ar.edu.unq.poo2.pedido.Inventario;
import ar.edu.unq.poo2.pedido.Pedido;

import java.util.HashMap;
import java.util.Map;

public class EstadoEnPreparacion extends EstadoReembolsador{
    @Override
    public void enviar(Pedido pedido){
        pedido.setEstadoActual(new EstadoEnviado());
    }

    @Override
    public void cancelar(Pedido pedido){
        // TODO: Reponer stock
        super.cancelar(pedido);
    }

    @Override
    protected Map<String, Double> extrasAReembolsar(Pedido pedido) {
        Map <String, Double> extras = new HashMap<>();
        // TODO: Agregar envio al diccionario de extras cuando se tenga su implementación.
        return extras;
    }
}
