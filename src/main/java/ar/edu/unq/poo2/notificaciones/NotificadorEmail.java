package ar.edu.unq.poo2.notificaciones;

import ar.edu.unq.poo2.pedido.Pedido;

public class NotificadorEmail extends ObservadorPedido {
	private MailSender mail;

	public NotificadorEmail(MailSender mail) {
		this.mail=mail;
	}
	
	@Override
	public void alConfirmar(Pedido pedido) {
		enviarNotificacion(pedido, "Pedido confirmado", "Tu pedido fue confirmado");
	}

	@Override
	public void alEnviar(Pedido pedido) {
		enviarNotificacion(pedido, "Pedido enviado", "Tu pedido está en camino");
	}

	@Override
	public void alEntregar(Pedido pedido) {
		enviarNotificacion(pedido, "Pedido entregado", "Tu pedido fue entregado");
	}

	private void enviarNotificacion(Pedido pedido, String titulo, String mensaje) {
		mail.enviarMail(pedido.getClienteEmail(), titulo, mensaje, null);
	}
}
