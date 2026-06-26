package ar.edu.unq.poo2.notificaciones;

import ar.edu.unq.poo2.pedido.Pedido;
import ar.edu.unq.poo2.pedido.estado.EstadoPedido;

public class Fidelizacion implements ObservadorPedido {
	private MailSender mail;
	
	public Fidelizacion(MailSender mail) {
		// TODO Auto-generated constructor stub
		this.mail=mail;
	}

	@Override
	public void alCancelar(Pedido pedido,EstadoPedido estAnterior,EstadoPedido estActual) {
		// TODO Auto-generated method stub
		Cupon cupon = new Cupon(pedido.getClienteEmail(), 0.05);
		mail.enviarMail(pedido.getClienteEmail(), 
				        "Cancelacion de Compra", 
				        "Debido a la cancelacion te enviamos un cupo de descuento para tu proxima compra",
				        cupon);

	}

}
