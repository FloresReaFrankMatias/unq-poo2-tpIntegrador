package ar.edu.unq.poo2.pago.comprobante;

public class ComprobanteTransferencia {

	private int numeroOperacion;

	public ComprobanteTransferencia(int numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}

	public int getNumeroOperacion() {
		return numeroOperacion;
	}

	public String imprimir() {
		return "Comprobante de transferencia - Operación N° "
				+ numeroOperacion;
	}
}