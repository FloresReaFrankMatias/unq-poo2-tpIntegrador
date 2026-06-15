package ar.edu.unq.poo2.pago;

public class CuponPago {

	private int codigoTransaccion;

	public CuponPago(int codigoTransaccion) {
		this.codigoTransaccion = codigoTransaccion;
	}

	public int getCodigoTransaccion() {
		return codigoTransaccion;
	}

	public String imprimir() {
		return "Cupón de pago - Operación N° "+ codigoTransaccion;
	}
}