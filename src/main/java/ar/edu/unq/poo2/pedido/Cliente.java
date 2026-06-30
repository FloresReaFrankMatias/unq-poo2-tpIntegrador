package ar.edu.unq.poo2.pedido;

import ar.edu.unq.poo2.envio.Direccion;

public class Cliente {
	private String mail;
	private Direccion direccion;

	public Cliente(String mail, Direccion direccion) {
		this.mail = mail;
		this.direccion = direccion;
	}
	
	public Direccion getDireccion() {
		return direccion;
	}

	public String getEmail() {
		return mail;
	}
}
