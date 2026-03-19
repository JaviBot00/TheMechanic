package view;

import controller.MainController;

/**
 * Reports and Statistics submenu.
 * Displays various reports and statistics about the workshop.
 */
public class ReportMenu {

    private MainController controller;

    public ReportMenu(MainController controller) {
        this.controller = controller;
    }

    public void show() {
        boolean running = true;

        while (running) {
            UIUtils.clearScreen();
            UIUtils.printTitle("REPORTS & STATISTICS");

            System.out.println(UIUtils.BOLD + "\nAvailable Reports:" + UIUtils.RESET);
            System.out.println("1. Client Statistics");
            System.out.println("2. Vehicle Statistics");
            System.out.println("3. Mechanic Workload");
            System.out.println("4. Revenue Report");
            System.out.println("5. Task Completion Report");
            System.out.println("6. Pending Tasks Report");
            System.out.println("0. Back to Main Menu");
            System.out.println();

            int option = UIUtils.readInt("Select a report: ");

            switch (option) {
                case 1:
                    clientStatistics();
                    break;
                case 2:
                    vehicleStatistics();
                    break;
                case 3:
                    mechanicWorkload();
                    break;
                case 4:
                    revenueReport();
                    break;
                case 5:
                    taskCompletionReport();
                    break;
                case 6:
                    pendingTasksReport();
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

    private void clientStatistics() {
        UIUtils.printHeader("Client Statistics");
        UIUtils.printInfo("Total Clients: " + controller.getClientController().getTotalClients());
        UIUtils.printInfo("Total Vehicles: " + controller.getVehicleController().getTotalVehicles());
        UIUtils.pause();
    }

    private void vehicleStatistics() {
        UIUtils.printHeader("Vehicle Statistics");
        UIUtils.printWarning("Detailed statistics feature not yet implemented");
        UIUtils.pause();
    }

    private void mechanicWorkload() {
        UIUtils.printHeader("Mechanic Workload");
        UIUtils.printWarning("Mechanic workload report not yet implemented");
        UIUtils.pause();
    }

    private void revenueReport() {
        UIUtils.printHeader("Revenue Report");
        UIUtils.printWarning("Revenue report not yet implemented");
        UIUtils.pause();
    }

    private void taskCompletionReport() {
        UIUtils.printHeader("Task Completion Report");
        UIUtils.printWarning("Task completion report not yet implemented");
        UIUtils.pause();
    }

    private void pendingTasksReport() {
        UIUtils.printHeader("Pending Tasks Report");
        UIUtils.printWarning("Pending tasks report not yet implemented");
        UIUtils.pause();
    }
}
