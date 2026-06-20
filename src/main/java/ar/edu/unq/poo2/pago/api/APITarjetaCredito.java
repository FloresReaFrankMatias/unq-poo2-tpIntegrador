package ar.edu.unq.poo2.pago.api;

public interface APITarjetaCredito {
	public abstract boolean validarTarjeta(String numeroTarjeta, String cvv, String fechaVencimiento);
	public abstract void preAutorizarFondos();
	public abstract void ejecutarTransaccion();
}
