package ar.edu.unq.poo2.busquedaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.busqueda.Catalogo;
import ar.edu.unq.poo2.busqueda.CriterioBusqueda;
import ar.edu.unq.poo2.item.Item;

public class CatalogoTest {
	private Catalogo catalogo;
	private Item itemMockUno;
	private Item itemMockDos;
	private Item itemMockTres;
	private CriterioBusqueda criterioMock;

	@BeforeEach
	void setUp() {
		itemMockUno = mock(Item.class);
		itemMockDos = mock(Item.class);
		itemMockTres = mock(Item.class);
		criterioMock = mock(CriterioBusqueda.class);

		catalogo = new Catalogo();
		catalogo.agregarItem(itemMockUno);
		catalogo.agregarItem(itemMockDos);
		catalogo.agregarItem(itemMockTres);
	}

	@Test
	void devuelveSoloLosItemsQueCumplenElCriterio() {
		when(criterioMock.cumple(itemMockUno)).thenReturn(true);
		when(criterioMock.cumple(itemMockDos)).thenReturn(false);
		when(criterioMock.cumple(itemMockTres)).thenReturn(true);

		List<Item> resultado = catalogo.buscar(criterioMock);

		assertEquals(2, resultado.size());
		assertTrue(resultado.contains(itemMockUno));
		assertTrue(resultado.contains(itemMockTres));
	}

	@Test
	void devuelveListaVaciaCuandoNingunItemCumpleElCriterio() {
		// Configuramos: Ninguno pasa la prueba
		when(criterioMock.cumple(itemMockUno)).thenReturn(false);
		when(criterioMock.cumple(itemMockDos)).thenReturn(false);
		when(criterioMock.cumple(itemMockTres)).thenReturn(false);

		List<Item> resultado = catalogo.buscar(criterioMock);

		assertTrue(resultado.isEmpty());
	}

	@Test
	void devuelveTodosLosItemsCuandoTodosCumplenElCriterio() {
		// Configuramos: Todos pasan la prueba
		when(criterioMock.cumple(itemMockUno)).thenReturn(true);
		when(criterioMock.cumple(itemMockDos)).thenReturn(true);
		when(criterioMock.cumple(itemMockTres)).thenReturn(true);

		List<Item> resultado = catalogo.buscar(criterioMock);

		assertEquals(3, resultado.size());
		assertTrue(resultado.contains(itemMockUno));
		assertTrue(resultado.contains(itemMockDos));
		assertTrue(resultado.contains(itemMockTres));
	}
}