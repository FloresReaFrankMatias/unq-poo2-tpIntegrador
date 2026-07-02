package ar.edu.unq.poo2.pago;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.pago.comprobante.CuponPago;

class CuponPagoTest {
    CuponPago cupon;

    @BeforeEach
    void setUp(){
        cupon = new CuponPago(10);
    }

    @Test
    void unCuponGuardaElCodigoDeTransaccion() {
        assertEquals(10, cupon.getCodigoTransaccion());
    }

    @Test
    void unCuponPuedeImprimirse() {
        assertEquals( "Cupón de pago - Operación N° 10", cupon.imprimir());
    }
}