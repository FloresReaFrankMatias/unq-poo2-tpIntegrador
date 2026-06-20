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
	public Categoria getCategoria() {

	    Categoria categoriaComun = items.get(0).getCategoria();

	    if (todosLosItemsSonDeLaMismaCategoria(categoriaComun)) {
	        return categoriaComun;
	    }

	    return Categoria.OTROS;
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
	public boolean coincideNombre(String textoBuscado) {

	    return coincideConNombreDePaquete(textoBuscado)
	        || coincideConNombreDeAlgunItem(textoBuscado);
	}
	
	private boolean coincideConNombreDePaquete(String textoBuscado) {

	    return this.getNombre().toLowerCase().contains(textoBuscado.toLowerCase());
	}
	
	private boolean coincideConNombreDeAlgunItem(String textoBuscado) {

	    return this.items.stream()
	                     .anyMatch(item ->
	                             item.coincideNombre(textoBuscado));
	}
	
	private boolean todosLosItemsSonDeLaMismaCategoria(Categoria categoriaComun) {

	    for (Item item : items) {

	        if (!item.getCategoria().equals(categoriaComun)) {
	            return false;
	        }
	    }

	    return true;
	}

}
