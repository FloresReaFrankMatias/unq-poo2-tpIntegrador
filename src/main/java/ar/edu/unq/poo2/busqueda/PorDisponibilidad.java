package ar.edu.unq.poo2.busqueda;

import ar.edu.unq.poo2.item.Item;

public class PorDisponibilidad implements CriterioBusqueda {
    private ConsultaDisponibilidadInventario consultaInventario;

    public PorDisponibilidad(ConsultaDisponibilidadInventario consultaInventario) {
        this.consultaInventario = consultaInventario;
    }

    @Override
    public boolean cumple(Item item) {
        return item.getResumenDeSku()
                   .keySet()
                   .stream()
                   .allMatch(consultaInventario::tieneStock);
    }
}