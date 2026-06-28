package ar.edu.unq.poo2.envio;

import java.util.concurrent.ThreadLocalRandom;

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
        
        return (double) correo.estimarEnvio(pesoTotal, destino);
	}

	@Override
	public int calcularDiasDeEntrega(Pedido pedido ) {
		return ThreadLocalRandom.current().nextInt(5, 8);
	}
}
