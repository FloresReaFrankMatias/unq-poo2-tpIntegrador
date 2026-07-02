package ar.edu.unq.poo2.reporte;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LineaDeReporteTest {
    private LineaDeReporte lineaDeReporte;

    @BeforeEach
    public void setUp() {
        lineaDeReporte = new LineaDeReporte("Item");
    }

    @Test
    public void seCreaConPrecioPromedioEnCero() {
        assertEquals(0, lineaDeReporte.getCantidadVendida());
    }

    @Test
    public void seCreaConCantidadVendidaEnCero() {
        assertEquals(0.0, lineaDeReporte.getPrecioPromedio());
    }

    @Test
    public void alAcumularSeSumaUnoACantidadVendida() {
        lineaDeReporte.acumular(1500.0);

        assertEquals(1, lineaDeReporte.getCantidadVendida());
    }

    @Test
    public void alAcumularSeSumaPrecioDado() {
        lineaDeReporte.acumular(1500.0);

        assertEquals(1500.0, lineaDeReporte.getPrecioPromedio());
    }

    @Test
    public void seCalculaElPromedioCorrectamente(){
        lineaDeReporte.acumular(100.0);
        lineaDeReporte.acumular(200.0);
        lineaDeReporte.acumular(300.0);

        assertEquals(200.0, lineaDeReporte.getPrecioPromedio());
    }
}