package Taller.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Mechanic extends Person {

    // Custom ordering for Mechanic
    public static final Comparator<Mechanic> BY_FECHA_ALTA =
            Comparator.comparing(Mechanic::getRegistrationDate);
    private static final String CSV_FORMAT = "registrationDate;specialty";
    private String registrationDate;
    private String specialty;
    private final List<WorkshopTask> workshopTasks;

    public Mechanic(String name, String surname1, String surname2, String nif, String email, String telephone, String registrationDate, String specialty) {
        super(name, surname1, surname2, nif, email, telephone);
        this.registrationDate = registrationDate;
        this.specialty = specialty;

        this.workshopTasks = new ArrayList<>();
    }

    public static String getCsvFormat() {
        return Person.getCsvFormat() + ";" + Mechanic.CSV_FORMAT;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public boolean addWorkshopTasks(String diagnostic, float previewHours, String initDate, Vehicle v) {
        Client c = v.getProprietary();
        WorkshopTask tt = new WorkshopTask(diagnostic, previewHours, initDate, c, v);
        workshopTasks.add(tt);
        // tt.setFechaInicio(initDate);
        return v.addWorkshopTask(tt);
    }

    public WorkshopTask getTrabajoTaller(int i) {
        return workshopTasks.get(i);
    }

    @Override
    public String toString() {
        return super.toString() + this.registrationDate + ";" + this.specialty;
    }
}
