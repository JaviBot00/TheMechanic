package src.main.java.com.workshopmanagement.view;

import src.main.java.com.workshopmanagement.controller.MainController;

/**
 * Mechanic Management submenu.
 * Allows CRUD operations for mechanics.
 */
public class MechanicMenu {

    private MainController controller;

    public MechanicMenu(MainController controller) {
        this.controller = controller;
    }

    public void show() {
        boolean running = true;

        while (running) {
            UIUtils.clearScreen();
            UIUtils.printTitle("MECHANIC MANAGEMENT");

            System.out.println(UIUtils.BOLD + "\nOptions:" + UIUtils.RESET);
            System.out.println("1. Register New Mechanic");
            System.out.println("2. List All Mechanics");
            System.out.println("3. Find Mechanic by NIF");
            System.out.println("4. Find Mechanics by Specialty");
            System.out.println("5. Update Mechanic Information");
            System.out.println("6. Delete Mechanic");
            System.out.println("7. View Mechanic Tasks");
            System.out.println("0. Back to Main Menu");
            System.out.println();

            int option = UIUtils.readInt("Select an option: ");

            switch (option) {
                case 1:
                    createMechanic();
                    break;
                case 2:
                    listAllMechanics();
                    break;
                case 3:
                    findMechanicByNif();
                    break;
                case 4:
                    findMechanicsBySpecialty();
                    break;
                case 5:
                    updateMechanic();
                    break;
                case 6:
                    deleteMechanic();
                    break;
                case 7:
                    viewMechanicTasks();
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

    private void createMechanic() {
        UIUtils.printHeader("Register New Mechanic");

        String name = UIUtils.readString("Name: ");
        String surname1 = UIUtils.readString("First Surname: ");
        String surname2 = UIUtils.readString("Second Surname: ");
        String nif = UIUtils.readString("NIF: ");
        String email = UIUtils.readString("Email: ");
        String telephone = UIUtils.readString("Telephone: ");
        String registrationDate = UIUtils.readString("Registration Date (yyyy-MM-dd): ");
        String specialty = UIUtils.readString("Specialty: ");

        String csvMechanic = name + ";" + surname1 + ";" + surname2 + ";" + nif + ";" + email + ";" + telephone + ";"
                + registrationDate + ";" + specialty;

        if (controller.getMechanicController().createMechanic(csvMechanic)) {
            UIUtils.printSuccess("Mechanic registered successfully");
        } else {
            UIUtils.printError("Failed to register mechanic. Check if NIF already exists.");
        }
        UIUtils.pause();
    }

    private void listAllMechanics() {
        UIUtils.printHeader("All Mechanics");
        String data = controller.getMechanicController().listMechanics();
        UIUtils.printTable(data);
        UIUtils.pause();
    }

    private void findMechanicByNif() {
        UIUtils.printHeader("Find Mechanic by NIF");
        String nif = UIUtils.readString("Enter NIF: ");
        String data = controller.getMechanicController().findMechanicByNif(nif);
        UIUtils.printTable(data);
        UIUtils.pause();
    }

    private void findMechanicsBySpecialty() {
        UIUtils.printHeader("Find Mechanics by Specialty");
        UIUtils.printWarning("Feature not yet implemented");
        UIUtils.pause();
    }

    private void updateMechanic() {
        UIUtils.printHeader("Update Mechanic Information");

        System.out.println("Leave fields blank to skip updating them");
        String name = UIUtils.readString("Name (or blank): ");
        String surname1 = UIUtils.readString("First Surname (or blank): ");
        String surname2 = UIUtils.readString("Second Surname (or blank): ");
        String nif = UIUtils.readString("NIF (or blank): ");
        String email = UIUtils.readString("Email (or blank): ");
        String telephone = UIUtils.readString("Telephone (or blank): ");
        String registrationDate = UIUtils.readString("Registration Date (or blank): ");
        String specialty = UIUtils.readString("Specialty (or blank): ");

        String csvMechanic = name + ";" + surname1 + ";" + surname2 + ";" + nif + ";" + email + ";" + telephone + ";"
                + registrationDate + ";" + specialty;

        if (controller.getMechanicController().updateMechanic(csvMechanic)) {
            UIUtils.printSuccess("Mechanic updated successfully");
        } else {
            UIUtils.printError("Failed to update mechanic");
        }
        UIUtils.pause();
    }

    private void deleteMechanic() {
        UIUtils.printHeader("Delete Mechanic");
        String nif = UIUtils.readString("Enter NIF of mechanic to delete: ");

        if (UIUtils.confirm("Are you sure? Mechanic cannot have assigned tasks.")) {
            if (controller.getMechanicController().deleteMechanicByNif(nif)) {
                UIUtils.printSuccess("Mechanic deleted successfully");
            } else {
                UIUtils.printError("Failed to delete mechanic. Check if mechanic has assigned tasks.");
            }
        }
        UIUtils.pause();
    }

    private void viewMechanicTasks() {
        UIUtils.printHeader("View Mechanic Tasks");
        String nif = UIUtils.readString("Enter mechanic NIF: ");
        String data = controller.getTaskController().findWorkshopTaskByMechanic(nif);
        if (data.isEmpty()) {
            UIUtils.printWarning("No tasks found for this mechanic");
        } else {
            UIUtils.printTable(data);
        }
        UIUtils.pause();
    }
}
