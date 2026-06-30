package ar.edu.unq.poo2.busqueda;

import ar.edu.unq.poo2.pedido.Inventario;

public class ConsultaDisponibilidadInventario {
    private Inventario inventario;

    public ConsultaDisponibilidadInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public boolean tieneStock(String sku) {
        return inventario.tieneStock(sku);
    }
}