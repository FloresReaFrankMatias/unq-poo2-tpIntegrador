package ar.edu.unq.poo2.envio;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ar.edu.unq.poo2.pedido.Pedido;
import static org.mockito.Mockito.*;
class RetiroEnSucursalTest {
	RetiroEnSucursal retiroEnSucursal;
	
   Pedido pedidoMock;
  
   Sucursal sucursalMock;
   @BeforeEach
   void setUp() throws Exception {
   	
       pedidoMock = mock(Pedido.class);
       sucursalMock = mock(Sucursal.class);
      
       retiroEnSucursal = new RetiroEnSucursal(sucursalMock);
   }
   @Test
   void testCalcularCosto_SiempreEsCero() {
      
       double costo = retiroEnSucursal.calcularCosto(pedidoMock);
      
       assertEquals(0.0, costo, "El retiro en sucursal siempre debe ser gratuito ($0)");
      
       verifyNoInteractions(sucursalMock);
   }
   @Test
   void testCalcularDiasDeEntrega_CuandoHayStockEnSucursal_DevuelveCero() {
      
       when(sucursalMock.hayStock(pedidoMock)).thenReturn(true);
      
       int diasDeEntrega = retiroEnSucursal.calcularDiasDeEntrega(pedidoMock);
       assertEquals(0, diasDeEntrega);
       verify(sucursalMock, times(1)).hayStock(pedidoMock);
   }
   @Test
   void testCalcularDiasDeEntrega_CuandoNoHayStockEnSucursal_DevuelveTres() {
      
       when(sucursalMock.hayStock(pedidoMock)).thenReturn(false);
      
       int diasDeEntrega = retiroEnSucursal.calcularDiasDeEntrega(pedidoMock);
       assertEquals(3, diasDeEntrega);
       verify(sucursalMock, times(1)).hayStock(pedidoMock);
   }
  
   @Test
   void test_PedidoNulo_LanzaNullPointerException() {
	    when(sucursalMock.hayStock(null)).thenThrow(new NullPointerException());
	       assertThrows(NullPointerException.class, () -> { retiroEnSucursal.calcularDiasDeEntrega(null);});
	      
	       verify(sucursalMock, times(1)).hayStock(null);
	   }
	}
