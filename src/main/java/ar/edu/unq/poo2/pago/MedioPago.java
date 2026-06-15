package ar.edu.unq.poo2.pago;

public abstract class MedioPago {

    private static int ultimoCodigoTransaccion = 0;

    private int codigoTransaccion;

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
        ultimoCodigoTransaccion++;
        codigoTransaccion = ultimoCodigoTransaccion;
    }

    public int getCodigoTransaccion() {
        return codigoTransaccion;
    }
}