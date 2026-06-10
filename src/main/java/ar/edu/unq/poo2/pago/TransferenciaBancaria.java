package ar.edu.unq.poo2.pago;

public class TransferenciaBancaria  extends MedioPago {
	
	private String cbu;
	private String alias;
	
	@Override
	protected void validarDatos(){
		// Validar CBU/CVU
		// Validar alias
	}
	
	@Override
	protected void reservarFondos() {
		// No aplica 
	}
	
	@Override 
	protected void ejecutarTransaccion() {
		// Ejecutar transferencia
		
	}

}
