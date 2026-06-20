package ar.edu.unq.poo2.pago.api;

public interface APITransferenciaBancaria {
	public abstract boolean validarCuenta(String cbu, String alias);
	public abstract void ejecutarTransferencia();
}
