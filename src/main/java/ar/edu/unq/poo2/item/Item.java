package ar.edu.unq.poo2.item;

import ar.edu.unq.poo2.venta.RegistroDeItem;
import java.util.List;
import java.util.Map;

public interface Item {
	String getNombre();
	String getDescripcion();
	double getPrecioBaseCalculado();
	void add(Item item);
	void remove(Item item);
	Map<String, Integer> getResumenDeSku();
	List<RegistroDeItem> getRegistroDeItem(double multiplicadorDescuento);
	default Map<String, Double> getResumenDePrecio(){
		return Map.of(getNombre(), getPrecioBaseCalculado());
	}
}
