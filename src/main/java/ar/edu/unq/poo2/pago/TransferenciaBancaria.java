package ar.edu.unq.poo2.pago;

public class TransferenciaBancaria  extends MedioPago {
	
	private String cbu;
	private String alias;
	
	@Override
	protected void validarDatos(){
		 if(cbu == null && alias == null) {

		        throw new PagoInvalidoException("Debe informar CBU o Alias");
		 }
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
