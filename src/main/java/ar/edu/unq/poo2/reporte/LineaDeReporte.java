package ar.edu.unq.poo2.reporte;

public class LineaDeReporte {
    private final String nombreItem;
    private int cantidadVendida;
    private double dineroAcumulado;

    public LineaDeReporte(String nombreItem) {
        this.nombreItem = nombreItem;
        this.cantidadVendida = 0;
        this.dineroAcumulado = 0.0;
    }

    public void acumular(double precioCobrado) {
        cantidadVendida++;
        dineroAcumulado+= precioCobrado;
    }

    public String getNombreItem() {
        return nombreItem;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    public double getPrecioPromedio() {
        return cantidadVendida != 0 ? dineroAcumulado/cantidadVendida : 0;
    }
}