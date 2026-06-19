package ar.edu.unq.poo2.reporte.formato;

import ar.edu.unq.poo2.reporte.LineaDeReporte;
import ar.edu.unq.poo2.reporte.ReporteDeProductosMasVendidos;

import java.util.List;

public class FormatoCSV implements FormatoVisitante {
    private String resultadoGenerado = "";

    @Override
    public void visitar(ReporteDeProductosMasVendidos reporte) {
        List<LineaDeReporte> lineasDeReporte = reporte.getLineasProcesadas();
        resultadoGenerado += "Item,Cantidad Vendida,Precio Promedio Cobrado\n";

        lineasDeReporte.forEach(linea ->
            resultadoGenerado += linea.getNombreItem() + "," +
                                 linea.getCantidadVendida() + "," +
                                 linea.getPrecioPromedio() + "\n");
    }

    public String getResultadoGenerado() {
        return resultadoGenerado;
    }
}