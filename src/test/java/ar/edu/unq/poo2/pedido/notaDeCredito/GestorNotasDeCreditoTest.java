package ar.edu.unq.poo2.pedido.notaDeCredito;

import ar.edu.unq.poo2.pedido.notadecredito.GestorNotasDeCredito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GestorNotasDeCreditoTest {
    GestorNotasDeCredito gestor;

    @BeforeEach
    void setUp(){
        gestor = new GestorNotasDeCredito();
    }

    @Test
    void seAgreganNotasDeCreditoCorrectamente() {
        Map<String, Double> datosReembolso = new HashMap<>();
        datosReembolso.put("Item", 10.0);
        gestor.hacerNotaDeCredito(datosReembolso);
        assertEquals(1, gestor.getNotas().size());
    }
}
