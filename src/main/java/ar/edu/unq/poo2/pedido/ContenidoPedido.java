package ar.edu.unq.poo2.pedido;

import ar.edu.unq.poo2.item.Item;
import java.util.*;

public class ContenidoPedido {
    private final List<Item> items = new ArrayList<>();

    public void agregarItem(Item item) {
        items.add(item);
    }

    public void quitarItem(Item item) {
        items.remove(item);
    }

    public boolean tieneItems() {
        return !items.isEmpty();
    }

    public double getPesoTotal() {
        return items.stream().
                mapToInt(item -> item.getPeso()).sum();
    }

    public Double getValorTotal() {
        return items.stream()
                .mapToDouble(item -> item.getPrecio())
                .sum();
    }

    public Map<String, Integer> getResumenDeSkus() {
        Map<String, Integer> resumen = new HashMap<>();
        items.forEach(item -> agregarItemAResumenDeSkus(item, resumen));
        return resumen;
    }

    private void agregarItemAResumenDeSkus(Item item, Map<String, Integer> resumen){
        item.getResumenDeSku().forEach((sku, cantidad) -> resumen.merge(sku, cantidad, Integer::sum));
    }

    public Map<String, Double> getResumenDePrecios(){
        Map<String, Double> resumen = new HashMap<>();
        items.forEach(item -> agregarItemAResumenDePrecios(item, resumen));
        return resumen;
    }

    private void agregarItemAResumenDePrecios(Item item, Map<String, Double> resumen){
        item.getResumenDePrecio().forEach((nombre, precio) -> resumen.merge(nombre, precio, Double::sum));
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(items); //Es inmutable para evitar que cualquiera pueda agregar o quitar items de un pedido.
    }
}
