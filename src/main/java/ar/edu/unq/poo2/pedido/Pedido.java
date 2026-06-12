package ar.edu.unq.poo2.pedido;

import ar.edu.unq.poo2.Metodo_Envio.MetodoDeEnvio;
import ar.edu.unq.poo2.item.Item;
import ar.edu.unq.poo2.pedido.estado.EstadoBorrador;
import ar.edu.unq.poo2.pedido.estado.EstadoPedido;
import ar.edu.unq.poo2.pedido.estado.GestorNotasDeCredito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pedido {
    private EstadoPedido estadoActual;
    private List<Item> contenido;
    private MetodoDeEnvio envio;
    private final Inventario inventario;
    private final GestorNotasDeCredito gestorNotasDeCredito;

    public Pedido(Inventario inventario, GestorNotasDeCredito gestorNotasDeCredito, MetodoDeEnvio envio){
        this.inventario = inventario;
        this.gestorNotasDeCredito = gestorNotasDeCredito;
        this.envio = envio;
        this.estadoActual = new EstadoBorrador();
        this.contenido = new ArrayList<>();
    }

    public void agregarItem(Item item){
        estadoActual.verificarAgregarItem(this, item);
        contenido.add(item);
    }

    public void quitarItem(Item item){
        estadoActual.verificarQuitarItem(this, item);
        contenido.add(item);
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

    public void descontarStock() {
        this.inventario.decrementarStock(this.getResumenDeSkus());
    }

    public void reponerStock() {
        this.inventario.incrementarStock(this.getResumenDeSkus());
    }

    private Map<String, Integer> getResumenDeSkus() {
        Map<String, Integer> resumen = new HashMap<>();
        contenido.forEach(item -> agregarItemAResumenDeSkus(item, resumen));
        return resumen;
    }

    private void agregarItemAResumenDeSkus(Item item, Map<String, Integer> resumen){
        item.getResumenDeSku().forEach((sku, cantidad) -> resumen.merge(sku, cantidad, Integer::sum));
    }

    public void generarNotaDeCredito(Map<String, Double> extras){
        Map<String, Double> resumen = getResumenDePrecios();
        resumen.putAll(extras);
        gestorNotasDeCredito.hacerNotaDeCredito(resumen);
    }

    private Map<String, Double> getResumenDePrecios(){
        Map<String, Double> resumen = new HashMap<>();
        contenido.forEach(item -> agregarItemAResumenDePrecios(item, resumen));
        return resumen;
    }

    private void agregarItemAResumenDePrecios(Item item, Map<String, Double> resumen){
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
