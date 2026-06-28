package ar.edu.unq.poo2.notificaciones;

import ar.edu.unq.poo2.pedido.Pedido;
import ar.edu.unq.poo2.pedido.estado.EstadoPedido;

public class Fidelizacion implements ObservadorPedido {
	private MailSender mail;
	private Cupon cupon;
	
	public Fidelizacion(MailSender mail,Cupon cupon) {
		// TODO Auto-generated constructor stub
		this.mail=mail;
		this.cupon=cupon;
	}

	@Override
	public void alCancelar(Pedido pedido) {
		// TODO Auto-generated method stub
		
		mail.enviarMail(pedido.getClienteEmail(), 
				        "Cancelacion de Compra", 
				        "Debido a la cancelacion te enviamos un cupo de descuento para tu proxima compra",
				        cupon);

	}

}
