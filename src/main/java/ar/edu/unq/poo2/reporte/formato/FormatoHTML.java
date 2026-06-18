package ar.edu.unq.poo2.reporte.formato;

import ar.edu.unq.poo2.reporte.LineaDeReporte;
import ar.edu.unq.poo2.reporte.ReporteDeProductosMasVendidos;
import java.util.List;

public class FormatoHTML implements FormatoVisitante {
    private String resultadoGenerado = "";

    @Override
    public void visitar(ReporteDeProductosMasVendidos reporte) {
        List<LineaDeReporte> lineasDeReporte = reporte.getLineasProcesadas();
        resultadoGenerado += "<h1>Reporte de Productos Más Vendidos</h1>\n<ul>\n";

        lineasDeReporte.forEach(linea ->
            resultadoGenerado += "<li>" + linea.getNombreItem() + ": " +
                                          linea.getCantidadVendida() + " unidades " +
                         "(Promedio: $" + linea.getPrecioPromedio() + ")</li>\n");
        resultadoGenerado += "</ul>\n";
    }

    public String getResultadoGenerado() {
        return resultadoGenerado;
    }
}
