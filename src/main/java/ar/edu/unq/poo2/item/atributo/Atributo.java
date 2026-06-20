package ar.edu.unq.poo2.item.atributo;

public abstract class Atributo<T> {
    private T valor;

    public Atributo(T valor) {
        this.valor = valor;
    }

    public T getValor() {
        return valor;
    }
}
