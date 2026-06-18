package ar.edu.unq.poo2.venta;

import ar.edu.unq.poo2.item.Item;

public class RegistroDeItem {
    private final Item item;
    private final Double precio;

    public RegistroDeItem(Item item, Double precio){
        this.item = item;
        this.precio = precio;
    }

    public String getNombreItem() {
        return this.item.getNombre();
    }

    public Item getItem() {
        return this.item;
    }

    public Double getPrecio() {
        return this.precio;
    }
}
