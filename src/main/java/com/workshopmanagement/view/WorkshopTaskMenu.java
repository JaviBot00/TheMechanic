package src.main.java.com.workshopmanagement.view;

import src.main.java.com.workshopmanagement.controller.MainController;

/**
 * Workshop Task Management submenu.
 * Allows CRUD operations and progress tracking for workshop tasks.
 */
public class WorkshopTaskMenu {

    private MainController controller;

    public WorkshopTaskMenu(MainController controller) {
        this.controller = controller;
    }

    public void show() {
        boolean running = true;

        while (running) {
            UIUtils.clearScreen();
            UIUtils.printTitle("WORKSHOP TASK MANAGEMENT");

            System.out.println(UIUtils.BOLD + "\nOptions:" + UIUtils.RESET);
            System.out.println("1. Create New Workshop Task");
            System.out.println("2. List All Tasks");
            System.out.println("3. Find Tasks by Client");
            System.out.println("4. Find Tasks by Vehicle");
            System.out.println("5. Find Tasks by Mechanic");
            System.out.println("6. Add Work Hours to Task");
            System.out.println("7. Complete Task");
            System.out.println("8. Mark Task as Paid");
            System.out.println("9. Update Task Information");
            System.out.println("10. Delete Task");
            System.out.println("0. Back to Main Menu");
            System.out.println();

            int option = UIUtils.readInt("Select an option: ");

            switch (option) {
                case 1:
                    createTask();
                    break;
                case 2:
                    listAllTasks();
                    break;
                case 3:
                    findTasksByClient();
                    break;
                case 4:
                    findTasksByVehicle();
                    break;
                case 5:
                    findTasksByMechanic();
                    break;
                case 6:
                    addWorkHours();
                    break;
                case 7:
                    completeTask();
                    break;
                case 8:
                    markTaskAsPaid();
                    break;
                case 9:
                    updateTask();
                    break;
                case 10:
                    deleteTask();
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

    private void createTask() {
        UIUtils.printHeader("Create New Workshop Task");

        String vehicleReg = UIUtils.readString("Vehicle Registration Code: ");
        String mechanicNif = UIUtils.readString("Mechanic NIF: ");
        String diagnostic = UIUtils.readString("Diagnostic/Problem Description: ");
        float previewHours = UIUtils.readFloat("Estimated Hours to Complete: ");
        String initDate = UIUtils.readString("Task Start Date (yyyy-MM-dd HH:mm): ");

        if (controller.getTaskController().createWorkshopTask(vehicleReg, mechanicNif, diagnostic, previewHours,
                initDate)) {
            UIUtils.printSuccess("Workshop task created successfully");
        } else {
            UIUtils.printError("Failed to create task. Check if vehicle and mechanic exist.");
        }
        UIUtils.pause();
    }

    private void listAllTasks() {
        UIUtils.printHeader("All Workshop Tasks");
        String data = controller.getTaskController().listWorkshopTask();
        UIUtils.printTable(data);
        UIUtils.pause();
    }

    private void findTasksByClient() {
        UIUtils.printHeader("Tasks by Client");
        String nif = UIUtils.readString("Enter client NIF: ");
        String data = controller.getTaskController().findWorkshopTaskByClient(nif);
        if (data.isEmpty()) {
            UIUtils.printWarning("No tasks found for this client");
        } else {
            UIUtils.printTable(data);
        }
        UIUtils.pause();
    }

    private void findTasksByVehicle() {
        UIUtils.printHeader("Tasks by Vehicle");
        String registrationCode = UIUtils.readString("Enter vehicle registration code: ");
        String data = controller.getTaskController().findWorkshopTaskByVehicle(registrationCode);
        if (data.isEmpty()) {
            UIUtils.printWarning("No tasks found for this vehicle");
        } else {
            UIUtils.printTable(data);
        }
        UIUtils.pause();
    }

    private void findTasksByMechanic() {
        UIUtils.printHeader("Tasks by Mechanic");
        String nif = UIUtils.readString("Enter mechanic NIF: ");
        String data = controller.getTaskController().findWorkshopTaskByMechanic(nif);
        if (data.isEmpty()) {
            UIUtils.printWarning("No tasks found for this mechanic");
        } else {
            UIUtils.printTable(data);
        }
        UIUtils.pause();
    }

    private void addWorkHours() {
        UIUtils.printHeader("Add Work Hours to Task");

        String vehicleReg = UIUtils.readString("Vehicle Registration Code: ");
        String mechanicNif = UIUtils.readString("Mechanic NIF: ");
        float hoursToAdd = UIUtils.readFloat("Hours to Add: ");

        if (controller.getTaskController().updateTaskProgress(vehicleReg, mechanicNif, hoursToAdd, false, false)) {
            UIUtils.printSuccess("Hours added successfully");
        } else {
            UIUtils.printError("Failed to add hours. Check vehicle and mechanic.");
        }
        UIUtils.pause();
    }

    private void completeTask() {
        UIUtils.printHeader("Complete Task");

        String vehicleReg = UIUtils.readString("Vehicle Registration Code: ");
        String mechanicNif = UIUtils.readString("Mechanic NIF: ");

        if (controller.getTaskController().updateTaskProgress(vehicleReg, mechanicNif, 0, true, false)) {
            UIUtils.printSuccess("Task marked as completed");
        } else {
            UIUtils.printError("Failed to complete task");
        }
        UIUtils.pause();
    }

    private void markTaskAsPaid() {
        UIUtils.printHeader("Mark Task as Paid");

        String vehicleReg = UIUtils.readString("Vehicle Registration Code: ");
        String mechanicNif = UIUtils.readString("Mechanic NIF: ");

        if (controller.getTaskController().updateTaskProgress(vehicleReg, mechanicNif, 0, false, true)) {
            UIUtils.printSuccess("Task marked as paid");
        } else {
            UIUtils.printError("Failed to mark task as paid. Task must be completed first.");
        }
        UIUtils.pause();
    }

    private void updateTask() {
        UIUtils.printHeader("Update Task Information");

        String vehicleReg = UIUtils.readString("Vehicle Registration Code: ");
        String mechanicNif = UIUtils.readString("Mechanic NIF: ");
        String diagnostic = UIUtils.readString("New diagnostic (or blank to skip): ");
        float previewHours = UIUtils.readFloat("New estimated hours (0 to skip): ");

        if (controller.getTaskController().updateWorkshopTask(vehicleReg, mechanicNif, diagnostic, previewHours)) {
            UIUtils.printSuccess("Task updated successfully");
        } else {
            UIUtils.printError("Failed to update task");
        }
        UIUtils.pause();
    }

    private void deleteTask() {
        UIUtils.printHeader("Delete Task");

        String vehicleReg = UIUtils.readString("Vehicle Registration Code: ");
        String mechanicNif = UIUtils.readString("Mechanic NIF: ");

        if (UIUtils.confirm("Are you sure you want to delete this task?")) {
            if (controller.getTaskController().deleteWorkshopTask(vehicleReg, mechanicNif)) {
                UIUtils.printSuccess("Task deleted successfully");
            } else {
                UIUtils.printError("Failed to delete task");
            }
        }
        UIUtils.pause();
    }
}
