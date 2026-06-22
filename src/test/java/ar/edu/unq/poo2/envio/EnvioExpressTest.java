package ar.edu.unq.poo2.envio;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ar.edu.unq.poo2.pedido.Pedido;
import static org.mockito.Mockito.*;
class EnvioExpressTest {
	EnvioExpress envioExpress;
   Pedido pedidoMock;
   LibEnvioExpress libEnvioExpressMock;
   @BeforeEach
   void setUp() throws Exception {
       // mocks
       pedidoMock = mock(Pedido.class);
       libEnvioExpressMock = mock(LibEnvioExpress.class);
      
       envioExpress = new EnvioExpress(libEnvioExpressMock);
   }
	@Test
   void testEnvioExpress_calcularCostoFuncionaCorrectamente() {
       double valorDelPedido = 5000.0;
       double costoDevueltoPorLibExpress = 750.0;
      
       when(pedidoMock.getValorTotal()).thenReturn(valorDelPedido);
       when(libEnvioExpressMock.calcularCosto(valorDelPedido)).thenReturn(costoDevueltoPorLibExpress);
       double costoCalculado = envioExpress.calcularCosto(pedidoMock);
       assertEquals(750.0, costoCalculado);
      
       // Verify (Verificación de interacciones)
       verify(pedidoMock, times(1)).getValorTotal();
       verify(libEnvioExpressMock, times(1)).calcularCosto(valorDelPedido);
   }


	@Test
   void testCalcularCosto_PedidoNulo_LanzaNullPointerException() {
      
       assertThrows(NullPointerException.class, () -> {
                                                      envioExpress.calcularCosto(null);});
      
     
       verifyNoInteractions(libEnvioExpressMock);
   }
	@Test
   void testCalcularCosto_PedidoValorCero_LanzaException() {
       //
       double valorCero = 0.0;
       when(pedidoMock.getValorTotal()).thenReturn(valorCero);
      
       when(libEnvioExpressMock.calcularCosto(valorCero))
           .thenThrow(new IllegalArgumentException("El valor del pedido debe ser mayor a cero "));
       IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () -> {
           envioExpress.calcularCosto(pedidoMock);
       });
       assertEquals("El valor del pedido debe ser mayor a cero ", excepcion.getMessage());
      
       verify(libEnvioExpressMock, times(1)).calcularCosto(valorCero);
   }
}
