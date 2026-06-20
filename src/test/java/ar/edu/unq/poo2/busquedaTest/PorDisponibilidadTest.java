package ar.edu.unq.poo2.busquedaTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.busqueda.ConsultaDisponibilidadInventario;
import ar.edu.unq.poo2.busqueda.PorDisponibilidad;
import ar.edu.unq.poo2.item.Item;

public class PorDisponibilidadTest {
    private PorDisponibilidad criterio;
    private Item itemMock;
    private ConsultaDisponibilidadInventario consultaInventarioMock;

    @BeforeEach
    void setUp() {
        itemMock = mock(Item.class);
        consultaInventarioMock = mock(ConsultaDisponibilidadInventario.class);

        criterio = new PorDisponibilidad(consultaInventarioMock);
    }

    @Test
    void cumpleCuandoHayStockDeTodosLosSkusDelItem() {
        when(itemMock.getResumenDeSku()).thenReturn(Map.of("SKU1", 1, "SKU2", 1));

        when(consultaInventarioMock.tieneStock("SKU1")).thenReturn(true);
        when(consultaInventarioMock.tieneStock("SKU2")).thenReturn(true);

        assertTrue(criterio.cumple(itemMock));
    }

    @Test
    void noCumpleCuandoFaltaStockDeAlMenosUnSkuDelItem() {
        when(itemMock.getResumenDeSku()).thenReturn(Map.of("SKU1", 1, "SKU2", 1));

        when(consultaInventarioMock.tieneStock("SKU1")).thenReturn(true);
        when(consultaInventarioMock.tieneStock("SKU2")).thenReturn(false);

        assertFalse(criterio.cumple(itemMock));
    }
}