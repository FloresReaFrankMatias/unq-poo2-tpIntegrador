package ar.edu.unq.poo2.item;

public interface Item {
	public String getNombre();
	public String getDescripcion();
	public int getPeso();
	public double getPrecioBaseCalculado();
	public void add(Item item);
	public void remove(Item item);
}
