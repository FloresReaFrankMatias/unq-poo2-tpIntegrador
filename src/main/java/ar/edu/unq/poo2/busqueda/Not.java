package ar.edu.unq.poo2.busqueda;

import ar.edu.unq.poo2.item.Item;

public class Not implements CriterioBusqueda {

    private CriterioBusqueda criterio;

    public Not(CriterioBusqueda criterio) {

        this.criterio = criterio;
    }

    @Override
    public boolean cumple(Item item) {

        return !criterio.cumple(item);
    }
}