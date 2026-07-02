package ar.edu.unq.poo2.pago;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.pago.api.APITarjetaCredito;

class TarjetaCreditoTest {
	private TarjetaCredito tarjeta;
	private APITarjetaCredito apiTarjetaMock;

	@BeforeEach
	void setUp() {
		apiTarjetaMock = mock(APITarjetaCredito.class);

		tarjeta = new TarjetaCredito(
				"123456789",
				"123",
				"12/30",
				apiTarjetaMock);
	}

	@Test
	void unaTarjetaSeCreaConTodosLosDatosEsperados() {
		assertEquals("123456789", tarjeta.getNumeroTarjeta());
		assertEquals("123", tarjeta.getCvv());
		assertEquals("12/30", tarjeta.getFechaVencimiento());
		assertEquals(apiTarjetaMock, tarjeta.getApiTarjeta());
	}

	@Test
	void unaTarjetaValidaConsultaALaApi() {
		when(apiTarjetaMock.validarTarjeta(anyString(), anyString(), anyString()))
				.thenReturn(true);

		tarjeta.procesarPago();

		verify(apiTarjetaMock).validarTarjeta("123456789","123","12/30");
	}

	@Test
	void unaTarjetaInvalidaLanzaExcepcion() {
		when(apiTarjetaMock.validarTarjeta(anyString(), anyString(), anyString()))
				.thenReturn(false);

		assertThrows(PagoInvalidoException.class,() -> tarjeta.procesarPago());
	}
	
	@Test
	void unaTarjetaValidaGeneraUnCuponDePago() {
	    when(apiTarjetaMock.validarTarjeta(anyString(), anyString(), anyString()))
				.thenReturn(true);

	    tarjeta.procesarPago();

	    assertNotNull( tarjeta.getCuponPago());
	}
}