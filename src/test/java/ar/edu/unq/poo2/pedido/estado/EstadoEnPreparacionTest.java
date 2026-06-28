package ar.edu.unq.poo2.pedido.estado;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

import ar.edu.unq.poo2.envio.MetodoDeEnvio;
import ar.edu.unq.poo2.item.Item;
import ar.edu.unq.poo2.notificaciones.ObservadorPedido;
import ar.edu.unq.poo2.pedido.Pedido;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class EstadoEnPreparacionTest{
    Pedido pedido;
    EstadoEnPreparacion estado;
    Item item;
    MetodoDeEnvio envio;

    @BeforeEach
    void setUp(){
        pedido = mock(Pedido.class);
        item = mock(Item.class);
        estado = new EstadoEnPreparacion();
        envio = mock(MetodoDeEnvio.class);
        when(pedido.getEnvio()).thenReturn(envio);
    }

    @Test
    void noPuedenAgregarseItemsEnEstadoEnPreparacion() {
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.verificarAgregarItem(pedido, item));
    }

    @Test
    void noPuedenQuitarseItemsEnEstadoEnPreparacion() {
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.verificarQuitarItem(pedido, item));
    }

    @Test
    void noSePuedeTransicionarAEstadoConfirmadoDesdeEstadoEnPreparacion(){
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.confirmar(pedido));
    }

    @Test
    void sePuedeTransicionarAEstadoCanceladoDesdeEstadoEnPreparacion(){
        estado.cancelar(pedido);
        verify(pedido).setEstadoActual(isA(EstadoCancelado.class));
    }

    @Test
    void transicionarAEstadoCanceladoDesdeEstadoEnPreparacionReponeStock(){
        estado.cancelar(pedido);
        verify(pedido).reponerStock();
    }

    @Test
    void transicionarAEstadoCanceladoDesdeEstadoEnPreparacionReembolsaProductoYEnvio(){
        when(envio.calcularCosto(pedido)).thenReturn(500.0);
        Map<String, Double> extrasEsperados = new HashMap<>();
        extrasEsperados.put("Envió", 500.0);
        estado.cancelar(pedido);
        verify(pedido).generarNotaDeCredito(extrasEsperados);
    }

    @Test
    void intentarTransicionarAEstadoEnPreparacionDesdeEstadoEnPreparacionNoCambiaEstadoActual(){
        estado.preparar(pedido);
        verify(pedido, never()).setEstadoActual(any());
    }

    @Test
    void sePuedeTransicionarAEstadoEnviadoDesdeEstadoEnPreparacion(){
        estado.enviar(pedido);
        verify(pedido).setEstadoActual(isA(EstadoEnviado.class));
    }

    @Test
    void noSePuedeTransicionarAEstadoEntregadoDesdeEstadoEnPreparacion(){
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.entregar(pedido));
    }

    @Test
    void alNotificarTransicionSeLlamaAAlPrepararDelObservadorDado() {
        ObservadorPedido observadorMock = mock(ObservadorPedido.class);
        estado.notificarTransicion(pedido, observadorMock);
        verify(observadorMock).alPreparar(pedido);
    }
}