package ar.edu.unq.poo2.reporte.formato;

import ar.edu.unq.poo2.reporte.LineaDeReporte;
import ar.edu.unq.poo2.reporte.ReporteDeProductosMasVendidos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FormatoHTMLTest {
    ReporteDeProductosMasVendidos reporteMock;
    LineaDeReporte linea1;
    LineaDeReporte linea2;
    FormatoHTML formatoHTML;

    @BeforeEach
    public void setUp() {
        reporteMock = mock(ReporteDeProductosMasVendidos.class);
        linea1 = mock(LineaDeReporte.class);
        linea2 = mock(LineaDeReporte.class);
        formatoHTML = new FormatoHTML();
        when(linea1.getNombreItem()).thenReturn("Auriculares");
        when(linea1.getCantidadVendida()).thenReturn(10);
        when(linea1.getPrecioPromedio()).thenReturn(2500.5);
        when(linea2.getNombreItem()).thenReturn("Mouse");
        when(linea2.getCantidadVendida()).thenReturn(5);
        when(linea2.getPrecioPromedio()).thenReturn(1200.0);
    }

    @Test
    public void seGeneraHTMLConDatosDelReporteDado() {
        when(reporteMock.getLineasProcesadas()).thenReturn(List.of(linea1, linea2));
        formatoHTML.visitar(reporteMock);
        String esperado = "<h1>Reporte de Productos Más Vendidos</h1>\n" +
               "<ul>\n" +"<li>Auriculares: 10 unidades (Promedio: $2500.5)</li>\n" +
                         "<li>Mouse: 5 unidades (Promedio: $1200.0)</li>\n" + "</ul>\n";
        assertEquals(esperado, formatoHTML.getResultadoGenerado());
    }

    @Test
    public void alGenerarHTMLConReporteVacioSeCreaCSVSoloConEncabezado() {
        when(reporteMock.getLineasProcesadas()).thenReturn(List.of());
        formatoHTML.visitar(reporteMock);
        String esperado = "<h1>Reporte de Productos Más Vendidos</h1>\n<ul>\n</ul>\n";
        assertEquals(esperado, formatoHTML.getResultadoGenerado());
    }
}