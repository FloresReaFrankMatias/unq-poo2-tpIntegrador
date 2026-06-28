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
    Pedido pedido;
    EstadoCancelado estado;
    Item item;

    @BeforeEach
    void setUp(){
        pedido = mock(Pedido.class);
        item = mock(Item.class);
        estado = new EstadoCancelado();
    }

    @Test
    void noPuedenAgregarseItemsEnEstadoCancelado() {
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.verificarAgregarItem(pedido, item));
    }

    @Test
    void noPuedenQuitarseItemsEnEstadoCancelado() {
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.verificarQuitarItem(pedido, item));
    }

    @Test
    void noSePuedeTransicionarAEstadoConfirmadoDesdeEstadoCancelado(){
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.confirmar(pedido));
    }

    @Test
    void intentarTransicionarAEstadoCanceladoDesdeEstadoCanceladoNoCambiaEstadoActual(){
        estado.cancelar(pedido);
        verify(pedido, never()).setEstadoActual(any());
    }

    @Test
    void noSePuedeTransicionarAEstadoEnPreparacionDesdeEstadoCancelado(){
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.preparar(pedido));
    }

    @Test
    void noSePuedeTransicionarAEstadoEnviadoDesdeEstadoCancelado(){
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.enviar(pedido));
    }

    @Test
    void noSePuedeTransicionarAEstadoEntregadoDesdeEstadoCancelado(){
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.entregar(pedido));
    }

    @Test
    void alNotificarTransicionSeLlamaAAlCancelarDelObservadorDado() {
        ObservadorPedido observadorMock = mock(ObservadorPedido.class);
        estado.notificarTransicion(pedido, observadorMock);
        verify(observadorMock).alCancelar(pedido);
     }
}