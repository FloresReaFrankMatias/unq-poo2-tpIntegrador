package ar.edu.unq.poo2.pago;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.pago.api.APITransferenciaBancaria;

class TransferenciaBancariaTest {
    private TransferenciaBancaria transferencia;
    private APITransferenciaBancaria apiTransferenciaMock;

    @BeforeEach
    void setUp() {
        apiTransferenciaMock = mock(APITransferenciaBancaria.class);

        transferencia = new TransferenciaBancaria(
                "123456789",
                null,
                apiTransferenciaMock);
    }

    @Test
    void unaTransferenciaSeCreaConTodosLosDatosEsperados() {
        assertEquals("123456789", transferencia.getCbu());
        assertNull(null, transferencia.getAlias());
        assertEquals(apiTransferenciaMock, transferencia.getApiTransferencia());
    }

    @Test
    void unaTransferenciaValidaConsultaALaApi() {
        when(apiTransferenciaMock.validarCuenta(anyString(), isNull()))
                .thenReturn(true);

        transferencia.procesarPago();

        verify(apiTransferenciaMock).validarCuenta("123456789", null);
    }

    @Test
    void unaTransferenciaInvalidaLanzaExcepcion() {
        when(apiTransferenciaMock.validarCuenta(anyString(), isNull()))
                .thenReturn(false);

        assertThrows(PagoInvalidoException.class, () -> transferencia.procesarPago());
    }

    @Test
    void unaTransferenciaValidaEjecutaLaTransferencia() {
        when(apiTransferenciaMock.validarCuenta(anyString(), isNull()))
                .thenReturn(true);

        transferencia.procesarPago();

        verify(apiTransferenciaMock).ejecutarTransferencia();
    }
    @Test
    void unaTransferenciaValidaGeneraUnComprobante() {
        when(apiTransferenciaMock.validarCuenta(anyString(), isNull()))
                .thenReturn(true);

        transferencia.procesarPago();

        assertNotNull(transferencia.getComprobante());
    }
}