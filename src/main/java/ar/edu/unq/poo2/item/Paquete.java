package ar.edu.unq.poo2.item;

import java.util.ArrayList;
import java.util.List;

public class Paquete implements Item {
	private String nombre;
	private double descuento;
	private String descripcion;
	private List<Item> items;
	
	
	public Paquete(String nombre,double descuento,String descripcion) {
		// TODO Auto-generated constructor stub
		this.nombre = nombre;
		this.descuento=descuento;
		this.descripcion=descripcion;
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
		return this.descripcion;
	}
	
	public List<Item> getItems() {
		return items;
	}

	
	public double getPrecioBase() {
		// TODO Auto-generated method stub
		return this.items.stream()
				         .mapToDouble(item -> item.getPrecioBaseCalculado())
				         .sum();
	}
	@Override
	public int getPeso() {
		return this.items.stream()
				         .mapToInt(item -> item.getPeso())
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

}
