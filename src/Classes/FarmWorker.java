package Classes;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Class for storing farmworker information in the system which is also an Employee
 * @author Doğukan Baysal
 * @version 1.0
 */

public class FarmWorker extends Employee{
    private String previousFarmName;
    private int workexperience;

    /**
     * Empty constructor
     */
    public FarmWorker(){}

    /**
     * Constructor with parameters
     * @param empID: int
     * @param gender: String - male or female
     * @param dateOfBirth: LocalDate
     * @param previousFarmName: String
     * @param workexperience: int
     */
    public FarmWorker(int empID, String gender, LocalDate dateOfBirth, String previousFarmName, int workexperience) {
        super(empID, gender, dateOfBirth);
        this.previousFarmName = previousFarmName;
        this.workexperience = workexperience;
    }

    /**
     * Method for setting previous farm name
     * @param previousFarmName: String
     */
    public void setPreviousFarmName(String previousFarmName) {
        this.previousFarmName = previousFarmName;
    }

    /**
     * Method for setting work experience
     * @param workexperience: int
     */
    public void setWorkexperience(int workexperience) {
        this.workexperience = workexperience;
    }

    /**
     * Method for getting name of the previous farm
     * @return String, previous farm name
     */
    public String getPreviousFarmName() {
        return previousFarmName;
    }

    /**
     * Method for getting work experience
     * @return int, experience
     */
    public int getWorkexperience() {
        return workexperience;
    }

    /**
     * Method for getting salary implemented from Payment interface
     */
    @Override
    public double getSalary() {
        return (grossSalary + 0.02 * grossSalary * this.getWorkexperience());
    }



    /**
     * Method for printing FarmWorker information
     * @return String, FarmWorker information
     */
    @Override
    public String toString() {
        String line0 = ("Employee Type : FarmWorker\n");
        String line1 = ("Employee ID : " + super.getEmpID() + "\n");
        String line2 = ("Gender : " + super.getGender() + "\n");
        String line3 = ("Date of birth : " + super.getDateOfBirth() + "\n");
        String line4 = ("Salary : " + this.getSalary() + "\n");
        String line5 = ("Previous Farm Name : " + this.previousFarmName + "\n");
        String line6 = ("Work Experience : " + this.workexperience + "\n");

        return (line0 + line1 + line2 + line3 + line4 + line5 + line6);
    }

    /**
     * Write farmworker details to a file
     * @throws IOException
     */
    public void writeToFile() throws IOException {
        String information = this.toString();
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("workers.dat", true)));
        out.writeUTF(information);
        out.close();

    }

}
