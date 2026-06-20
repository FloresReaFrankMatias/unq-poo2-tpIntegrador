package ar.edu.unq.poo2.busquedaTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.busqueda.PorCategoria;
import ar.edu.unq.poo2.item.Categoria;
import ar.edu.unq.poo2.item.Item;

public class PorCategoriaTest {
    PorCategoria criterio;
    Item itemMock;

    @BeforeEach
    void setUp(){
        itemMock = mock(Item.class);
        criterio = new PorCategoria(Categoria.DEPORTES);
    }

    @Test
    void unItemCumpleCuandoPerteneceALaCategoriaBuscada() {
        when(itemMock.getCategoria()).thenReturn(Categoria.DEPORTES);

        assertTrue(criterio.cumple(itemMock));
    }

    @Test
    void unItemNoCumpleCuandoPerteneceAOtraCategoria() {
        when(itemMock.getCategoria()).thenReturn(Categoria.LIBROS);

        assertFalse(criterio.cumple(itemMock));
    }
}