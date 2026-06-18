package ar.edu.unq.poo2.reporte.formato;

import ar.edu.unq.poo2.reporte.LineaDeReporte;
import ar.edu.unq.poo2.reporte.ReporteDeProductosMasVendidos;

import java.util.List;

public class FormatoTXT implements FormatoVisitante{
    private String resultadoGenerado = "";

    @Override
    public void visitar(ReporteDeProductosMasVendidos reporte) {
        List<LineaDeReporte> lineasDeReporte = reporte.getLineasProcesadas();
        resultadoGenerado += "REPORTE DE PRODUCTOS MÁS VENDIDOS\n";

        lineasDeReporte.forEach(linea ->
            resultadoGenerado += "- " + linea.getNombreItem() +
                      " | Unidades: " + linea.getCantidadVendida() +
                     " | Promedio: $" + linea.getPrecioPromedio() + "\n");
    }

    public String getResultadoGenerado() {
        return resultadoGenerado;
    }
}
