package ar.edu.unq.poo2.test.pago;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    void unaTransferenciaValidaConsultaALaApi() {

        when(apiTransferencia.validarCuenta(
                anyString(),
                isNull()))
                .thenReturn(true);

        transferencia.procesarPago();

        verify(apiTransferencia)
                .validarCuenta("123456789", null);
    }

    @Test
    void unaTransferenciaInvalidaLanzaExcepcion() {

        when(apiTransferencia.validarCuenta(
                anyString(),
                isNull()))
                .thenReturn(false);

        assertThrows(
                PagoInvalidoException.class,
                () -> transferencia.procesarPago());
    }

    @Test
    void unaTransferenciaValidaEjecutaLaTransferencia() {

        when(apiTransferencia.validarCuenta(
                anyString(),
                isNull()))
                .thenReturn(true);

        transferencia.procesarPago();

        verify(apiTransferencia)
                .ejecutarTransferencia();
    }
    @Test
    void unaTransferenciaValidaGeneraUnComprobante() {

        when(apiTransferencia.validarCuenta(
                anyString(),
                isNull()))
                .thenReturn(true);

        transferencia.procesarPago();

        assertNotNull(
                transferencia.getComprobante());
    }
}