package ar.edu.unq.poo2.notificaciones;

import ar.edu.unq.poo2.pedido.Cliente;

public class Cupon {

	private  String mailCliente;
    private  double porcentaje;

    public Cupon(String mailCliente, double porcentaje) {
        this.mailCliente = mailCliente;
        this.porcentaje = porcentaje;
    }
}
