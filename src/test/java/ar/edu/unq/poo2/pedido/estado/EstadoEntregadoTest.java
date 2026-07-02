package ar.edu.unq.poo2.pedido.estado;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import ar.edu.unq.poo2.item.Item;
import ar.edu.unq.poo2.notificaciones.ObservadorPedido;
import ar.edu.unq.poo2.pedido.Pedido;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EstadoEntregadoTest{
    Pedido pedidoMock;
    Item itemMock;
    EstadoEntregado estado;

    @BeforeEach
    void setUp(){
        pedidoMock = mock(Pedido.class);
        itemMock = mock(Item.class);

        estado = new EstadoEntregado();
    }

    @Test
    void noPuedenAgregarseItemsEnEstadoEntregado() {
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.verificarAgregarItem(pedidoMock, itemMock));
    }

    @Test
    void noPuedenQuitarseItemsEnEstadoEntregado() {
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.verificarQuitarItem(pedidoMock, itemMock));
    }

    @Test
    void noSePuedeTransicionarAEstadoConfirmadoDesdeEstadoEntregado(){
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.confirmar(pedidoMock));
    }

    @Test
    void noSePuedeTransicionarAEstadoCanceladoDesdeEstadoEntregado(){
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.cancelar(pedidoMock));
    }

    @Test
    void noSePuedeTransicionarAEstadoEnPreparacionDesdeEstadoEntregado(){
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.preparar(pedidoMock));
    }

    @Test
    void noSePuedeTransicionarAEstadoEnviadoDesdeEstadoEntregado(){
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.enviar(pedidoMock));
    }

    @Test
    void intentarTransicionarAEstadoEntregadoDesdeEstadoEntregadoNoCambiaEstadoActual(){
        estado.entregar(pedidoMock);

        verify(pedidoMock, never()).setEstadoActual(any());
    }

    @Test
    void alNotificarTransicionSeLlamaAAlEntregarDelObservadorDado() {
        ObservadorPedido observadorMock = mock(ObservadorPedido.class);

        estado.notificarTransicion(pedidoMock, observadorMock);

        verify(observadorMock).alEntregar(pedidoMock);
    }
}