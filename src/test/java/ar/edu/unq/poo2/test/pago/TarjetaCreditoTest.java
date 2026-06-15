package ar.edu.unq.poo2.test.pago;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

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
	void unaTarjetaSinNumeroEsInvalida() {

	    TarjetaCredito tarjetaInvalida =
	            new TarjetaCredito(
	                    null,
	                    "123",
	                    "12/30",
	                    apiTarjeta);

	    assertThrows(PagoInvalidoException.class,() -> tarjetaInvalida.procesarPago());
	}
	
	@Test
	void unaTarjetaSinCvvEsInvalida() {

	    TarjetaCredito tarjetaInvalida =
	            new TarjetaCredito(
	                    "123456789",
	                    null,
	                    "12/30",
	                    apiTarjeta);

	    assertThrows(PagoInvalidoException.class,() -> tarjetaInvalida.procesarPago());
	}
	
	@Test
	void unaTarjetaSinFechaEsInvalida() {

	    TarjetaCredito tarjetaInvalida =
	            new TarjetaCredito(
	                    "123456789",
	                    "123",
	                    null,
	                    apiTarjeta);

	    assertThrows(PagoInvalidoException.class,() -> tarjetaInvalida.procesarPago());
	}
}