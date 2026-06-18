package ar.edu.unq.poo2.pago.api;

public interface APIBilleteraVirtual {
	
	public abstract boolean validarSaldo();

	public abstract void bloquearSaldo();

	public abstract void acreditarFondos();

	public abstract void enviarPush(String mensaje);

}
