package ar.edu.unq.poo2.Metodo_Envio;

import ar.edu.unq.poo2.pedido.Pedido;

public class EnvioEstandar implements MetodoDeEnvio {
	private CorreoArgentino correo;

	public EnvioEstandar( CorreoArgentino correo) {
		this.correo = correo;
	}

	@Override
	public double calcularCosto(Pedido pedido) {
		double pesoTotal = pedido.getPesoTotal(); 
        Direccion destino = pedido.getDireccionEntrega();
        
        return correo.estimarEnvio(pesoTotal, destino);
	}

	@Override
	public int calcularDiasDeEntrega(Pedido pedido ) {
		return 7;
	}
}
