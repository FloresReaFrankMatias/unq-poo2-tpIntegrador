package ar.edu.unq.poo2.busquedaTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.busqueda.PorCategoria;
import ar.edu.unq.poo2.item.Categoria;
import ar.edu.unq.poo2.item.Producto;

public class PorCategoriaTest {

    private Producto mate;

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
}