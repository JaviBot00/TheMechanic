package view;

import controller.MainController;

import java.util.Scanner;

public class MainTaller {

    private static MainController mainController;

    public static void main(String[] args) {
        System.out.println("Bienvenido al sistema de gestión del taller.");

        mainController = MainController.getInstance();
        System.out.println("MainController inicializado: " + (mainController != null));

        boolean onGoing = true;

        do {
            System.out.println(showMenu());
            int option = getOptionMenu();
            if (option != -1) {
                realizarOpcion(option);
            } else {
                onGoing = false;
            }
        } while (onGoing);
    }

    public static String showMenu() {
        String s = "";
        s += "Super Taller\n";
        s += "-------------\n";
        s += "1. Registrar clientes\n";
        s += "2. Registrar mecánicos\n";
        s += "3. Listar clientes ordenados\n";
        s += "4. Listar clientes por primer apellidos\n";
        s += "5. Listar mecánicos ordenados\n";
        s += "6. Listar personas\n";
        s += "-1. Para salir\n";
        return s;
    }

    public static void realizarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                registerClient();
                break;
            case 2:
                registrarMecanico();
                break;
            case 3:
                listClients();
                break;
            case 4:
                findClientsBySurname();
                break;
            case 5:
                listarMecanicos();
                break;
            case 6:
                listarPersonas();
                break;
            default:
                break;
        }
    }

    public static void registerClient() { // se necesita "name;surname1;surname2;nif;email;telephone;clientCode"
        String person = registerPerson();
        String cc = String.valueOf(readInt("Client Code: "));
        String csvCliente = person + ";" + cc;
        checkRegistration(mainController.getClientController().createClient(csvCliente));
    }

    public static void registrarMecanico() { // se necesita
                                             // "name;surname1;surname2;nif;email;telephone;registrationDate;especiality"
        String person = registerPerson();
        String rD = requestData("Registration Date: ");
        String espe = requestData("Especiality: ");
        String csvMecanico = person + ";" + rD + ";" + espe;
        checkRegistration(mainController.getMechanicController().createMechanic(csvMecanico));
    }

    private static void checkRegistration(boolean b) {
        if (b) {
            System.out.println("\nSuccessfully Registered\n");
        } else {
            System.out.println("\nError to Keep Registered\n");
        }
    }

    private static String registerPerson() {
        String name = requestData("Name: ");
        String sN1 = requestData("Surname1: ");
        String sN2 = requestData("Surname2: ");
        String nif = requestData("NIF: ");
        String email = requestData("Email: ");
        String tel = requestData("Telephone: ");
        return name + ";" + sN1 + ";" + sN2 + ";" + nif + ";" + email + ";" + tel;
    }

    public static void findClientByNif() { // return csv
        String nif = requestData("NIF to look for: ");
        String clientData = mainController.getClientController().findClientByNif(nif);
        imprimirDatos(clientData);
    }

    public static void findClientsBySurname() { // return csv
        String surname = requestData("Surname to look for: ");
        String clientData = mainController.getClientController().findClientsBySurname(surname);
        imprimirDatos(clientData);
    }

    public static void listClients() { // return csv
        String allClientsData = mainController.getClientController().listClients();
        System.out.println("List of Clients:\n");
        imprimirDatos(allClientsData);
    }

    public static void listarMecanicos() { // return csv
        String datosDeMecanicosTodas = mainController.getMechanicController().listMechanics();
        System.out.println("List of Mechanics:\n");
        imprimirDatos(datosDeMecanicosTodas);
    }

    public static void listarPersonas() { // return csv
        imprimirDatos(mainController.listPeople());
    }

    // public boolean payTask(String vehicleReg, String mechanicNif) {
    // WorkshopTask task = ...
    // if (task == null || !task.isFinished()) return false;
    //
    // task.setPaid(true);
    // return true;
    // }

    private static void imprimirDatos(String dataset) {
        String[] filas = dataset.split("\n");
        for (String f : filas) {
            imprimirColumnas(f);
        }
        System.out.println("\n-------------- END --------------\n");
        // System.out.println(datos.replace(';', ' '));
        // echar vistazo al formateo
    }

    private static void imprimirColumnas(String line) {
        // String formato = "%-15s %-15s %-15s %-15s %-30s %-12s %-12s%n";
        // System.out.printf(formato, "nombre", "apellido1", "apellido2", "dni",
        // "email", "telefono", "codCliente");

        String[] columnas = line.split(";");
        for (String c : columnas) {
            System.out.printf("| %-20.20s ", c);
            // System.out.println();
        }
        System.out.println();
        // System.out.printf("%-10.10s", datos.replace(';', ' '));
        // System.out.println(linea.replace(';', ' '));
    }

    private static int getOptionMenu() {
        return readInt("Option: ");
    }

    private static int readInt(String info) {
        System.out.print(info);
        return (new Scanner(System.in)).nextInt();
    }

    private static String requestData(String data) {
        System.out.print(data);
        return (new Scanner(System.in)).nextLine();
    }

}
