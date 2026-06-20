package ar.edu.unq.poo2.pago;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.pago.comprobante.ComprobanteTransferencia;

class ComprobanteTransferenciaTest {

    @Test
    void unComprobanteGuardaElNumeroDeOperacion() {

        ComprobanteTransferencia comprobante =  new ComprobanteTransferencia(20);

        assertEquals(20,comprobante.getNumeroOperacion());
    }

    @Test
    void unComprobantePuedeImprimirse() {

        ComprobanteTransferencia comprobante = new ComprobanteTransferencia(20);

        assertEquals("Comprobante de transferencia - Operación N° 20", comprobante.imprimir());
    }
}