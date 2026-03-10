package model;

public class WorkshopTask implements Comparable<WorkshopTask> {

    private final String initDate;
    private final Client client;
    private final Vehicle vehicle;
    private final Mechanic mechanic;
    private String diagnostic;
    private float previewHours;
    private float realHours;
    private boolean isFinished;
    private boolean isPaid;

    /**
     * Constructor for WorkshopTask class.
     * Initializes the workshop task with the provided attributes.
     * 
     * @param diagnostic   The diagnostic information of the workshop task
     * @param previewHours The previewed hours of the workshop task
     * @param initDate     The initial date of the workshop task
     * @param c            The client associated with the workshop task
     * @param v            The vehicle associated with the workshop task
     * @param m            The mechanic associated with the workshop task
     */
    public WorkshopTask(String diagnostic, float previewHours, String initDate, Client c, Vehicle v, Mechanic m) {
        this.diagnostic = diagnostic;
        this.previewHours = previewHours;
        this.initDate = initDate;
        this.client = c;
        this.vehicle = v;
        this.mechanic = m;
        this.realHours = 0f;
        this.isFinished = false;
        this.isPaid = false;
    }

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
        this.diagnostic = diagnostic;
    }

    public float getPreviewHours() {
        return previewHours;
    }

    public void setPreviewHours(float previewHours) {
        this.previewHours = previewHours;
    }

    public float getRealHours() {
        return realHours;
    }

    public void setRealHours(float realHours) {
        this.realHours = realHours;
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

    /**
     * Sets the paid status of the workshop task.
     * The paid status can only be set if the workshop task is finished.
     * 
     * @param paid The paid status to be set
     */
    public void setPaid(boolean paid) {
        if (isFinished) {
            this.isPaid = paid;
        }
    }

    /**
     * Adds hours to the workshop task.
     * The hours can only be added if the workshop task is not finished.
     * 
     * @param hours The hours to be added
     */
    public void addHoras(float hours) {
        if (isFinished) {
            return;
        }
        if (hours <= 0)
            return;
        realHours += hours;
    }

    /**
     * Finishes the workshop task.
     * The workshop task can only be finished if it is not already finished.
     */
    public void finish() {
        if (!isFinished) {
            isFinished = true;
        }
    }

    /**
     * Compares this workshop task with another workshop task based on their initial
     * dates.
     * 
     * @param o The other workshop task to compare with
     * @return a negative integer, zero, or a positive integer as this object is
     *         less than, equal to, or greater than the specified object
     */
    @Override
    public int compareTo(WorkshopTask o) {
        return this.getInitDate().compareTo(o.getInitDate());
    }
}
