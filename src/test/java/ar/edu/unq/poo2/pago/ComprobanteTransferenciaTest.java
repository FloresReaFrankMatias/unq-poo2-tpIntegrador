package ar.edu.unq.poo2.pago;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.pago.comprobante.ComprobanteTransferencia;

class ComprobanteTransferenciaTest {
    ComprobanteTransferencia comprobante;

    @BeforeEach
    void setUp(){
        comprobante = new ComprobanteTransferencia(20);
    }

    @Test
    void unComprobanteGuardaElNumeroDeOperacion() {
        assertEquals(20,comprobante.getNumeroOperacion());
    }

    @Test
    void unComprobantePuedeImprimirse() {
        assertEquals("Comprobante de transferencia - Operación N° 20", comprobante.imprimir());
    }
}