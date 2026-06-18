package ar.edu.unq.poo2.venta;

import ar.edu.unq.poo2.item.Item;

import java.time.LocalDate;
import java.util.List;

public class Venta {
    private final LocalDate fechaDeVenta;
    private final List<RegistroDeItem> registroDeItems; // Si contiene paquetes, contiene el paquete y agrega también los productos o paquetes que este contenga.

    public Venta(List<Item> contenidoDeVenta, LocalDate fechaDeVenta){
        this.fechaDeVenta = fechaDeVenta;
        this.registroDeItems = convertirARegistros(contenidoDeVenta);
    }

    private List<RegistroDeItem> convertirARegistros(List<Item> contenido){
        return contenido.stream()
                .flatMap(itemVendido -> itemVendido.getRegistroDeItem(1).stream())
                .toList();
    }

    public LocalDate getFechaDeVenta() {
        return this.fechaDeVenta;
    }

    public List<RegistroDeItem> getRegistroDeItems() {
        return this.registroDeItems;
    }

    public boolean ocurrioEntre(LocalDate inicio, LocalDate fin) {
        return !this.fechaDeVenta.isBefore(inicio) && !this.fechaDeVenta.isAfter(fin);
    }
}
