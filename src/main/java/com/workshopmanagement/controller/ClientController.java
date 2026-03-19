package src.main.java.com.workshopmanagement.controller;

import java.util.Collections;

import src.main.java.com.workshopmanagement.data.DataStore;
import src.main.java.com.workshopmanagement.model.Client;

public class ClientController {

    private final DataStore data;

    public ClientController(DataStore data) {
        this.data = data;
    }

    public boolean createClient(String csvClient) {
        Client c = parseClient(csvClient);
        if (c == null)
            return false;

        for (Client aux : data.getClients()) {
            if (aux.getNif().equalsIgnoreCase(c.getNif())) {
                return false;
            }
        }
        return data.getClients().add(c);
    }

    public int getTotalClients() {
        return data.getClients().size();
    }

    public String listClients() {
        StringBuilder sb = new StringBuilder();
        // Collections.sort(clients);
        data.getClients().sort(Client.BY_COD_CLIENT);

        for (Client c : data.getClients()) {
            sb.append(c).append("\n");
        }
        return Client.getCsvFormat() + "\n" + sb;
    }

    public String findClientByNif(String nif) {
        // StringBuilder sb = new StringBuilder();
        for (Client c : data.getClients()) {
            if (c.getNif().equalsIgnoreCase(nif)) {
                // sb.append(c).append("\n");
                return Client.getCsvFormat() + "\n" + c;
            }
        }
        // return Client.getCsvFormat() + "\n" + sb;
        return Client.getCsvFormat();
    }

    public String findClientsBySurname(String surname) {
        StringBuilder sb = new StringBuilder();
        for (Client c : data.getClients()) {
            if (c.getSurname1().equalsIgnoreCase(surname)) {
                sb.append(c).append("\n");
            }
        }
        return Client.getCsvFormat() + "\n" + sb;
    }

    public boolean updateClient(int cCode, String csvClient) {
        Client c = parseClient(csvClient);
        if (c == null)
            return false;

        for (Client aux : data.getClients()) {
            if (aux.getClientCode() == cCode) {
                if (isValid(c.getName()))
                    aux.setName(c.getName());
                if (isValid(c.getSurname1()))
                    aux.setSurname1(c.getSurname1());
                if (isValid(c.getSurname2()))
                    aux.setSurname2(c.getSurname2());
                if (isValid(c.getNif()))
                    aux.setNif(c.getNif());
                if (isValid(c.getEmail()))
                    aux.setEmail(c.getEmail());
                if (isValid(c.getTelephone()))
                    aux.setTelephone(c.getTelephone());
                return true;
            }
        }
        return false;
    }

    // public boolean deleteClientByNif(String nif) {
    // if (nif == null) return false;
    // for (Client c : data.getClients()) {
    // if (c.getNif().equalsIgnoreCase(nif)) {
    // // Do not delete if client still has vehicles
    // if (c.getVehiclesSize() > 0) return false;
    //
    // return data.getClients().remove(c);
    // }
    // }
    // return false;
    // return data.getClients().removeIf(c -> c.getNif().equalsIgnoreCase(nif));
    // }

    public boolean deleteClientByNif(String nif) {
        if (nif == null)
            return false;

        Client clientToRemove = data.getClients().stream().filter(c -> c.getNif().equalsIgnoreCase(nif)).findFirst()
                .orElse(null);

        if (clientToRemove == null)
            return false;

        // Remove all vehicles from global list
        data.getVehicles().removeIf(v -> v.getProprietary().equals(clientToRemove));

        // Remove client
        return data.getClients().remove(clientToRemove);
    }

    private Client parseClient(String csvClient) {
        if (csvClient == null)
            return null;
        String[] dataset = csvClient.split(";");
        if (dataset.length < 7)
            return null;
        return new Client(dataset[0], dataset[1], dataset[2], dataset[3], dataset[4], dataset[5],
                Integer.parseInt(dataset[6]));
    }

    private boolean isValid(String s) {
        return s != null && !s.isBlank();
    }

}
