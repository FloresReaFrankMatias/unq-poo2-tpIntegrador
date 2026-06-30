package ar.edu.unq.poo2.notificaciones;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import ar.edu.unq.poo2.pedido.Pedido;

class NotificadorEmailTest {
	Pedido pedidoMock;
	MailSender mailSender;
	
	NotificadorEmail obsNotificador;
	
	@BeforeEach
	void setUp(){
		pedidoMock = mock(Pedido.class);
		mailSender = mock(MailSender.class);
		
		
		obsNotificador = new NotificadorEmail(mailSender);
		
		when(pedidoMock.getClienteEmail()).thenReturn("cliente@mail.com");
		
	}

	@Test
    void alConfirmar_EnviaMailAlCliente() {
		
		obsNotificador.alConfirmar(pedidoMock);
		
        verify(mailSender).enviarMail("cliente@mail.com", "Pedido confirmado", "Tu pedido fue confirmado", null);
    }

    @Test
    void alEnviar_EnviaMailAlCliente() {
    	
    	obsNotificador.alEnviar(pedidoMock);

        verify(mailSender).enviarMail("cliente@mail.com", "Pedido enviado", "Tu pedido está en camino", null);
    }

    @Test
    void alEntregar_EnviaMailAlCliente() {
    	
    	obsNotificador.alEntregar(pedidoMock);

        verify(mailSender).enviarMail("cliente@mail.com", "Pedido entregado", "Tu pedido fue entregado", null);
    }

    @Test
    void alCancelar_NoEnviaMail() {
    
    	obsNotificador.alCancelar(pedidoMock);

        verifyNoInteractions(mailSender);
    }
}


