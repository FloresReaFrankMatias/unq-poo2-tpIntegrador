package ar.edu.unq.poo2.pedido.notadecredito;

import java.util.Map;
import java.util.Set;

public class GestorNotasDeCredito {
    private Set<NotaDeCredito> notas;

    public void hacerNotaDeCredito(Map<String, Double> reembolsado){
        notas.add(new NotaDeCredito(reembolsado));
    }
}
