package ar.edu.unq.poo2.test.pago;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

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

        billetera = new BilleteraVirtual(
                1000.0,
                apiBilletera);
    }
    
    @Test
    void unaBilleteraSeCreaConLosDatosEsperados() {

        assertEquals(1000.0, billetera.getSaldoDisponible());
        assertEquals(apiBilletera, billetera.getApiBilletera());
    }
    
    @Test
    void unaBilleteraSinSaldoEsInvalida() {

        BilleteraVirtual billeteraInvalida =
                new BilleteraVirtual(
                        null,
                        apiBilletera);

        assertThrows(PagoInvalidoException.class,() -> billeteraInvalida.procesarPago());
    }
    
    @Test
    void unaBilleteraConSaldoNegativoEsInvalida() {

        BilleteraVirtual billeteraInvalida =
                new BilleteraVirtual(
                        -100.0,
                        apiBilletera);

        assertThrows(PagoInvalidoException.class,() -> billeteraInvalida.procesarPago());
    }
}