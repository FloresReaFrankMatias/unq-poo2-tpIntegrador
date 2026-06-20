package ar.edu.unq.poo2.busquedaTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.busqueda.PorNombre;
import ar.edu.unq.poo2.item.Categoria;
import ar.edu.unq.poo2.item.Paquete;
import ar.edu.unq.poo2.item.Producto;

public class PorNombreTest {

    private Producto mate;
    private Producto bombilla;
    private Paquete paquete;

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
                "Bombilla Pico de Loro",
                "Bombilla de acero",
                1,
                "Lumilagro",
                Categoria.DEPORTES,
                2000,
                0);

        paquete = new Paquete(
                "Pack Matero",
                0,
                "Kit para tomar mate");

        paquete.add(mate);
        paquete.add(bombilla);
    }

    @Test
    void encuentraCoincidenciaEnElNombreDeUnProducto() {

        PorNombre criterio = new PorNombre("stanley");

        assertTrue(criterio.cumple(mate));
    }

    @Test
    void encuentraCoincidenciaEnElNombreDelPaquete() {

        PorNombre criterio = new PorNombre("matero");

        assertTrue(criterio.cumple(paquete));
    }

    @Test
    void encuentraCoincidenciaEnUnProductoContenidoEnUnPaquete() {

        PorNombre criterio = new PorNombre("bombilla");

        assertTrue(criterio.cumple(paquete));
    }

    @Test
    void ignoraMayusculasYMinusculas() {

        PorNombre criterio = new PorNombre("STANLEY");

        assertTrue(criterio.cumple(paquete));
    }

    @Test
    void noEncuentraCoincidenciaCuandoNiElPaqueteNiSusItemsCoinciden() {

        PorNombre criterio = new PorNombre("libro");

        assertFalse(criterio.cumple(paquete));
    }
}