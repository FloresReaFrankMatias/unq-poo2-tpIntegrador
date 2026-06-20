package ar.edu.unq.poo2.item;

import ar.edu.unq.poo2.venta.RegistroDeItem;
import java.util.List;
import java.util.Map;

public abstract class Item {
	public Map<String, Double> getResumenDePrecio(){
		return Map.of(getNombre(), getPrecioBaseCalculado());
	}

	public abstract String getNombre();
	public abstract String getDescripcion();
    public abstract Categoria getCategoria();
    public abstract int getPeso();
	public abstract double getPrecioBaseCalculado();
	public abstract void add(Item item);
	public abstract void remove(Item item);
	public abstract List<RegistroDeItem> getRegistroDeItem(double multiplicadorDescuento);
	public abstract Map<String, Integer> getResumenDeSku();
}
