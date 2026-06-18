package ar.edu.unq.poo2.Metodo_Envio;

import ar.edu.unq.poo2.pedido.Pedido;

public interface MetodoDeEnvio {
    public double calcularCosto(Pedido pedido);
    public int calcularDiasDeEntrega(Pedido pedido);
}
