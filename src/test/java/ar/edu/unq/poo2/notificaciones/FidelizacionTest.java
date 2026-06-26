package ar.edu.unq.poo2.notificaciones;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*; 


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.pedido.Pedido;
import ar.edu.unq.poo2.pedido.estado.EstadoPedido;

class FidelizacionTest {
	Pedido pedido;
	Fidelizacion fidelizacion;
	MailSender mailSender;
	EstadoPedido anterior;
	EstadoPedido actual;
	Cupon cupon;
	

	@BeforeEach
	void setUp() throws Exception {
		
		pedido = mock(Pedido.class);
		mailSender = mock(MailSender.class);
		anterior = mock(EstadoPedido.class);
		actual = mock(EstadoPedido.class);
		
		fidelizacion = new Fidelizacion(mailSender);
		
		cupon = new Cupon( "cliente@mail.com",0.5);
		
	    when(pedido.getClienteEmail()).thenReturn("cliente@mail.com");
	    }

	    @Test
	    void alCancelarEnviaMailConCupon() {
	    	
	        fidelizacion.alCancelar(pedido, anterior, actual);

	        verify(mailSender).enviarMail("cliente@mail.com",any(),any(), cupon);
	    }

	   

	    @Test
	    void alConfirmarNoEnviaMail() {
	    	
	        fidelizacion.alConfirmar(pedido, anterior, actual);

	        verifyNoInteractions(mailSender);
	    }

	    @Test
	    void alEntregarNoEnviaMail() {
	        fidelizacion.alEntregar(pedido, anterior, actual);

	        verifyNoInteractions(mailSender);
	    }

}
