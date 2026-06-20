package ar.edu.unq.poo2.busquedaTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.busqueda.CriterioBusqueda;
import ar.edu.unq.poo2.busqueda.Not;
import ar.edu.unq.poo2.item.Item;

public class NotTest {

    private Item item;
    private CriterioBusqueda criterio;
    private Not not;

    @BeforeEach
    void setUp() {

        item = mock(Item.class);

        criterio = mock(CriterioBusqueda.class);

        not = new Not(criterio);
    }

    @Test
    void cumpleCuandoElCriterioOriginalNoSeCumple() {

        when(criterio.cumple(item)).thenReturn(false);

        assertTrue(not.cumple(item));
    }

    @Test
    void noCumpleCuandoElCriterioOriginalSeCumple() {

        when(criterio.cumple(item)).thenReturn(true);

        assertFalse(not.cumple(item));
    }
}