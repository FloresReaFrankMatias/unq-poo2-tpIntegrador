package ar.edu.unq.poo2.venta;

import ar.edu.unq.poo2.item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RegistroDeItemTest {
    Item itemMock;
    RegistroDeItem registro;

    @BeforeEach
    public void setUp() {
        itemMock = mock(Item.class);
        registro = new RegistroDeItem(itemMock, 1.0);
    }

    @Test
    public void seDevuelveComoNombreElNombreDelItemConElQueSeCreo() {
        when(itemMock.getNombre()).thenReturn("Item");
        assertEquals("Item", registro.getNombreItem());
    }
}