package Taller.model;

public class WorkshopTask implements Comparable<WorkshopTask> {

    private final String initDate;
    private final Client client;
    private final Vehicle vehicle;
    private String diagnostic;
    private float previewHours;
    private float realHours;
    private boolean isFinished;
    private boolean isPaid;

    public WorkshopTask(String diagnostic, float previewHours, String initDate, Client c, Vehicle v) {
        this.diagnostic = diagnostic;
        this.previewHours = previewHours;
        this.initDate = initDate;
        realHours = 0f;
        isFinished = false;
        isPaid = false;
        client = c;
        vehicle = v;
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

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        this.isPaid = paid;
    }

    public String getInitDate() {
        return this.initDate;
    }

    public Client getCliente() {
        return this.client;
    }

    public Vehicle getVehiculo() {
        return this.vehicle;
    }

    public void addHoras(float horas) {
        if (isFinished) {
            return;
        }
        realHours += horas;
    }

    public void setTerminado() {
        isFinished = true;
    }

    @Override
    public int compareTo(WorkshopTask o) {
        return this.getInitDate().compareTo(o.getInitDate());
    }
}
