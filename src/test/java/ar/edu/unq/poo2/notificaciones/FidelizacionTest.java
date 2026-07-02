package ar.edu.unq.poo2.notificaciones;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ar.edu.unq.poo2.pedido.Pedido;

class FidelizacionTest {
	Pedido pedidoMock;
	Fidelizacion fidelizacion;
	MailSender mailSenderMock;
	Cupon cupon;
	
	@BeforeEach
	void setUp(){
		pedidoMock = mock(Pedido.class);
		mailSenderMock = mock(MailSender.class);
		
		cupon = new Cupon( "cliente@mail.com",0.5);
		fidelizacion = new Fidelizacion(mailSenderMock,cupon);

	    when(pedidoMock.getClienteEmail()).thenReturn("cliente@mail.com");
	   }

	@Test
	void alCancelar_EnviaMailConCupon() {
	    fidelizacion.alCancelar(pedidoMock);

	    verify(mailSenderMock).enviarMail("cliente@mail.com","Cancelacion de Compra",
		        "Debido a la cancelacion te enviamos un cupo de descuento para tu proxima compra", cupon);
	}

	   
	@Test
	void alConfirmar_NoEnviaMail() {
		fidelizacion.alConfirmar(pedidoMock);

	    verifyNoInteractions(mailSenderMock);
    }

	@Test
	void alEntregar_NoEnviaMail() {
		fidelizacion.alEntregar(pedidoMock);

	    verifyNoInteractions(mailSenderMock);
	}
}
