package ar.edu.unq.poo2.pedido.estado;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

import ar.edu.unq.poo2.item.Item;
import ar.edu.unq.poo2.pedido.Pedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class EstadoEnviadoTest{
    Pedido pedido;
    EstadoEnviado estado;
    Item item;

    @BeforeEach
    void setUp(){
        pedido = mock(Pedido.class);
        item = mock(Item.class);
        estado = new EstadoEnviado();
    }

    @Test
    void noPuedenAgregarseItemsEnEstadoEnviado() {
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.verificarAgregarItem(pedido, item));
    }

    @Test
    void noPuedenQuitarseItemsEnEstadoEnviado() {
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.verificarQuitarItem(pedido, item));
    }

    @Test
    void noSePuedeTransicionarAEstadoConfirmadoDesdeEstadoEnviado(){
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.confirmar(pedido));
    }

    @Test
    void sePuedeTransicionarAEstadoCanceladoDesdeEstadoEnviado(){
        estado.cancelar(pedido);
        verify(pedido).setEstadoActual(isA(EstadoCancelado.class));
    }

    @Test
    void transicionarAEstadoCanceladoDesdeEstadoEnviadoNoReembolsaEnvio(){
        Map<String, Double> sinExtras = new HashMap<>();
        estado.cancelar(pedido);
        verify(pedido).generarNotaDeCredito(sinExtras);
    }

    @Test
    void noSePuedeTransicionarAEstadoEnPreparacionDesdeEstadoEnviado(){
        assertThrows(OperacionInvalidaParaEstadoException.class, () -> estado.preparar(pedido));
    }

    @Test
    void intentarTransicionarAEstadoEnviadoDesdeEstadoEnviadoNoCambiaEstadoActual(){
        estado.enviar(pedido);
        verify(pedido, never()).setEstadoActual(any());
    }


    @Test
    void sePuedeTransicionarAEstadoEntregadoDesdeEstadoEnviado(){
        estado.entregar(pedido);
        verify(pedido).setEstadoActual(isA(EstadoEntregado.class));
    }
}