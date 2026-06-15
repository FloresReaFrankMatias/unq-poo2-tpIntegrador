package ar.edu.unq.poo2.pago;

import ar.edu.unq.poo2.pago.api.APITransferenciaBancaria;

public class TransferenciaBancaria extends MedioPago {

	private String cbu;
	private String alias;
	private APITransferenciaBancaria apiTransferencia;
	private ComprobanteTransferencia comprobante;
	
	public TransferenciaBancaria(String cbu,
								 String alias,
								 APITransferenciaBancaria apiTransferencia) {

		this.cbu = cbu;
		this.alias = alias;
		this.apiTransferencia = apiTransferencia;
	}

	@Override
	protected void validarDatos() {

		boolean cuentaValida =
				apiTransferencia.validarCuenta(
						cbu,
						alias);

		if (!cuentaValida) {
			throw new PagoInvalidoException(
					"La cuenta bancaria no es válida");
		}
	}

	@Override
	protected void reservarFondos() {
		// No aplica para transferencias bancarias
	}

	@Override
	protected void ejecutarTransaccion() {
		apiTransferencia.ejecutarTransferencia();
	}

	@Override
	protected void notificarResultado() {
		super.notificarResultado();

		comprobante =new ComprobanteTransferencia(getCodigoTransaccion());
	}
	public String getCbu() {
		return cbu;
	}

	public String getAlias() {
		return alias;
	}

	public APITransferenciaBancaria getApiTransferencia() {
		return apiTransferencia;
	}
	
	public ComprobanteTransferencia getComprobante() {
		return comprobante;
	}
}