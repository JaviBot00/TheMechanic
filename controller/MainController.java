package controller;

import data.DataStore;
import model.*;

import java.util.ArrayList;
import java.util.List;

public class MainController {

    private static MainController instance;
    private final ClientController clientController;
    private final VehicleController vehicleController;
    private final MechanicController mechanicController;
    private final WorkshopTaskController taskController;

    private MainController() {

        DataStore data = new DataStore();

        this.clientController = new ClientController(data);
        this.vehicleController = new VehicleController(data);
        this.mechanicController = new MechanicController(data);
        this.taskController = new WorkshopTaskController(data);
    }

    public static MainController getInstance() {
        if (instance == null) {
            instance = new MainController();
        }
        return instance;
    }

    public ClientController getClientController() {
        return clientController;
    }

    public VehicleController getVehicleController() {
        return vehicleController;
    }

    public MechanicController getMechanicController() {
        return mechanicController;
    }

    public WorkshopTaskController getTaskController() {
        return taskController;
    }

    // Other Stuff
    public String listPeople() {
        StringBuilder sb = new StringBuilder();

        List<Person> people = new ArrayList<>();
        // people.addAll(clients);
        // people.addAll(mechanics);

        for (Person p : people) {
            sb.append(p.toString()).append("\n");
        }
        return Person.getCsvFormat() + "\n" + sb;
    }

}
