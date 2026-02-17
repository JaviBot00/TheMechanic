package Taller.model;

import java.util.ArrayList;
import java.util.List;

public class DataStore {

    private final List<Client> clients;
    private final List<Vehicle> vehicles;
    private final List<Mechanic> mechanics;
    private final List<WorkshopTask> tasks;


    public DataStore(){
        clients = new ArrayList<>();
        vehicles = new ArrayList<>();
        mechanics = new ArrayList<>();
        tasks = new ArrayList<>();

        clients.add(new Client("Daniel", "Alvarez", "Castro", "89012345J", "danielalvarez@gmail.com", "677890123", 9));
        clients.add(new Client("Ana", "Gomez", "Lopez", "12345678A", "anagomez@gmail.com", "600123456", 2));
        clients.add(new Client("Pedro", "Hernandez", "Jimenez", "67890123G", "pedroh@gmail.com", "655678901", 7));
        clients.add(new Client("Carlos", "Perez", "Ruiz", "23456789C", "carlosperez@gmail.com", "611234567", 3));
        clients.add(new Client("Elena", "Ortega", "Vidal", "90123456K", "elenaortega@gmail.com", "688901234", 10));
        clients.add(new Client("Javier", "Fernandez", "Moreno", "45678901E", "javierf@gmail.com", "633456789", 5));
        clients.add(new Client("Luis", "Martinez", "Garcia", "87654321B", "luismartinez@gmail.com", "600654321", 1));
        clients.add(new Client("Laura", "Diaz", "Torres", "56789012F", "lauradiaz@gmail.com", "644567890", 6));
        clients.add(new Client("Sofia", "Romero", "Navarro", "78901234H", "sofiaromero@gmail.com", "666789012", 8));
        clients.add(new Client("Maria", "Lopez", "Sanchez", "34567890D", "marialopez@gmail.com", "622345678", 4));

        mechanics.add(new Mechanic("Paco", "Omero", "Garcia", "25658192R", "pacomelero@gmail.com", "654738129", "20180901", "Mechanic General"));
        mechanics.add(new Mechanic("Ana", "Lopez", "Martinez", "37849210A", "analopez@gmail.com", "612345678", "20170515", "Electricity"));
        mechanics.add(new Mechanic("Carlos", "Sanchez", "Ruiz", "48920317B", "carlossanchez@gmail.com", "623456789", "20190322", "Chapista"));
        mechanics.add(new Mechanic("Laura", "Fernandez", "Moreno", "59031428C", "laurafernandez@gmail.com", "634567890", "20160110", "Mechanic General"));
        mechanics.add(new Mechanic("Javier", "Hernandez", "Lopez", "60142539D", "javierh@gmail.com", "645678901", "20200105", "Electricity"));
        mechanics.add(new Mechanic("Sofia", "Diaz", "Torres", "71253640E", "sofiadiaz@gmail.com", "656789012", "20181130", "Mechanic General"));
        mechanics.add(new Mechanic("Pedro", "Romero", "Navarro", "82364751F", "pedroromero@gmail.com", "667890123", "20140218", "Chapista"));
        mechanics.add(new Mechanic("Elena", "Ortega", "Vidal", "93475862G", "elenaortega@gmail.com", "678901234", "20210412", "Electricity"));
        mechanics.add(new Mechanic("Daniel", "Alvarez", "Castro", "14586973H", "danielalvarez@gmail.com", "689012345", "20150907", "Mechanic General"));

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
