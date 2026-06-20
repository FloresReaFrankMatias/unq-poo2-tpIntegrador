package ar.edu.unq.poo2.envio;

import ar.edu.unq.poo2.pedido.Pedido;

public class EnvioExpress implements MetodoDeEnvio {
	private  LibEnvioExpress envioExpress;

    public EnvioExpress(LibEnvioExpress envioExpress) {
    	this.envioExpress = envioExpress;
        
    }

	@Override
	public int calcularDiasDeEntrega(Pedido pedido) {
		return 1;
	}

	@Override
	public double calcularCosto(Pedido pedido) {
		return envioExpress.calcularCosto(
                pedido.valorTotalPedido());
	}
}
