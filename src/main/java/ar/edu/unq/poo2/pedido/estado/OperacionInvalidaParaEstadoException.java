package ar.edu.unq.poo2.pedido.estado;

public class OperacionInvalidaParaEstadoException extends RuntimeException{
    public OperacionInvalidaParaEstadoException() {
        super("No se puede realizar la operación deseada en el estado actual.");
    }
}
