package ar.edu.unq.poo2.envio;

import ar.edu.unq.poo2.pedido.Pedido;

public interface Sucursal {
	public boolean hayStock(Pedido pedido);
}
