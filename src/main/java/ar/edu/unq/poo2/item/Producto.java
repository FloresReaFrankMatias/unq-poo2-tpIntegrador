package ar.edu.unq.poo2.item;

import ar.edu.unq.poo2.item.atributo.Atributo;
import ar.edu.unq.poo2.item.atributo.AtributoBooleano;
import ar.edu.unq.poo2.item.atributo.AtributoNumero;
import ar.edu.unq.poo2.item.atributo.AtributoString;
import ar.edu.unq.poo2.venta.RegistroDeItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Producto extends Item {
	private String sku;
	private int peso;
	private String marca;
	private double precioBase;
	private Map<String, Atributo<?>> atributosDinamicos;

	public Producto(String sku, String nombre, String descripcion, int peso, String marca, Categoria categoria, double precioBase, double descuento) {
		super(nombre, descripcion, categoria, descuento);
		this.sku = sku;
		this.peso = peso;
		this.marca = marca;
		this.precioBase = precioBase;
		this.atributosDinamicos = new HashMap<>();
	}

	public Atributo<?> getAtributoDinamico(String nombre) {
		return this.atributosDinamicos.get(nombre);
	}

	public void setAtributoDinamico(String nombre, String valor) {
		this.atributosDinamicos.put(nombre, new AtributoString(valor));
	}

	public void setAtributoDinamico(String nombre, Number valor) {
		this.atributosDinamicos.put(nombre, new AtributoNumero(valor));
	}

	public void setAtributoDinamico(String nombre, Boolean valor) {
		this.atributosDinamicos.put(nombre, new AtributoBooleano(valor));
	}

	public boolean atributosSonValidos() {
		return atributosFijosSonValidos() && atributosDinamicosSonValidos();
	}

	private boolean atributosFijosSonValidos() {
		return  sku != null && getNombre() != null;
	}

	private boolean atributosDinamicosSonValidos() {
		return atributosDinamicos.values().stream().allMatch(atributo -> atributo.getValor() != null);
	}

	@Override
	public List<RegistroDeItem> getRegistroDeItem(double multiplicadorDescuento) {
		double precio = this.getPrecio() * multiplicadorDescuento;
		return List.of(new RegistroDeItem(this, precio));
	}

	@Override
	public Map<String, Integer> getResumenDeSku() {
		Map<String, Integer> resumen = new HashMap<>();
		resumen.put(sku, 1);
		return resumen;
	}

	@Override
	protected double getPrecioBase() {
		return precioBase;
	}

	@Override
	public int getPeso(){
		return peso;
	}
}
