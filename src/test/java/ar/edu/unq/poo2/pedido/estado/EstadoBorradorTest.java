package ar.edu.unq.poo2.pedido.estado;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

import ar.edu.unq.poo2.item.Item;
import ar.edu.unq.poo2.pedido.Pedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EstadoBorradorTest{
    Pedido pedidoMock;
    Item itemMock;
    EstadoBorrador estado;

    @BeforeEach
    void setUp(){
        pedidoMock = mock(Pedido.class);
        itemMock = mock(Item.class);

        estado = new EstadoBorrador();
    }

    @Test
    void puedenAgregarseItemsEnEstadoBorrador() {
       assertDoesNotThrow(() -> estado.verificarAgregarItem(pedidoMock, itemMock));
    }

    @Test
    void puedenQuitarseItemsEnEstadoBorrador() {
        assertDoesNotThrow(() -> estado.verificarQuitarItem(pedidoMock, itemMock));
    }

    @Test
    void sePuedeTransicionarAEstadoConfirmadoDesdeEstadoBorrador(){
        when(pedidoMock.tieneItems()).thenReturn(true);

        estado.confirmar(pedidoMock);

        verify(pedidoMock).setEstadoActual(isA(EstadoConfirmado.class));
    }

    @Test
    void noSePuedeTransicionarAEstadoConfirmadoDadoUnPedidoVacioDesdeEstadoBorrador(){
        when(pedidoMock.tieneItems()).thenReturn(false);

        assertThrows(RuntimeException.class, () -> estado.confirmar(pedidoMock));
    }

    @Test
    void transicionarAEstadoConfirmadoDesdeEstadoBorradorDescuentaStock(){
        when(pedidoMock.tieneItems()).thenReturn(true);

        estado.confirmar(pedidoMock);

        verify(pedidoMock).descontarStock();
    }

    @Test
    void sePuedeTransicionarAEstadoCanceladoDesdeEstadoBorrador(){
        estado.cancelar(pedidoMock);

        verify(pedidoMock).setEstadoActual(isA(EstadoCancelado.class));
    }

    @Test
    void noSePuedeTransicionarAEstadoPreparadoDesdeEstadoBorrador(){
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.preparar(pedidoMock));
    }

    @Test
    void noSePuedeTransicionarAEstadoEnviadoDesdeEstadoBorrador(){
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.enviar(pedidoMock));
    }

    @Test
    void noSePuedeTransicionarAEstadoEntregadoDesdeEstadoBorrador(){
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.entregar(pedidoMock));
    }
}
