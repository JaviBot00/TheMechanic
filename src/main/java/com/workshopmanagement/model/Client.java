package src.main.java.com.workshopmanagement.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Client class representing a client of the workshop.
 * Inherits from Person and has additional attributes and methods specific to
 * clients.
 */
public class Client extends Person {

    /**
     * Custom ordering for Client
     * Compares clients based on their client code.
     */
    public static final Comparator<Client> BY_COD_CLIENT = Comparator.comparingInt(Client::getClientCode);
    private static final String CSV_FORMAT = "clientCode";
    private final int clientCode;
    private final List<Vehicle> vehicles;

    /**
     * Constructor for Client class.
     * Initializes the client with the provided attributes and an empty list of
     * vehicles.
     *
     * @param name       The name of the client.
     * @param surname1   The first surname of the client.
     * @param surname2   The second surname of the client.
     * @param nif        The NIF (identification number) of the client.
     * @param email      The email address of the client.
     * @param telephone  The telephone number of the client.
     * @param clientCode The unique code assigned to the client.
     */
    public Client(String name, String surname1, String surname2, String nif, String email, String telephone,
            int clientCode) {
        super(name, surname1, surname2, nif, email, telephone);
        this.clientCode = clientCode;
        this.vehicles = new ArrayList<>();
    }

    /**
     * Retrieves the CSV format string for the Client class, which includes the CSV
     * format of the Person class and the clientCode attribute.
     * The format is: "name;surname1;surname2;nif;email;telephone;clientCode"
     *
     * @return A string representing the CSV format for the Client class.
     */
    public static String getCsvFormat() {
        return Person.getCsvFormat() + ";" + Client.CSV_FORMAT;
    }

    /**
     * Lists the vehicles associated with the client.
     *
     * @return A string representation in CSS of the client's vehicles.
     */
    public String listVehicles() {
        StringBuilder sb = new StringBuilder();

        for (Vehicle v : vehicles) {
            sb.append(v).append("\n");
        }
        return sb.toString();
    }

    /**
     * Retrieves a vehicle from the client's list of vehicles based on its index.
     *
     * @param index The index of the vehicle to be retrieved.
     * @return The vehicle at the specified index, or null if the index is out of
     *         bounds.
     */
    public Vehicle getVehicle(int index) {
        if (index < 0 || index >= vehicles.size())
            return null;
        return vehicles.get(index);
    }

    /**
     * Retrieves a vehicle from the client's list of vehicles based on its
     * registration code.
     *
     * @param rC The registration code of the vehicle to be retrieved.
     * @return The vehicle with the specified registration code, or null if no such
     *         vehicle exists in the client's list.
     */
    public Vehicle getVehicleByRegistrationCode(String rC) {
        for (Vehicle v : vehicles) {
            if (v.getRegistrationCode().equalsIgnoreCase(rC)) {
                return v;
            }
        }
        return null;
    }

    /**
     * Adds a vehicle to the client's list of vehicles.
     *
     * @param v The vehicle to be added.
     * @return true if the vehicle was added successfully, false if the vehicle is
     *         null or already exists in the client's list.
     */
    public boolean addVehicle(Vehicle v) {
        if (v != null) {
            Vehicle existing = this.getVehicleByRegistrationCode(v.getRegistrationCode());
            if (existing != null) {
                return false;
            }
            vehicles.add(v);
            return true;
        }
        return false;
    }

    /**
     * Updates a vehicle in the client's list of vehicles based on its registration
     * code.
     * The method searches for the vehicle with the specified registration code and
     * replaces it with the new vehicle information if found.
     *
     * @param rC   The registration code of the vehicle to be updated.
     * @param newV The new vehicle information to replace the existing vehicle.
     * @return true if the vehicle was updated successfully, false if no such
     *         vehicle exists in the client's list or if the new vehicle is null.
     */
    public boolean updateVehicle(String rC, Vehicle newV) {
        // Vehicle v = this.getVehicleByRegistrationCode(rC);
        // if (v == null) return false;
        if (newV == null)
            return false;

        if (!newV.getRegistrationCode().equalsIgnoreCase(rC))
            return false;

        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getRegistrationCode().equalsIgnoreCase(rC)) {
                vehicles.set(i, newV);
                return true;
            }
        }
        return false;
    }

    /**
     * Deletes a vehicle from the client's list of vehicles based on its
     * registration code.
     * The method searches for the vehicle with the specified registration code and
     * removes it from the list if found.
     *
     * @param v The vehicle to be removed.
     * @return true if the vehicle was deleted successfully, false if no such
     *         vehicle exists in the client's list.
     */
    public boolean deleteVehicle(Vehicle v) {
        return vehicles.remove(v);
    }

    public int getClientCode() {
        return clientCode;
    }

    public int getVehiclesSize() {
        return vehicles.size();
    }

    /**
     * Returns a string representation of the Client object in CSV format.
     * The format is: "name;surname1;surname2;nif;email;telephone;clientCode"
     * followed by a newline character.
     * The string includes the CSV representation of the Person attributes and the
     * clientCode specific to the Client class.
     *
     * @return A string representing the Client in CSV format.
     */
    @Override
    public String toString() {
        // return "Client [codCliente=" + codCliente + ", nombre=" + nombre + ",
        // apellidos1=" + apellidos1
        // + ", apellidos2=" + apellidos2 + ", dni=" + dni + ", email=" + email + ",
        // telefono=" + telefono + "]";
        return super.toString() + clientCode + ";";
    }

    /**
     * Compares this client with another person based on their client codes.
     * If the other person is not a Client, it delegates to the superclass's
     * compareTo method.
     * Otherwise, it compares the client codes of this client and the other person.
     *
     * @param p The other person to compare with
     * @return a negative integer, zero, or a positive integer as this object is
     *         less than, equal to, or greater than the specified object
     */
    @Override
    public int compareTo(Person p) {
        if (!(p instanceof Client)) {
            return super.compareTo(p);
        }
        return Integer.compare(this.clientCode, ((Client) p).getClientCode());
    }

    // @Override
    // public int compareTo(Client p) {
    // if (p instanceof Client){
    // Integer c1 = new Integer(this.getCodCliente());
    // Integer c2 = new Integer(((Client)p).getCodCliente());
    // return c1.compareTo(c2);
    // }
    // return super.compareTo(p);
    // }
}
