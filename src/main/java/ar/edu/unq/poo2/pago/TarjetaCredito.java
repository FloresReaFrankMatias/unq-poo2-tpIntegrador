package ar.edu.unq.poo2.pago;

import ar.edu.unq.poo2.pago.api.APITarjetaCredito;
import ar.edu.unq.poo2.pago.comporbante.CuponPago;

public class TarjetaCredito extends MedioPago {

	private String numeroTarjeta;
	private String cvv;
	private String fechaVencimiento;
	private APITarjetaCredito apiTarjeta;
	private CuponPago cuponPago;

	public TarjetaCredito(String numeroTarjeta,
						  String cvv,
						  String fechaVencimiento,
						  APITarjetaCredito apiTarjeta) {

		this.numeroTarjeta = numeroTarjeta;
		this.cvv = cvv;
		this.fechaVencimiento = fechaVencimiento;
		this.apiTarjeta = apiTarjeta;
	}

	@Override
	protected void validarDatos() {

		boolean esValida = apiTarjeta.validarTarjeta(
				numeroTarjeta,
				cvv,
				fechaVencimiento);

		if (!esValida) {
			throw new PagoInvalidoException(
					"La tarjeta de crédito no es válida");
		}
	}

	@Override
	protected void reservarFondos() {
		apiTarjeta.preAutorizarFondos();
	}

	@Override
	protected void ejecutarTransaccion() {
		apiTarjeta.ejecutarTransaccion();
	}

	@Override
	protected void notificarResultado() {
		super.notificarResultado();

		cuponPago = new CuponPago(getCodigoTransaccion());
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public String getCvv() {
		return cvv;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public APITarjetaCredito getApiTarjeta() {
		return apiTarjeta;
	}
	
	public CuponPago getCuponPago() {
		return cuponPago;
	}
}