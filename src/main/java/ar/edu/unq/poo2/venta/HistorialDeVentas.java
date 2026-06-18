package ar.edu.unq.poo2.venta;

import ar.edu.unq.poo2.item.Item;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HistorialDeVentas {
    private List<Venta> ventasRegistradas = new ArrayList<>();

    public void registrarVenta(List<Item> contenido, LocalDate fechaDeVenta){
        ventasRegistradas.add(new Venta(contenido, fechaDeVenta));
    }

    public List<Venta> getVentasEntre(LocalDate inicio, LocalDate fin) {
        return ventasRegistradas.stream().filter(venta -> venta.ocurrioEntre(inicio, fin)).toList();
    }
}
