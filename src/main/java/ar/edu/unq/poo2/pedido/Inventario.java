package ar.edu.unq.poo2.pedido;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventario {
    private final Map<String, Integer> stockDeInventario;
    public Inventario() {
        stockDeInventario = new HashMap<>();
    }

    public void decrementarStock(Map<String, Integer> articulos) {
        validarDecrementoDeStock(articulos);
        articulos.forEach((sku, cantidad)-> modificarStock(sku, -cantidad));
    }

    public void incrementarStock(Map<String, Integer> articulos) {
        validarCantidadesSolicitadas(articulos.values());
        articulos.forEach(this::modificarStock);
    }

    private void modificarStock(String sku, Integer cantidad) {
        stockDeInventario.merge(sku, cantidad, Integer::sum);
    }

    private void validarDecrementoDeStock(Map<String, Integer> articulos) {
        validarCantidadesSolicitadas(articulos.values());
        validarHayStocksSuficientes(articulos);
    }

    private void validarHayStocksSuficientes(Map<String, Integer> articulos){
        articulos.forEach(this::validarHayStockSuficiente);
    }

    private void validarHayStockSuficiente(String sku, Integer cantidadSolicitada) {
        if (cantidadSolicitada > getCantidadDisponible(sku)){
            throw new RuntimeException();
        }
    }

    private void validarCantidadSolicitada(Integer cantidadSolicitada){
        if (cantidadSolicitada <= 0){
            throw new RuntimeException();
        }
    }

    private void validarCantidadesSolicitadas(Collection<Integer> cantidades) {
        cantidades.forEach(this::validarCantidadSolicitada);
    }

    private Integer getCantidadDisponible(String sku){
        return stockDeInventario.getOrDefault(sku, 0);
    }
}
