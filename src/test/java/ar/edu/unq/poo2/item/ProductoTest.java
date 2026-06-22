package ar.edu.unq.poo2.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductoTest {
	Producto pr1;
	
	@BeforeEach
	void setUp() throws Exception {
		pr1 = new Producto("SKU-NGA", "Notebook", "Notebook gamer 16GB", 2500, "Asus", Categoria.ELECTRONICA, 100000.0, 0.10);	}

	@Test
	void testGetters() {
		
		
		assertEquals("SKU-NGA", pr1.getResumenDeSku().keySet().iterator().next());
		assertEquals(1, pr1.getResumenDeSku().values().iterator().next());
		assertEquals(2500, pr1.getPeso());
		assertEquals("Notebook", pr1.getNombre());
		assertEquals("Notebook gamer 16GB", pr1.getDescripcion());
		assertEquals(Categoria.ELECTRONICA, pr1.getCategoria());
		assertEquals(90000.0, pr1.getPrecio(), 0.001);
	}
	
	

}
