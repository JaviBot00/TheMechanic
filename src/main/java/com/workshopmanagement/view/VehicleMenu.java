package src.main.java.com.workshopmanagement.view;

import src.main.java.com.workshopmanagement.controller.MainController;
import src.main.java.com.workshopmanagement.model.Vehicle.VehicleType;

/**
 * Vehicle Management submenu.
 * Allows CRUD operations for vehicles.
 */
public class VehicleMenu {

    private MainController controller;

    public VehicleMenu(MainController controller) {
        this.controller = controller;
    }

    public void show() {
        boolean running = true;

        while (running) {
            UIUtils.clearScreen();
            UIUtils.printTitle("VEHICLE MANAGEMENT");

            System.out.println(UIUtils.BOLD + "\nOptions:" + UIUtils.RESET);
            System.out.println("1. Add Vehicle to Client");
            System.out.println("2. List All Vehicles");
            System.out.println("3. Find Vehicles by Registration");
            System.out.println("4. Find Vehicles by Client");
            System.out.println("5. Update Vehicle Information");
            System.out.println("6. Change Vehicle Owner");
            System.out.println("7. Change Vehicle Type");
            System.out.println("8. Delete Vehicle");
            System.out.println("0. Back to Main Menu");
            System.out.println();

            int option = UIUtils.readInt("Select an option: ");

            switch (option) {
                case 1:
                    addVehicle();
                    break;
                case 2:
                    listAllVehicles();
                    break;
                case 3:
                    findVehicleByRegistration();
                    break;
                case 4:
                    findVehiclesByClient();
                    break;
                case 5:
                    updateVehicle();
                    break;
                case 6:
                    changeVehicleOwner();
                    break;
                case 7:
                    changeVehicleType();
                    break;
                case 8:
                    deleteVehicle();
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

    private void addVehicle() {
        UIUtils.printHeader("Add New Vehicle");

        String clientNif = UIUtils.readString("Client NIF: ");

        System.out.println("\nVehicle Types:");
        System.out.println("1. Motorcycle (20€/hour)");
        System.out.println("2. Car (25€/hour)");
        System.out.println("3. Van (30€/hour + 30€ fixed)");
        System.out.println("4. Truck (40€/hour + 50€ fixed)");

        int typeOption = UIUtils.readInt("Select vehicle type: ");
        VehicleType type = getVehicleType(typeOption);

        if (type == null) {
            UIUtils.printError("Invalid vehicle type");
            UIUtils.pause();
            return;
        }

        String registrationCode = UIUtils.readString("Registration Code/License Plate: ");
        String model = UIUtils.readString("Model/Brand: ");

        String csvVehicle = registrationCode + ";" + model;

        if (controller.getVehicleController().createVehicleToClient(clientNif, type, csvVehicle)) {
            UIUtils.printSuccess("Vehicle added successfully");
        } else {
            UIUtils.printError("Failed to add vehicle. Check if client exists or registration already exists.");
        }
        UIUtils.pause();
    }

    private void listAllVehicles() {
        UIUtils.printHeader("All Vehicles");
        String data = controller.getVehicleController().listVehicles();
        UIUtils.printTable(data);
        UIUtils.pause();
    }

    private void findVehicleByRegistration() {
        UIUtils.printHeader("Find Vehicle by Registration");
        String registration = UIUtils.readString("Enter registration code: ");
        // TODO: Implement find by registration in controller
        UIUtils.printWarning("Feature not yet implemented");
        UIUtils.pause();
    }

    private void findVehiclesByClient() {
        UIUtils.printHeader("Find Vehicles by Client");
        String nif = UIUtils.readString("Enter client NIF: ");
        String data = controller.getVehicleController().findVehiclesFromClient(nif);
        UIUtils.printTable(data);
        UIUtils.pause();
    }

    private void updateVehicle() {
        UIUtils.printHeader("Update Vehicle Information");
        String registrationCode = UIUtils.readString("Enter registration code: ");
        String model = UIUtils.readString("New model (or blank to skip): ");

        String csvVehicle = registrationCode + ";" + model;

        if (controller.getVehicleController().updateVehicle(registrationCode, csvVehicle)) {
            UIUtils.printSuccess("Vehicle updated successfully");
        } else {
            UIUtils.printError("Failed to update vehicle");
        }
        UIUtils.pause();
    }

    private void changeVehicleOwner() {
        UIUtils.printHeader("Change Vehicle Owner");
        String registrationCode = UIUtils.readString("Enter vehicle registration code: ");
        String newOwnerNif = UIUtils.readString("Enter new owner NIF: ");

        if (controller.getVehicleController().changeVehicleOwner(registrationCode, newOwnerNif)) {
            UIUtils.printSuccess("Vehicle owner changed successfully");
        } else {
            UIUtils.printError("Failed to change vehicle owner");
        }
        UIUtils.pause();
    }

    private void changeVehicleType() {
        UIUtils.printHeader("Change Vehicle Type");
        String registrationCode = UIUtils.readString("Enter vehicle registration code: ");

        System.out.println("\nNew Vehicle Types:");
        System.out.println("1. Motorcycle");
        System.out.println("2. Car");
        System.out.println("3. Van");
        System.out.println("4. Truck");

        int typeOption = UIUtils.readInt("Select new vehicle type: ");
        VehicleType newType = getVehicleType(typeOption);

        if (newType == null) {
            UIUtils.printError("Invalid vehicle type");
            UIUtils.pause();
            return;
        }

        if (controller.getVehicleController().changeVehicleType(registrationCode, newType)) {
            UIUtils.printSuccess("Vehicle type changed successfully");
        } else {
            UIUtils.printError("Failed to change vehicle type");
        }
        UIUtils.pause();
    }

    private void deleteVehicle() {
        UIUtils.printHeader("Delete Vehicle");
        String registrationCode = UIUtils.readString("Enter registration code: ");

        if (UIUtils.confirm("Are you sure you want to delete this vehicle?")) {
            if (controller.getVehicleController().deleteVehicle(registrationCode)) {
                UIUtils.printSuccess("Vehicle deleted successfully");
            } else {
                UIUtils.printError("Failed to delete vehicle");
            }
        }
        UIUtils.pause();
    }

    private VehicleType getVehicleType(int option) {
        return switch (option) {
            case 1 -> VehicleType.MOTORCYCLE;
            case 2 -> VehicleType.CAR;
            case 3 -> VehicleType.VAN;
            case 4 -> VehicleType.TRUCK;
            default -> null;
        };
    }
}
