package ar.edu.unq.poo2.pedido.estado;

import ar.edu.unq.poo2.item.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GestorNotasDeCredito {
    private Set<NotaDeCredito> notas;

    public void hacerNotaDeCredito(Map<String, Double> reembolsado){
        notas.add(new NotaDeCredito(reembolsado));
    }
}
