package ar.edu.unq.poo2.busquedaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.busqueda.And;
import ar.edu.unq.poo2.busqueda.Catalogo;
import ar.edu.unq.poo2.busqueda.PorCategoria;
import ar.edu.unq.poo2.busqueda.PorNombre;
import ar.edu.unq.poo2.busqueda.PorPrecioMaximo;
import ar.edu.unq.poo2.item.Categoria;
import ar.edu.unq.poo2.item.Item;
import ar.edu.unq.poo2.item.Producto;

public class CatalogoTest {

	private Catalogo catalogo;

	private Producto mate;
	private Producto bombilla;
	private Producto libro;

	@BeforeEach
	void setUp() {

		catalogo = new Catalogo();

		mate = new Producto(
				"SKU1",
				"Mate Stanley",
				"Mate térmico",
				1,
				"Stanley",
				Categoria.DEPORTES,
				10000,
				0);

		bombilla = new Producto(
				"SKU2",
				"Bombilla Pico de Loro",
				"Bombilla",
				1,
				"Lumilagro",
				Categoria.DEPORTES,
				2000,
				0);

		libro = new Producto(
				"SKU3",
				"El Señor de los Anillos",
				"Libro",
				1,
				"Minotauro",
				Categoria.LIBROS,
				5000,
				0);

		catalogo.agregarItem(mate);
		catalogo.agregarItem(bombilla);
		catalogo.agregarItem(libro);
	}

	@Test
	void devuelveItemsQueCumplenElCriterioDeBusquedaPorNombre() {

		List<Item> resultado =
				catalogo.buscar(new PorNombre("stanley"));

		assertEquals(1, resultado.size());
		assertTrue(resultado.contains(mate));
	}

	@Test
	void devuelveListaVaciaCuandoNingunItemCumpleElCriterio() {

		List<Item> resultado = catalogo.buscar(new PorNombre("televisor"));

		assertTrue(resultado.isEmpty());
	}

	@Test
	void devuelveTodosLosItemsQueCumplenElCriterio() {

		List<Item> resultado =	catalogo.buscar(new PorCategoria(Categoria.DEPORTES));

		assertEquals(2, resultado.size());
		assertTrue(resultado.contains(mate));
		assertTrue(resultado.contains(bombilla));
	}

	@Test
	void permiteBuscarConCriteriosCompuestos() {

		List<Item> resultado =
				catalogo.buscar(
						new And(
								new PorCategoria(Categoria.DEPORTES),
								new PorPrecioMaximo(5000)));

		assertEquals(1, resultado.size());
		assertTrue(resultado.contains(bombilla));
	}
}