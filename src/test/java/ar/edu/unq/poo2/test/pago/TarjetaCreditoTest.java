package ar.edu.unq.poo2.test.pago;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.pago.PagoInvalidoException;
import ar.edu.unq.poo2.pago.TarjetaCredito;
import ar.edu.unq.poo2.pago.api.APITarjetaCredito;

class TarjetaCreditoTest {

	private TarjetaCredito tarjeta;
	private APITarjetaCredito apiTarjeta;

	@BeforeEach
	void setUp() {
		apiTarjeta = mock(APITarjetaCredito.class);

		tarjeta = new TarjetaCredito(
				"123456789",
				"123",
				"12/30",
				apiTarjeta);
	}

	@Test
	void unaTarjetaSeCreaConTodosLosDatosEsperados() {
		assertEquals("123456789", tarjeta.getNumeroTarjeta());
		assertEquals("123", tarjeta.getCvv());
		assertEquals("12/30", tarjeta.getFechaVencimiento());
		assertEquals(apiTarjeta, tarjeta.getApiTarjeta());
	}

	@Test
	void unaTarjetaValidaConsultaALaApi() {

		when(apiTarjeta.validarTarjeta(
				anyString(),
				anyString(),
				anyString()))
				.thenReturn(true);

		tarjeta.procesarPago();

		verify(apiTarjeta).validarTarjeta(
				"123456789",
				"123",
				"12/30");
	}

	@Test
	void unaTarjetaInvalidaLanzaExcepcion() {

		when(apiTarjeta.validarTarjeta(
				anyString(),
				anyString(),
				anyString()))
				.thenReturn(false);

		assertThrows(
				PagoInvalidoException.class,
				() -> tarjeta.procesarPago());
	}
}