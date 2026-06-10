package ar.edu.unq.poo2.pago;

public class TarjetaCredito  extends MedioPago {
	
	private String numeroTarjeta;
	private String cvv;
	private String fechaVencimiento;
	
	@Override
	protected void validarDatos() {
		// Validar numero de tarjeta
		// Validar CVV
		// Validar fecha de vencimiento
	}
	
	@Override
	protected void reservarFondos() {
		// Solicitar preautorizacion al banco 
	}
	
	@Override
	protected void ejecutarTransaccion() {
		// Realizar debito diferido
	}
	

}
