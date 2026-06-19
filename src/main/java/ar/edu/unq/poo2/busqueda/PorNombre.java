package ar.edu.unq.poo2.busqueda;

import ar.edu.unq.poo2.item.Item;

public class PorNombre implements CriterioBusqueda {

    private String textoBuscado;

    public PorNombre(String textoBuscado) {
        this.textoBuscado = textoBuscado;
    }

    @Override
    public boolean cumple(Item item) {

        String nombreItem = item.getNombre().toLowerCase();
        String textoABuscar = textoBuscado.toLowerCase();

        return nombreItem.contains(textoABuscar);
    }
}