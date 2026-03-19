package src.main.java.com.workshopmanagement.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class representing a vehicle in the workshop.
 * Implements Comparable interface to allow sorting vehicles by registration
 * code.
 *
 * Each vehicle type has different hourly rates for billing purposes.
 */
public abstract class Vehicle implements Comparable<Vehicle> {

    private static final String CSV_FORMAT = "registrationCode;model;type;proprietary;workshopTasks";
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
     * @param registrationCode The registration code/license plate of the vehicle
     * @param model            The model/brand of the vehicle
     * @param type             The type of vehicle (CAR, MOTORCYCLE, VAN, TRUCK)
     * @param proprietary      The client who owns the vehicle
     */
    public Vehicle(String registrationCode, String model, VehicleType type, Client proprietary) {
        this.registrationCode = registrationCode;
        this.model = model;
        this.type = type;
        this.proprietary = proprietary;
        this.workshopTasks = new ArrayList<>();
    }

    /**
     * Returns the hourly rate for this vehicle type in euros.
     *
     * @return the hourly rate
     */
    public abstract float getHourlyRate();

    /**
     * Returns any fixed difficulty fee for this vehicle type in euros.
     * Returns 0 for vehicle types without a fixed fee.
     *
     * @return the fixed difficulty fee
     */
    public abstract float getFixedDifficultyFee();

    /**
     * Calculates the total price for the given number of hours.
     * Formula: (hours * hourlyRate) + fixedDifficultyFee
     *
     * @param hours the number of hours worked
     * @return the total price in euros
     * @throws IllegalArgumentException if hours is negative
     */
    public float calculatePrice(float hours) {
        if (hours < 0) {
            throw new IllegalArgumentException("Hours cannot be negative");
        }
        return (hours * getHourlyRate()) + getFixedDifficultyFee();
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

    /**
     * Adds a workshop task to the vehicle's list of tasks.
     *
     * @param task the workshop task to be added
     * @return true if the task was successfully added, false otherwise
     */
    public boolean addWorkshopTask(WorkshopTask task) {
        return workshopTasks.add(task);
    }

    /**
     * Returns the index of a workshop task in the vehicle's list of tasks.
     *
     * @param wt the workshop task
     * @return the index of the task, or -1 if not found
     */
    public int getIndexOfWorkshopTask(WorkshopTask wt) {
        return workshopTasks.indexOf(wt);
    }

    /**
     * Returns a workshop task from the vehicle's list of tasks by index.
     *
     * @param i the index of the workshop task
     * @return the workshop task at the specified index, or null if out of bounds
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

    /**
     * Returns the number of workshop tasks associated with this vehicle.
     *
     * @return the number of tasks
     */
    public int getWorkshopTasksCount() {
        return workshopTasks.size();
    }

    /**
     * Returns the total number of hours worked on this vehicle.
     *
     * @return the sum of real hours from all finished tasks
     */
    public float getTotalHoursWorked() {
        return workshopTasks.stream()
                .filter(WorkshopTask::isFinished)
                .map(WorkshopTask::getRealHours)
                .reduce(0f, Float::sum);
    }

    /**
     * Returns the total revenue generated from this vehicle.
     *
     * @return the sum of costs from all paid tasks
     */
    public float getTotalRevenue() {
        return workshopTasks.stream()
                .filter(WorkshopTask::isPaid)
                .map(WorkshopTask::getTotalCost)
                .reduce(0f, Float::sum);
    }

    /**
     * Returns the percentage of completed tasks for this vehicle.
     *
     * @return percentage as a number between 0 and 100, or 0 if no tasks
     */
    public float getCompletionPercentage() {
        if (workshopTasks.isEmpty()) {
            return 0f;
        }
        long finishedCount = workshopTasks.stream()
                .filter(WorkshopTask::isFinished)
                .count();
        return (finishedCount * 100f) / workshopTasks.size();
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
     * Returns a string representation of the Vehicle object in CSV format.
     * Format: "registrationCode;model;type;proprietary;workshopTasksSize"
     *
     * @return a string representation of the Vehicle object
     */
    @Override
    public String toString() {
        return this.registrationCode + ";"
                + this.model + ";"
                + this.type + ";"
                + (this.proprietary != null ? this.proprietary.getNif() : "N/A") + ";"
                + this.workshopTasks.size() + ";";
    }

    /**
     * Compares this vehicle with another vehicle based on registration codes.
     *
     * @param v The other vehicle to compare with
     * @return a negative integer, zero, or a positive integer
     */
    @Override
    public int compareTo(Vehicle v) {
        return this.getRegistrationCode().compareTo(v.getRegistrationCode());
    }

    /**
     * Enum representing the type of vehicle.
     */
    public enum VehicleType {
        MOTORCYCLE("Motorcycle"),
        CAR("Car"),
        VAN("Van"),
        TRUCK("Truck");

        private final String displayName;

        VehicleType(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }
}
