package ar.edu.unq.poo2.busquedaTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.busqueda.ConsultaDisponibilidadInventario;
import ar.edu.unq.poo2.pedido.Inventario;

public class ConsultaDisponibilidadInventarioTest {

    private Inventario inventario;
    private ConsultaDisponibilidadInventario consulta;

    @BeforeEach
    void setUp() {

        inventario = mock(Inventario.class);

        consulta =  new ConsultaDisponibilidadInventario(inventario);
    }

    @Test
    void retornaTrueCuandoElInventarioIndicaQueHayStock() {

        when(inventario.tieneStock("SKU1")).thenReturn(true);

        assertTrue(consulta.tieneStock("SKU1"));
    }

    @Test
    void retornaFalseCuandoElInventarioIndicaQueNoHayStock() {

        when(inventario.tieneStock("SKU1")).thenReturn(false);

        assertFalse(consulta.tieneStock("SKU1"));
    }
}