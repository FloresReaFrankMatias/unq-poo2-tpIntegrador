package ar.edu.unq.poo2.busqueda;

import ar.edu.unq.poo2.item.Item;

public class PorPrecioMaximo implements CriterioBusqueda{
	private double precioMaximo;
	
    public PorPrecioMaximo(double precioMaximo) {
	        this.precioMaximo = precioMaximo;
	}
    
    @Override
    public boolean cumple(Item item) {
        return item.getPrecio() <= precioMaximo;
    }
}
