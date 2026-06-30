package ar.edu.unq.poo2.notificaciones;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ar.edu.unq.poo2.pedido.Pedido;

class FidelizacionTest {
	Pedido pedido;
	Fidelizacion fidelizacion;
	MailSender mailSender;
	Cupon cupon;
	
	@BeforeEach
	void setUp(){
		pedido = mock(Pedido.class);
		mailSender = mock(MailSender.class);
		
		cupon = new Cupon( "cliente@mail.com",0.5);
		fidelizacion = new Fidelizacion(mailSender,cupon);

	    when(pedido.getClienteEmail()).thenReturn("cliente@mail.com");
	   }

	@Test
	void alCancelar_EnviaMailConCupon() {
	    fidelizacion.alCancelar(pedido);

	    verify(mailSender).enviarMail("cliente@mail.com","Cancelacion de Compra", 
		        "Debido a la cancelacion te enviamos un cupo de descuento para tu proxima compra", cupon);
	}

	   
	@Test
	void alConfirmar_NoEnviaMail() {
		fidelizacion.alConfirmar(pedido);

	    verifyNoInteractions(mailSender);
    }

	@Test
	void alEntregar_NoEnviaMail() {
		fidelizacion.alEntregar(pedido);

	    verifyNoInteractions(mailSender);
	}
}
