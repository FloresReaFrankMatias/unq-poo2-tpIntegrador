package ar.edu.unq.poo2.busqueda;

import ar.edu.unq.poo2.item.Item;

public interface CriterioBusqueda {
    boolean cumple(Item item);
}