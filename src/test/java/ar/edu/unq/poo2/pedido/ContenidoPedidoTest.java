package ar.edu.unq.poo2.pedido;

import ar.edu.unq.poo2.item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ContenidoPedidoTest {
    ContenidoPedido contenido;
    Item itemMockUno;
    Item itemMockDos;

    @BeforeEach
    void setUp() {
        contenido = new ContenidoPedido();
        itemMockUno = mock(Item.class);
        itemMockDos = mock(Item.class);
    }

    @Test
    void alCrearseEstaVacio() {
        assertFalse(contenido.tieneItems());
    }

    @Test
    void agregaItemsCorrectamente() {
        contenido.agregarItem(itemMockUno);
        assertTrue(contenido.tieneItems());

        contenido.quitarItem(itemMockUno);
        assertFalse(contenido.tieneItems());
    }

    @Test
    void quitaItemsCorrectamente() {
        contenido.agregarItem(itemMockUno);
        contenido.quitarItem(itemMockUno);

        assertFalse(contenido.tieneItems());
    }

    @Test
    void devuelveLosItemsDeFormaInmutable() {
        contenido.agregarItem(itemMockUno);
        List<Item> items = contenido.getItems();

        assertThrows(UnsupportedOperationException.class, () -> items.add(itemMockDos));
        assertThrows(UnsupportedOperationException.class, () -> items.remove(itemMockUno));
    }

    @Test
    void generaElResumenDeSkusCombinandoLosSkusDeSusItems() {
        contenido.agregarItem(itemMockUno);
        contenido.agregarItem(itemMockDos);

        when(itemMockUno.getResumenDeSku()).thenReturn(Map.of("SKU-1", 2));
        when(itemMockDos.getResumenDeSku()).thenReturn(Map.of("SKU-2", 1));

        Map<String, Integer> resumen = contenido.getResumenDeSkus();

        assertEquals(2, resumen.get("SKU-1"));
        assertEquals(1, resumen.get("SKU-2"));
        assertEquals(2, resumen.size());
    }

    @Test
    void generaElResumenDePreciosCombinandoLosPreciosDeSusItems() {
        contenido.agregarItem(itemMockUno);
        contenido.agregarItem(itemMockDos);

        when(itemMockUno.getResumenDePrecio()).thenReturn(Map.of("ItemUno", 200.0));
        when(itemMockDos.getResumenDePrecio()).thenReturn(Map.of("ItemDos", 150.0));

        Map<String, Double> resumen = contenido.getResumenDePrecios();

        assertEquals(200.0, resumen.get("ItemUno"));
        assertEquals(150.0, resumen.get("ItemDos"));
        assertEquals(2, resumen.size());
    }
}