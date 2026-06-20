package ar.edu.unq.poo2.busquedaTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.busqueda.PorPrecioMaximo;
import ar.edu.unq.poo2.item.Item;

public class PorPrecioMaximoTest {
    private PorPrecioMaximo criterio;
    private Item itemMock;


    @BeforeEach
    void setUp() {
        itemMock = mock(Item.class);
        criterio = new PorPrecioMaximo(1000.0);
    }

    @Test
    void cumpleCuandoElPrecioEsMenorAlMaximo() {
        when(itemMock.getPrecio()).thenReturn(500.0);

        assertTrue(criterio.cumple(itemMock));
    }

    @Test
    void cumpleCuandoElPrecioEsIgualAlMaximo() {
        when(itemMock.getPrecio()).thenReturn(1000.0);

        assertTrue(criterio.cumple(itemMock));
    }

    @Test
    void noCumpleCuandoElPrecioSuperaElMaximo() {
        when(itemMock.getPrecio()).thenReturn(1500.0);

        assertFalse(criterio.cumple(itemMock));
    }
}