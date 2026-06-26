package ar.edu.unq.poo2.notificaciones;

import java.time.LocalDate;

import ar.edu.unq.poo2.pedido.Pedido;
import ar.edu.unq.poo2.pedido.estado.EstadoPedido;

public class NotificadorEmail implements ObservadorPedido {
	private MailSender mail;
	public NotificadorEmail(MailSender mail) {
		// TODO Auto-generated constructor stub
		this.mail=mail;
	}
	
	 @Override
	    public void alEntregar(Pedido pedido,EstadoPedido estAnterior,EstadoPedido estActual){
	        mail.enviarMail(pedido.getClienteEmail(), " sdf", " dfs", null );
	    }
	 

}
