package ar.edu.unq.poo2.reporte;

import ar.edu.unq.poo2.reporte.formato.FormatoVisitante;
import ar.edu.unq.poo2.venta.HistorialDeVentas;
import ar.edu.unq.poo2.venta.RegistroDeItem;
import ar.edu.unq.poo2.venta.Venta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReporteDeProductosMasVendidosTest {
    private HistorialDeVentas historialMock;
    private FormatoVisitante visitanteMock;
    private Venta venta1Mock;
    private Venta venta2Mock;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    @BeforeEach
    public void setUp() {
        historialMock = mock(HistorialDeVentas.class);
        visitanteMock = mock(FormatoVisitante.class);
        venta1Mock = mock(Venta.class);
        venta2Mock = mock(Venta.class);
        fechaInicio = LocalDate.of(2025, 6, 1);
        fechaFin = LocalDate.of(2025, 6, 30);
        RegistroDeItem itemTeclado1Mock = mock(RegistroDeItem.class);
        RegistroDeItem itemTeclado2Mock = mock(RegistroDeItem.class);
        RegistroDeItem itemMouse1Mock = mock(RegistroDeItem.class);

        when(itemTeclado1Mock.getNombreItem()).thenReturn("Teclado");
        when(itemTeclado1Mock.getPrecio()).thenReturn(15000.0);

        when(itemTeclado2Mock.getNombreItem()).thenReturn("Teclado");
        when(itemTeclado2Mock.getPrecio()).thenReturn(15000.0);

        when(itemMouse1Mock.getNombreItem()).thenReturn("Mouse");
        when(itemMouse1Mock.getPrecio()).thenReturn(8000.0);

        when(venta1Mock.getRegistroDeItems()).thenReturn(List.of(itemTeclado1Mock, itemMouse1Mock));
        when(venta2Mock.getRegistroDeItems()).thenReturn(List.of(itemTeclado2Mock));
    }

    @Test
    public void seCreaReporteVacioSiSeCreaEnPeriodoSinVentas() {
        when(historialMock.getVentasEntre(fechaInicio, fechaFin)).thenReturn(List.of());
        ReporteDeProductosMasVendidos reporte = new ReporteDeProductosMasVendidos(fechaInicio, fechaFin, historialMock);
        assertTrue(reporte.getLineasProcesadas().isEmpty());
    }

    @Test
    public void seCreaLaCantidadCorrectaDeLineasDelReporte() {
        when(historialMock.getVentasEntre(fechaInicio, fechaFin)).thenReturn(List.of(venta1Mock, venta2Mock));
        ReporteDeProductosMasVendidos reporte = new ReporteDeProductosMasVendidos(fechaInicio, fechaFin, historialMock);
        List<LineaDeReporte> lineas = reporte.getLineasProcesadas();
        assertEquals(2, lineas.size());
    }

    @Test
    public void seOrdenanCorrectamenteLasLineasDelReporte() {
        when(historialMock.getVentasEntre(fechaInicio, fechaFin)).thenReturn(List.of(venta1Mock, venta2Mock));
        ReporteDeProductosMasVendidos reporte = new ReporteDeProductosMasVendidos(fechaInicio, fechaFin, historialMock);
        List<LineaDeReporte> lineas = reporte.getLineasProcesadas();
        assertEquals("Teclado", lineas.get(0).getNombreItem());
        assertEquals("Mouse", lineas.get(1).getNombreItem());
    }

    @Test
    public void seDelegaCuandoSeAceptaAVisitante() {
        when(historialMock.getVentasEntre(fechaInicio, fechaFin)).thenReturn(List.of());
        ReporteDeProductosMasVendidos reporte = new ReporteDeProductosMasVendidos(fechaInicio, fechaFin, historialMock);
        reporte.aceptar(visitanteMock);
        verify(visitanteMock).visitar(reporte);
    }
}