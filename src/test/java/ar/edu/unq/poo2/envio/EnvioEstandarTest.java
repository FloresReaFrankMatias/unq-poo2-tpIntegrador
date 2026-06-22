package ar.edu.unq.poo2.envio;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import ar.edu.unq.poo2.pedido.Pedido;
class EnvioEstandarTest {
   EnvioEstandar envioEstandar;
   Pedido pedido;
   CorreoArgentino correoMock;
   Direccion direccion;
	
	
	@BeforeEach
	void setUp() throws Exception {
		
		correoMock = mock(CorreoArgentino.class);
		envioEstandar = new EnvioEstandar(correoMock);
		
		pedido = mock(Pedido.class);
		direccion =mock(Direccion.class);
		pedido = mock(Pedido.class);
		
		}
	@Test
	void testEnvioEstandar_CalcularEnvio_Funcionacorrectamente() {
		
		when(pedido.getPesoTotal()).thenReturn((double) 100);
       when(pedido.getDireccionEntrega()).thenReturn(direccion);
      
       double costoEsperado= 150.0;
		when(correoMock.estimarEnvio((float) 100, direccion)).thenReturn(costoEsperado);
		verify(correoMock, times(0)).estimarEnvio((float) 100, direccion);
		assertEquals(costoEsperado, envioEstandar.calcularCosto(pedido));
	}
	
	@Test
	void testEnvioEstandar_calcularCosto_conDireccionNula_lanzaError() {
	    double pesoPedido = 100.0;
	    float pesoEsperadoPorCorreo = 100.0f;
	   
	    when(pedido.getPesoTotal()).thenReturn(pesoPedido);
	    when(pedido.getDireccionEntrega()).thenReturn(null);
	   
	    String msj= "La dirección de destino no puede ser nula";
	    when(correoMock.estimarEnvio(pesoEsperadoPorCorreo, null))
	        .thenThrow(new IllegalArgumentException(msj));
	    IllegalArgumentException excepcionLanzada = assertThrows(IllegalArgumentException.class, () -> {
	        envioEstandar.calcularCosto(pedido);
	    }, "Se esperaba un IllegalArgumentException al calcular el envío con una dirección nula");
	    assertEquals(msj, excepcionLanzada.getMessage());
	   
	    // 3. Verify
	    verify(correoMock, times(1)).estimarEnvio(pesoEsperadoPorCorreo, null);
	}
	
	
}
