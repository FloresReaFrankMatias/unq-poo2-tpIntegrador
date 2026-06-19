package ar.edu.unq.poo2.busquedaTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.busqueda.PorCategoria;
import ar.edu.unq.poo2.item.Categoria;
import ar.edu.unq.poo2.item.Paquete;
import ar.edu.unq.poo2.item.Producto;

public class PorCategoriaTest {
	
	private Producto mate;
	private Producto bombilla;
	private Producto libro;
	private Paquete paqueteDeportes;
	private Paquete paqueteMixto;

    @BeforeEach
    void setUp() {

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
                "Bombilla",
                "Bombilla",
                1,
                "Stanley",
                Categoria.DEPORTES,
                2000,
                0);

        libro = new Producto(
                "SKU3",
                "Libro",
                "Libro",
                1,
                "Planeta",
                Categoria.LIBROS,
                5000,
                0);

        paqueteDeportes = new Paquete(
                "Pack Matero",
                0,
                "Pack");

        paqueteDeportes.add(mate);
        paqueteDeportes.add(bombilla);

        paqueteMixto = new Paquete(
                "Pack Regalo",
                0,
                "Pack");

        paqueteMixto.add(mate);
        paqueteMixto.add(libro);
    }

    @Test
    void unProductoCumpleCuandoPerteneceALaCategoriaBuscada() {

        PorCategoria criterio = new PorCategoria(Categoria.DEPORTES);

        assertTrue(criterio.cumple(mate));
    }

    @Test
    void unProductoNoCumpleCuandoPerteneceAOtraCategoria() {

        PorCategoria criterio = new PorCategoria(Categoria.LIBROS);

        assertFalse(criterio.cumple(mate));
    }
    
    @Test
    void unPaqueteCumpleCuandoTodosSusItemsSonDeLaCategoriaBuscada() {

        PorCategoria criterio = new PorCategoria(Categoria.DEPORTES);

        assertTrue(criterio.cumple(paqueteDeportes));
    }
    
    @Test
    void unPaqueteNoCumpleCuandoTieneCategoriasMezcladas() {

        PorCategoria criterio =new PorCategoria(Categoria.DEPORTES);

        assertFalse(criterio.cumple(paqueteMixto));
    }
}