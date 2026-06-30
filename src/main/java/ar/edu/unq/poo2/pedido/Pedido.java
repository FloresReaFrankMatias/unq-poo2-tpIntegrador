package ar.edu.unq.poo2.pedido;

import ar.edu.unq.poo2.envio.Direccion;
import ar.edu.unq.poo2.envio.MetodoDeEnvio;
import ar.edu.unq.poo2.item.Item;
import ar.edu.unq.poo2.notificaciones.ObservadorPedido;
import ar.edu.unq.poo2.pago.MedioPago;
import ar.edu.unq.poo2.pedido.estado.EstadoBorrador;
import ar.edu.unq.poo2.pedido.estado.EstadoPedido;
import ar.edu.unq.poo2.pedido.notadecredito.GestorNotasDeCredito;

import java.util.*;

public class Pedido {
    private EstadoPedido estadoActual = new EstadoBorrador();
    private ContenidoPedido contenido = new ContenidoPedido();
    private MetodoDeEnvio envio;
    private final Inventario inventario;
    private final GestorNotasDeCredito gestorNotasDeCredito;
    private MedioPago medioPago;
    private Cliente cliente;
    private final Set<ObservadorPedido> observadores;

    public Pedido(Inventario inventario, GestorNotasDeCredito gestorNotasDeCredito, MetodoDeEnvio envio, Set<ObservadorPedido> observadores,Cliente cliente){
        this.inventario = inventario;
        this.gestorNotasDeCredito = gestorNotasDeCredito;
        this.envio = envio;
        this.observadores = observadores;
        this.cliente=cliente;
    }

    public void agregarItem(Item item){
        estadoActual.verificarAgregarItem(this, item);
        contenido.agregarItem(item);
    }

    public void quitarItem(Item item){
        estadoActual.verificarQuitarItem(this, item);
        contenido.quitarItem(item);
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

    public void descontarStock() {
        this.inventario.decrementarStock(contenido.getResumenDeSkus());
    }

    public void reponerStock() {
        this.inventario.incrementarStock(contenido.getResumenDeSkus());
    }

    public void generarNotaDeCredito(Map<String, Double> extras){
        Map<String, Double> resumen = contenido.getResumenDePrecios();
        resumen.putAll(extras);
        gestorNotasDeCredito.hacerNotaDeCredito(resumen);
    }

    public boolean tieneItems() {
        return contenido.tieneItems();
    }
    
    //-------- NOTIFICACIONES --------------------------------

    public void notificarObservadores() {
        observadores.forEach(observador -> estadoActual.notificarTransicion(this, observador));
    }

    public void suscribir(ObservadorPedido obs) {
    	this.observadores.add(obs);
    }

    public void desuscribir(ObservadorPedido obs) {
    	this.observadores.remove(obs);
    }

    //-------------- 	GETTERS ------------------------------------
    public EstadoPedido getEstadoActual() {
        return estadoActual;
    }
    public double getPesoTotal() {
        return contenido.getPesoTotal();
    }

    public Direccion getDireccionEntrega() {
        return cliente.getDireccion();
    }

    public Double getValorTotal() {
        return contenido.getValorTotal();
    }

    public String getClienteEmail() {
        return cliente.getEmail();
    }
    public MedioPago getMedioPago() {
        return medioPago;
    }

    public List<Item> getContenido() {
        return contenido.getItems();
    }

    public MetodoDeEnvio getEnvio() {
        return envio;
    }
    public double getCostoEnvio() {
        return this.getEnvio().calcularCosto(this);
    }


    //------------------  SETTERS -------------------------------

    public void setEstadoActual(EstadoPedido estado) {
        estadoActual= estado;
    }
    public void setMedioPago(MedioPago medioDePago) {
        this.medioPago = medioDePago;
    }
    public void setMetodoEnvio(MetodoDeEnvio envio) {
        this.envio = envio;
    }

}
