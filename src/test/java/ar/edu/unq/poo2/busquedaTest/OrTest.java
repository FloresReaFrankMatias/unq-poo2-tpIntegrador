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
    private Or criterioOr;
    private Item itemMock;
    private CriterioBusqueda criterioMockUno;
    private CriterioBusqueda criterioMockDos;

    @BeforeEach
    void setUp() {
        itemMock = mock(Item.class);
        criterioMockUno = mock(CriterioBusqueda.class);
        criterioMockDos = mock(CriterioBusqueda.class);

        criterioOr = new Or(criterioMockUno, criterioMockDos);
    }

    @Test
    void cumpleCuandoAmbosCriteriosSeCumplen() {
        when(criterioMockUno.cumple(itemMock)).thenReturn(true);
        when(criterioMockDos.cumple(itemMock)).thenReturn(true);

        assertTrue(criterioOr.cumple(itemMock));
    }

    @Test
    void cumpleCuandoSoloElPrimerCriterioSeCumple() {
        when(criterioMockUno.cumple(itemMock)).thenReturn(true);
        when(criterioMockDos.cumple(itemMock)).thenReturn(false);

        assertTrue(criterioOr.cumple(itemMock));
    }

    @Test
    void cumpleCuandoSoloElSegundoCriterioSeCumple() {
        when(criterioMockUno.cumple(itemMock)).thenReturn(false);
        when(criterioMockDos.cumple(itemMock)).thenReturn(true);

        assertTrue(criterioOr.cumple(itemMock));
    }

    @Test
    void noCumpleCuandoNingunCriterioSeCumple() {
        when(criterioMockUno.cumple(itemMock)).thenReturn(false);
        when(criterioMockDos.cumple(itemMock)).thenReturn(false);

        assertFalse(criterioOr.cumple(itemMock));
    }
}