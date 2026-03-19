package src.main.java.com.workshopmanagement.controller;

import src.main.java.com.workshopmanagement.data.DataStore;
import src.main.java.com.workshopmanagement.model.Mechanic;

public class MechanicController {

    private final DataStore data;

    public MechanicController(DataStore data) {
        this.data = data;
    }

    public boolean createMechanic(String csvMechanic) {
        Mechanic m = parseMechanic(csvMechanic);
        if (m == null)
            return false;

        for (Mechanic aux : data.getMechanics()) {
            if (aux.getNif().equalsIgnoreCase(m.getNif())) {
                return false;
            }
        }
        return data.getMechanics().add(m);
    }

    public String listMechanics() {
        StringBuilder sb = new StringBuilder();

        // Collections.sort(mechanics);
        data.getMechanics().sort(Mechanic.BY_FECHA_ALTA);
        for (Mechanic m : data.getMechanics()) {
            sb.append(m.toString()).append("\n");
        }
        return Mechanic.getCsvFormat() + "\n" + sb;
    }

    public String findMechanicByNif(String nif) {
        StringBuilder sb = new StringBuilder();
        for (Mechanic m : data.getMechanics()) {
            if (m.getNif().equalsIgnoreCase(nif)) {
                sb.append(m).append("\n");
            }
        }
        return Mechanic.getCsvFormat() + "\n" + sb;
    }

    public boolean updateMechanic(String csvMechanic) {

        Mechanic newData = parseMechanic(csvMechanic);
        if (newData == null)
            return false;

        for (Mechanic m : data.getMechanics()) {

            // if (m.getNif().equalsIgnoreCase(newData.getNif())) {

            if (isValid(newData.getNif()))
                m.setNif(newData.getNif());
            if (isValid(newData.getName()))
                m.setName(newData.getName());
            if (isValid(newData.getSurname1()))
                m.setSurname1(newData.getSurname1());
            if (isValid(newData.getSurname2()))
                m.setSurname2(newData.getSurname2());
            if (isValid(newData.getEmail()))
                m.setEmail(newData.getEmail());
            if (isValid(newData.getTelephone()))
                m.setTelephone(newData.getTelephone());
            if (isValid(newData.getRegistrationDate()))
                m.setRegistrationDate(newData.getRegistrationDate());
            if (isValid(newData.getSpecialty()))
                m.setSpecialty(newData.getSpecialty());

            return true;
            // }
        }
        return false;
    }

    // public boolean deleteMechanicByNif(String nif) {
    // if (nif == null) return false;
    //
    // for (Mechanic m : data.getMechanics()) {
    // if (m.getNif().equalsIgnoreCase(nif)) {
    // data.getMechanics().remove(m);
    // return true;
    // }
    // }
    // return false;
    // return data.getMechanics().removeIf(m -> m.getNif().equalsIgnoreCase(nif));
    // }

    public boolean deleteMechanicByNif(String nif) {
        for (Mechanic m : data.getMechanics()) {
            if (m.getNif().equalsIgnoreCase(nif)) {
                if (m.getWorkshopTasksSize() > 0)
                    return false;
                return data.getMechanics().remove(m);
            }
        }
        return false;
    }

    private Mechanic parseMechanic(String csvMechanic) {
        if (csvMechanic == null)
            return null;
        String[] dataset = csvMechanic.split(";");
        if (dataset.length < 8)
            return null;
        return new Mechanic(dataset[0], dataset[1], dataset[2], dataset[3], dataset[4], dataset[5], dataset[6],
                dataset[7]);
    }

    private boolean isValid(String s) {
        return s != null && !s.isBlank();
    }
}
