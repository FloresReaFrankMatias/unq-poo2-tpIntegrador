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
    Pedido pedido;
    EstadoBorrador estado;
    Item item;

    @BeforeEach
    void setUp(){
        pedido = mock(Pedido.class);
        item = mock(Item.class);
        estado = new EstadoBorrador();
    }

    @Test
    void puedenAgregarseItemsEnEstadoBorrador() {
       assertDoesNotThrow(() -> estado.verificarAgregarItem(pedido, item));
    }

    @Test
    void puedenQuitarseItemsEnEstadoBorrador() {
        assertDoesNotThrow(() -> estado.verificarQuitarItem(pedido, item));
    }

    @Test
    void sePuedeTransicionarAEstadoConfirmadoDesdeEstadoBorrador(){
        when(pedido.tieneItems()).thenReturn(true);
        estado.confirmar(pedido);
        verify(pedido).setEstadoActual(isA(EstadoConfirmado.class));
    }

    @Test
    void noSePuedeTransicionarAEstadoConfirmadoDadoUnPedidoVacioDesdeEstadoBorrador(){
        when(pedido.tieneItems()).thenReturn(false);
        assertThrows(RuntimeException.class, () -> estado.confirmar(pedido));
    }

    @Test
    void transicionarAEstadoConfirmadoDesdeEstadoBorradorDescuentaStock(){
        when(pedido.tieneItems()).thenReturn(true);
        estado.confirmar(pedido);
        verify(pedido).descontarStock();
    }

    @Test
    void sePuedeTransicionarAEstadoCanceladoDesdeEstadoBorrador(){
        estado.cancelar(pedido);
        verify(pedido).setEstadoActual(isA(EstadoCancelado.class));
    }

    @Test
    void noSePuedeTransicionarAEstadoPreparadoDesdeEstadoBorrador(){
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.preparar(pedido));
    }

    @Test
    void noSePuedeTransicionarAEstadoEnviadoDesdeEstadoBorrador(){
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.enviar(pedido));
    }

    @Test
    void noSePuedeTransicionarAEstadoEntregadoDesdeEstadoBorrador(){
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.entregar(pedido));
    }
}
