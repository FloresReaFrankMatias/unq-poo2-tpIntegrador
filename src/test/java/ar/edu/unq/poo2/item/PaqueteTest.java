package ar.edu.unq.poo2.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
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



}
