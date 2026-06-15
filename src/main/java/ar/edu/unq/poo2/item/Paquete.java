package ar.edu.unq.poo2.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Paquete extends Item {
	private String nombre;
	private double descuento;
	private List<Item> items;
	
	
	public Paquete(String nombre,double descuento) {
		this.nombre = nombre;
		this.descuento=descuento;
		this.items = new ArrayList<>();
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public String getDescripcion() {
		return "Paquete: " + this.nombre + "\n" +
			   "Contiene: " + this.items.size() + " items" + "\n" + 
		       "Descuento: " + (this.descuento * 100) + "%";
	}

	
	public double getPrecioBase() {
		return this.items.stream()
				 .mapToDouble(item -> item.getPrecioBaseCalculado())
				 .sum();
	}
	@Override
	public double getPrecioBaseCalculado() {
		return this.getPrecioBase()* (1.0 - this.descuento);
	}

	@Override
	public void add(Item item) {
		this.items.add(item);

	}

	@Override
	public void remove(Item item) {
		this.puedeEliminarItem(item);
		this.items.remove(item);
	}

	private void puedeEliminarItem(Item item) {
		if (!this.items.contains(item)) {
			throw new RuntimeException("El item no se encuentra en el paquete");
		}
	}

	@Override
	public Map<String, Integer> getResumenDeSku() {
		Map<String, Integer> resumen = new HashMap<>();
		for (Item item : this.items) {
			item.getResumenDeSku().forEach((sku, cantidad) -> resumen.merge(sku, cantidad, Integer::sum));
		}
		return resumen;
	}
}
