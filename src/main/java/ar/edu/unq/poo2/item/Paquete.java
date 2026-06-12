package ar.edu.unq.poo2.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Paquete implements Item {
	private String nombre;
	private double descuento;
	private List<Item> items;
	
	
	public Paquete(String nombre,double descuento) {
		// TODO Auto-generated constructor stub
		this.nombre = nombre;
		this.descuento=descuento;
		this.items = new ArrayList<>();
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return nombre;
	}

	@Override
	public String getDescripcion() {
		// TODO Auto-generated method stub
		return "Paquete: " + this.nombre + "\n" +
			   "Contiene: " + this.items.size() + " items" + "\n" + 
		       "Descuento: " + (this.descuento * 100) + "%";
	}

	
	public double getPrecioBase() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		this.items.add(item);

	}

	@Override
	public void remove(Item item) {
		// TODO Auto-generated method stub
		this.puedeEliminarItem(item);
		this.items.remove(item);
	}

	private void puedeEliminarItem(Item item) {
		// TODO Auto-generated method stub
		if (!this.items.contains(item)) {
			throw new RuntimeException("El item no se encuentra en el paquete");
		}
	}

	@Override
	public Map<String, Double> getResumenDePrecio(){
		Map<String, Double> resumen = new HashMap<>();
		resumen.put(nombre, getPrecioBaseCalculado());
		return Map.of(this.getNombre(), this.getPrecioBaseCalculado());
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
