package ar.edu.unq.poo2.notificaciones;

import ar.edu.unq.poo2.pedido.Pedido;
import ar.edu.unq.poo2.pedido.estado.EstadoPedido;

public class GeneradorDeFactura implements ObservadorPedido {
	private ComprobanteFiscal comprobante ;

	public GeneradorDeFactura(ComprobanteFiscal comprobante ) {
		this.comprobante =comprobante ;
	}
	@Override
	public void alEntregar(Pedido pedido) {
		comprobante.generarComprobante(pedido); 
	}
	
	  

}
