package ar.edu.unq.poo2.reporte.formato;

import ar.edu.unq.poo2.reporte.LineaDeReporte;
import ar.edu.unq.poo2.reporte.ReporteDeProductosMasVendidos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FormatoTXTTest {
    ReporteDeProductosMasVendidos reporteMock;
    LineaDeReporte linea1;
    LineaDeReporte linea2;
    FormatoTXT formatoTXT;

    @BeforeEach
    public void setUp() {
        reporteMock = mock(ReporteDeProductosMasVendidos.class);
        linea1 = mock(LineaDeReporte.class);
        linea2 = mock(LineaDeReporte.class);
        formatoTXT = new FormatoTXT();
        when(linea1.getNombreItem()).thenReturn("Auriculares");
        when(linea1.getCantidadVendida()).thenReturn(10);
        when(linea1.getPrecioPromedio()).thenReturn(2500.5);
        when(linea2.getNombreItem()).thenReturn("Mouse");
        when(linea2.getCantidadVendida()).thenReturn(5);
        when(linea2.getPrecioPromedio()).thenReturn(1200.0);
    }

    @Test
    public void testGenerarTXTConDatos() {
        when(reporteMock.getLineasProcesadas()).thenReturn(List.of(linea1, linea2));
        formatoTXT.visitar(reporteMock);
        String esperado = "REPORTE DE PRODUCTOS MÁS VENDIDOS\n" +
                          "- Auriculares | Unidades: 10 | Promedio: $2500.5\n" +
                          "- Mouse | Unidades: 5 | Promedio: $1200.0\n";
        assertEquals(esperado, formatoTXT.getResultadoGenerado());
    }

    @Test
    public void testGenerarTXTVacioImprimeEstructuraBase() {
        when(reporteMock.getLineasProcesadas()).thenReturn(List.of());
        formatoTXT.visitar(reporteMock);
        String esperado = "REPORTE DE PRODUCTOS MÁS VENDIDOS\n";
        assertEquals(esperado, formatoTXT.getResultadoGenerado());
    }
}