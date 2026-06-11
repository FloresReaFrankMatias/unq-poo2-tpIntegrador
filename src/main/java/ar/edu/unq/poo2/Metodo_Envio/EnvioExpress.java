package ar.edu.unq.poo2.Metodo_Envio;

public class EnvioExpress implements MetodoDeEnvio {
	private  LibEnvioExpress envioExpress;

    public EnvioExpress(LibEnvioExpress envioExpress) {
    	this.envioExpress = envioExpress;
        
    }
	@Override
	public int calcularDiasDeEntrega(Pedido pedido) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public double calcularCosto(Pedido pedido) {
		// TODO Auto-generated method stub
		return envioExpress.calcularCosto(
                pedido.totalProductos());
	}

}
