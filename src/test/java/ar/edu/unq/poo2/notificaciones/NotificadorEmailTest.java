package ar.edu.unq.poo2.notificaciones;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*; 

import ar.edu.unq.poo2.pedido.Pedido;
import ar.edu.unq.poo2.pedido.estado.EstadoPedido;

class NotificadorEmailTest {
	Pedido pedidoMock;
	MailSender mailSender;
	EstadoPedido anterior;
	EstadoPedido actual;
	NotificadorEmail obsNotificador;
	
	@BeforeEach
	void setUp() throws Exception {
		pedidoMock = mock(Pedido.class);
		mailSender = mock(MailSender.class);
		anterior = mock(EstadoPedido.class);
		actual = mock(EstadoPedido.class);
		
		obsNotificador = new NotificadorEmail(mailSender);
		
		when(pedidoMock.getClienteEmail()).thenReturn("cliente@mail.com");
		
	}

	@Test
    void alConfirmar_EnviaMailAlCliente() {
		
		obsNotificador.alConfirmar(pedidoMock, anterior, actual);
		
        verify(mailSender).enviarMail("cliente@mail.com", "Pedido confirmado", "Tu pedido fue confirmado", null);
    }

    @Test
    void alEnviar_EnviaMailAlCliente() {
    	
    	obsNotificador.alEnviar(pedidoMock, anterior, actual);

        verify(mailSender).enviarMail("cliente@mail.com", "Pedido enviado", "Tu pedido está en camino", null);
    }

    @Test
    void alEntregar_EnviaMailAlCliente() {
    	
    	obsNotificador.alEntregar(pedidoMock, anterior, actual);

        verify(mailSender).enviarMail("cliente@mail.com", "Pedido entregado", "Tu pedido fue entregado", null);
    }

    @Test
    void alCancelar_NoEnviaMail() {
    
    	obsNotificador.alCancelar(pedidoMock, anterior, actual);

        verifyNoInteractions(mailSender);
    }

    @Test
    void alPreparar_NoEnviaMail() {
    	
    	obsNotificador.alPreparar(pedidoMock, anterior, actual);
        verifyNoInteractions(mailSender);
    }
}


