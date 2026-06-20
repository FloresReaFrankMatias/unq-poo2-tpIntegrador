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
    private Not criterioNot;
    private Item itemMock;
    private CriterioBusqueda criterioMock;

    @BeforeEach
    void setUp() {
        itemMock = mock(Item.class);
        criterioMock = mock(CriterioBusqueda.class);

        criterioNot = new Not(criterioMock);
    }

    @Test
    void cumpleCuandoElCriterioOriginalNoSeCumple() {
        when(criterioMock.cumple(itemMock)).thenReturn(false);

        assertTrue(criterioNot.cumple(itemMock));
    }

    @Test
    void noCumpleCuandoElCriterioOriginalSeCumple() {
        when(criterioMock.cumple(itemMock)).thenReturn(true);

        assertFalse(criterioNot.cumple(itemMock));
    }
}