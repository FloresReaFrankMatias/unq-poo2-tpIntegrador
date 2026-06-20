package ar.edu.unq.poo2.item.atributo;

public class AtributoNumero extends Atributo<Double> {
    public AtributoNumero(Number valor) {
        super(valor.doubleValue());
    }
}