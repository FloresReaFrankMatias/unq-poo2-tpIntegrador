package ar.edu.unq.poo2.pedido;

import ar.edu.unq.poo2.envio.Direccion;
import ar.edu.unq.poo2.envio.MetodoDeEnvio;
import ar.edu.unq.poo2.item.Item;
import ar.edu.unq.poo2.pago.MedioPago;
import ar.edu.unq.poo2.pedido.estado.EstadoBorrador;
import ar.edu.unq.poo2.pedido.estado.EstadoPedido;
import ar.edu.unq.poo2.pedido.notadecredito.GestorNotasDeCredito;
import ar.edu.unq.poo2.pedido.observadores.ObservadorPedido;

import java.util.*;

public class Pedido {
    private EstadoPedido estadoActual;
    private List<Item> contenido;
    private MetodoDeEnvio envio;
    private final Inventario inventario;
    private final GestorNotasDeCredito gestorNotasDeCredito;
    private MedioPago medioPago;

    private Cliente cliente;

    private final Set<ObservadorPedido> observadores;

    public Pedido(Inventario inventario, GestorNotasDeCredito gestorNotasDeCredito, MetodoDeEnvio envio, Set<ObservadorPedido> observadores){
        this.inventario = inventario;
        this.gestorNotasDeCredito = gestorNotasDeCredito;
        this.envio = envio;
        this.observadores = observadores;
        this.estadoActual = new EstadoBorrador();
        this.contenido = new ArrayList<>();
    }

    public void agregarItem(Item item){
        estadoActual.verificarAgregarItem(this, item);
        contenido.add(item);
    }

    public void quitarItem(Item item){
        estadoActual.verificarQuitarItem(this, item);
        contenido.remove(item);
    }

    public void confirmar(){
        estadoActual.confirmar(this);
        notificarObservadores();
    }

    public void cancelar(){
        estadoActual.cancelar(this);
        notificarObservadores();
    }

    public void preparar(){
        estadoActual.preparar(this);
        notificarObservadores();
    }

    public void enviar(){
        estadoActual.enviar(this);
        notificarObservadores();
    }

    public void entregar(){
        estadoActual.entregar(this);
        notificarObservadores();
    }

    private void notificarObservadores() {
        observadores.forEach(observador -> estadoActual.notificarTransicion(this, observador));
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

    public boolean tieneItems() {
        return !contenido.isEmpty();
    }

    public List<Item> getContenido() {
        return Collections.unmodifiableList(contenido); //Es inmutable para evitar que cualquiera pueda agregar o quitar items de un pedido.
    }

    public MetodoDeEnvio getEnvio() {
        return envio;
    }

    public void setEstadoActual(EstadoPedido estadoPedido){
        estadoActual = estadoPedido;
    }

    public MedioPago getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(MedioPago medioPago) {
        this.medioPago = medioPago;
    }
    public double getPesoTotal() {
		return contenido.stream().mapToInt(Item::getPeso).sum();
	}
    public Direccion getDireccionEntrega() {
		return cliente.getDireccion();
		}

	public Double valorTotalPedido() {
		return contenido.stream()
				        .mapToDouble(Item::getPrecio)
				        .sum();
	}
}
