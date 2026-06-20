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
	private String nombre;
	private String descripcion;
	private int peso;
	private String marca;
	private Categoria categoria;
	private double precioBase;
	private double descuento;
	private HashMap<String, Atributo<?>> atributosDinamicos;

	public Producto(String sku, String nombre,String descripcion, int peso, String marca, Categoria categoria, double precioBase, double descuento) {
		super();
		this.sku = sku;
		this.nombre = nombre;
		this.descripcion= descripcion;
		this.peso = peso;
		this.marca = marca;
		this.categoria = categoria;
		this.precioBase = precioBase;
		this.descuento = descuento;
		this.atributosDinamicos = new HashMap<>();
	}

	@Override
	public String getNombre() {
		return this.nombre;
	}

	@Override
	public String getDescripcion() {
		return this.descripcion;
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

	public Object getAtributoDinamico(String nombre) {
        return this.atributosDinamicos.get(nombre);
    }

	public int getPeso() {
		return this.peso;
	}

	public boolean atributosSonValidos() {
		return atributosFijosSonValidos() && atributosDinamicosSonValidos();
	}

	public boolean atributosFijosSonValidos() {
		return  sku != null && nombre != null;
	}

	public boolean atributosDinamicosSonValidos() {
		return atributosDinamicos.values().stream().allMatch(valorAtributo -> valorAtributo != null);
	}

	@Override
	public double getPrecioBaseCalculado() {
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
	public Map<String, Integer> getResumenDeSku() {
		Map<String, Integer> resumen = new HashMap<>();
		resumen.put(sku, 1);
		return resumen;
	}

	@Override
	public Categoria getCategoria() {
		return categoria;
	}

	@Override
	public List<RegistroDeItem> getRegistroDeItem(double multiplicadorDescuento) {
		double precio = this.getPrecioBaseCalculado() * multiplicadorDescuento;
		return List.of(new RegistroDeItem(this, precio));
	}
}
