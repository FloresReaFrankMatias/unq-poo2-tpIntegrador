package ar.edu.unq.poo2.notificaciones;

import ar.edu.unq.poo2.pedido.Pedido;
import ar.edu.unq.poo2.venta.HistorialDeVentas;
import java.time.LocalDate;

public class ObservadorHistorialDeVentas implements ObservadorPedido{
    private HistorialDeVentas historial;

    public ObservadorHistorialDeVentas(HistorialDeVentas historial) {
        this.historial = historial;
    }

    @Override
    public void alEntregar(Pedido pedido){
        historial.registrarVenta(pedido.getContenido(), LocalDate.now());
    }
}
