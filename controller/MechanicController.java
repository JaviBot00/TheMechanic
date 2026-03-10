package controller;

import data.DataStore;
import model.Mechanic;

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

            if (newData.getNif() != null && !newData.getNif().isBlank())
                m.setNif(newData.getNif());
            if (newData.getName() != null && !newData.getName().isBlank())
                m.setName(newData.getName());
            if (newData.getSurname1() != null && !newData.getSurname1().isBlank())
                m.setSurname1(newData.getSurname1());
            if (newData.getSurname2() != null && !newData.getSurname2().isBlank())
                m.setSurname2(newData.getSurname2());
            if (newData.getEmail() != null && !newData.getEmail().isBlank())
                m.setEmail(newData.getEmail());
            if (newData.getTelephone() != null && !newData.getTelephone().isBlank())
                m.setTelephone(newData.getTelephone());
            if (newData.getRegistrationDate() != null && !newData.getRegistrationDate().isBlank())
                m.setRegistrationDate(newData.getRegistrationDate());
            if (newData.getSpecialty() != null && !newData.getSpecialty().isBlank())
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

}
