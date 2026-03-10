package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class representing a vehicle in the workshop.
 * Implements Comparable interface to allow sorting vehicles by registration
 * code.
 */
public abstract class Vehicle implements Comparable<Vehicle> {

    private static final String CSV_FORMAT = "registrationCode;model;proprietary;workshopTasks";
    private final List<WorkshopTask> workshopTasks;
    private String registrationCode;
    private String model;
    private VehicleType type;
    private Client proprietary;

    /**
     * Constructor for Vehicle class.
     * Initializes the vehicle with the provided attributes and an empty list of
     * workshop tasks.
     * 
     * @param registrationCode The registration code of the vehicle
     * @param model            The model of the vehicle
     * @param type             The type of vehicle (e.g., CAR, MOTORCYCLE, VAN,
     *                         TRUCK)
     * @param proprietary      The proprietary (client) of the vehicle
     */
    public Vehicle(String registrationCode, String model, VehicleType type, Client proprietary) {
        this.registrationCode = registrationCode;
        this.model = model;
        this.type = type;
        this.proprietary = proprietary;
        this.workshopTasks = new ArrayList<>();
    }

    /**
     * Returns the CSV format string for Vehicle objects.
     * The format is: "registrationCode;model;proprietary;workshopTasksSize"
     *
     * @return the CSV format string
     */
    public static String getCsvFormat() {
        return Vehicle.CSV_FORMAT;
    }

    public abstract float getPrice();

    // public boolean addWorkshopTask(WorkshopTask wt) {
    // if (workshopTasks.contains(wt)) return false;
    //
    // if (wt != null) {
    // workshopTasks.add(wt);
    // return true;
    // }
    // return false;
    // }

    /**
     * Adds a workshop task to the vehicle's list of tasks.
     *
     * @param task the workshop task to be added
     * @return true if the task was successfully added, false otherwise
     */
    public boolean addWorkshopTask(WorkshopTask task) {
        return workshopTasks.add(task);
    }

    public int getIndexOfWorkshopTask(WorkshopTask wt) {
        return workshopTasks.indexOf(wt);
    }
    // public int getWorkshopTaskCount() {
    // return workshopTasks.size();
    // }

    /**
     * Returns a workshop task from the vehicle's list of tasks by index.
     * If the index is out of bounds, returns null.
     *
     * @param i the index of the workshop task to be returned
     * @return the workshop task at the specified index, or null if the index is out
     *         of bounds
     */
    public WorkshopTask getWorkshopTaskByIndex(int i) {
        if (i < 0 || i >= workshopTasks.size()) {
            return null;
        }
        return workshopTasks.get(i);
    }

    /**
     * Removes a workshop task from the vehicle's list of tasks.
     *
     * @param task the workshop task to be removed
     * @return true if the task was successfully removed, false otherwise
     */
    public boolean removeWorkshopTask(WorkshopTask task) {
        return workshopTasks.remove(task);
    }

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

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public Client getProprietary() {
        return proprietary;
    }

    public void setProprietary(Client proprietary) {
        this.proprietary = proprietary;
    }

    /**
     * Returns a string representation of the Vehicle object.
     * The string includes all the attributes of the Vehicle object.
     * The format is: "registrationCode;model;proprietary;workshopTasksSize"
     * followed by a newline character.
     *
     * @return a string representation of the Vehicle object
     */
    @Override
    public String toString() {
        return this.registrationCode + ";"
                + this.model + ";"
                + this.proprietary.getNif() + ";"
                + this.workshopTasks.size() + ";";
    }

    /**
     * Compares this vehicle with another vehicle based on their registration codes.
     * 
     * @param v The other vehicle to compare with
     * @return a negative integer, zero, or a positive integer as this object is
     *         less than, equal to, or greater than the specified object
     */
    @Override
    public int compareTo(Vehicle v) {
        return this.getRegistrationCode().compareTo(v.getRegistrationCode());
    }

    /**
     * Enum representing the type of vehicle.
     */
    public enum VehicleType {
        MOTORCYCLE, CAR, VAN, TRUCK
    }
}
