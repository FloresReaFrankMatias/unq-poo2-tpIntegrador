package ar.edu.unq.poo2.busquedaTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.busqueda.PorPrecioMaximo;
import ar.edu.unq.poo2.item.Categoria;
import ar.edu.unq.poo2.item.Item;
import ar.edu.unq.poo2.item.Paquete;
import ar.edu.unq.poo2.item.Producto;

public class PorPrecioMaximoTest {

    private Item item;
    private PorPrecioMaximo criterio;

    private Producto mate;
    private Producto bombilla;
    private Paquete paquete;

    @BeforeEach
    void setUp() {

        item = mock(Item.class);

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

    @Test
    void unPaqueteCumpleCuandoSuPrecioEsMenorAlMaximo() {

        criterio = new PorPrecioMaximo(15000);

        assertTrue(criterio.cumple(paquete));
    }

    @Test
    void unPaqueteNoCumpleCuandoSuPrecioSuperaElMaximo() {

        criterio = new PorPrecioMaximo(10000);

        assertFalse(criterio.cumple(paquete));
    }
}