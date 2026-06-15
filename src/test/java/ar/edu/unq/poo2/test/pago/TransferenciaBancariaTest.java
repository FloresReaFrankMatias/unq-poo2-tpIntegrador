package ar.edu.unq.poo2.test.pago;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.pago.PagoInvalidoException;
import ar.edu.unq.poo2.pago.TransferenciaBancaria;
import ar.edu.unq.poo2.pago.api.APITransferenciaBancaria;

class TransferenciaBancariaTest {

    private TransferenciaBancaria transferencia;
    private APITransferenciaBancaria apiTransferencia;

    @BeforeEach
    void setUp() {

        apiTransferencia = mock(APITransferenciaBancaria.class);

        transferencia = new TransferenciaBancaria(
                "123456789",
                null,
                apiTransferencia);
    }
    
    @Test
    void unaTransferenciaSeCreaConTodosLosDatosEsperados() {

        assertEquals("123456789", transferencia.getCbu());
        assertEquals(null, transferencia.getAlias());
        assertEquals(apiTransferencia, transferencia.getApiTransferencia());
    }
    
    @Test
    void unaTransferenciaSinCbuNiAliasEsInvalida() {

        TransferenciaBancaria transferenciaInvalida =
                new TransferenciaBancaria(
                        null,
                        null,
                        apiTransferencia);

        assertThrows(PagoInvalidoException.class,() -> transferenciaInvalida.procesarPago());
    }
}