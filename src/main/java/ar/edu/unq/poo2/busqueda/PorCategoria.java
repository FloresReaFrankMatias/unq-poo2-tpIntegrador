package ar.edu.unq.poo2.busqueda;

import ar.edu.unq.poo2.item.Categoria;
import ar.edu.unq.poo2.item.Item;

public class PorCategoria implements CriterioBusqueda {
    private Categoria categoria;

    public PorCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean cumple(Item item) {
        return item.getCategoria().equals(categoria);
    }
}