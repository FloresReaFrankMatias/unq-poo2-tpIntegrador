package ar.edu.unq.poo2.Metodo_Envio;

import ar.edu.unq.poo2.pedido.Pedido;

public class EnvioEstandar implements MetodoDeEnvio {
	private CorreoArgentino correo;

	public EnvioEstandar( CorreoArgentino correo) {
		// TODO Auto-generated constructor stub
		this.correo = correo;
	}

	@Override
	public double calcularCosto(Pedido pedido) {
		// TODO Auto-generated method stub
		
		double pesoTotal = pedido.getPesoTotal(); 
        Direccion destino = pedido.getDireccionEntrega();
        
        return correo.estimarEnvio(pesoTotal, destino);
		
		
		return 0;
	}

	@Override
	public int calcularDiasDeEntrega(Pedido pedido ) {
		// TODO Auto-generated method stub
		return 7;
	}

}
