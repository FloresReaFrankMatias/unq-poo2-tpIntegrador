package ar.edu.unq.poo2.pago;

public abstract class MedioPago {
   
	private String codigoTransaccion;

    // Template Method
    public void procesarPago() {
        validarDatos();
        reservarFondos();
        ejecutarTransaccion();
        notificarResultado();
    }

    // Primitive Operations
    protected abstract void validarDatos();
    protected abstract void reservarFondos();
    protected abstract void ejecutarTransaccion();

    // Hook Method
    protected void notificarResultado() {
        // Registrar código de transacción
    }

    public abstract void reembolsar(double monto);

    public String getCodigoTransaccion() {
        return codigoTransaccion;
    }

    protected void setCodigoTransaccion(String codigoTransaccion) {
        this.codigoTransaccion = codigoTransaccion;
    }


}
