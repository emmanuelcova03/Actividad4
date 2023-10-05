import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Actividad4 {
    // Declaración de variables y estructura de datos
    private Map<String, String> contacts = new HashMap<>();
    private String filePath = "contactos.csv"; // Nombre del archivo de los contactos

    // Constructor
    public Actividad4() {
        load(); // Carga los contactos al inicio
    }

    // Método para listar contactos
    public void list() {
        System.out.println("Contactos:");
        for (Map.Entry<String, String> entry : contacts.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    // Método para crear un nuevo contacto
    public void create() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el número de teléfono: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Ingrese el nombre: ");
        String name = scanner.nextLine();

        // Agrega el nuevo contacto al Map y guarda los cambios en el archivo CSV
        contacts.put(phoneNumber, name);
        save();
        System.out.println("Contacto creado con éxito.");
    }

    // Método para eliminar un contacto
    public void delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el número de teléfono a eliminar: ");
        String phoneNumber = scanner.nextLine();

        // Verifica si el contacto existe en el Map y lo elimina si es así,
        // luego guarda los cambios en el archivo CSV
        if (contacts.containsKey(phoneNumber)) {
            contacts.remove(phoneNumber);
            save();
            System.out.println("Contacto eliminado con éxito.");
        } else {
            System.out.println("El contacto no existe.");
        }
    }

    // Método para cargar contactos desde el archivo CSV
    private void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    contacts.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo: " + e.getMessage());
        }
    }

    // Método para guardar contactos en el archivo CSV
    private void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, String> entry : contacts.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    // Método principal para ejecutar el programa
    public static void main(String[] args) {
        Actividad4 addressBook = new Actividad4();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Mostrar los contactos de la agenda");
            System.out.println("2. Crear un nuevo contacto");
            System.out.println("3. Borrar un contacto");
            System.out.println("4. Salir");
            System.out.print("Opción: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            // Switch para manejar las opciones del usuario
            switch (choice) {
                case 1:
                    addressBook.list();
                    break;
                case 2:
                    addressBook.create();
                    break;
                case 3:
                    addressBook.delete();
                    break;
                case 4:
                    System.out.println("¡Hasta luego!");
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }
}
