package ar.edu.unq.poo2.pago;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.pago.api.APIBilleteraVirtual;

class BilleteraVirtualTest {
    private BilleteraVirtual billetera;
    private APIBilleteraVirtual apiBilleteraMock;

    @BeforeEach
    void setUp() {
        apiBilleteraMock = mock(APIBilleteraVirtual.class);

        billetera = new BilleteraVirtual(apiBilleteraMock);
    }

    @Test
    void unaBilleteraSeCreaConLosDatosEsperados() {
        assertEquals(apiBilleteraMock, billetera.getApiBilletera());
    }

    @Test
    void unaBilleteraValidaConsultaALaApi() {
        when(apiBilleteraMock.validarSaldo()).thenReturn(true);

        billetera.procesarPago();

        verify(apiBilleteraMock).validarSaldo();
    }

    @Test
    void unaBilleteraSinSaldoValidoLanzaExcepcion() {
        when(apiBilleteraMock.validarSaldo()).thenReturn(false);

        assertThrows(PagoInvalidoException.class, () -> billetera.procesarPago());
    }

    @Test
    void unaBilleteraValidaBloqueaSaldoYAcreditaFondos() {
        when(apiBilleteraMock.validarSaldo()).thenReturn(true);

        billetera.procesarPago();

        verify(apiBilleteraMock).bloquearSaldo();
        verify(apiBilleteraMock).acreditarFondos();
    }

    @Test
    void unaBilleteraValidaEnviaNotificacionPush() {
        when(apiBilleteraMock.validarSaldo()).thenReturn(true);

        billetera.procesarPago();

        verify(apiBilleteraMock).enviarPush(contains("Pago realizado"));
    }
}