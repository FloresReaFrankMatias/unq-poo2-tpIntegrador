package ar.edu.unq.poo2.Metodo_Envio;

public interface MetodoDeEnvio {
	public double calcularCosto(Pedido pedido);
	public int calcularDiasDeEntrega(Pedido pedido );

}
