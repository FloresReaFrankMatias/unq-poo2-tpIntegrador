package ar.edu.unq.poo2.busquedaTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.busqueda.ConsultaDisponibilidadInventario;
import ar.edu.unq.poo2.pedido.Inventario;

public class ConsultaDisponibilidadInventarioTest {
    private ConsultaDisponibilidadInventario consulta;
    private Inventario inventarioMock;

    @BeforeEach
    void setUp() {
        inventarioMock = mock(Inventario.class);
        consulta = new ConsultaDisponibilidadInventario(inventarioMock);
    }

    @Test
    void retornaTrueCuandoElInventarioIndicaQueHayStock() {
        when(inventarioMock.tieneStock("SKU1")).thenReturn(true);

        assertTrue(consulta.tieneStock("SKU1"));

        verify(inventarioMock).tieneStock("SKU1");
    }

    @Test
    void retornaFalseCuandoElInventarioIndicaQueNoHayStock() {
        when(inventarioMock.tieneStock("SKU1")).thenReturn(false);

        assertFalse(consulta.tieneStock("SKU1"));

        verify(inventarioMock).tieneStock("SKU1");
    }
}