package ar.edu.unq.poo2.pedido;

import ar.edu.unq.poo2.envio.Direccion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class ClienteTest {
    Cliente cliente;
    Direccion direccionMock;

    @BeforeEach
    void setUp(){
        direccionMock = mock(Direccion.class);

        cliente = new Cliente("cliente@gmail.com", direccionMock);
    }

    @Test
    void clienteDevuelveCorreoElectronicoEsperado(){
        assertEquals("cliente@gmail.com", cliente.getEmail());
    }

    @Test
    void clienteDevuelveDireccionEsperada(){
        assertEquals(direccionMock, cliente.getDireccion());
    }
}
