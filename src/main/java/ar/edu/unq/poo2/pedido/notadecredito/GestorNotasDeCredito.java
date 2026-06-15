package ar.edu.unq.poo2.pedido.notadecredito;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GestorNotasDeCredito {
    private final Set<NotaDeCredito> notas;

    public GestorNotasDeCredito(){
        this.notas = new HashSet<>();
    }

    public void hacerNotaDeCredito(Map<String, Double> reembolsado){
        notas.add(new NotaDeCredito(reembolsado));
    }

    public Set<NotaDeCredito> getNotas(){
        return Collections.unmodifiableSet(notas);
    }
}
