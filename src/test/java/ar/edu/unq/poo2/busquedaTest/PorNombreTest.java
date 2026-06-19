package ar.edu.unq.poo2.busquedaTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.busqueda.PorNombre;
import ar.edu.unq.poo2.item.Item;

public class PorNombreTest {

    private Item item;
    private PorNombre criterio;

    @BeforeEach
    void setUp() {
        item = mock(Item.class);
    }

    @Test
    void encuentraCoincidenciaPorNombre() {

        when(item.getNombre()).thenReturn("Mate Stanley");

        criterio = new PorNombre("stanley");

        assertTrue(criterio.cumple(item));
    }
    
    @Test
    void ignoraMayusculasYMinusculas() {

        when(item.getNombre()).thenReturn("Mate Stanley");

        criterio = new PorNombre("STANLEY");

        assertTrue(criterio.cumple(item));
    }
   

    @Test
    void retornaFalseCuandoNoHayCoincidencia() {

        when(item.getNombre()).thenReturn("Mate Stanley");

        criterio = new PorNombre("bombilla");

        assertFalse(criterio.cumple(item));
    }
    
    

  
}