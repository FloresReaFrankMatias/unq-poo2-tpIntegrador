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
    void unPedidoSeCreaConContenidoVacio() {
        Pedido nuevoPedido = new Pedido(inventarioMock, gestorMock, envioMock, Set.of(observadorMock));
        assertFalse(nuevoPedido.tieneItems());
    }

    @Test
    void unPedidoAgregaUnItem() {
        pedido.agregarItem(itemMockUno);
        assertTrue(pedido.getContenido().contains(itemMockUno));
    }

    @Test
    void unPedidoSacaUnItem() {
        pedido.agregarItem(itemMockUno);
        pedido.quitarItem(itemMockUno);
        assertFalse(pedido.getContenido().contains(itemMockUno));
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
    public void alDescontarStockSeEnviaElResumenEsperadoAlInventario() {
        pedido.agregarItem(itemMockUno);
        pedido.agregarItem(itemMockDos);
        Map<String, Integer> resumenEsperadoItemUno = Map.of("ItemUno", 2);
        Map<String, Integer> resumenEsperadoItemDos = Map.of("ItemDos", 1);
        Map<String, Integer> resumenEsperadoCombinado = new HashMap<>(resumenEsperadoItemUno);
        resumenEsperadoCombinado.putAll(resumenEsperadoItemDos);when(itemMockUno.getResumenDeSku()).thenReturn(resumenEsperadoItemUno);
        when(itemMockDos.getResumenDeSku()).thenReturn(resumenEsperadoItemDos);
        pedido.descontarStock();
        verify(inventarioMock).decrementarStock(resumenEsperadoCombinado);
    }

    @Test
    public void alReponerStockSeLlamaAlInventario(){
        pedido.reponerStock();
        verify(inventarioMock).incrementarStock(anyMap());
    }

    @Test
    public void alReponerStockSeEnviaElResumenEsperadoAlInventario() {
        pedido.agregarItem(itemMockUno);
        pedido.agregarItem(itemMockDos);
        Map<String, Integer> resumenEsperadoItemUno = Map.of("ItemUno", 2);
        Map<String, Integer> resumenEsperadoItemDos = Map.of("ItemDos", 1);
        Map<String, Integer> resumenEsperadoCombinado = new HashMap<>(resumenEsperadoItemUno);
        resumenEsperadoCombinado.putAll(resumenEsperadoItemDos);when(itemMockUno.getResumenDeSku()).thenReturn(resumenEsperadoItemUno);
        when(itemMockDos.getResumenDeSku()).thenReturn(resumenEsperadoItemDos);
        pedido.reponerStock();
        verify(inventarioMock).incrementarStock(resumenEsperadoCombinado);
    }

    @Test
    public void alGenerarNotaDeCreditoSeLlamaAlGestor(){
        pedido.generarNotaDeCredito(new HashMap<>());
        verify(gestorMock).hacerNotaDeCredito(anyMap());
    }

    @Test
    public void alGenerarNotaDeCreditoSeEnviaElResumenEsperadoAlGestor() {
        pedido.agregarItem(itemMockUno);
        pedido.agregarItem(itemMockDos);
        Map<String, Double> resumenEsperadoItemUno = Map.of("ItemUno", 2.0);
        Map<String, Double> resumenEsperadoItemDos = Map.of("ItemDos", 1.0);
        Map<String, Double> resumenEsperadoCombinado = new HashMap<>(resumenEsperadoItemUno);
        resumenEsperadoCombinado.putAll(resumenEsperadoItemDos);
        when(itemMockUno.getResumenDePrecio()).thenReturn(resumenEsperadoItemUno);
        when(itemMockDos.getResumenDePrecio()).thenReturn(resumenEsperadoItemDos);
        pedido.generarNotaDeCredito(new HashMap<>());
        verify(gestorMock).hacerNotaDeCredito(resumenEsperadoCombinado);
    }

    @Test
    public void alGenerarNotaDeCreditoSeEnviaElResumenConExtras() {
        pedido.agregarItem(itemMockUno);
        Map<String, Double> resumenEsperadoItem = Map.of("ItemUno", 2.0);
        Map<String, Double> extras = Map.of("ItemDos", 1.0);
        Map<String, Double> resumenEsperadoCombinado = new HashMap<>(resumenEsperadoItem);
        resumenEsperadoCombinado.putAll(extras);
        when(itemMockUno.getResumenDePrecio()).thenReturn(resumenEsperadoItem);
        pedido.generarNotaDeCredito(extras);
        verify(gestorMock).hacerNotaDeCredito(resumenEsperadoCombinado);
    }

    @Test
    void unPedidoDevuelveSuContenidoInmutableCuandoSeLoPiden() {
        pedido.agregarItem(itemMockUno);
        List<Item> contenido = pedido.getContenido();
        assertThrows(UnsupportedOperationException.class, () -> contenido.add(itemMockDos));
        assertThrows(UnsupportedOperationException.class, () -> contenido.remove(itemMockUno));
    }
}
