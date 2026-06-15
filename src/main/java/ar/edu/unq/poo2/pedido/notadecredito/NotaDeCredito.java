package ar.edu.unq.poo2.pedido.notadecredito;

import java.util.Collections;
import java.util.Map;

public class NotaDeCredito {
    private final Map<String, Double> reembolsado;

    public NotaDeCredito(Map<String,Double> reembolsado){
        this.reembolsado = reembolsado;
    }

    public Map<String, Double> getReembolsado() {
        return Collections.unmodifiableMap(reembolsado);
    }
}
