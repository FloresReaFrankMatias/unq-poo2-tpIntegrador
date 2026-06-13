package ar.edu.unq.poo2.pedido.notadecredito;

import java.util.Map;

public class NotaDeCredito {
    private final Map<String, Double> reembolsado;

    public NotaDeCredito(Map<String,Double> reembolsado){
        this.reembolsado = reembolsado;
    }
}
