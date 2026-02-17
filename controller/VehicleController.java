package Taller.controller;

import Taller.model.*;

public class VehicleController {

    private final DataStore data;

    public VehicleController(DataStore data) {
        this.data = data;
    }

    public boolean createVehicleToClient(String cNif, Vehicle.VehicleType vType, String csvVehicle) {
        if (cNif == null || vType == null || csvVehicle == null) return false;

        for (Client c : data.getClients()) {
            if (c.getNif().equalsIgnoreCase(cNif)) {
                Vehicle v = parseVehicle(vType, csvVehicle, c);
                if (v == null) return false;
                if (!c.addVehicle(v)) return false;
                return !data.getVehicles().contains(v);
            }
        }
        return false;
    }

    public String listVehicles() {
        StringBuilder sb = new StringBuilder();

        for (Vehicle v : data.getVehicles()) {
            sb.append(v);
        }
        return Vehicle.getCsvFormat() + "\n" + sb;
    }

    public String findVehiclesFromClient(String nif) {
        for (Client c : data.getClients()) {
            if (c.getNif().equalsIgnoreCase(nif)) {
                return Vehicle.getCsvFormat() + "\n" + c.listVehicles();
            }
        }
        return Vehicle.getCsvFormat();
    }

    public boolean updateVehicle(String registrationCode, String csvVehicle) {
        if (registrationCode == null || csvVehicle == null) return false;

        Vehicle v = findVehicleByRegistration(registrationCode);
        if (v == null) return false;

        String[] dataSet = csvVehicle.split(";");
        if (dataSet.length < 2) return false;

        if (!dataSet[0].isBlank()) v.setRegistrationCode(dataSet[0]);

        if (!dataSet[1].isBlank()) v.setModel(dataSet[1]);

        return false;
    }

    public boolean changeVehicleOwner(String registrationCode, String newOwnerNif) {

        if (registrationCode == null || newOwnerNif == null) return false;

        Vehicle v = findVehicleByRegistration(registrationCode);
        if (v == null) return false;

        Client newOwner = findClientByNif(newOwnerNif);
        if (newOwner == null) return false;

        Client oldOwner = v.getProprietary();

        if (oldOwner.equals(newOwner)) return true; // nothing to change

        oldOwner.deleteVehicle(v);
        v.setProprietary(newOwner);
        newOwner.addVehicle(v);

        return true;
    }

    public boolean changeVehicleType(String registrationCode, Vehicle.VehicleType newType) {
        if (registrationCode == null || newType == null) return false;

        Vehicle oldVehicle = findVehicleByRegistration(registrationCode);
        if (oldVehicle == null) return false;

        Client owner = oldVehicle.getProprietary();

        // Create new vehicle of desired type
        Vehicle newVehicle = parseVehicle(newType, oldVehicle.getRegistrationCode() + ";" + oldVehicle.getModel(), owner);

        if (newVehicle == null) return false;

        // Copy workshop tasks
        int index = 0;
        WorkshopTask task;
        while ((task = oldVehicle.getWorkshopTaskByIndex(index++)) != null) {
            newVehicle.addWorkshopTask(task);
        }

        boolean removedFromOwner = owner.deleteVehicle(oldVehicle);
        boolean removedFromGlobal = data.getVehicles().remove(oldVehicle);

        if (!removedFromOwner || !removedFromGlobal) {
            return false;
        }

        boolean addedToOwner = owner.addVehicle(newVehicle);
        boolean addedToGlobal = data.getVehicles().add(newVehicle);

        if (!addedToOwner || !addedToGlobal) {
            // rollback
            owner.addVehicle(oldVehicle);
            data.getVehicles().add(oldVehicle);
            return false;
        }
        return true;
    }

    public boolean deleteVehicle(String registrationCode) {
        if (registrationCode == null) return false;

        Vehicle v = findVehicleByRegistration(registrationCode);
        if (v == null) return false;

        // Remove from owner
        if (v.getProprietary() != null) {
            v.getProprietary().deleteVehicle(v);
        }

        // Remove from global list
        data.getVehicles().remove(v);

        return true;
    }


    private Vehicle parseVehicle(Vehicle.VehicleType vType, String csvVehicle, Client c) {
        if (csvVehicle == null) return null;
        String[] dataset = csvVehicle.split(";");
        if (dataset.length < 2) return null;
        switch (vType) {
            case Vehicle.VehicleType.MOTORCYCLE -> {
                return new Motorcycle(dataset[0], dataset[1], c);
            }
            case Vehicle.VehicleType.CAR -> {
                return new Car(dataset[0], dataset[1], c);
            }
            case Vehicle.VehicleType.VAN -> {
                return new Van(dataset[0], dataset[1], c);
            }
            case Vehicle.VehicleType.TRUCK -> {
                return new Truck(dataset[0], dataset[1], c);
            }
        }
        return null;
    }

    private Client findClientByNif(String nif) {
        for (Client c : data.getClients()) {
            if (c.getNif().equalsIgnoreCase(nif)) return c;
        }
        return null;
    }

    private Vehicle findVehicleByRegistration(String registration) {
        for (Vehicle v : data.getVehicles()) {
            if (v.getRegistrationCode().equalsIgnoreCase(registration)) return v;
        }
        return null;
    }
}
