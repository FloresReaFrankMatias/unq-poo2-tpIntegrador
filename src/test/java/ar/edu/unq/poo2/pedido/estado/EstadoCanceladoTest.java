package ar.edu.unq.poo2.pedido.estado;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import ar.edu.unq.poo2.item.Item;
import ar.edu.unq.poo2.notificaciones.ObservadorPedido;
import ar.edu.unq.poo2.pedido.Pedido;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EstadoCanceladoTest{
    Pedido pedidoMock;
    Item itemMock;
    EstadoCancelado estado;

    @BeforeEach
    void setUp(){
        pedidoMock = mock(Pedido.class);
        itemMock = mock(Item.class);

        estado = new EstadoCancelado();
    }

    @Test
    void noPuedenAgregarseItemsEnEstadoCancelado() {
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.verificarAgregarItem(pedidoMock, itemMock));
    }

    @Test
    void noPuedenQuitarseItemsEnEstadoCancelado() {
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.verificarQuitarItem(pedidoMock, itemMock));
    }

    @Test
    void noSePuedeTransicionarAEstadoConfirmadoDesdeEstadoCancelado(){
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.confirmar(pedidoMock));
    }

    @Test
    void intentarTransicionarAEstadoCanceladoDesdeEstadoCanceladoNoCambiaEstadoActual(){
        estado.cancelar(pedidoMock);

        verify(pedidoMock, never()).setEstadoActual(any());
    }

    @Test
    void noSePuedeTransicionarAEstadoEnPreparacionDesdeEstadoCancelado(){
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.preparar(pedidoMock));
    }

    @Test
    void noSePuedeTransicionarAEstadoEnviadoDesdeEstadoCancelado(){
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.enviar(pedidoMock));
    }

    @Test
    void noSePuedeTransicionarAEstadoEntregadoDesdeEstadoCancelado(){
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.entregar(pedidoMock));
    }

    @Test
    void alNotificarTransicionSeLlamaAAlCancelarDelObservadorDado() {
        ObservadorPedido observadorMock = mock(ObservadorPedido.class);

        estado.notificarTransicion(pedidoMock, observadorMock);

        verify(observadorMock).alCancelar(pedidoMock);
     }
}