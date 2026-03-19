package src.main.java.com.workshopmanagement.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Mechanic class representing a mechanic of the workshop.
 * Inherits from Person and has additional attributes and methods specific to
 * mechanics.
 */
public class Mechanic extends Person {

    /**
     * Custom ordering for Mechanic
     * Compares mechanics based on their registration date.
     */
    public static final Comparator<Mechanic> BY_FECHA_ALTA = Comparator.comparing(Mechanic::getRegistrationDate);
    private static final String CSV_FORMAT = "registrationDate;specialty;workshopTasks";
    private final List<WorkshopTask> workshopTasks;
    private String registrationDate;
    private String specialty;

    /**
     * Constructor for Mechanic class.
     * Initializes the mechanic with the provided attributes and an empty list of
     * workshop tasks.
     *
     * @param name             The name of the mechanic
     * @param surname1         The first surname of the mechanic
     * @param surname2         The second surname of the mechanic
     * @param nif              The NIF (National Identity Document) of the mechanic
     * @param email            The email address of the mechanic
     * @param telephone        The telephone number of the mechanic
     * @param registrationDate The registration date of the mechanic
     * @param specialty        The specialty of the mechanic
     */
    public Mechanic(String name, String surname1, String surname2, String nif, String email, String telephone,
            String registrationDate, String specialty) {
        super(name, surname1, surname2, nif, email, telephone);
        this.registrationDate = registrationDate;
        this.specialty = specialty;

        this.workshopTasks = new ArrayList<>();
    }

    /**
     * Returns the CSV format string for Mechanic objects.
     * The format is:
     * "name;surname1;surname2;nif;email;telephone;registrationDate;specialty"
     *
     * @return the CSV format string
     */
    public static String getCsvFormat() {
        return Person.getCsvFormat() + ";" + Mechanic.CSV_FORMAT;
    }

    /**
     * Adds a workshop task to the mechanic's list of tasks.
     *
     * @param task the workshop task to be added
     * @return true if the task was successfully added, false otherwise
     */
    public boolean addWorkshopTask(WorkshopTask task) {
        return workshopTasks.add(task);
    }

    public int getWorkshopTasksSize() {
        return workshopTasks.size();
    }

    public int getIndexOfWorkshopTask(WorkshopTask wt) {
        return workshopTasks.indexOf(wt);
    }

    public WorkshopTask getWorkshopTaskByIndex(int i) {
        if (i < 0 || i >= workshopTasks.size()) {
            return null;
        }
        return workshopTasks.get(i);
    }

    // public WorkshopTask getWorkshopTask(int i) {
    // return workshopTasks.get(i);
    // // return workshopTasks.get(i)
    // // .stream()
    // // .filter(t -> t.getMechanic().equals(this))
    // // .toList();
    // }

    /**
     * Deletes a workshop task from the mechanic's list of tasks.
     *
     * @param task the workshop task to be deleted
     * @return true if the task was successfully deleted, false otherwise
     */
    public boolean deleteWorkshopTask(WorkshopTask task) {
        return workshopTasks.remove(task);
    }

    public void finishTask(WorkshopTask task) {
        task.finish();
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

    /**
     * Returns a string representation of the Mechanic object.
     * The string includes all the attributes of the Mechanic object.
     * The format is:
     * "name;surname1;surname2;nif;email;telephone;registrationDate;specialty;workshopTasksSize"
     * followed by a newline character.
     *
     * @return a string representation of the Mechanic object
     */
    @Override
    public String toString() {
        return super.toString() + this.registrationDate + ";" + this.specialty + this.workshopTasks.size() + ";";
    }
}
