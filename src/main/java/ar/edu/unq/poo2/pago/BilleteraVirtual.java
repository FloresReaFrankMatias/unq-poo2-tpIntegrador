package ar.edu.unq.poo2.pago;

public class BilleteraVirtual  extends MedioPago{
	private float saldoDisponible;
	
	@Override
	protected void validarDatos() {
		// Validar saldo suficiente
	}
	
	@Override
	protected void reservarFondos() {
		// Bloquear saldo hasta confirmar la operacion
		
	}
	
	@Override 
	protected void ejecutarTransaccion() {
		// Acreditar fondos al vendedor
	}
	
	@Override
	protected void notificarResultado() {
		// Enviar notificacion push
		// Aplicar cashback si corresponde
	}

}
