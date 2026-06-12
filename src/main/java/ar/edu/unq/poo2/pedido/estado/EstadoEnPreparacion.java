package ar.edu.unq.poo2.pedido.estado;

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
        pedido.reponerStock();
        super.cancelar(pedido);
    }

    @Override
    protected Map<String, Double> extrasAReembolsar(Pedido pedido) {
        Map <String, Double> extras = new HashMap<>();
        extras.put("Envió", pedido.getEnvio().calcularCosto(pedido));
        return extras;
    }
}
