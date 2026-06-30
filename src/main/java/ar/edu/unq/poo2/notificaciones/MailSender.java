package ar.edu.unq.poo2.notificaciones;


public interface MailSender {
	public void enviarMail(String direcciónDestino,String  título, String mensaje, Object adjunto);
}
