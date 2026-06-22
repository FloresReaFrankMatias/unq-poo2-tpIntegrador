package ar.edu.unq.poo2.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

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
		assertEquals(100000.0, pr1.getPrecioBase());
	}
	@Test
	void test_Accesors_AtributosDinamicos() {
		pr1.setAtributoDinamico("Color","Rojo");
		pr1.setAtributoDinamico("Graficos Integrados", false);
		pr1.setAtributoDinamico("Disco", 500);
		
		assertEquals("Rojo", pr1.getAtributoDinamico("Color").getValor());
		assertEquals(false, pr1.getAtributoDinamico("Graficos Integrados").getValor());
		assertEquals(500.0, pr1.getAtributoDinamico("Disco").getValor());
		
		
	}
	@Test
	void test_AtributosSonValidos() {
		assertTrue(pr1.atributosSonValidos());
		
		pr1.setAtributoDinamico("Color","Rojo");
		pr1.setAtributoDinamico("Graficos Integrados", false);
		pr1.setAtributoDinamico("Disco", 500);
		
		assertTrue(pr1.atributosSonValidos());
		
		
	}
	@Test
	void test_AtributosSonInValidos_Por_Setear_StringVacio() {
		assertTrue(pr1.atributosSonValidos());
		
		
		pr1.setAtributoDinamico("Graficos Integrados", false);
		pr1.setAtributoDinamico("Disco", 500);
		
		assertTrue(pr1.atributosSonValidos());
		
		pr1.setAtributoDinamico("Color"," ");
		assertFalse(pr1.atributosSonValidos());
		
		
	}
	@Test
	void test_resumenDeSku_producto() {
		assertEquals(1, pr1.getResumenDeSku().size());
		assertTrue(pr1.getResumenDeSku().containsKey("SKU-NGA"));
		assertEquals(1, pr1.getResumenDeSku().get("SKU-NGA").intValue());
	}
	
	@Test
	void test_registroDeItem_producto() {
		assertEquals(1, pr1.getRegistroDeItem(1.0).size());
		assertEquals(pr1, pr1.getRegistroDeItem(1.0).get(0).getItem());
		assertEquals(90000.0, pr1.getRegistroDeItem(1.0).get(0).getPrecio(), 0.001);
	}
	
	@Test
	void test_NoPuedeAgregarProducto() {
		Producto prMock = mock(Producto.class);
		assertThrows(UnsupportedOperationException.class, () -> pr1.add(prMock));
	}
	@Test
	void test_NoPuedeEliminarProducto() {
		Producto prMock = mock(Producto.class);
		assertThrows(UnsupportedOperationException.class, () -> pr1.remove(prMock));
	}


}
