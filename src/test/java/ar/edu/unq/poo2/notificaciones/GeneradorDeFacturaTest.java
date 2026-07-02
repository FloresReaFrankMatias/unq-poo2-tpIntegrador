package ar.edu.unq.poo2.notificaciones;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ar.edu.unq.poo2.pedido.Pedido;
import static org.mockito.Mockito.*;


class GeneradorDeFacturaTest {
	Pedido pedidoMock;
	GeneradorDeFactura generadorFactura;
	MailSender mailSenderMock;
	ComprobanteFiscal comprobanteMock;

	@BeforeEach
	void setUp(){
		pedidoMock = mock(Pedido.class);
		mailSenderMock = mock(MailSender.class);
		comprobanteMock = mock(ComprobanteFiscal.class);
		
		generadorFactura = new GeneradorDeFactura(comprobanteMock);
	}
	
    @Test
	void alEntregar_GeneraFactura() {
		generadorFactura.alEntregar(pedidoMock);

	    verify(comprobanteMock, times(1)).generarComprobante(pedidoMock);
	}

	@Test
	void alConfirmar_NoGeneraFactura() {
	    generadorFactura.alConfirmar(pedidoMock);

	    verifyNoInteractions(comprobanteMock);
	}

    @Test
	void alCancelar_NoGeneraFactura() {
	    generadorFactura.alCancelar(pedidoMock);

	    verifyNoInteractions(comprobanteMock);
	}
}


