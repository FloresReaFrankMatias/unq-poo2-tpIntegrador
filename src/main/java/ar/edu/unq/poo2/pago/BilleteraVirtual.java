package ar.edu.unq.poo2.pago;

import ar.edu.unq.poo2.pago.api.APIBilleteraVirtual;

public class BilleteraVirtual  extends MedioPago{
	
	private Double saldoDisponible;
	private APIBilleteraVirtual apiBilletera;
	
	@Override
	protected void validarDatos() {
		if(saldoDisponible == null || saldoDisponible < 0) {
	            throw new PagoInvalidoException("Saldo Insuficiente");
	      }
	}
	
	@Override
	protected void reservarFondos() {
	    // Delegar bloqueo de saldo al servicio APIBilleteraVirtual
	}

	
	@Override
	protected void ejecutarTransaccion() {
	    // Delegar acreditación de fondos al servicio APIBilleteraVirtual
	}
	
	
	@Override
	public void reembolsar(double monto) {
	    // Solicitar acreditación del reintegro mediante APIBilleteraVirtual
	}

	
	@Override
	protected void notificarResultado() {
	    super.notificarResultado();

	    // Enviar notificación push mediante APIBilleteraVirtual
	}
	


}
