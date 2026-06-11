package ar.edu.unq.poo2.pedido;

import java.util.HashMap;
import java.util.Map;

public class Inventario {
    private final Map<String, Integer> stockDeInventario;

    public Inventario() {
        stockDeInventario = new HashMap<>();
    }

    public void incrementarStock(String sku, Integer cantidadSolicitada){
        modificarStock(sku, cantidadSolicitada, cantidadSolicitada);
    }

    public void decrementarStock(String sku, Integer cantidadSolicitada){
        modificarStock(sku, cantidadSolicitada, -cantidadSolicitada);
    }

    public void incrementarStock(String sku){
        incrementarStock(sku, 1);
    }

    public void decrementarStock(String sku){
        decrementarStock(sku, 1);
    }

    private void modificarStock(String sku, Integer cantidadSolicitada, Integer diferencia) {
        validarCantidadSolicitada(cantidadSolicitada);
        Integer stockDisponible = stockDeInventario.getOrDefault(sku, 0);
        if (diferencia < 0) { // seguramente se podría evitar el if.
            validarHayCantidadSuficiente(Math.abs(diferencia), stockDisponible);
        }
        stockDeInventario.put(sku, stockDisponible + diferencia);
    }

    private void validarCantidadSolicitada(Integer cantidadSolicitada){
        if (cantidadSolicitada <= 0){
            throw new RuntimeException();
        }
    }

    private void validarHayCantidadSuficiente(Integer cantidadSolicitada, Integer cantidadDisponible) {
        if (cantidadSolicitada > cantidadDisponible){
            throw new RuntimeException();
        }
    }
}
