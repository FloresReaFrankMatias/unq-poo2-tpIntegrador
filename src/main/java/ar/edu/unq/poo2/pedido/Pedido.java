package ar.edu.unq.poo2.pedido;

import ar.edu.unq.poo2.Metodo_Envio.MetodoDeEnvio;
import ar.edu.unq.poo2.item.Item;
import ar.edu.unq.poo2.pedido.estado.EstadoPedido;
import ar.edu.unq.poo2.pedido.estado.GestorNotasDeCredito;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pedido {
    private EstadoPedido estadoActual;
    private List<Item> contenidoDePedido;
    private MetodoDeEnvio envio;
    private final Inventario inventario;
    private final GestorNotasDeCredito gestorNotasDeCredito;

    public Pedido(Inventario inventario, GestorNotasDeCredito gestorNotasDeCredito){
        this.inventario = inventario;
        this.gestorNotasDeCredito = gestorNotasDeCredito;
    }

    public void agregarItem(Item item){
        estadoActual.verificarAgregarItem(this, item);
        contenidoDePedido.add(item);
    }

    public void quitarItem(Item item){
        estadoActual.verificarAgregarItem(this, item);
        contenidoDePedido.add(item);
    }

    public void confirmar(Inventario inventario){
        estadoActual.confirmar(this);
    }

    public void cancelar(Inventario inventario){
        estadoActual.cancelar(this);
    }

    public void preparar(){
        estadoActual.preparar(this);
    }

    public void enviar(){
        estadoActual.enviar(this);
    }

    public void entregar(){
        estadoActual.entregar(this);
    }

    public void generarNotaDeCredito(Map<String, Double> extras){
        Map<String, Double> resumen = getResumenDePedido();
        resumen.putAll(extras);
        gestorNotasDeCredito.hacerNotaDeCredito(getResumenDePedido());
    }

    private Map<String, Double> getResumenDePedido(){
        Map<String, Double> resumen = new HashMap<>();
        contenidoDePedido.forEach(item -> agregarItemAResumen(item, resumen));
        return resumen;
    }

    private void agregarItemAResumen(Item item, Map<String, Double> resumen){
        item.getResumenDePrecio().forEach((nombre, precio) -> resumen.merge(nombre, precio, Double::sum));
    }

    public Inventario getInventario() {
        return inventario;
    }

    public MetodoDeEnvio getEnvio() {
        return envio;
    }

    public void setEstadoActual(EstadoPedido estadoPedido){
        estadoActual = estadoPedido;
    }
}
