package ar.edu.unq.poo2.pago;

import ar.edu.unq.poo2.pago.api.APIBilleteraVirtual;

public class BilleteraVirtual extends MedioPago {
	private APIBilleteraVirtual apiBilletera;

	public BilleteraVirtual(APIBilleteraVirtual apiBilletera) {
		this.apiBilletera = apiBilletera;
	}

	@Override
	protected boolean cumpleValidacion() {
		return apiBilletera.validarSaldo();
	}

	@Override
	protected String getMensajeErrorValidacion() {
		return "Saldo insuficiente";
	}

	@Override
	protected void reservarFondos() {
		apiBilletera.bloquearSaldo();
	}

	@Override
	protected void ejecutarTransaccion() {
		apiBilletera.acreditarFondos();
	}

	@Override
	protected void notificarResultado() {
		super.notificarResultado();

		apiBilletera.enviarPush(
				"Pago realizado. Código: "
				+ getCodigoTransaccion());
	}

	public APIBilleteraVirtual getApiBilletera() {
		return apiBilletera;
	}
}