package ar.edu.unq.poo2.busquedaTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.busqueda.And;
import ar.edu.unq.poo2.busqueda.CriterioBusqueda;
import ar.edu.unq.poo2.item.Item;

public class AndTest {

    private Item item;

    private CriterioBusqueda criterio1;
    private CriterioBusqueda criterio2;

    private And and;

    @BeforeEach
    void setUp() {

        item = mock(Item.class);

        criterio1 = mock(CriterioBusqueda.class);
        criterio2 = mock(CriterioBusqueda.class);

        and = new And(criterio1, criterio2);
    }

    @Test
    void cumpleCuandoAmbosCriteriosSeCumplen() {

        when(criterio1.cumple(item)).thenReturn(true);
        when(criterio2.cumple(item)).thenReturn(true);

        assertTrue(and.cumple(item));
    }

    @Test
    void noCumpleCuandoElPrimerCriterioNoSeCumple() {

        when(criterio1.cumple(item)).thenReturn(false);
        when(criterio2.cumple(item)).thenReturn(true);

        assertFalse(and.cumple(item));
    }

    @Test
    void noCumpleCuandoElSegundoCriterioNoSeCumple() {

        when(criterio1.cumple(item)).thenReturn(true);
        when(criterio2.cumple(item)).thenReturn(false);

        assertFalse(and.cumple(item));
    }

    @Test
    void noCumpleCuandoNingunCriterioSeCumple() {

        when(criterio1.cumple(item)).thenReturn(false);
        when(criterio2.cumple(item)).thenReturn(false);

        assertFalse(and.cumple(item));
    }
}