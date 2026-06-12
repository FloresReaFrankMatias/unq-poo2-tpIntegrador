package ar.edu.unq.poo2.item;

import java.util.Map;

public interface Item {
	public String getNombre();
	public String getDescripcion();
	public double getPrecioBaseCalculado();
	public void add(Item item);
	public void remove(Item item);
	public Map<String, Double> getResumenDePrecio(); // TODO: solucionar repetición de código en Paquete y Producto, los cuales implementan está interfaz.
	public Map<String, Integer> getResumenDeSku();
}
