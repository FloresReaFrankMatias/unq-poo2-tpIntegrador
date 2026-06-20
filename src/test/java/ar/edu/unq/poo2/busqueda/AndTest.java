package ar.edu.unq.poo2.busqueda;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.item.Item;

public class AndTest {
    private And criterioAnd;
    private Item itemMock;
    private CriterioBusqueda criterioMockUno;
    private CriterioBusqueda criterioMockDos;

    @BeforeEach
    void setUp() {
        itemMock = mock(Item.class);
        criterioMockUno = mock(CriterioBusqueda.class);
        criterioMockDos = mock(CriterioBusqueda.class);

        criterioAnd = new And(criterioMockUno, criterioMockDos);
    }

    @Test
    void cumpleCuandoAmbosCriteriosSeCumplen() {
        when(criterioMockUno.cumple(itemMock)).thenReturn(true);
        when(criterioMockDos.cumple(itemMock)).thenReturn(true);

        assertTrue(criterioAnd.cumple(itemMock));
    }

    @Test
    void noCumpleCuandoElPrimerCriterioNoSeCumple() {
        when(criterioMockUno.cumple(itemMock)).thenReturn(false);
        when(criterioMockDos.cumple(itemMock)).thenReturn(true);

        assertFalse(criterioAnd.cumple(itemMock));
    }

    @Test
    void noCumpleCuandoElSegundoCriterioNoSeCumple() {
        when(criterioMockUno.cumple(itemMock)).thenReturn(true);
        when(criterioMockDos.cumple(itemMock)).thenReturn(false);

        assertFalse(criterioAnd.cumple(itemMock));
    }

    @Test
    void noCumpleCuandoNingunCriterioSeCumple() {
        when(criterioMockUno.cumple(itemMock)).thenReturn(false);
        when(criterioMockDos.cumple(itemMock)).thenReturn(false);

        assertFalse(criterioAnd.cumple(itemMock));
    }
}