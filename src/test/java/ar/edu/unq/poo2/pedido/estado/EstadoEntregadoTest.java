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
    Pedido pedido;
    EstadoEntregado estado;
    Item item;

    @BeforeEach
    void setUp(){
        pedido = mock(Pedido.class);
        item = mock(Item.class);
        estado = new EstadoEntregado();
    }

    @Test
    void noPuedenAgregarseItemsEnEstadoEntregado() {
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.verificarAgregarItem(pedido, item));
    }

    @Test
    void noPuedenQuitarseItemsEnEstadoEntregado() {
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.verificarQuitarItem(pedido, item));
    }

    @Test
    void noSePuedeTransicionarAEstadoConfirmadoDesdeEstadoEntregado(){
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.confirmar(pedido));
    }

    @Test
    void noSePuedeTransicionarAEstadoCanceladoDesdeEstadoEntregado(){
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.cancelar(pedido));
    }

    @Test
    void noSePuedeTransicionarAEstadoEnPreparacionDesdeEstadoEntregado(){
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.preparar(pedido));
    }

    @Test
    void noSePuedeTransicionarAEstadoEnviadoDesdeEstadoEntregado(){
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.enviar(pedido));
    }

    @Test
    void intentarTransicionarAEstadoEntregadoDesdeEstadoEntregadoNoCambiaEstadoActual(){
        estado.entregar(pedido);
        verify(pedido, never()).setEstadoActual(any());
    }

    @Test
    void alNotificarTransicionSeLlamaAAlEntregarDelObservadorDado() {
        ObservadorPedido observadorMock = mock(ObservadorPedido.class);
        estado.notificarTransicion(pedido, observadorMock);
        verify(observadorMock).alEntregar(pedido);
    }
}