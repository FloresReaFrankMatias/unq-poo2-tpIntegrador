package ar.edu.unq.poo2.pedido;

import ar.edu.unq.poo2.Metodo_Envio.Direccion;
import ar.edu.unq.poo2.Metodo_Envio.MetodoDeEnvio;
import ar.edu.unq.poo2.pedido.notadecredito.GestorNotasDeCredito;

public class Cliente {
	private String mail;
	private Direccion direccion;
	public Cliente(String mail, Direccion direccion) {
		super();
		// TODO Auto-generated constructor stub
		this.mail = mail;
		this.direccion = direccion;
	}
	
	public Direccion getDireccion() {
		return direccion;
	}

}
