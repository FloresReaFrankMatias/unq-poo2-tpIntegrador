package ar.edu.unq.poo2.notificaciones;

import ar.edu.unq.poo2.pedido.Pedido;

public class GeneradorDeFactura extends ObservadorPedido {
	private ComprobanteFiscal comprobante ;

	public GeneradorDeFactura(ComprobanteFiscal comprobante ) {
		this.comprobante =comprobante ;
	}

	@Override
	public void alEntregar(Pedido pedido) {
		comprobante.generarComprobante(pedido); 
	}
}
