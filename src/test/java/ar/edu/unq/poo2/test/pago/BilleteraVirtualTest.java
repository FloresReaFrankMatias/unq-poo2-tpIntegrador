package ar.edu.unq.poo2.test.pago;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.pago.BilleteraVirtual;
import ar.edu.unq.poo2.pago.PagoInvalidoException;
import ar.edu.unq.poo2.pago.api.APIBilleteraVirtual;

class BilleteraVirtualTest {

    private BilleteraVirtual billetera;
    private APIBilleteraVirtual apiBilletera;

    @BeforeEach
    void setUp() {

        apiBilletera = mock(APIBilleteraVirtual.class);

        billetera = new BilleteraVirtual(apiBilletera);
    }

    @Test
    void unaBilleteraSeCreaConLosDatosEsperados() {

        assertEquals(
                apiBilletera,
                billetera.getApiBilletera());
    }

    @Test
    void unaBilleteraValidaConsultaALaApi() {

        when(apiBilletera.validarSaldo())
                .thenReturn(true);

        billetera.procesarPago();

        verify(apiBilletera).validarSaldo();
    }

    @Test
    void unaBilleteraSinSaldoValidoLanzaExcepcion() {

        when(apiBilletera.validarSaldo())
                .thenReturn(false);

        assertThrows(
                PagoInvalidoException.class,
                () -> billetera.procesarPago());
    }

    @Test
    void unaBilleteraValidaBloqueaSaldoYAcreditaFondos() {

        when(apiBilletera.validarSaldo())
                .thenReturn(true);

        billetera.procesarPago();

        verify(apiBilletera).bloquearSaldo();
        verify(apiBilletera).acreditarFondos();
    }

    @Test
    void unaBilleteraValidaEnviaNotificacionPush() {

        when(apiBilletera.validarSaldo())
                .thenReturn(true);

        billetera.procesarPago();

        verify(apiBilletera)
                .enviarPush(contains("Pago realizado"));
    }
}