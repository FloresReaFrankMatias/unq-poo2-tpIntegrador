package ar.edu.unq.poo2.item;

import ar.edu.unq.poo2.venta.RegistroDeItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Paquete extends Item {
	private List<Item> items;

	public Paquete(String nombre, double descuento, String descripcion, Categoria categoria) {
		// Pasamos al padre
		super(nombre, descripcion, categoria, descuento);
		this.items = new ArrayList<>();
	}
	
	public List<Item> getItems() {
		return items;
	}
	
	@Override
	public double getPrecioBase() {
		return this.items.stream()
				.mapToDouble(item -> item.getPrecio())
				.sum();
	}

	@Override
	public int getPeso() {
		return this.items.stream()
				.mapToInt(item -> item.getPeso())
				.sum();
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

	@Override
	public List<RegistroDeItem> getRegistroDeItem(double multiplicadorDescuento) {
		double precioEfectivoPaquete = this.getPrecio() * multiplicadorDescuento;
		double nuevoMultiplicador = multiplicadorDescuento * (1.0 - getDescuento());

		List<RegistroDeItem> registros = new ArrayList<>();
		registros.add(new RegistroDeItem(this, precioEfectivoPaquete));
		this.items.forEach(item -> registros.addAll(item.getRegistroDeItem(nuevoMultiplicador)));

		return registros;
	}
	
	@Override
	public Map<String, Integer> getResumenDeSku() {
		Map<String, Integer> resumen = new HashMap<>();
		for (Item item : this.items) {
			item.getResumenDeSku().forEach((sku, cantidad) -> resumen.merge(sku, cantidad, Integer::sum));
		}
		return resumen;
	}

	private void puedeEliminarItem(Item item) {
		if (!this.items.contains(item)) {
			throw new RuntimeException("El item no se encuentra en el paquete");
		}
	}
	
	@Override
	public boolean coincideNombre(String nombre) {
	    return super.coincideNombre(nombre) || coincideConNombreDeAlgunItem(nombre);
	}
	
	private boolean coincideConNombreDeAlgunItem(String nombre) {
	    return this.items.stream()
	                     .anyMatch(item -> item.coincideNombre(nombre));
	}
}
