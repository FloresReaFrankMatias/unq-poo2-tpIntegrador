package ar.edu.unq.poo2.reporte.formato;

import ar.edu.unq.poo2.reporte.LineaDeReporte;
import ar.edu.unq.poo2.reporte.ReporteDeProductosMasVendidos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FormatoCSVTest {
    ReporteDeProductosMasVendidos reporteMock;
    LineaDeReporte linea1;
    LineaDeReporte linea2;
    FormatoCSV formatoCSV;

    @BeforeEach
    public void setUp() {
        reporteMock = mock(ReporteDeProductosMasVendidos.class);
        linea1 = mock(LineaDeReporte.class);
        linea2 = mock(LineaDeReporte.class);
        formatoCSV = new FormatoCSV();
        when(linea1.getNombreItem()).thenReturn("Auriculares");
        when(linea1.getCantidadVendida()).thenReturn(10);
        when(linea1.getPrecioPromedio()).thenReturn(2500.5);
        when(linea2.getNombreItem()).thenReturn("Mouse");
        when(linea2.getCantidadVendida()).thenReturn(5);
        when(linea2.getPrecioPromedio()).thenReturn(1200.0);
    }

    @Test
    public void seGeneraCSVConDatosDelReporteDado() {
        when(reporteMock.getLineasProcesadas()).thenReturn(List.of(linea1, linea2));
        formatoCSV.visitar(reporteMock);
        String esperado = "Item,Cantidad Vendida,Precio Promedio Cobrado\n" +
                          "Auriculares,10,2500.5\n" +
                          "Mouse,5,1200.0\n";
        assertEquals(esperado, formatoCSV.getResultadoGenerado());
    }

    @Test
    public void alGenerarCSVConReporteVacioSeCreaCSVSoloConEncabezado() {
        when(reporteMock.getLineasProcesadas()).thenReturn(List.of());
        formatoCSV.visitar(reporteMock);
        String esperado = "Item,Cantidad Vendida,Precio Promedio Cobrado\n";
        assertEquals(esperado, formatoCSV.getResultadoGenerado());
    }
}