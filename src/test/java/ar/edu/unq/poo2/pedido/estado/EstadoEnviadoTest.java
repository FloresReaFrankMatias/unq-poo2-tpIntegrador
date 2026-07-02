package ar.edu.unq.poo2.pedido.estado;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

import ar.edu.unq.poo2.item.Item;
import ar.edu.unq.poo2.notificaciones.ObservadorPedido;
import ar.edu.unq.poo2.pedido.Pedido;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class EstadoEnviadoTest{
    Pedido pedidoMock;
    Item itemMock;
    EstadoEnviado estado;

    @BeforeEach
    void setUp(){
        pedidoMock = mock(Pedido.class);
        itemMock = mock(Item.class);

        estado = new EstadoEnviado();
    }

    @Test
    void noPuedenAgregarseItemsEnEstadoEnviado() {
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.verificarAgregarItem(pedidoMock, itemMock));
    }

    @Test
    void noPuedenQuitarseItemsEnEstadoEnviado() {
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.verificarQuitarItem(pedidoMock, itemMock));
    }

    @Test
    void noSePuedeTransicionarAEstadoConfirmadoDesdeEstadoEnviado(){
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.confirmar(pedidoMock));
    }

    @Test
    void sePuedeTransicionarAEstadoCanceladoDesdeEstadoEnviado(){
        estado.cancelar(pedidoMock);

        verify(pedidoMock).setEstadoActual(isA(EstadoCancelado.class));
    }

    @Test
    void transicionarAEstadoCanceladoDesdeEstadoEnviadoNoReembolsaEnvio(){
        Map<String, Double> sinExtras = new HashMap<>();

        estado.cancelar(pedidoMock);

        verify(pedidoMock).generarNotaDeCredito(sinExtras);
    }

    @Test
    void noSePuedeTransicionarAEstadoEnPreparacionDesdeEstadoEnviado(){
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.preparar(pedidoMock));
    }

    @Test
    void intentarTransicionarAEstadoEnviadoDesdeEstadoEnviadoNoCambiaEstadoActual(){
        estado.enviar(pedidoMock);

        verify(pedidoMock, never()).setEstadoActual(any());
    }


    @Test
    void sePuedeTransicionarAEstadoEntregadoDesdeEstadoEnviado(){
        estado.entregar(pedidoMock);

        verify(pedidoMock).setEstadoActual(isA(EstadoEntregado.class));
    }

    @Test
    void alNotificarTransicionSeLlamaAAlEnviarDelObservadorDado() {
        ObservadorPedido observadorMock = mock(ObservadorPedido.class);

        estado.notificarTransicion(pedidoMock, observadorMock);

        verify(observadorMock).alEnviar(pedidoMock);
    }
}