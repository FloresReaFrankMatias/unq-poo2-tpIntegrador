package ar.edu.unq.poo2.notificaciones;

import ar.edu.unq.poo2.envio.Direccion;

public interface MailSender {
	public void enviarMail(Direccion direcciónDestino,String  título, String mensaje, Object adjunto);

}
