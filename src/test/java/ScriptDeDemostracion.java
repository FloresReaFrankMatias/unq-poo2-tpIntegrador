import ar.edu.unq.poo2.pedido.*;
import ar.edu.unq.poo2.envio.*;
import ar.edu.unq.poo2.pago.*;
import ar.edu.unq.poo2.pago.api.*;
import ar.edu.unq.poo2.pedido.notadecredito.*;
import ar.edu.unq.poo2.item.*;
import ar.edu.unq.poo2.notificaciones.*;
import ar.edu.unq.poo2.venta.*;
import ar.edu.unq.poo2.reporte.*;
import ar.edu.unq.poo2.reporte.formato.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;

public class ScriptDeDemostracion {
    public static void main(String[] args) {
        // 1. Se configura inventario y stock
        Inventario inventario = new Inventario();
        inventario.incrementarStock(Map.of("SKU-123", 10));

        // Instanciamos al gestor de notas, el historial de ventas y cliente y, además, simulamos Direccion
        GestorNotasDeCredito gestorNotas = new GestorNotasDeCredito();
        HistorialDeVentas historial = new HistorialDeVentas();
        Direccion direccion = new Direccion() {};
        Cliente cliente = new Cliente("cliente@unq.edu.ar", direccion);

        // 3. Instanciamos estrategias de envío y de pago (simulando las API externas).
        CorreoArgentino correoMock = (peso, dest) -> 1500f;
        MetodoDeEnvio envio = new EnvioEstandar(correoMock);
        APITarjetaCredito apiTarjetaMock = new APITarjetaCredito() {
            public boolean validarTarjeta(String n, String c, String f) { return true; }
            public void preAutorizarFondos() {}
            public void ejecutarTransaccion() {}
        };
        MedioPago pago = new TarjetaCredito("4145...", "123", "12/28", apiTarjetaMock);

        // 4. Creamos el pedido y le asignamos el metodo de pago.
        Pedido pedido = new Pedido(inventario, gestorNotas, envio, new HashSet<>(), cliente);
        pedido.setMedioPago(pago);

        // 5. Instanciamos y suscribimos observadores, además de simular MailSender
        MailSender mailMock = (dest, tit, msj, adj) -> {};
        ObservadorPedido notificadorMail = new NotificadorEmail(mailMock);
        ObservadorPedido notificadorVentas = new ObservadorHistorialDeVentas(historial);

        pedido.suscribir(notificadorMail);
        pedido.suscribir(notificadorVentas);

        // 6. Instanciamos un Producto concreto con todos los parámetros y le agregamos un atributo dinamico.
        Producto producto = new Producto(
                "SKU-123",               // sku
                "Auriculares",           // nombre
                "Auriculares in-ear",    // descripcion
                1,                       // peso
                "Sony",                  // marca
                Categoria.ELECTRONICA,   // categoria
                5000.0,                  // precioBase
                0.0                      // descuento
        );
        producto.setAtributoDinamico("Color", "Negro");

        // 7. Se ejecuta el ciclo de vida completo de un pedido
        pedido.agregarItem(producto);
        pedido.confirmar();
        pedido.preparar();
        pedido.enviar();
        pedido.entregar();

        LocalDate hoy = LocalDate.now();

        Reporte reporteMasVendidos = new ReporteDeProductosMasVendidos(hoy.minusDays(1), hoy.plusDays(1), historial);
        FormatoTXT formatoTxt = new FormatoTXT();

        reporteMasVendidos.aceptar(formatoTxt);
        System.out.print(formatoTxt.getResultadoGenerado());
    }
}