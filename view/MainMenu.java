package view;

import controller.MainController;

/**
 * Main menu for the Workshop Management System.
 * Provides access to all major modules and submenu options.
 */
public class MainMenu {

    private MainController controller;

    public MainMenu(MainController controller) {
        this.controller = controller;
    }

    /**
     * Displays and handles the main menu loop.
     */
    public void show() {
        boolean running = true;

        while (running) {
            UIUtils.clearScreen();
            UIUtils.printTitle("WORKSHOP MANAGEMENT SYSTEM");
            System.out.println(UIUtils.BOLD + "\nMain Menu:" + UIUtils.RESET);
            System.out.println("1. Client Management");
            System.out.println("2. Vehicle Management");
            System.out.println("3. Mechanic Management");
            System.out.println("4. Workshop Tasks");
            System.out.println("5. Reports & Statistics");
            System.out.println("0. Exit Application");
            System.out.println();

            int option = UIUtils.readInt("Select an option: ");

            switch (option) {
                case 1:
                    new ClientMenu(controller).show();
                    break;
                case 2:
                    new VehicleMenu(controller).show();
                    break;
                case 3:
                    new MechanicMenu(controller).show();
                    break;
                case 4:
                    new WorkshopTaskMenu(controller).show();
                    break;
                case 5:
                    new ReportMenu(controller).show();
                    break;
                case 0:
                    if (UIUtils.confirm("Are you sure you want to exit?")) {
                        running = false;
                        UIUtils.printSuccess("Goodbye!");
                    }
                    break;
                default:
                    UIUtils.printError("Invalid option");
                    UIUtils.pause();
            }
        }
    }
}
