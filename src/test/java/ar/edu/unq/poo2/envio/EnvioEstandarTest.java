package ar.edu.unq.poo2.envio;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import ar.edu.unq.poo2.pedido.Pedido;
class EnvioEstandarTest {
   EnvioEstandar envioEstandar;
   Pedido pedidoMock;
   CorreoArgentino correoMock;
   Direccion direccionMock;
	
	
	@BeforeEach
	void setUp(){
		pedidoMock = mock(Pedido.class);
		direccionMock =mock(Direccion.class);
		correoMock = mock(CorreoArgentino.class);

		envioEstandar = new EnvioEstandar(correoMock);
	}

	@Test
	void testEnvioEstandar_CalcularEnvio_Funcionacorrectamente() {
		double costoEsperado= 150.0;
		when(correoMock.estimarEnvio((float) 100, direccionMock)).thenReturn((float) costoEsperado);
		when(pedidoMock.getPesoTotal()).thenReturn((double) 100);
		when(pedidoMock.getDireccionEntrega()).thenReturn(direccionMock);

		double costoCalculado = envioEstandar.calcularCosto(pedidoMock);

		assertEquals(costoEsperado, costoCalculado);
		verify(correoMock, times(1)).estimarEnvio((float) 100, direccionMock);
	}
	
	@Test
	void testEnvioEstandar_calcularCosto_conDireccionNula_lanzaError() {
	    double pesoPedido = 100.0;
	    float pesoEsperadoPorCorreo = 100.0f;
	    when(pedidoMock.getPesoTotal()).thenReturn(pesoPedido);
	    when(pedidoMock.getDireccionEntrega()).thenReturn(null);

	    String msj= "La dirección de destino no puede ser nula";
	    when(correoMock.estimarEnvio(pesoEsperadoPorCorreo, null)).thenThrow(new IllegalArgumentException(msj));
	    IllegalArgumentException excepcionLanzada = assertThrows(IllegalArgumentException.class, () -> {envioEstandar.calcularCosto(pedidoMock);},
				"Se esperaba un IllegalArgumentException al calcular el envío con una dirección nula");

	    assertEquals(msj, excepcionLanzada.getMessage());
	    verify(correoMock, times(1)).estimarEnvio(pesoEsperadoPorCorreo, null);
	}

	@Test
    void test_CalcularDiasDeEntrega() {
        int dias = envioEstandar.calcularDiasDeEntrega(pedidoMock);
       
        assertTrue(dias >= 5 && dias <= 7);
    }
}
