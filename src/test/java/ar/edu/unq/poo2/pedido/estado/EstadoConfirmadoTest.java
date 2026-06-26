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
    Pedido pedido;
    EstadoConfirmado estado;
    Item item;

    @BeforeEach
    void setUp(){
        pedido = mock(Pedido.class);
        item = mock(Item.class);
        estado = new EstadoConfirmado();
    }

    @Test
    void noPuedenAgregarseItemsEnEstadoConfirmado() {
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.verificarAgregarItem(pedido, item));
    }

    @Test
    void noPuedenQuitarseItemsEnEstadoConfirmado() {
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.verificarQuitarItem(pedido, item));
    }

    @Test
    void intentarTransicionarAEstadoConfirmadoDesdeEstadoConfirmadoNoCambiaEstadoActual(){
        estado.confirmar(pedido);
        verify(pedido, never()).setEstadoActual(any());
    }

    @Test
    void sePuedeTransicionarAEstadoCanceladoDesdeEstadoConfirmado(){
        estado.cancelar(pedido);
        verify(pedido).setEstadoActual(isA(EstadoCancelado.class));
    }

    @Test
    void transicionarAEstadoCanceladoDesdeEstadoConfirmadoReponeStock(){
        estado.cancelar(pedido);
        verify(pedido).reponerStock();
    }

    @Test
    void sePuedeTransicionarAEstadoEnPreparacionDesdeEstadoConfirmado(){
        estado.preparar(pedido);
        verify(pedido).setEstadoActual(isA(EstadoEnPreparacion.class));
    }

    @Test
    void noSePuedeTransicionarAEstadoEnviadoDesdeEstadoConfirmado(){
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.enviar(pedido));
    }

    @Test
    void noSePuedeTransicionarAEstadoEntregadoDesdeEstadoConfirmado(){
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.entregar(pedido));
    }

    @Test
    void alNotificarTransicionSeLlamaAAlConfirmarDelObservadorDado() {
        ObservadorPedido observadorMock = mock(ObservadorPedido.class);
        estado.notificarTransicion(pedido, observadorMock,pedido.getEstadoAnterior());
        verify(observadorMock).alConfirmar(pedido,pedido.getEstadoActual(),pedido.getEstadoAnterior());
    }
}