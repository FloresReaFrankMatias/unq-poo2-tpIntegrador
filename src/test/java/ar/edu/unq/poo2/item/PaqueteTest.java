package ar.edu.unq.poo2.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.venta.RegistroDeItem;

import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Map;
class PaqueteTest {

	 Paquete paquete;
     Item itemMock1;
     Item itemMock2;

    @BeforeEach
    void setUp() {
        // Inicializamos el paquete con 10% de descuento. 
        // Usamos un valor de tu Enum Categoria (ej: ELECTRONICA, ajustalo al tuyo).
        paquete = new Paquete("Combo Gamer", 0.10, "Teclado + Mouse", Categoria.ELECTRONICA);

        // Mockeamos los hijos para aislar la prueba del Paquete
        itemMock1 = mock(Item.class);
        itemMock2 = mock(Item.class);
    }

    @Test
    void test_gettersPaquete() {
    	
    	assertEquals("Combo Gamer", paquete.getNombre());
		assertEquals("Teclado + Mouse", paquete.getDescripcion());
		assertEquals(Categoria.ELECTRONICA, paquete.getCategoria());
		assertEquals(0.10, paquete.getDescuento(), 0.001);
		assertTrue(paquete.getResumenDeSku().isEmpty());
    }
    
    
    @Test
    void test_GetPrecioBasePaquete() {
        when(itemMock1.getPrecio()).thenReturn(5000.0);
        when(itemMock2.getPrecio()).thenReturn(3000.0);

        paquete.add(itemMock1);
        paquete.add(itemMock2);

       
        assertEquals(8000.0, paquete.getPrecioBase(), 0.01);
    }
    @Test
    void test_GetPesoPaquete() {
		when(itemMock1.getPeso()).thenReturn(2000);
		when(itemMock2.getPeso()).thenReturn(1000);

		paquete.add(itemMock1);
		paquete.add(itemMock2);
		
		assertEquals(3000, paquete.getPeso());
	}
    @Test
    void test_Remove_EliminaUnItemExistente() {
        paquete.add(itemMock1);
        assertEquals(1, paquete.getItems().size());
    }

    @Test
    void test_Remove_LanzaExcepcionSiItemNoExiste() {
        paquete.add(itemMock1);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            paquete.remove(itemMock2);
        });

        assertEquals("El item no se encuentra en el paquete", exception.getMessage());

    }
    @Test
    void test_GetRegistroDeItem_Paquete() {
    	
        when(itemMock1.getPrecio()).thenReturn(5000.0);
        when(itemMock2.getPrecio()).thenReturn(3000.0);
        
        RegistroDeItem registroHijo1 = new RegistroDeItem(itemMock1, 4500.0);
        RegistroDeItem registroHijo2 = new RegistroDeItem(itemMock2, 2700.0); 
        
        when(itemMock1.getRegistroDeItem(0.9)).thenReturn(List.of(registroHijo1));
        when(itemMock2.getRegistroDeItem(0.9)).thenReturn(List.of(registroHijo2));

        
        paquete.add(itemMock1);
        paquete.add(itemMock2);

   
        List<RegistroDeItem> registrosResultantes = paquete.getRegistroDeItem(1.0);

        assertEquals(3, registrosResultantes.size());

       
        RegistroDeItem registroDelPaquete = registrosResultantes.get(0);
        assertEquals(paquete, registroDelPaquete.getItem());
        assertEquals(7200.0, registroDelPaquete.getPrecio());

        
        assertTrue(registrosResultantes.contains(registroHijo1));
        assertTrue(registrosResultantes.contains(registroHijo2));
        
        verify(itemMock1).getRegistroDeItem(0.9);
        verify(itemMock2).getRegistroDeItem(0.9);
    }
    
    
    @Test
    void test_resumenDeSku_Paquete() {
		when(itemMock1.getResumenDeSku()).thenReturn(Map.of("SKU-123", 2));
		when(itemMock2.getResumenDeSku()).thenReturn(Map.of("SKU-456", 1));

		paquete.add(itemMock1);
		paquete.add(itemMock2);

		
		Map<String, Integer> resumen = paquete.getResumenDeSku();
		assertEquals(2, resumen.size());
		assertTrue(resumen.containsKey("SKU-123"));
		assertTrue(resumen.containsKey("SKU-456"));
		assertEquals(2, resumen.get("SKU-123").intValue());
		assertEquals(1, resumen.get("SKU-456").intValue());
	}
    
    @Test
    void test_CoincideNombre_NoCoincideConNingunItem() {
		
		when(itemMock1.coincideNombre("Mouse")).thenReturn(false);
		when(itemMock2.coincideNombre("Mouse")).thenReturn(false);

		paquete.add(itemMock1);
		paquete.add(itemMock2);

		
		assertFalse(paquete.coincideNombre("Mouse"));
	}
    
    @Test
    void test_CoincideNombreConAlgunItem() {
        
        when(itemMock1.coincideNombre("Mouse")).thenReturn(false);
        when(itemMock2.coincideNombre("Mouse")).thenReturn(true);

        paquete.add(itemMock1);
        paquete.add(itemMock2);

        
        assertTrue(paquete.coincideNombre("Mouse"));
    }



}
