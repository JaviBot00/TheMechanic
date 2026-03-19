package view;

import controller.MainController;

/**
 * Client Management submenu.
 * Allows CRUD operations and searching for clients.
 */
public class ClientMenu {

    private MainController controller;

    public ClientMenu(MainController controller) {
        this.controller = controller;
    }

    public void show() {
        boolean running = true;

        while (running) {
            UIUtils.clearScreen();
            UIUtils.printTitle("CLIENT MANAGEMENT");

            System.out.println(UIUtils.BOLD + "\nOptions:" + UIUtils.RESET);
            System.out.println("1. Create New Client");
            System.out.println("2. List All Clients");
            System.out.println("3. Find Client by NIF");
            System.out.println("4. Find Clients by Surname");
            System.out.println("5. Update Client Information");
            System.out.println("6. Delete Client");
            System.out.println("7. View Client Vehicles");
            System.out.println("0. Back to Main Menu");
            System.out.println();

            int option = UIUtils.readInt("Select an option: ");

            switch (option) {
                case 1:
                    createClient();
                    break;
                case 2:
                    listAllClients();
                    break;
                case 3:
                    findClientByNif();
                    break;
                case 4:
                    findClientsBySurname();
                    break;
                case 5:
                    updateClient();
                    break;
                case 6:
                    deleteClient();
                    break;
                case 7:
                    viewClientVehicles();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    UIUtils.printError("Invalid option");
                    UIUtils.pause();
            }
        }
    }

    private void createClient() {
        UIUtils.printHeader("Create New Client");

        String name = UIUtils.readString("Name: ");
        String surname1 = UIUtils.readString("First Surname: ");
        String surname2 = UIUtils.readString("Second Surname: ");
        String nif = UIUtils.readString("NIF: ");
        String email = UIUtils.readString("Email: ");
        String telephone = UIUtils.readString("Telephone: ");
        int clientCode = UIUtils.readInt("Client Code: ");

        String csvClient = name + ";" + surname1 + ";" + surname2 + ";" + nif + ";" + email + ";" + telephone + ";"
                + clientCode;

        if (controller.getClientController().createClient(csvClient)) {
            UIUtils.printSuccess("Client created successfully");
        } else {
            UIUtils.printError("Failed to create client. Check if NIF already exists.");
        }
        UIUtils.pause();
    }

    private void listAllClients() {
        UIUtils.printHeader("All Clients");
        String data = controller.getClientController().listClients();
        UIUtils.printTable(data);
        UIUtils.pause();
    }

    private void findClientByNif() {
        UIUtils.printHeader("Find Client by NIF");
        String nif = UIUtils.readString("Enter NIF: ");
        String data = controller.getClientController().findClientByNif(nif);
        UIUtils.printTable(data);
        UIUtils.pause();
    }

    private void findClientsBySurname() {
        UIUtils.printHeader("Find Clients by Surname");
        String surname = UIUtils.readString("Enter surname: ");
        String data = controller.getClientController().findClientsBySurname(surname);
        UIUtils.printTable(data);
        UIUtils.pause();
    }

    private void updateClient() {
        UIUtils.printHeader("Update Client Information");
        int clientCode = UIUtils.readInt("Enter Client Code to update: ");

        System.out.println("Leave fields blank to skip updating them");
        String name = UIUtils.readString("Name (or blank): ");
        String surname1 = UIUtils.readString("First Surname (or blank): ");
        String surname2 = UIUtils.readString("Second Surname (or blank): ");
        String nif = UIUtils.readString("NIF (or blank): ");
        String email = UIUtils.readString("Email (or blank): ");
        String telephone = UIUtils.readString("Telephone (or blank): ");

        String csvClient = name + ";" + surname1 + ";" + surname2 + ";" + nif + ";" + email + ";" + telephone + ";";

        if (controller.getClientController().updateClient(clientCode, csvClient)) {
            UIUtils.printSuccess("Client updated successfully");
        } else {
            UIUtils.printError("Failed to update client");
        }
        UIUtils.pause();
    }

    private void deleteClient() {
        UIUtils.printHeader("Delete Client");
        String nif = UIUtils.readString("Enter NIF of client to delete: ");

        if (UIUtils.confirm("Are you sure you want to delete this client?")) {
            if (controller.getClientController().deleteClientByNif(nif)) {
                UIUtils.printSuccess("Client deleted successfully");
            } else {
                UIUtils.printError("Failed to delete client");
            }
        }
        UIUtils.pause();
    }

    private void viewClientVehicles() {
        UIUtils.printHeader("View Client Vehicles");
        String nif = UIUtils.readString("Enter client NIF: ");
        String data = controller.getVehicleController().findVehiclesFromClient(nif);
        UIUtils.printTable(data);
        UIUtils.pause();
    }
}
