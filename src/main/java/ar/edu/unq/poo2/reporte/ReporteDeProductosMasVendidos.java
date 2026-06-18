package ar.edu.unq.poo2.reporte;

import ar.edu.unq.poo2.reporte.formato.FormatoVisitante;
import ar.edu.unq.poo2.venta.HistorialDeVentas;
import ar.edu.unq.poo2.venta.RegistroDeItem;
import ar.edu.unq.poo2.venta.Venta;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReporteDeProductosMasVendidos implements Reporte {
    private List<LineaDeReporte> lineasProcesadas;

    public ReporteDeProductosMasVendidos(LocalDate inicio, LocalDate fin, HistorialDeVentas historial) {
        this.lineasProcesadas = armarReporte(inicio, fin, historial);
    }

    private List<LineaDeReporte> armarReporte(LocalDate inicio, LocalDate fin, HistorialDeVentas historial) {
        Map<String, LineaDeReporte> analizadosHastaAhora = new HashMap<>();
        List<Venta> ventasAAnalizar = historial.getVentasEntre(inicio, fin);
        ventasAAnalizar.forEach(venta -> analizarVenta(venta, analizadosHastaAhora));
        return analizadosOrdenados(analizadosHastaAhora);
    }

    private void analizarVenta(Venta venta, Map<String, LineaDeReporte> analizados){
        venta.getRegistroDeItems().forEach(registroDeItem -> armarLinea(registroDeItem, analizados));
    }

    private void armarLinea(RegistroDeItem registro, Map<String, LineaDeReporte> analizados) {
        String nombreItemVendido = registro.getNombreItem();
        analizados.putIfAbsent(nombreItemVendido, new LineaDeReporte(nombreItemVendido));
        analizados.get(nombreItemVendido).acumular(registro.getPrecio());
    }

    private List<LineaDeReporte> analizadosOrdenados(Map<String, LineaDeReporte> analizados){
        return analizados.values().stream()
                .sorted(comparadorPorMasVendidos())
                .toList();
    }

    private Comparator<LineaDeReporte> comparadorPorMasVendidos() {
        return Comparator.comparingInt(linea -> -linea.getCantidadVendida()); // Negativo para ordenar de mayor a menor.
    }

    public List<LineaDeReporte> getLineasProcesadas() {
        return lineasProcesadas;
    }

    @Override
    public void aceptar(FormatoVisitante visitante) {
        visitante.aceptar(this);
    }
}
