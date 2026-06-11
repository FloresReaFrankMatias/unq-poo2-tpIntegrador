package ar.edu.unq.poo2.pedido.estado;

import ar.edu.unq.poo2.item.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GestorNotasDeCredito {
    private Set<NotaDeCredito> notas;

    public void hacerNotaDeCredito(List<Item> items){
        Map<String, Double> reembolsado = new HashMap<>();
        items.forEach(item -> agregarDatosDeItem(item, reembolsado));
        notas.add(new NotaDeCredito(reembolsado));
    }

    private void agregarDatosDeItem(Item item, Map<String,Double> reembolsadoHastaAhora){
        reembolsadoHastaAhora.put(item.getNombre(), item.getPrecioBaseCalculado());
    }
}
