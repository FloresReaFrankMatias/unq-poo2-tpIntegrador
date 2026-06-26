package ar.edu.unq.poo2.notificaciones;

import ar.edu.unq.poo2.envio.Direccion;

public interface MailSender {
	public void enviarMail(String direcciónDestino,String  título, String mensaje, String adjunto);

}
