package ar.edu.unq.poo2.pedido;

import ar.edu.unq.poo2.envio.MetodoDeEnvio;
import ar.edu.unq.poo2.item.Item;
import ar.edu.unq.poo2.pedido.estado.EstadoPedido;
import ar.edu.unq.poo2.pedido.notadecredito.GestorNotasDeCredito;
import ar.edu.unq.poo2.pedido.observadores.ObservadorPedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PedidoTest {
    Pedido pedido;
    EstadoPedido estadoMock;
    MetodoDeEnvio envioMock;
    Inventario inventarioMock;
    GestorNotasDeCredito gestorMock;
    Item itemMockUno;
    Item itemMockDos;
    ObservadorPedido observadorMock;

    @BeforeEach
    void setUp(){
        estadoMock = mock(EstadoPedido.class);
        itemMockUno = mock(Item.class);
        itemMockDos = mock(Item.class);
        envioMock = mock(MetodoDeEnvio.class);
        inventarioMock = mock(Inventario.class);
        gestorMock = mock(GestorNotasDeCredito.class);
        observadorMock = mock(ObservadorPedido.class);
        pedido = new Pedido(inventarioMock, gestorMock, envioMock, Set.of(observadorMock));
        pedido.setEstadoActual(estadoMock);
    }

    @Test
    void unPedidoSeCreaEnEstadoBorrador() {
        Pedido nuevoPedido = new Pedido(inventarioMock, gestorMock, envioMock, Set.of(observadorMock));
        assertDoesNotThrow(() -> nuevoPedido.agregarItem(itemMockUno));
    }

    @Test
    void unPedidoDelegaLaAccionASuEstadoAlIntentarConfirmarlo(){
        pedido.confirmar();
        verify(estadoMock).confirmar(pedido);
    }

    @Test
    void alConfirmarSePideAlEstadoActualQueNotifiqueTransicionALosObservadores() {
        pedido.confirmar();
        verify(estadoMock).notificarTransicion(pedido, observadorMock);
    }

    @Test
    void unPedidoDelegaLaAccionASuEstadoAlIntentarCancelarlo(){
        pedido.cancelar();
        verify(estadoMock).cancelar(pedido);
    }

    @Test
    void alCancelarSePideAlEstadoActualQueNotifiqueTransicionALosObservadores() {
        pedido.cancelar();
        verify(estadoMock).notificarTransicion(pedido, observadorMock);
    }

    @Test
    void unPedidoDelegaLaAccionASuEstadoAlIntentarPrepararlo(){
        pedido.preparar();
        verify(estadoMock).preparar(pedido);
    }

    @Test
    void alPrepararSePideAlEstadoActualQueNotifiqueTransicionALosObservadores() {
        pedido.preparar();
        verify(estadoMock).notificarTransicion(pedido, observadorMock);
    }

    @Test
    void unPedidoDelegaLaAccionASuEstadoAlIntentarEnviarlo(){
        pedido.enviar();
        verify(estadoMock).enviar(pedido);
    }

    @Test
    void alEnviarSePideAlEstadoActualQueNotifiqueTransicionALosObservadores() {
        pedido.enviar();
        verify(estadoMock).notificarTransicion(pedido, observadorMock);
    }

    @Test
    void unPedidoDelegaLaAccionASuEstadoAlIntentarEntregarlo(){
        pedido.entregar();
        verify(estadoMock).entregar(pedido);
    }

    @Test
    void alEntregarSePideAlEstadoActualQueNotifiqueTransicionALosObservadores() {
        pedido.entregar();
        verify(estadoMock).notificarTransicion(pedido, observadorMock);
    }

    @Test
    public void alDescontarStockSeLlamaAlInventario(){
        pedido.descontarStock();
        verify(inventarioMock).decrementarStock(anyMap());
    }

    @Test
    public void alReponerStockSeLlamaAlInventario(){
        pedido.reponerStock();
        verify(inventarioMock).incrementarStock(anyMap());
    }

    @Test
    public void alGenerarNotaDeCreditoSeLlamaAlGestor(){
        pedido.generarNotaDeCredito(new HashMap<>());
        verify(gestorMock).hacerNotaDeCredito(anyMap());
    }

    @Test
    public void alDescontarStockSeLePasaElResumenAlInventario() {
        pedido.agregarItem(itemMockUno);

        pedido.descontarStock();

        verify(inventarioMock).decrementarStock(anyMap());
    }

    @Test
    public void alGenerarNotaDeCreditoSeAgreganLosExtrasAlResumenDelContenido() {
        pedido.agregarItem(itemMockUno);
        when(itemMockUno.getResumenDePrecio()).thenReturn(Map.of("ItemUno", 100.0));
        Map<String, Double> extras = Map.of("CostoEnvio", 50.0);
        Map<String, Double> mapaEsperado = Map.of(
                "ItemUno", 100.0,
                "CostoEnvio", 50.0
        );

        pedido.generarNotaDeCredito(extras);

        verify(gestorMock).hacerNotaDeCredito(mapaEsperado);
    }

    @Test
    void indicaCorrectamenteSiTieneItems() {
        pedido.agregarItem(itemMockUno);

        assertTrue(pedido.tieneItems(), "El pedido debería registrar que tiene ítems");
    }

    @Test
    void devuelveElPesoTotalCorrectoBasadoEnSusItems() {
        when(itemMockUno.getPeso()).thenReturn(10);
        when(itemMockDos.getPeso()).thenReturn(15);

        pedido.agregarItem(itemMockUno);
        pedido.agregarItem(itemMockDos);

        assertEquals(25, pedido.getPesoTotal());
    }

    @Test
    void devuelveElValorTotalCorrectoBasadoEnSusItems() {
        when(itemMockUno.getPrecio()).thenReturn(150.0);
        when(itemMockDos.getPrecio()).thenReturn(50.5);

        pedido.agregarItem(itemMockUno);
        pedido.agregarItem(itemMockDos);

        assertEquals(200.5, pedido.getValorTotal());
    }

    @Test
    void devuelveSuContenidoDeFormaInmodificable() {
        pedido.agregarItem(itemMockUno);
        List<Item> listaObtenida = pedido.getContenido();

        assertThrows(UnsupportedOperationException.class, () -> listaObtenida.add(itemMockDos));
        assertThrows(UnsupportedOperationException.class, () -> listaObtenida.remove(itemMockUno));
    }

    @Test
    public void alReponerStockSeEnviaElResumenCorrectoDeSusItemsAlInventario() {
        when(itemMockUno.getResumenDeSku()).thenReturn(Map.of("SKU-1", 2));
        when(itemMockDos.getResumenDeSku()).thenReturn(Map.of("SKU-2", 1));
        pedido.agregarItem(itemMockUno);
        pedido.agregarItem(itemMockDos);
        Map<String, Integer> resumenEsperado = Map.of("SKU-1", 2, "SKU-2", 1);

        pedido.reponerStock();

        verify(inventarioMock).incrementarStock(resumenEsperado);
    }

    @Test
    public void alGenerarNotaDeCreditoSeEnviaElResumenCorrectoDeSusItemsConExtrasAlGestor() {
        when(itemMockUno.getResumenDePrecio()).thenReturn(Map.of("ItemUno", 100.0));
        pedido.agregarItem(itemMockUno);
        Map<String, Double> extras = Map.of("CostoEnvio", 50.0);
        Map<String, Double> resumenEsperadoFinal = Map.of(
                "ItemUno", 100.0,
                "CostoEnvio", 50.0
        );

        pedido.generarNotaDeCredito(extras);

        verify(gestorMock).hacerNotaDeCredito(resumenEsperadoFinal);
    }
}
