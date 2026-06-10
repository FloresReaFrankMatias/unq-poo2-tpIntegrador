package ar.edu.unq.poo2.pago;

import ar.edu.unq.poo2.pago.api.APITransferenciaBancaria;

public class TransferenciaBancaria  extends MedioPago {
	
	private String cbu;
	private String alias;
	private APITransferenciaBancaria apiTransferencia;
	
	@Override
	protected void validarDatos(){
		 if(cbu == null && alias == null) {

		        throw new PagoInvalidoException("Debe informar CBU o Alias");
		 }
	}
	
	@Override
	protected void reservarFondos() {
	    // No aplica para transferencias bancarias
	}
	
	@Override
	protected void ejecutarTransaccion() {
	    // Delegar ejecución de la transferencia al servicio APITransferenciaBancaria
	}
	
	@Override
	public void reembolsar(double monto) {
	    // Solicitar transferencia de devolución mediante APITransferenciaBancaria
	}

}
