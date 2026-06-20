package ar.edu.unq.poo2.busqueda;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.item.Item;

public class PorNombreTest {
    PorNombre criterio;
    private Item itemMock;
    String nombreBusqueda;

    @BeforeEach
    void setUp() {
        itemMock = mock(Item.class);
        nombreBusqueda = "stanley";
        criterio = new PorNombre(nombreBusqueda);
    }

    @Test
    void cumpleCuandoItemIndicaQueSuNombreCoincide() {
        when(itemMock.coincideNombre(nombreBusqueda)).thenReturn(true);

        assertTrue(criterio.cumple(itemMock));
    }

    @Test
    void noCumpleCuandoItemIndicaQueSuNombreNoCoincide() {
        when(itemMock.coincideNombre(nombreBusqueda)).thenReturn(false);

        assertFalse(criterio.cumple(itemMock));
    }
}