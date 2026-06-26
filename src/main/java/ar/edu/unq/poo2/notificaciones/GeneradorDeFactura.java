package ar.edu.unq.poo2.notificaciones;

import ar.edu.unq.poo2.pedido.Pedido;
import ar.edu.unq.poo2.pedido.estado.EstadoPedido;

public class GeneradorDeFactura implements ObservadorPedido {
	private ComprobanteFiscal comprobante ;

	public GeneradorDeFactura(ComprobanteFiscal comprobante ) {
		// TODO Auto-generated constructor stub
		this.comprobante =comprobante ;
	}
	@Override
	public void alEntregar(Pedido pedido,EstadoPedido estAnterior,EstadoPedido estActual) {
		comprobante.generarComprobante(pedido); 
	}
	
	  

}
