package ar.edu.unq.poo2.pago;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MedioPagoTest {
    MedioPagoStub pago;

    @BeforeEach
    void setUp(){
        pago = new MedioPagoStub();
        pago.procesarPago();
    }

    @Test
    void procesarPagoEjecutaTodosLosPasosDelTemplateMethod() {
        assertTrue(pago.validado);
        assertTrue(pago.reservado);
        assertTrue(pago.ejecutado);
        assertTrue(pago.notificado);
    }

    @Test
    void alProcesarUnPagoSeGeneraUnCodigoDeTransaccion() {
        assertTrue(pago.getCodigoTransaccion() > 0);
    }

    private class MedioPagoStub extends MedioPago { // Clase utilizada para testear clase abstracta.

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
        protected boolean cumpleValidacion() {
            return true;
        }

        @Override
        protected String getMensajeErrorValidacion() {
            return "";
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