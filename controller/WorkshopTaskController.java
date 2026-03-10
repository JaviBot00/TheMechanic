package controller;

import data.DataStore;
import model.*;

public class WorkshopTaskController {

    private final DataStore data;

    public WorkshopTaskController(DataStore data) {
        this.data = data;
    }

    public boolean createWorkshopTask(String vehicleReg, String mechanicNif, String diagnostic, float previewHours,
            String initDate) {
        Vehicle vehicle = findVehicleByRegistration(vehicleReg);
        Mechanic mechanic = findMechanicByNif(mechanicNif);

        if (vehicle == null || mechanic == null)
            return false;

        Client client = vehicle.getProprietary();
        if (client == null)
            return false;

        WorkshopTask task = new WorkshopTask(diagnostic, previewHours, initDate, client, vehicle, mechanic);

        if (!vehicle.addWorkshopTask(task))
            return false;
        if (!mechanic.addWorkshopTask(task)) {
            vehicle.removeWorkshopTask(task);
            return false;
        }

        return data.getWorkshopTask().add(task);
    }

    public String listWorkshopTask() {
        StringBuilder sb = new StringBuilder();
        for (WorkshopTask task : data.getWorkshopTask()) {
            sb.append(task.toString());
        }
        return sb.toString();
    }

    public String findWorkshopTaskByClient(String nif) {
        StringBuilder sb = new StringBuilder();
        for (WorkshopTask task : data.getWorkshopTask()) {
            if (task.getClient().getNif().equals(nif)) {
                sb.append(task.toString());
            }
        }
        return sb.toString();
    }

    public String findWorkshopTaskByVehicle(String rCode) {
        StringBuilder sb = new StringBuilder();
        for (WorkshopTask task : data.getWorkshopTask()) {
            if (task.getVehicle().getRegistrationCode().equalsIgnoreCase(rCode)) {
                sb.append(task.toString());
            }
        }
        return sb.toString();
    }

    public String findWorkshopTaskByMechanic(String nif) {
        StringBuilder sb = new StringBuilder();
        for (WorkshopTask task : data.getWorkshopTask()) {
            if (task.getMechanic().getNif().equals(nif)) {
                sb.append(task.toString());
            }
        }
        return sb.toString();
    }

    public boolean updateWorkshopTask(String vehicleReg, String mechanicNif, String diagnostic, float previewHours) {
        Vehicle vehicle = findVehicleByRegistration(vehicleReg);
        Mechanic mechanic = findMechanicByNif(mechanicNif);

        if (vehicle == null || mechanic == null)
            return false;

        WorkshopTask task = findWorkshopTask(vehicle, mechanic);
        if (task == null)
            return false;

        if (diagnostic != null && !diagnostic.isBlank())
            task.setDiagnostic(diagnostic);
        if (previewHours > 0)
            task.setPreviewHours(previewHours);

        // i = vehicle.getIndexOfWorkshopTask(task);
        // vehicle.getWorkshopTaskByIndex(i).setDiagnostic(diagnostic);
        // vehicle.getWorkshopTaskByIndex(i).setPreviewHours(previewHours);
        //
        // i = mechanic.getIndexOfWorkshopTask(task);
        // mechanic.getWorkshopTaskByIndex(i).setDiagnostic(diagnostic);
        // mechanic.getWorkshopTaskByIndex(i).setPreviewHours(previewHours);

        return true;
    }

    public boolean updateTaskProgress(String vehicleReg, String mechanicNif, float hoursToAdd, boolean finished,
            boolean paid) {
        Vehicle vehicle = findVehicleByRegistration(vehicleReg);
        Mechanic mechanic = findMechanicByNif(mechanicNif);

        if (vehicle == null || mechanic == null)
            return false;

        WorkshopTask task = findWorkshopTask(vehicle, mechanic);
        if (task == null)
            return false;

        // Add hours only if valid
        if (hoursToAdd > 0) {
            task.addHoras(hoursToAdd);
        }

        // Finish task if requested
        if (finished) {
            task.finish();
        }

        // Only allow payment if task is finished
        if (paid && task.isFinished()) {
            task.setPaid(true);
        }
        return true;
    }

    public boolean deleteWorkshopTask(String vehicleReg, String mechanicNif) {

        Vehicle vehicle = findVehicleByRegistration(vehicleReg);
        Mechanic mechanic = findMechanicByNif(mechanicNif);

        if (vehicle == null || mechanic == null)
            return false;

        WorkshopTask task = findWorkshopTask(vehicle, mechanic);
        if (task == null)
            return false;

        if (!vehicle.removeWorkshopTask(task))
            return false;
        if (!mechanic.deleteWorkshopTask(task)) {
            vehicle.addWorkshopTask(task); // rollback
            return false;
        }

        data.getWorkshopTask().remove(task);
        return true;
    }

    private WorkshopTask findWorkshopTask(Vehicle v, Mechanic m) {
        for (WorkshopTask task : data.getWorkshopTask()) {
            if (task.getVehicle().equals(v) && task.getMechanic().equals(m))
                return task;
        }
        return null;
    }

    private Vehicle findVehicleByRegistration(String reg) {
        for (Vehicle v : data.getVehicles()) {
            if (v.getRegistrationCode().equalsIgnoreCase(reg))
                return v;
        }
        return null;
    }

    private Mechanic findMechanicByNif(String nif) {
        for (Mechanic m : data.getMechanics()) {
            if (m.getNif().equalsIgnoreCase(nif)) {
                return m;
            }
        }
        return null;
    }

}
