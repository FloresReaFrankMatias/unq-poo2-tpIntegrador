package ar.edu.unq.poo2.venta;

import ar.edu.unq.poo2.item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VentaTest {
    Venta ventaTest;
    Item itemMockUno;
    Item itemMockDos;
    RegistroDeItem registroDeItemMockUno;
    RegistroDeItem registroDeItemMockDos;
    RegistroDeItem registroDeItemMockTres;
    LocalDate fechaDeVenta;

    @BeforeEach
    void setUp(){
        itemMockUno = mock(Item.class);
        itemMockDos = mock(Item.class);
        registroDeItemMockUno = mock(RegistroDeItem.class);
        registroDeItemMockDos = mock(RegistroDeItem.class);
        registroDeItemMockTres = mock(RegistroDeItem.class);
        
        when(itemMockUno.getRegistroDeItem(anyDouble())).thenReturn(List.of(registroDeItemMockUno, registroDeItemMockDos));
        when(itemMockDos.getRegistroDeItem(anyDouble())).thenReturn(List.of(registroDeItemMockTres));

        fechaDeVenta = LocalDate.of(2025, 6, 6);
        ventaTest = new Venta(List.of(itemMockUno, itemMockDos), fechaDeVenta);
    }

    @Test
    void seCreaConRegistroDeItemsCorrectos(){
        List<RegistroDeItem> registrosEsperados = List.of(registroDeItemMockUno, registroDeItemMockDos, registroDeItemMockTres);

        assertEquals(ventaTest.getRegistroDeItems(), registrosEsperados);
    }

    @Test
    void ocurreEntreFechasDadas(){
        assertTrue(ventaTest.ocurrioEntre(fechaDeVenta.minusDays(1), fechaDeVenta.plusDays(1)));
    }

    @Test
    void ocurreEntreFechasDadasInclusivamente(){
        assertTrue(ventaTest.ocurrioEntre(fechaDeVenta, fechaDeVenta));
    }

    @Test
    void noOcurreEntreFechasDadas(){
        assertFalse(ventaTest.ocurrioEntre(fechaDeVenta.plusDays(1), fechaDeVenta.plusDays(2)));
    }
}
