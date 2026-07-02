package ar.edu.unq.poo2.pago;

import ar.edu.unq.poo2.pago.api.APITransferenciaBancaria;
import ar.edu.unq.poo2.pago.comprobante.ComprobanteTransferencia;

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
	protected void reservarFondos() {}

	@Override
	protected boolean cumpleValidacion() {
		return apiTransferencia.validarCuenta(cbu, alias);
	}

	@Override
	protected String getMensajeErrorValidacion() {
		return "La cuenta bancaria no es válida";
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