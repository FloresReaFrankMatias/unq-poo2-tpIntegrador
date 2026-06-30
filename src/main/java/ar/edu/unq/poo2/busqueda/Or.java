package ar.edu.unq.poo2.busqueda;

import ar.edu.unq.poo2.item.Item;

public class Or implements CriterioBusqueda {
    private CriterioBusqueda criterio1;
    private CriterioBusqueda criterio2;

    public Or(CriterioBusqueda criterio1, CriterioBusqueda criterio2) {
        this.criterio1 = criterio1;
        this.criterio2 = criterio2;
    }

    @Override
    public boolean cumple(Item item) {
        return criterio1.cumple(item)
            || criterio2.cumple(item);
    }
}