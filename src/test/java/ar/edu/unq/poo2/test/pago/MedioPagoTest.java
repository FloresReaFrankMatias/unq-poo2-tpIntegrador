package ar.edu.unq.poo2.test.pago;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.pago.MedioPago;

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
            notificado = true;
        }

        @Override
        public void reembolsar(double monto) {
        }
    }
}