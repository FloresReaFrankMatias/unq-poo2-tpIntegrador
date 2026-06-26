package ar.edu.unq.poo2.notificaciones;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.pedido.Pedido;
import ar.edu.unq.poo2.pedido.estado.EstadoPedido;

import static org.mockito.Mockito.*;


class GeneradorDeFacturaTest {
	Pedido pedido;
	GeneradorDeFactura generadorFactura;
	MailSender mailSender;
	EstadoPedido anterior;
	EstadoPedido actual;
	ComprobanteFiscal comprobante;
	
	

	@BeforeEach
	void setUp() throws Exception {
		
		pedido = mock(Pedido.class);
		mailSender = mock(MailSender.class);
		anterior = mock(EstadoPedido.class);
		actual = mock(EstadoPedido.class);
		comprobante = mock(ComprobanteFiscal.class);
		
		generadorFactura = new GeneradorDeFactura(comprobante);
	}
	
    @Test
	void alEntregar_GeneraFactura() {
		generadorFactura.alEntregar(pedido, anterior, actual);

	    verify(comprobante, times(1)).generarComprobante(pedido);
	}
	    

	@Test
	void alConfirmar_NoGeneraFactura() {
	    generadorFactura.alConfirmar(pedido, anterior, actual);

	    verifyNoInteractions(pedido);
	}
    @Test
	void alCancelar_NoGeneraFactura() {
	    generadorFactura.alCancelar(pedido, anterior, actual);

	    verifyNoInteractions(pedido);
	}

    @Test 
    void alPreparar_NoGeneraFactura(){
    	generadorFactura.alPreparar(pedido, anterior, actual);
    	
    	verifyNoInteractions(pedido);
    	
    }	    

}


