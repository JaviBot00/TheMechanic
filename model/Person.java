package Taller.model;

import java.util.Comparator;

/**
 * Abstract class representing a person in the workshop system.
 * This class serves as a base for specific types of people, such as clients and mechanics.
 * It implements the Comparable interface to allow sorting by surname and name.
 */
public abstract class Person implements Comparable<Person> {

    private static final String CSV_FORMAT = "name;surname1;surname2;nif;email;telephone";
    private String name;
    private String surname1;
    private String surname2;
    private String nif;
    private String email;
    private String telephone;

    /**
     * Constructor for the Person class.
     *
     * @param name      The name of the person.
     * @param surname1  The first surname of the person.
     * @param surname2  The second surname of the person.
     * @param nif       The NIF (identification number) of the person.
     * @param email     The email address of the person.
     * @param telephone The telephone number of the person.
     */
    public Person(String name, String surname1, String surname2, String nif, String email, String telephone) {
        this.name = name;
        this.surname1 = surname1;
        this.surname2 = surname2;
        this.nif = nif;
        this.email = email;
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname1() {
        return surname1;
    }

    public void setSurname1(String surname1) {
        this.surname1 = surname1;
    }

    public String getSurname2() {
        return surname2;
    }

    public void setSurname2(String surname2) {
        this.surname2 = surname2;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    
    /**
     * Gets the CSV format string for the Person class.
     * "name;surname1;surname2;nif;email;telephone"
     *
     * @return A string representing the CSV format for a Person.
     */
    public static String getCsvFormat() {
        return CSV_FORMAT;
    }

    /**
     * Returns a string representation of the Person object in CSV format.
     * The format is: "name;surname1;surname2;nif;email;telephone;"
     *
     * @return A string representing the Person in CSV format.
     */
    @Override
    public String toString() {
        return this.name + ";"
            + this.surname1 + ";"
            + this.surname2 + ";"
            + this.nif + ";"
            + this.email + ";"
            + this.telephone + ";";
    }

//    @Override
//    public int compareTo(Person p) {
//        return this.codCliente - p.codCliente; // sort by id
//        if (this.apellido1 == null && p.apellido1 == null) return 0;
//        if (this.apellido1 == null) return -1;
//        if (p.apellido1 == null) return 1;
//        return this.apellido1.compareTo(p.apellido1);
//        return this.apellido1.compareToIgnoreCase(p.apellido1);
//        return Comparator
//                .nullsLast(String::compareTo)
//                .compare(this.apellido1, p.apellido1);

//    @Override
//    public int compareTo(Person p) {
//        return Comparator
//                .comparing(Person::getApellido1, String.CASE_INSENSITIVE_ORDER)
//                .thenComparing(Person::getApellido2, String.CASE_INSENSITIVE_ORDER)
//                .thenComparing(Person::getNombre, String.CASE_INSENSITIVE_ORDER)
//                .compare(this, p);
//    }

    @Override
    public int compareTo(Person p) {
        return Comparator
            .comparing(Person::getSurname1,
                Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER))
            .thenComparing(Person::getSurname2,
                Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER))
            .thenComparing(Person::getName,
                Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER))
            .compare(this, p);
    }

}
