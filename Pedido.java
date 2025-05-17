public class Pedido {
    
    // Atributos privados para aplicar el principio de encaps
    private int idPedido;
    private String nombreCliente;
    private String direccion;
    private String telefono;
    private String fecha;
    private double total;
    private Articulo articulo; // Relación con la clase Articulo

    // Constructor para inicializar el objeto Pedido
    public Pedido(int idPedido, String nombreCliente, String direccion, String telefono, String fecha, double total, Articulo articulo) {
        this.idPedido = idPedido;
        this.nombreCliente = nombreCliente;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fecha = fecha;
        this.total = total;
        this.articulo = articulo; // Asignar el objeto Articulo al pedido
    }

    public int getIdPedido() {
        return idPedido;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void mostrarPedido() {
        // Método para mostrar la información del pedido
        System.out.println("ID Pedido: " + idPedido + " | Cliente: " + nombreCliente + " | Dirección: " + direccion + " | Teléfono: " + telefono + " | Fecha: " + fecha + " | Total: $" + total);
        articulo.mostrar(); // Mostrar información del artículo asociado al pedido
    }

}
