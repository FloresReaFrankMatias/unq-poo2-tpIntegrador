package ar.edu.unq.poo2.busquedaTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.busqueda.ConsultaDisponibilidadInventario;
import ar.edu.unq.poo2.busqueda.PorDisponibilidad;
import ar.edu.unq.poo2.item.Categoria;
import ar.edu.unq.poo2.item.Paquete;
import ar.edu.unq.poo2.item.Producto;

public class PorDisponibilidadTest {

    private Producto mate;
    private Producto bombilla;
    private Paquete paquete;

    private ConsultaDisponibilidadInventario consultaInventario;
    private PorDisponibilidad criterio;

    @BeforeEach
    void setUp() {

        mate = new Producto(
                "SKU1",
                "Mate Stanley",
                "Mate térmico",
                1,
                "Stanley",
                Categoria.DEPORTES,
                10000,
                0);

        bombilla = new Producto(
                "SKU2",
                "Bombilla Stanley",
                "Bombilla",
                1,
                "Stanley",
                Categoria.DEPORTES,
                2000,
                0);

        paquete = new Paquete(
                "Pack Matero",
                0,
                "Pack");

        paquete.add(mate);
        paquete.add(bombilla);

        consultaInventario =
                mock(ConsultaDisponibilidadInventario.class);

        criterio =
                new PorDisponibilidad(consultaInventario);
    }

    @Test
    void unProductoCumpleCuandoTieneStockDisponible() {

        when(consultaInventario.tieneStock("SKU1"))
                .thenReturn(true);

        assertTrue(criterio.cumple(mate));
    }

    @Test
    void unProductoNoCumpleCuandoNoTieneStockDisponible() {

        when(consultaInventario.tieneStock("SKU1"))
                .thenReturn(false);

        assertFalse(criterio.cumple(mate));
    }

    @Test
    void unPaqueteCumpleCuandoTodosSusProductosTienenStock() {

        when(consultaInventario.tieneStock("SKU1"))
                .thenReturn(true);

        when(consultaInventario.tieneStock("SKU2"))
                .thenReturn(true);

        assertTrue(criterio.cumple(paquete));
    }

    @Test
    void unPaqueteNoCumpleCuandoUnoDeSusProductosNoTieneStock() {

        when(consultaInventario.tieneStock("SKU1"))
                .thenReturn(true);

        when(consultaInventario.tieneStock("SKU2"))
                .thenReturn(false);

        assertFalse(criterio.cumple(paquete));
    }
}