package ar.edu.unq.poo2.pedido.estado;

import ar.edu.unq.poo2.pedido.notadecredito.NotaDeCredito;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotaDeCreditoTest {
    @Test
    void seCreaNotaDeCreditoConDatosEsperados(){
        Map<String, Double> datosEsperados = Map.of("Producto", 22.0);
        NotaDeCredito notaDeCredito = new NotaDeCredito(datosEsperados);
        assertEquals(notaDeCredito.getReembolsado(), datosEsperados);
    }
}
