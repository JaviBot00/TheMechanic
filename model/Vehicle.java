package Taller.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle implements Comparable<Vehicle> {

    private static final String CSV_FORMAT = "registrationCode;model;proprietary;workshopTasks";

    private String registrationCode;
    private String model;
    private Client proprietary;
    private final List<WorkshopTask> workshopTasks;

    public Vehicle(String registrationCode, String model, Client proprietary) {
        this.registrationCode = registrationCode;
        this.model = model;
        this.proprietary = proprietary;
        this.workshopTasks = new ArrayList<>();
    }

    public abstract float getPrice();

    public String getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(String registrationCode) {
        this.registrationCode = registrationCode;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Client getProprietary() {
        return proprietary;
    }

    public void setProprietary(Client proprietary) {
        this.proprietary = proprietary;
    }

    public boolean addWorkshopTask(WorkshopTask wt) {
        if (workshopTasks.contains(wt)) return false;

        if (wt != null) {
            workshopTasks.add(wt);
            return true;
        }
        return false;
    }

    public int getWorkshopTaskCount() {
        return workshopTasks.size();
    }

    public WorkshopTask getWorkshopTask(int index) {
        return workshopTasks.get(index);
    }

    public static String getCsvFormat() {
        return Vehicle.CSV_FORMAT;
    }

    @Override
    public String toString() {
        return this.registrationCode + ";"
                + this.model + ";"
                + this.proprietary.getNif() + ";"
                + this.workshopTasks.size() + ";";
    }

    @Override
    public int compareTo(Vehicle v) {
        return this.getRegistrationCode().compareTo(v.getRegistrationCode());
    }
}
