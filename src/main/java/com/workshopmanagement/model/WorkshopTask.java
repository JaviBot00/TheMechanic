package src.main.java.com.workshopmanagement.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * WorkshopTask class representing a repair or maintenance task in the workshop.
 * Tracks diagnostic information, estimated and actual hours, completion status,
 * and payment status.
 */
public class WorkshopTask implements Comparable<WorkshopTask> {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final String initDate;
    private final Client client;
    private final Vehicle vehicle;
    private final Mechanic mechanic;
    private String diagnostic;
    private String solution;
    private float previewHours;
    private float realHours;
    private boolean isFinished;
    private boolean isPaid;
    private String notes;

    /**
     * Constructor for WorkshopTask class.
     * Initializes the workshop task with the provided attributes.
     *
     * @param diagnostic   Description of the problem identified
     * @param previewHours Estimated number of hours to complete the task
     * @param initDate     Date when the task was created (format: yyyy-MM-dd HH:mm)
     * @param client       The client who owns the vehicle
     * @param vehicle      The vehicle being serviced
     * @param mechanic     The mechanic assigned to the task
     */
    public WorkshopTask(String diagnostic, float previewHours, String initDate, Client client, Vehicle vehicle,
            Mechanic mechanic) {
        this.diagnostic = diagnostic;
        this.previewHours = previewHours;
        this.initDate = initDate;
        this.client = client;
        this.vehicle = vehicle;
        this.mechanic = mechanic;
        this.realHours = 0f;
        this.isFinished = false;
        this.isPaid = false;
        this.solution = "";
        this.notes = "";
    }

    /**
     * Calculates the total cost of this task based on the vehicle type and real
     * hours.
     * Only calculated if the task is finished.
     *
     * @return the total cost in euros, or 0 if not finished
     */
    public float getTotalCost() {
        if (!isFinished) {
            return 0f;
        }
        return vehicle.calculatePrice(realHours);
    }

    /**
     * Returns the progress percentage of this task.
     * Calculated as: (realHours / previewHours) * 100
     * If previewHours is 0, returns 0.
     *
     * @return progress as a percentage between 0 and 100
     */
    public float getProgress() {
        if (previewHours <= 0) {
            return 0f;
        }
        return Math.min((realHours / previewHours) * 100f, 100f);
    }

    /**
     * Returns the estimated cost based on preview hours.
     * Useful for quotations before work begins.
     *
     * @return the estimated cost in euros
     */
    public float getEstimatedCost() {
        return vehicle.calculatePrice(previewHours);
    }

    /**
     * Adds hours to the workshop task.
     * Hours can only be added if the task is not finished.
     *
     * @param hours the hours to be added
     * @throws IllegalArgumentException if hours is negative or zero
     */
    public void addHours(float hours) {
        if (isFinished) {
            throw new IllegalStateException("Cannot add hours to a finished task");
        }
        if (hours <= 0) {
            throw new IllegalArgumentException("Hours must be greater than zero");
        }
        realHours += hours;
    }

    /**
     * Finishes the workshop task.
     * Once finished, no more hours can be added and the task can be paid.
     */
    public void finish() {
        if (!isFinished) {
            isFinished = true;
        }
    }

    /**
     * Marks the task as paid.
     * Can only be paid if the task is already finished.
     *
     * @throws IllegalStateException if the task is not finished
     */
    public void markAsPaid() {
        if (!isFinished) {
            throw new IllegalStateException("Cannot pay an unfinished task");
        }
        this.isPaid = true;
    }

    /**
     * Marks the task as unpaid.
     * Can be used to reverse a payment if needed.
     */
    public void markAsUnpaid() {
        this.isPaid = false;
    }

    // ============ Getters and Setters ============

    public String getInitDate() {
        return initDate;
    }

    public Client getClient() {
        return client;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        if (diagnostic != null && !diagnostic.isBlank()) {
            this.diagnostic = diagnostic;
        }
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        if (solution != null) {
            this.solution = solution;
        }
    }

    public float getPreviewHours() {
        return previewHours;
    }

    public void setPreviewHours(float previewHours) {
        if (previewHours > 0) {
            this.previewHours = previewHours;
        }
    }

    public float getRealHours() {
        return realHours;
    }

    public void setRealHours(float realHours) {
        if (realHours >= 0) {
            this.realHours = realHours;
        }
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        if (paid && !isFinished) {
            throw new IllegalStateException("Cannot mark task as paid before it is finished");
        }
        this.isPaid = paid;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        if (notes != null) {
            this.notes = notes;
        }
    }

    /**
     * Returns the current status of the task as a string.
     *
     * @return status string (Pending, In Progress, Finished, or Paid)
     */
    public String getStatus() {
        if (isPaid) {
            return "Paid";
        } else if (isFinished) {
            return "Finished";
        } else if (realHours > 0) {
            return "In Progress";
        } else {
            return "Pending";
        }
    }

    /**
     * Compares this workshop task with another based on their initial dates.
     *
     * @param o The other workshop task to compare with
     * @return a negative integer, zero, or a positive integer
     */
    @Override
    public int compareTo(WorkshopTask o) {
        return this.getInitDate().compareTo(o.getInitDate());
    }

    /**
     * Returns a string representation of the WorkshopTask in a detailed format.
     *
     * @return string representation of the task
     */
    @Override
    public String toString() {
        return String.format(
                "WorkshopTask{" +
                        "diagnostic='%s', " +
                        "vehicle=%s, " +
                        "mechanic=%s, " +
                        "previewHours=%.1f, " +
                        "realHours=%.1f, " +
                        "status=%s, " +
                        "estimatedCost=€%.2f, " +
                        "actualCost=€%.2f}",
                diagnostic, vehicle.getRegistrationCode(), mechanic.getName(),
                previewHours, realHours, getStatus(),
                getEstimatedCost(), getTotalCost());
    }
}
