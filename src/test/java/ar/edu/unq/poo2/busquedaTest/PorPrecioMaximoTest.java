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

    private Item item;
    private PorPrecioMaximo criterio;

    @BeforeEach
    void setUp() {
        item = mock(Item.class);
    }
    
    @Test
    void cumpleCuandoElPrecioEsMenorAlMaximo() {

        when(item.getPrecioBaseCalculado()).thenReturn(1000.0);

        criterio = new PorPrecioMaximo(1500.0);

        assertTrue(criterio.cumple(item));
    }
    
    @Test
    void cumpleCuandoElPrecioEsIgualAlMaximo() {

        when(item.getPrecioBaseCalculado()).thenReturn(1000.0);

        criterio = new PorPrecioMaximo(1000.0);

        assertTrue(criterio.cumple(item));
    }
    
    @Test
    void noCumpleCuandoElPrecioSuperaElMaximo() {

        when(item.getPrecioBaseCalculado()).thenReturn(2000.0);

        criterio = new PorPrecioMaximo(1500.0);

        assertFalse(criterio.cumple(item));
    }
    
}