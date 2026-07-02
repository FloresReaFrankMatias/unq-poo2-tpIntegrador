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
    Pedido pedidoMock;
    Item itemMock;
    MetodoDeEnvio envioMock;
    EstadoEnPreparacion estado;

    @BeforeEach
    void setUp(){
        pedidoMock = mock(Pedido.class);
        itemMock = mock(Item.class);
        envioMock = mock(MetodoDeEnvio.class);

        when(pedidoMock.getEnvio()).thenReturn(envioMock);
        estado = new EstadoEnPreparacion();
    }

    @Test
    void noPuedenAgregarseItemsEnEstadoEnPreparacion() {
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.verificarAgregarItem(pedidoMock, itemMock));
    }

    @Test
    void noPuedenQuitarseItemsEnEstadoEnPreparacion() {
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.verificarQuitarItem(pedidoMock, itemMock));
    }

    @Test
    void noSePuedeTransicionarAEstadoConfirmadoDesdeEstadoEnPreparacion(){
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.confirmar(pedidoMock));
    }

    @Test
    void sePuedeTransicionarAEstadoCanceladoDesdeEstadoEnPreparacion(){
        estado.cancelar(pedidoMock);

        verify(pedidoMock).setEstadoActual(isA(EstadoCancelado.class));
    }

    @Test
    void transicionarAEstadoCanceladoDesdeEstadoEnPreparacionReponeStock(){
        estado.cancelar(pedidoMock);

        verify(pedidoMock).reponerStock();
    }

    @Test
    void transicionarAEstadoCanceladoDesdeEstadoEnPreparacionReembolsaProductoYEnvio(){
        when(pedidoMock.getCostoEnvio()).thenReturn(500.0);
        Map<String, Double> extrasEsperados = new HashMap<>();
        extrasEsperados.put("Envió", 500.0);

        estado.cancelar(pedidoMock);

        verify(pedidoMock).generarNotaDeCredito(extrasEsperados);
    }

    @Test
    void intentarTransicionarAEstadoEnPreparacionDesdeEstadoEnPreparacionNoCambiaEstadoActual(){
        estado.preparar(pedidoMock);

        verify(pedidoMock, never()).setEstadoActual(any());
    }

    @Test
    void sePuedeTransicionarAEstadoEnviadoDesdeEstadoEnPreparacion(){
        estado.enviar(pedidoMock);
        verify(pedidoMock).setEstadoActual(isA(EstadoEnviado.class));
    }

    @Test
    void noSePuedeTransicionarAEstadoEntregadoDesdeEstadoEnPreparacion(){
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.entregar(pedidoMock));
    }

    @Test
    void alNotificarTransicionNoSeEnviaMensajeAlObservadorDado() {
        ObservadorPedido observadorMock = mock(ObservadorPedido.class);

        estado.notificarTransicion(pedidoMock, observadorMock);

        verifyNoInteractions(observadorMock);
    }
}