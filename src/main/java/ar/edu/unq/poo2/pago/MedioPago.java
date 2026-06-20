package ar.edu.unq.poo2.pago;

public abstract class MedioPago {
    private static int ultimoCodigoTransaccion = 0;
    private int codigoTransaccion;

    public void procesarPago() {
        validarDatos();
        reservarFondos();
        ejecutarTransaccion();
        notificarResultado();
    }

    protected abstract void validarDatos();
    protected abstract void reservarFondos();
    protected abstract void ejecutarTransaccion();

    protected void notificarResultado() {
        ultimoCodigoTransaccion++;
        codigoTransaccion = ultimoCodigoTransaccion;
    }

    public int getCodigoTransaccion() {
        return codigoTransaccion;
    }
}