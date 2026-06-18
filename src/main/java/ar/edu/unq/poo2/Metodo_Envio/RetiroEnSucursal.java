package ar.edu.unq.poo2.Metodo_Envio;

import ar.edu.unq.poo2.pedido.Pedido;

public class RetiroEnSucursal implements MetodoDeEnvio {
	private Sucursal sucursal;
	public RetiroEnSucursal(Sucursal sucursal) {
		// TODO Auto-generated constructor stub
		this.sucursal = sucursal;
	}

	@Override
	public int calcularDiasDeEntrega(Pedido pedido) {
		// TODO Auto-generated method stub
		return sucursal.hayStock(pedido) ? 0 : 3;
	}

	@Override
	public double calcularCosto(Pedido pedido) {
		// TODO Auto-generated method stub
		return 0;
	}

}
