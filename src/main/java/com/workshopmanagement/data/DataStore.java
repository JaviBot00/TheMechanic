package src.main.java.com.workshopmanagement.data;

import java.util.ArrayList;
import java.util.List;

import src.main.java.com.workshopmanagement.model.Car;
import src.main.java.com.workshopmanagement.model.Client;
import src.main.java.com.workshopmanagement.model.Mechanic;
import src.main.java.com.workshopmanagement.model.Motorcycle;
import src.main.java.com.workshopmanagement.model.Truck;
import src.main.java.com.workshopmanagement.model.Van;
import src.main.java.com.workshopmanagement.model.Vehicle;
import src.main.java.com.workshopmanagement.model.WorkshopTask;

public class DataStore {

    private final List<Client> clients;
    private final List<Vehicle> vehicles;
    private final List<Mechanic> mechanics;
    private final List<WorkshopTask> tasks;

    public DataStore() {
        clients = new ArrayList<>();
        vehicles = new ArrayList<>();
        mechanics = new ArrayList<>();
        tasks = new ArrayList<>();

        Client c1 = new Client("Daniel", "Alvarez", "Castro", "89012345J", "danielalvarez@gmail.com", "677890123", 9);
        Client c2 = new Client("Ana", "Gomez", "Lopez", "12345678A", "anagomez@gmail.com", "600123456", 2);
        Client c3 = new Client("Pedro", "Hernandez", "Jimenez", "67890123G", "pedroh@gmail.com", "655678901", 7);
        Client c4 = new Client("Carlos", "Perez", "Ruiz", "23456789C", "carlosperez@gmail.com", "611234567", 3);
        Client c5 = new Client("Elena", "Ortega", "Vidal", "90123456K", "elenaortega@gmail.com", "688901234", 10);
        Client c6 = new Client("Javier", "Fernandez", "Moreno", "45678901E", "javierf@gmail.com", "633456789", 5);
        Client c7 = new Client("Luis", "Martinez", "Garcia", "87654321B", "luismartinez@gmail.com", "600654321", 1);
        Client c8 = new Client("Laura", "Diaz", "Torres", "56789012F", "lauradiaz@gmail.com", "644567890", 6);
        Client c9 = new Client("Sofia", "Romero", "Navarro", "78901234H", "sofiaromero@gmail.com", "666789012", 8);
        Client c10 = new Client("Maria", "Lopez", "Sanchez", "34567890D", "marialopez@gmail.com", "622345678", 4);

        clients.add(c1);
        clients.add(c2);
        clients.add(c3);
        clients.add(c4);
        clients.add(c5);
        clients.add(c6);
        clients.add(c7);
        clients.add(c8);
        clients.add(c9);
        clients.add(c10);

        Mechanic m1 = new Mechanic("Paco", "Omero", "Garcia", "25658192R", "pacomelero@gmail.com", "654738129",
                "20180901", "Mechanic General");
        Mechanic m2 = new Mechanic("Ana", "Lopez", "Martinez", "37849210A", "analopez@gmail.com", "612345678",
                "20170515", "Electricity");
        Mechanic m3 = new Mechanic("Carlos", "Sanchez", "Ruiz", "48920317B", "carlossanchez@gmail.com", "623456789",
                "20190322", "Chapista");
        Mechanic m4 = new Mechanic("Laura", "Fernandez", "Moreno", "59031428C", "laurafernandez@gmail.com", "634567890",
                "20160110", "Mechanic General");
        Mechanic m5 = new Mechanic("Javier", "Hernandez", "Lopez", "60142539D", "javierh@gmail.com", "645678901",
                "20200105", "Electricity");
        Mechanic m6 = new Mechanic("Sofia", "Diaz", "Torres", "71253640E", "sofiadiaz@gmail.com", "656789012",
                "20181130", "Mechanic General");
        Mechanic m7 = new Mechanic("Pedro", "Romero", "Navarro", "82364751F", "pedroromero@gmail.com", "667890123",
                "20140218", "Chapista");
        Mechanic m8 = new Mechanic("Elena", "Ortega", "Vidal", "93475862G", "elenaortega@gmail.com", "678901234",
                "20210412", "Electricity");
        Mechanic m9 = new Mechanic("Daniel", "Alvarez", "Castro", "14586973H", "danielalvarez@gmail.com", "689012345",
                "20150907", "Mechanic General");

        mechanics.add(m1);
        mechanics.add(m2);
        mechanics.add(m3);
        mechanics.add(m4);
        mechanics.add(m5);
        mechanics.add(m6);
        mechanics.add(m7);
        mechanics.add(m8);
        mechanics.add(m9);

        Vehicle v1 = new Car("Toyota", "Corolla", c1);
        Vehicle v2 = new Car("Ford", "Focus", c2);
        Vehicle v3 = new Motorcycle("BMW", "X5", c1);
        Vehicle v4 = new Motorcycle("Volkswagen", "Golf", c3);
        Vehicle v5 = new Truck("Honda", "Civic", c5);
        Vehicle v6 = new Truck("Renault", "Megane", c8);
        Vehicle v7 = new Van("Volkswagen", "Passat", c10);
        Vehicle v8 = new Van("Seat", "Ibiza", c6);

        vehicles.add(v1);
        vehicles.add(v2);
        vehicles.add(v3);
        vehicles.add(v4);
        vehicles.add(v5);
        vehicles.add(v6);
        vehicles.add(v7);
        vehicles.add(v8);

        WorkshopTask wT1 = new WorkshopTask("Oil Change", 8, "20260217", c1, v1, m1);
        WorkshopTask wT2 = new WorkshopTask("Brake Check", 4, "20260315", c2, v2, m2);
        WorkshopTask wT3 = new WorkshopTask("Tire Rotation", 6, "20260410", c3, v3, m3);
        WorkshopTask wT4 = new WorkshopTask("Engine Check", 12, "20260515", c4, v4, m4);
        WorkshopTask wT5 = new WorkshopTask("Battery Replacement", 2, "20260610", c5, v5, m5);
        WorkshopTask wT6 = new WorkshopTask("Transmission Service", 10, "20260715", c6, v6, m6);
        WorkshopTask wT7 = new WorkshopTask("Air Filter Change", 3, "20260810", c7, v7, m7);
        WorkshopTask wT8 = new WorkshopTask("Coolant Flush", 5, "20260915", c8, v8, m8);

        tasks.add(wT1);
        tasks.add(wT2);
        tasks.add(wT3);
        tasks.add(wT4);
        tasks.add(wT5);
        tasks.add(wT6);
        tasks.add(wT7);
        tasks.add(wT8);
    }

    public List<Client> getClients() {
        return clients;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public List<Mechanic> getMechanics() {
        return mechanics;
    }

    public List<WorkshopTask> getWorkshopTask() {
        return tasks;
    }
}
