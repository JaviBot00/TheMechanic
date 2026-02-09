package Taller.controller;

import Taller.model.*;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    private static Controller instance;
    private final List<Client> clients;
    private final List<Mechanic> mechanics;

    private Controller() {
        // Constructor privado para evitar instanciación externa
        clients = new ArrayList<>();
        mechanics = new ArrayList<>();

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

        mechanics.add(new Mechanic("Paco", "Melero", "Garcia", "25658192R", "pacomelero@gmail.com", "654738129", "20180901", "Mechanic General"));
        mechanics.add(new Mechanic("Ana", "Lopez", "Martinez", "37849210A", "analopez@gmail.com", "612345678", "20170515", "Electricista"));
        mechanics.add(new Mechanic("Carlos", "Sanchez", "Ruiz", "48920317B", "carlossanchez@gmail.com", "623456789", "20190322", "Chapista"));
        mechanics.add(new Mechanic("Laura", "Fernandez", "Moreno", "59031428C", "laurafernandez@gmail.com", "634567890", "20160110", "Mechanic General"));
        mechanics.add(new Mechanic("Javier", "Hernandez", "Lopez", "60142539D", "javierh@gmail.com", "645678901", "20200105", "Electricista"));
        mechanics.add(new Mechanic("Sofia", "Diaz", "Torres", "71253640E", "sofiadiaz@gmail.com", "656789012", "20181130", "Mechanic General"));
        mechanics.add(new Mechanic("Pedro", "Romero", "Navarro", "82364751F", "pedroromero@gmail.com", "667890123", "20140218", "Chapista"));
        mechanics.add(new Mechanic("Elena", "Ortega", "Vidal", "93475862G", "elenaortega@gmail.com", "678901234", "20210412", "Electricista"));
        mechanics.add(new Mechanic("Daniel", "Alvarez", "Castro", "14586973H", "danielalvarez@gmail.com", "689012345", "20150907", "Mechanic General"));
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    // CRUD Clients
    public boolean addClient(String csvClient) {
        Client c = parseClient(csvClient);
        if (c == null) return false;

        for (Client aux : clients) {
            if (aux.getNif().equals(c.getNif())) {
                return false;
            }
        }
        clients.add(c);
        return true;
    }

    public String listClients() {
        StringBuilder sb = new StringBuilder();

//        Collections.sort(clients);
        clients.sort(Client.BY_COD_CLIENTE);

        for (Client c : clients) {
            sb.append(c).append("\n");
        }
        return Client.getCsvFormat() + "\n" + sb;
    }

    public String findClientsBySurname(String surname) {
        StringBuilder sb = new StringBuilder();
        for (Client c : clients) {
            if (c.getSurname1().equalsIgnoreCase(surname)) {
                sb.append(c).append("\n");
            }
        }
        return Client.getCsvFormat() + "\n" + sb;
    }

    public String findClientByNif(String nif) {
        StringBuilder sb = new StringBuilder();
        for (Client c : clients) {
            if (c.getNif().equalsIgnoreCase(nif)) {
                sb.append(c).append("\n");
            }
        }
        return Client.getCsvFormat() + "\n" + sb;
    }

    public boolean updateClient(int cCode, String csvClient) {
        Client c = parseClient(csvClient);
        if (c == null) return false;

        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getClientCode() == cCode) {
                clients.set(i, c);
                return true;
            }
        }
        return false;
    }

    public boolean deleteClientByNif(String nif) {
        if (nif == null) return false;
        Client aux = null;
        for (Client c : clients) {
            if (c.getNif().equalsIgnoreCase(nif)) {
                aux = c;
//                return true;
            }
        }
        return aux != null && clients.remove(aux);
//        return clients.removeIf(c -> c.getNif().equalsIgnoreCase(nif));
    }

    // CRUD Vehicle
    public boolean addVehicleToClient(String nif, Integer vType, String csvVehicle) {
        if (nif == null || vType == null || csvVehicle == null) return false;

//        String clientData = findClientByNif(nif);
//        if (clientData == null) return false;
//
//        String[] csvClient = clientData.split("\n");
//        if (csvClient.length < 2) return false;
//
//        Client c = parseClient(csvClient[1]);

        for (Client c : clients) {
            if (c.getNif().equalsIgnoreCase(nif)) {
                Vehicle v = parseVehicle(vType, csvVehicle, c);
                if (v == null) return false;
                return c.addVehicle(v);
            }
        }
        return false;
    }

    public String listVehicles() {
        StringBuilder sb = new StringBuilder();
        
        for (Client c : clients) {
//            for (int i = 0; i < c.getNumVehicles(); i++){
//                sb.append(c.getVehicle(i).toString()).append("\n");
//            }
            sb.append(c.listVehicles());
        }
        return Vehicle.getCsvFormat() + "\n" + sb;
    }

    public String listVehiclesFromClient(String nif) {
        for (Client c : clients) {
            if (c.getNif().equalsIgnoreCase(nif)) {
                return Vehicle.getCsvFormat() + "\n" + c.listVehicles();
            }
        }
        return Vehicle.getCsvFormat();
    }

    // CRUD Mechanics
    public String listMechanics() {
        StringBuilder sb = new StringBuilder();

//        Collections.sort(mechanics);
        mechanics.sort(Mechanic.BY_FECHA_ALTA);
        for (Mechanic m : mechanics) {
            sb.append(m.toString()).append("\n");
        }
        return Mechanic.getCsvFormat() + "\n" + sb;
    }

    public boolean addMechanic(String csvMecanico) {
        Mechanic m = parseMechanic(csvMecanico);
        if (m == null) return false;

        for (Mechanic aux : mechanics) {
            if (aux.getNif().equals(m.getNif())) {
                return false;
            }
        }
        mechanics.add(m);
        return true;
    }

    public String findMechanicByNif(String nif) {
        StringBuilder sb = new StringBuilder();
        for (Mechanic m : mechanics) {
            if (m.getNif().equalsIgnoreCase(nif)) {
                sb.append(m).append("\n");
            }
        }
        return Mechanic.getCsvFormat() + "\n" + sb;
    }

    public boolean updateMechanic(String csvMechanic) {
        Mechanic m = parseMechanic(csvMechanic);
        if (m == null) return false;

        for (int i = 0; i < mechanics.size(); i++) {
            if (mechanics.get(i).getNif().equalsIgnoreCase(m.getNif())) {
                mechanics.set(i, m);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMechanicByNif(String nif) {
//        if (nif == null) return false;
//
//        for (Mechanic m : mechanics) {
//            if (m.getNif().equalsIgnoreCase(nif)) {
//                mechanics.remove(m);
//                return true;
//            }
//        }
//        return false;
        return mechanics.removeIf(m -> m.getNif().equalsIgnoreCase(nif));
    }

    // Other Stuff
    public String listPeople() {
        StringBuilder sb = new StringBuilder();

        List<Person> people = new ArrayList<>();
        people.addAll(clients);
        people.addAll(mechanics);

        for (Person p : people) {
            sb.append(p.toString()).append("\n");
        }
        return Person.getCsvFormat() + "\n" + sb;
    }

    private Client parseClient(String csvClient) {
        if (csvClient == null) return null;
        String[] dataset = csvClient.split(";");
        if (dataset.length < 7) return null;
        return new Client(dataset[0], dataset[1], dataset[2], dataset[3], dataset[4], dataset[5], Integer.parseInt(dataset[6]));
    }

    private Vehicle parseVehicle(int vType, String csvVehicle, Client c) {
        if (csvVehicle == null) return null;
        String[] dataset = csvVehicle.split(";");
        if (dataset.length < 2) return null;
        switch (vType) {
            case 1 -> {
                return new Motorcycle(dataset[0], dataset[1], c);
            }
            case 2 -> {
                return new Car(dataset[0], dataset[1], c);
            }
            case 3 -> {
                return new Van(dataset[0], dataset[1], c);
            }
            case 4 -> {
                return new Truck(dataset[0], dataset[1], c);
            }
        }
        return null;
    }

    private Mechanic parseMechanic(String csvMechanic) {
        if (csvMechanic == null) return null;
        String[] dataset = csvMechanic.split(";");
        if (dataset.length < 8) return null;
        return new Mechanic(dataset[0], dataset[1], dataset[2], dataset[3], dataset[4], dataset[5], dataset[6], dataset[7]);
    }
}
