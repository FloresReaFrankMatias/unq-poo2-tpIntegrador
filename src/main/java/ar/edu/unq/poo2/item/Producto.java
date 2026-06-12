package ar.edu.unq.poo2.item;

import java.util.HashMap;
import java.util.Map;

public class Producto implements Item {
	private String sku; 
	private String nombre;
	private int peso;
	private String marca;
	private Categoria categoria;
	private double precioBase;
	private double descuento;

	public Producto(String sku, String nombre, int peso, String marca, Categoria categoria, double precioBase, double descuento) {
		super();
		this.sku = sku;
		this.nombre = nombre;
		this.peso = peso;
		this.marca = marca;
		this.categoria = categoria;
		this.precioBase = precioBase;
		this.descuento = descuento;
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return this.nombre;
	}

	@Override
	public String getDescripcion() {
		// TODO Auto-generated method stub
		
		return   "SKU: " + this.sku + "\n" + 
		         "Marca: " + this.marca + "\n"  +
		         "Categoria: " + this.categoria  + "\n" + 
		         "Peso: " + this.peso + "g";
				
	}

	@Override
	public double getPrecioBaseCalculado() {
		// TODO Auto-generated method stub
		return this.precioBase * (1.0  - this.descuento);
	}

	@Override
	public void add(Item item) {
		// lanza excepcion, no se pueden agregar items a un producto

	}

	@Override
	public void remove(Item item) {
		// lanza excepcion, no se pueden agregar items a un producto
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
		resumen.put(sku, 1);
		return resumen;
	}
}
