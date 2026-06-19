package ar.edu.unq.poo2.venta;


import ar.edu.unq.poo2.item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HistorialDeVentasTest {
    HistorialDeVentas historial;
    LocalDate primeraFecha;
    LocalDate segundaFecha;

    @BeforeEach
    void setUp(){
        historial = new HistorialDeVentas();
        List<Item> itemsVacios = new ArrayList<>();
        primeraFecha = LocalDate.of(2026, 6, 1);
        segundaFecha = LocalDate.of(2026, 7, 1);
        historial.registrarVenta(itemsVacios, primeraFecha);
        historial.registrarVenta(itemsVacios, segundaFecha);
    }

    @Test
    void seDevuelvenVentasRegistradasDelHistorialFiltradas() {
        List<Venta> filtradas = historial.getVentasEntre(primeraFecha.minusDays(1), segundaFecha.minusDays(1));

        assertEquals(1, filtradas.size());
    }

    @Test
    void seRegistranTodasLasVentas() {
        List<Venta> todasLasVentasRegistradas = historial.getVentasEntre(LocalDate.MIN, LocalDate.MAX);

        assertEquals(2, todasLasVentasRegistradas.size());
    }
}