package ar.edu.unq.poo2.busqueda;

import ar.edu.unq.poo2.item.Item;

import java.util.ArrayList;
import java.util.List;

public class Catalogo {

    private List<Item> items;

    public Catalogo() {
        this.items = new ArrayList<>();
    }

    public void agregarItem(Item item) {
        this.items.add(item);
    }

    public List<Item> buscar(CriterioBusqueda criterio) {

        return this.items.stream()
                         .filter(item ->
                                 criterio.cumple(item))
                         .toList();
    }
}