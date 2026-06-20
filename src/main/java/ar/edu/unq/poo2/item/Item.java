package ar.edu.unq.poo2.item;

import ar.edu.unq.poo2.venta.RegistroDeItem;
import java.util.List;
import java.util.Map;

public abstract class Item {
	private String nombre;
	private String descripcion;
	private Categoria categoria;
	private double descuento;

	public Item(String nombre, String descripcion, Categoria categoria, double descuento) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.descuento = descuento;
	}

	public Map<String, Double> getResumenDePrecio(){
		return Map.of(getNombre(), getPrecio());
	}

	public double getPrecio(){
		return this.getPrecioBase() * (1.0 - descuento);
	}

	protected abstract double getPrecioBase();

	public void add(Item item){
		throw new UnsupportedOperationException("No se pueden agregar items a este elemento");
	}

	public void remove(Item item){
		throw new UnsupportedOperationException("No se pueden remover items de este elemento");
	}

    public boolean coincideNombre(String nombre){
        return this.nombre.toLowerCase().contains(nombre.toLowerCase());
    }

	public String getNombre(){
		return nombre;
	}

	public Categoria getCategoria(){
		return categoria;
	}

	public String getDescripcion(){
		return descripcion;
	}

	protected double getDescuento(){
		return descuento;
	}

	public abstract int getPeso();
	public abstract List<RegistroDeItem> getRegistroDeItem(double multiplicadorDescuento);
	public abstract Map<String, Integer> getResumenDeSku();
}
