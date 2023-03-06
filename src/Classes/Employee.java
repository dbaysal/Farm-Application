package Classes;
import java.io.IOException;
import java.time.LocalDate;
import java.lang.*;

/**
 * Abstract class for storing employee information which are common for Veterinary and FarmWorker classes
 * @author Doğukan Baysal
 * @version 1.0
 */
public abstract class Employee implements Payment, Comparable<Employee>{
    private int empID;
    private String gender;
    private LocalDate dateOfBirth;


    /**
     * Empty constructor
     */
    public Employee(){}

    /**
     * Constructor with parameters
     * @param empID: int
     * @param gender: String - male or female
     * @param dateOfBirth: LocalDate
     */
    public Employee(int empID, String gender, LocalDate dateOfBirth) {
        this.empID = empID;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Method for setting employee ID
     * @param empID: int
     */
    public void setEmpID(int empID) {
        this.empID = empID;
    }

    /**
     * Method for setting gender
     * @param gender: String
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Method for setting date of birth
     * @param dateOfBirth: LocalDate
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Method for getting gender
     * @return String, gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Method for getting date of birth
     * @return LocalDate, date of birth
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Method for getting employee ID
     * @return int, employee ID
     */
    public int getEmpID() {
        return empID;
    }

    /**
     * Method for comparison implemented from Comparable interface
     * @param other the employee to be compared.
     * @return: int, this>other return 1, other>this return 0, other==this return -1
     */
    @Override
    public int compareTo(Employee other) {
        if(this.getSalary() > other.getSalary())
            return 1;
        else if (this.getSalary() < other.getSalary())
            return 0;
        else
            return -1;
    }

    /**
     * Abstract method for writing Employee to a file
     * @throws IOException
     */
    public abstract void writeToFile() throws IOException;



}
