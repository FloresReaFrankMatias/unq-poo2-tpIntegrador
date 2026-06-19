package ar.edu.unq.poo2.item;

import java.util.HashMap;
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
	private HashMap<String, Object> atributosDinamicos;


	public Producto(String sku, String nombre,String descripcion, int peso, String marca, Categoria categoria, double precioBase, double descuento) {
		super();
		this.sku = sku;
		this.nombre = nombre;
		this.descripcion= descripcion;
		this.peso = peso;
		this.marca = marca;
		this.setCategoria(categoria);
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

	public void setAtributoDinamico(String nombre, Object valor) {
        this.atributosDinamicos.put(nombre, valor);
    }

	public Object getAtributoDinamico(String nombre) {
        return this.atributosDinamicos.get(nombre);
    }

	public int getPeso() {
		return this.peso;
	}


	public boolean validacionDeAtributos() {
		return this.validacionAtributosFijos() &&
			   this.validacionAtributosDinamicos();
	}
	public boolean validacionAtributosFijos() {
		return this.sku != null && !this.sku.isEmpty() &&
				this.nombre != null && !this.nombre.isEmpty();
	}
	public boolean validacionAtributosDinamicos() {
		return this.atributosDinamicos.entrySet().stream()
		                              .allMatch(entry -> entry.getValue() != null &&
		                               !entry.getValue()
		                               .toString()
		                               .trim()
		                               .isEmpty());
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
