
import java.util.ArrayList;
import java.util.Scanner;

// Clase principal del programa
public class Main {
    // Lista que almacena objetos de tipo Articulo
    static ArrayList<Articulo> lista = new ArrayList<>();
    static int contadorProductos = 0; // Contador para asignar IDs únicos a los artículos
    // Scanner para entrada de datos por consola
    static Scanner sc = new Scanner(System.in);

    // Método principal
    public static void main(String[] args) {
        int opcion;
        // Bucle principal del programa con menú interactivo
        do {
            // crear un menu grfico con barras para dos
            System.out.println("===================================");
            System.out.println("         MENÚ PRINCIPAL           ");
            System.out.println("===================================");
            System.out.println("Bienvenido al sistema de gestión de artículos");
            System.out.println("===================================");
            System.out.println("Seleccione una opción:");
            // Mostrar opciones del menú
            System.out.println("1. Crear artículo");
            System.out.println("2. Listar artículos");
            System.out.println("3. Modificar artículo");
            System.out.println("4. Eliminar artículo");
            System.out.println("5. Nombre del Artículo");
            System.out.println("6. Cargar pedido");
            System.out.println("0. Salir");

            System.out.println("===================================");
            System.out.println("===================================");
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt(); // Leer opción del usuario
            sc.nextLine(); // Limpiar buffer del scanner

            // Estructura switch para manejar las opciones
            switch (opcion) {
                case 1 -> crearArticulo();
                case 2 -> listarArticulos();
                case 3 -> modificarArticulo();
                case 4 -> eliminarArticulo();
                case 5 -> devolverNombreArticulo();
                case 6 -> crearPedido();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida");
            }

        } while (opcion != 0); // Repetir hasta que el usuario elija salir
    }

    // Método para crear un nuevo artículo
    public static void crearArticulo() {
        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // Leer ID

        // Validar que el ID no exista ya en la lista
        boolean idExistente = false;
        for (Articulo art : lista) {
            if (art.getId() == id) {
                idExistente = true;
                break;
            }
        }

        if (idExistente) {
            System.out.println("Ya existe un artículo con ese ID. Por favor, utilice otro ID.");
            return; // Salir del método sin crear el artículo
        }

        System.out.print("Nombre: ");
        String nombre = sc.nextLine(); // Leer nombre

        //Valida que se haya ingresado un nombre
        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return; // Salir del método sin crear el artículo
        }
        
        System.out.print("Precio: ");
        double precio = sc.nextDouble(); // Leer precio

        // Validar que se ingrese un precio positivo
        if (precio <= 0) {
            System.out.println("El precio debe ser mayor que cero.");
            return; // Salir del método sin crear el artículo
        }

        // Crear un nuevo objeto Articulo y agregarlo a la lista
        Articulo nuevo = new Articulo(id, nombre, precio);
        lista.add(nuevo);
        System.out.println("Artículo agregado.");
    }

    // Método para mostrar todos los artículos de la lista
    public static void listarArticulos() {
        if (lista.isEmpty()) {
            System.out.println("No hay artículos cargados.");
        } else {
            for (Articulo a : lista) {
                // a.mostrar(); // Llamada polimórfica al método mostrar()
                a.mostrar(); // Mostrar información del artículo
            }
        }
    }

    // Método para modificar un artículo existente
    public static void modificarArticulo() {
        System.out.print("ID del artículo a modificar: ");
        int id = sc.nextInt();
        for (Articulo articulo : lista) {
            if (articulo.getId() == id) {
                sc.nextLine();
                System.out.print("Nuevo nombre: ");
                articulo.setNombre(sc.nextLine()); // Usar setter para cambiar nombre
                System.out.print("Nuevo precio: ");
                articulo.setPrecio(sc.nextDouble());
                // Usar setter para cambiar precio
                System.out.println("Artículo actualizado.");
                return;
            }
        }
        System.out.println("Artículo no encontrado.");
    }

    // Método para eliminar un artículo por ID
    public static void eliminarArticulo() {
        System.out.print("ID del artículo a eliminar: ");
        int id = sc.nextInt();
        // Usamos removeIf con expresión lambda para eliminar por ID
        lista.removeIf(a -> a.getId() == id);
        System.out.println("Artículo eliminado si existía.");
    }

    // method para devolver el nombre del artículo por su id
    // Método para modificar un artículo existente
    public static void devolverNombreArticulo() {
        System.out.print("ID del artículo para la consulta: ");
        int id = sc.nextInt();
        for (Articulo articulo : lista) {
            if (articulo.getId() == id) {
                System.out.println("Nombre del artículo: " + articulo.getNombre());
                return;
            }
        }
        System.out.println("Artículo no encontrado.");
    }

    // Método para crear un pedido
    public static void crearPedido() {
        // Implementar la creación de un pedido
        System.out.print("ID del pedido: ");
        int idPedido = sc.nextInt();
        sc.nextLine(); // Limpiar buffer
        System.out.print("Nombre del cliente: ");
        String nombreCliente = sc.nextLine();
        System.out.print("Dirección: ");
        String direccion = sc.nextLine();
        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();
        System.out.print("Fecha: ");
        String fecha = sc.nextLine();
        System.out.print("Total: ");
        double total = sc.nextDouble();

        // Mostrar lista de artículos para seleccionar uno
        listarArticulos();
        System.out.print("Seleccione el ID del artículo para el pedido: ");
        int idArticulo = sc.nextInt();

        // Buscar el artículo por ID
        Articulo articuloSeleccionado = null;
        for (Articulo articulo : lista) {
            if (articulo.getId() == idArticulo) {
                articuloSeleccionado = articulo;
                break;
            }
        }

        if (articuloSeleccionado != null) {
            // Crear un nuevo objeto Pedido y mostrarlo
            Pedido nuevoPedido = new Pedido(idPedido, nombreCliente, direccion, telefono, fecha, total,
                    articuloSeleccionado);
            nuevoPedido.mostrarPedido();
            System.out.println("Pedido creado.");
        } else {
            System.out.println("Artículo no encontrado.");
        }
    }
}
