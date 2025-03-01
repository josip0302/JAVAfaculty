package hr.fer.oprpp1.hw04.db;

import java.util.Objects;

public class StudentRecord {
    private String jmbag;
    private String lastName;
    private String firstName;
    private int finalGrade;

    public StudentRecord(String jmbag, String lastName, String firstName, int finalGrade) {
        this.jmbag = jmbag;
        this.lastName = lastName;
        this.firstName = firstName;
        this.finalGrade = finalGrade;
    }

    /**
     *
     * @return jmbag
     */
    public String getJmbag() {
        return jmbag;
    }

    /**
     *
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }


    /**
     *
     * @return final grade
     */
    public int getFinalGrade() {
        return finalGrade;
    }


    /**
     *
     * @param o
     * @return true if this equals o and false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentRecord that = (StudentRecord) o;
        return Objects.equals(jmbag, that.jmbag);
    }

    /**
     *
     * @return hashcode based on jmbag
     */
    @Override
    public int hashCode() {
        return Objects.hash(jmbag);
    }

    @Override
    public String toString() {
        return  jmbag + ' ' + lastName + ' '+ firstName + ' ' + finalGrade+'\n';
    }
}
