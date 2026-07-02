package ar.edu.unq.poo2.pedido.estado;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

import ar.edu.unq.poo2.item.Item;
import ar.edu.unq.poo2.notificaciones.ObservadorPedido;
import ar.edu.unq.poo2.pedido.Pedido;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EstadoConfirmadoTest{
    Pedido pedidoMock;
    Item itemMock;
    EstadoConfirmado estado;

    @BeforeEach
    void setUp(){
        pedidoMock = mock(Pedido.class);
        itemMock = mock(Item.class);

        estado = new EstadoConfirmado();
    }

    @Test
    void noPuedenAgregarseItemsEnEstadoConfirmado() {
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.verificarAgregarItem(pedidoMock, itemMock));
    }

    @Test
    void noPuedenQuitarseItemsEnEstadoConfirmado() {
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.verificarQuitarItem(pedidoMock, itemMock));
    }

    @Test
    void intentarTransicionarAEstadoConfirmadoDesdeEstadoConfirmadoNoCambiaEstadoActual(){
        estado.confirmar(pedidoMock);

        verify(pedidoMock, never()).setEstadoActual(any());
    }

    @Test
    void sePuedeTransicionarAEstadoCanceladoDesdeEstadoConfirmado(){
        estado.cancelar(pedidoMock);

        verify(pedidoMock).setEstadoActual(isA(EstadoCancelado.class));
    }

    @Test
    void transicionarAEstadoCanceladoDesdeEstadoConfirmadoReponeStock(){
        estado.cancelar(pedidoMock);

        verify(pedidoMock).reponerStock();
    }

    @Test
    void sePuedeTransicionarAEstadoEnPreparacionDesdeEstadoConfirmado(){
        estado.preparar(pedidoMock);

        verify(pedidoMock).setEstadoActual(isA(EstadoEnPreparacion.class));
    }

    @Test
    void noSePuedeTransicionarAEstadoEnviadoDesdeEstadoConfirmado(){
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.enviar(pedidoMock));
    }

    @Test
    void noSePuedeTransicionarAEstadoEntregadoDesdeEstadoConfirmado(){
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.entregar(pedidoMock));
    }

    @Test
    void alNotificarTransicionSeLlamaAAlConfirmarDelObservadorDado() {
        ObservadorPedido observadorMock = mock(ObservadorPedido.class);

        estado.notificarTransicion(pedidoMock, observadorMock);

        verify(observadorMock).alConfirmar(pedidoMock);
    }
}