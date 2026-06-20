package ar.edu.unq.poo2.Metodo_Envio;

import ar.edu.unq.poo2.pedido.Pedido;

public class RetiroEnSucursal implements MetodoDeEnvio {
	private Sucursal sucursal;

	public RetiroEnSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	@Override
	public int calcularDiasDeEntrega(Pedido pedido) {
		return sucursal.hayStock(pedido) ? 0 : 3;
	}

	@Override
	public double calcularCosto(Pedido pedido) {
		return 0;
	}
}
