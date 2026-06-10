package ar.edu.unq.poo2.pago;

public class BilleteraVirtual  extends MedioPago{
	private Double saldoDisponible;
	
	@Override
	protected void validarDatos() {
		if(saldoDisponible == null || saldoDisponible < 0) {
	            throw new PagoInvalidoException("Saldo Insuficiente");
	      }
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
		// Simulación de push y cashback
	}
	
	@Override
	public void reembolsar(double monto) {
		// Acreditar saldo al usuario
	}

}
