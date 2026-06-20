package ar.edu.unq.poo2.pago;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.pago.comprobante.CuponPago;

class CuponPagoTest {

    @Test
    void unCuponGuardaElCodigoDeTransaccion() {

        CuponPago cupon = new CuponPago(10);

        assertEquals(10, cupon.getCodigoTransaccion());
    }

    @Test
    void unCuponPuedeImprimirse() {

        CuponPago cupon = new CuponPago(10);

        assertEquals( "Cupón de pago - Operación N° 10", cupon.imprimir());
    }
}