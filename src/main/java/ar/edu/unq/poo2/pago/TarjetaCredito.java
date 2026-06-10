package ar.edu.unq.poo2.pago;

import ar.edu.unq.poo2.pago.api.APITarjetaCredito;

public class TarjetaCredito  extends MedioPago {
	
	private String numeroTarjeta;
	private String cvv;
	private String fechaVencimiento;
	private APITarjetaCredito apiTarjeta;
	
	@Override
	protected void validarDatos() {
		 if(numeroTarjeta == null || cvv == null ||   fechaVencimiento == null) {
			        throw new PagoInvalidoException("Datos de tarjeta incompletos");
		 }
	}
	
	@Override
	protected void reservarFondos() {
	    // Delegar preautorización al servicio APITarjetaCredito
	}
	
	@Override
	protected void ejecutarTransaccion() {
	    // Delegar ejecución de la transacción al servicio APITarjetaCredito
	}
	
	@Override
	public void reembolsar(double monto) {
	    // Solicitar reintegro mediante el servicio APITarjetaCredito
	}
	
	@Override
	protected void notificarResultado() {
	    super.notificarResultado();

	    // Generar y registrar cupón de pago imprimible
	}
}
