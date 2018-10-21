package me.nikhila.person;

import java.util.Date;


/**
 * Person Class
 * @author Nikhila Galala
 *
 */
public class Person {

    private String ssn;
    private Date dateOfBirth;
    private String firstName;
    private String lastName;
    private Double heightIn;
    private Double weightLb;

    public Person(String ssn, Date dateOfBirth, String firstName, String lastName, Double heightIn, Double weightLb){
        this.ssn = ssn;
        this.dateOfBirth = dateOfBirth;
        this.firstName = firstName;
        this.lastName = lastName;
        this.heightIn = heightIn;
        this.weightLb = weightLb;
    }

    @Override
    public String toString() {
        return "Person{" +
                "ssn='" + ssn + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", heightIn=" + heightIn +
                ", weightLb=" + weightLb +
                '}';
    }

    public String getSsn() {
        return ssn;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Double getHeightIn() {
        return heightIn;
    }

    public Double getWeightLb() {
        return weightLb;
    }


}

