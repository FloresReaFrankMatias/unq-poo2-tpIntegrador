package ar.edu.unq.poo2.pago;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class MedioPagoTest {

    @Test
    void procesarPagoEjecutaTodosLosPasosDelTemplateMethod() {

        MedioPagoStub pago = new MedioPagoStub();

        pago.procesarPago();

        assertTrue(pago.validado);
        assertTrue(pago.reservado);
        assertTrue(pago.ejecutado);
        assertTrue(pago.notificado);
    }

    @Test
    void alProcesarUnPagoSeGeneraUnCodigoDeTransaccion() {

        MedioPagoStub pago = new MedioPagoStub();

        pago.procesarPago();

        assertTrue(pago.getCodigoTransaccion() > 0);
    }

    private class MedioPagoStub extends MedioPago {

        boolean validado = false;
        boolean reservado = false;
        boolean ejecutado = false;
        boolean notificado = false;

        @Override
        protected void validarDatos() {
            validado = true;
        }

        @Override
        protected void reservarFondos() {
            reservado = true;
        }

        @Override
        protected void ejecutarTransaccion() {
            ejecutado = true;
        }

        @Override
        protected void notificarResultado() {
            super.notificarResultado();
            notificado = true;
        }
    }
}