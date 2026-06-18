package ar.edu.unq.poo2.item;

import java.util.Map;

public abstract class Item {
	public Map<String, Double> getResumenDePrecio(){
		return Map.of(getNombre(), getPrecioBaseCalculado());
	}

	public abstract String getNombre();
	public abstract String getDescripcion();
    public abstract int getPeso();
	public abstract double getPrecioBaseCalculado();
	public abstract void add(Item item);
	public abstract void remove(Item item);
	public abstract Map<String, Integer> getResumenDeSku();
}