package ar.edu.unq.poo2.pedido;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class InventarioTest {
    Inventario inventario;

    @BeforeEach
    void setUp(){
         inventario = new Inventario();
    }

    @Test
    public void seIncrementaStockDeProducto(){
        Map<String, Integer> productoYCantidad = Map.of("Producto", 2);

        inventario.incrementarStock(productoYCantidad);

        assertDoesNotThrow(() -> inventario.decrementarStock(productoYCantidad));
    }

    @Test
    public void seDecrementaStockDeProducto(){
        Map<String, Integer> productoYCantidad = Map.of("Producto", 2);

        inventario.incrementarStock(productoYCantidad);
        inventario.decrementarStock(productoYCantidad);

        assertThrows(RuntimeException.class, () -> inventario.decrementarStock(Map.of("Producto", 1)));
    }

    @Test
    public void noSePuedeIncrementarEnCantidadNegativaElStockDeUnProducto(){
        Map<String, Integer> productoYCantidadNegativa = Map.of("Producto", -1);

        assertThrows(RuntimeException.class, () -> inventario.incrementarStock(productoYCantidadNegativa));
    }

    @Test
    public void noSePuedeIncrementarEnCantidadCeroElStockDeUnProducto(){
        Map<String, Integer> productoYCantidadNegativa = Map.of("Producto", 0);

        assertThrows(RuntimeException.class, () -> inventario.incrementarStock(productoYCantidadNegativa));
    }

    @Test
    public void noSePuedeDecrementarElStockDeUnProductoConCantidadMayorALaDisponible(){
        Map<String, Integer> productoYCantidad = Map.of("Producto", 2);

        inventario.incrementarStock(productoYCantidad);

        assertThrows(RuntimeException.class, () -> inventario.decrementarStock(Map.of("Producto", 3)));
    }

    @Test
    public void noSePuedeDecrementarEnCantidadNegativaElStockDeUnProducto(){
        Map<String, Integer> productoYCantidadNegativa = Map.of("Producto", -1);

        assertThrows(RuntimeException.class, () -> inventario.decrementarStock(productoYCantidadNegativa));
    }

    @Test
    public void noSePuedeDecrementarEnCantidadCeroElStockDeUnProducto(){
        Map<String, Integer> productoYCantidadNegativa = Map.of("Producto", 0);
        assertThrows(RuntimeException.class, () -> inventario.decrementarStock(productoYCantidadNegativa));
    }

    @Test
    public void cuandoInventarioNoTieneStockDeUnSkuDevuelveFalso(){
        assertFalse(inventario.tieneStock("Producto"));
    }

    @Test
    public void cuandoInventarioTieneStockDeUnSkuDevuelveVerdadero(){
        String nombreProducto = "Producto";

        inventario.incrementarStock(Map.of(nombreProducto, 1));

        assertTrue(inventario.tieneStock(nombreProducto));
    }
}
