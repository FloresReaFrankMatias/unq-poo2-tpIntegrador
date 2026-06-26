package ar.edu.unq.poo2.pedido.observadores;

import ar.edu.unq.poo2.item.Item;
import ar.edu.unq.poo2.notificaciones.ObservadorHistorialDeVentas;
import ar.edu.unq.poo2.pedido.Pedido;
import ar.edu.unq.poo2.venta.HistorialDeVentas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ObservadorHistorialDeVentasTest {
    HistorialDeVentas historialMock;
    Pedido pedidoMock;
    Item itemMock;
    ObservadorHistorialDeVentas observador;

    @BeforeEach
    public void setUp() {
        historialMock = mock(HistorialDeVentas.class);
        pedidoMock = mock(Pedido.class);
        itemMock = mock(Item.class);
        observador = new ObservadorHistorialDeVentas(historialMock);
    }

    @Test
    public void alReaccionarAEntregaSeEnviaElContenidoDadoAlHistorialDeVentas() {
        List<Item> contenidoVacio = List.of(itemMock);
        when(pedidoMock.getContenido()).thenReturn(contenidoVacio);
        observador.alEntregar(pedidoMock,pedidoMock.getEstadoActual(),pedidoMock.getEstadoAnterior());
        verify(historialMock).registrarVenta(eq(contenidoVacio), any(LocalDate.class));
    }

    @Test
    public void noSeReaccionaAOtrosEventosDePedidoQueNoSeanEntregar() {
        observador.alConfirmar(pedidoMock,pedidoMock.getEstadoActual(),pedidoMock.getEstadoAnterior());
        observador.alPreparar(pedidoMock,pedidoMock.getEstadoActual(),pedidoMock.getEstadoAnterior());
        observador.alEnviar(pedidoMock,pedidoMock.getEstadoActual(),pedidoMock.getEstadoAnterior());
        observador.alCancelar(pedidoMock,pedidoMock.getEstadoActual(),pedidoMock.getEstadoAnterior());
        verifyNoInteractions(historialMock,pedidoMock.getEstadoActual(),pedidoMock.getEstadoAnterior());
    }
}