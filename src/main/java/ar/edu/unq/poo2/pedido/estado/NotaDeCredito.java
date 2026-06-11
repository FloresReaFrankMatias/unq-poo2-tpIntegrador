package ar.edu.unq.poo2.pedido.estado;

import java.util.Map;

public class NotaDeCredito {
    private final Map<String, Double> reembolsado;

    public NotaDeCredito(Map<String,Double> reembolsado){
        this.reembolsado = reembolsado;
    }

    public Map<String, Double> getReembolsado() {
        return reembolsado;
    }
}
