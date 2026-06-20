package ar.edu.unq.poo2.busquedaTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.busqueda.CriterioBusqueda;
import ar.edu.unq.poo2.busqueda.Or;
import ar.edu.unq.poo2.item.Item;

public class OrTest {

    private Item item;

    private CriterioBusqueda criterio1;
    private CriterioBusqueda criterio2;

    private Or or;

    @BeforeEach
    void setUp() {

        item = mock(Item.class);

        criterio1 = mock(CriterioBusqueda.class);
        criterio2 = mock(CriterioBusqueda.class);

        or = new Or(criterio1, criterio2);
    }

    @Test
    void cumpleCuandoAmbosCriteriosSeCumplen() {

        when(criterio1.cumple(item)).thenReturn(true);
        when(criterio2.cumple(item)).thenReturn(true);

        assertTrue(or.cumple(item));
    }

    @Test
    void cumpleCuandoSoloElPrimerCriterioSeCumple() {

        when(criterio1.cumple(item)).thenReturn(true);
        when(criterio2.cumple(item)).thenReturn(false);

        assertTrue(or.cumple(item));
    }

    @Test
    void cumpleCuandoSoloElSegundoCriterioSeCumple() {

        when(criterio1.cumple(item)).thenReturn(false);
        when(criterio2.cumple(item)).thenReturn(true);

        assertTrue(or.cumple(item));
    }

    @Test
    void noCumpleCuandoNingunCriterioSeCumple() {

        when(criterio1.cumple(item)).thenReturn(false);
        when(criterio2.cumple(item)).thenReturn(false);

        assertFalse(or.cumple(item));
    }
}