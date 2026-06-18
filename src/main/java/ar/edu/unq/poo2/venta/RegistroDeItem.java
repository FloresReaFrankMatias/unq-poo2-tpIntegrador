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
        return item.getNombre();
    }

    public Item getItem() {
        return item;
    }

    public Double getPrecio() {
        return precio;
    }
}
