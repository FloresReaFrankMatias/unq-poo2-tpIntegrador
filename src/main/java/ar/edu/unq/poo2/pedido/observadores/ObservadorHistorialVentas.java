package ar.edu.unq.poo2.pedido.observadores;

import ar.edu.unq.poo2.pedido.Pedido;
import ar.edu.unq.poo2.venta.HistorialDeVentas;

public class ObservadorHistorialVentas implements ObservadorPedido{
    private HistorialDeVentas historial;

    @Override
    public void alEntregar(Pedido pedido){
        historial.registrarVenta(pedido.getContenido());
    }
}
