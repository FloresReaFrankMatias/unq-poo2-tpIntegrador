package ar.edu.unq.poo2.pago;

public class TarjetaCredito  extends MedioPago {
	
	private String numeroTarjeta;
	private String cvv;
	private String fechaVencimiento;
	
	@Override
	protected void validarDatos() {
		 if(numeroTarjeta == null || cvv == null ||   fechaVencimiento == null) {
			        throw new PagoInvalidoException("Datos de tarjeta incompletos");
		 }
	}
	
	@Override
	protected void reservarFondos() {
		 // Simulación de preautorización
	}
	
	@Override
	protected void ejecutarTransaccion() {
		// Realizar debito diferido
	}
	
	@Override
	public void reembolsar(double monto) {
		   // Reintegrar importe a la tarjeta
	}
	

}
