package Classes;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Class for storing Veterinary information in the system which is also an Employee
 * @author Doğukan Baysal
 * @version 1.0
 */
public class Veterinary extends Employee{
    private boolean BScDegree;
    private LocalDate dateOfGraduation;
    private int expertiseLevel;


    /**
     * Empty constructor
     */
    public Veterinary(){}

    /**
     * Constructor with parameters
     * @param empID: int
     * @param gender: String - male or female
     * @param dateOfBirth: LocalDate
     * @param BScDegree: boolean
     * @param dateOfGraduation: LocalDate
     * @param expertiseLevel: int
     */
    public Veterinary(int empID, String gender, LocalDate dateOfBirth, boolean BScDegree, LocalDate dateOfGraduation, int expertiseLevel) {
        super(empID, gender, dateOfBirth);
        this.BScDegree = BScDegree;
        this.dateOfGraduation = dateOfGraduation;
        this.expertiseLevel = expertiseLevel;
    }

    /**
     * Method for setting availability of BScDegree
     * @param BScDegree: boolean
     */
    public void setBScDegree(boolean BScDegree) {
        this.BScDegree = BScDegree;
    }

    /**
     * Method for setting date of graduation
     * @param dateOfGraduation: LocalDate
     */
    public void setDateOfGraduation(LocalDate dateOfGraduation) {
        this.dateOfGraduation = dateOfGraduation;
    }

    /**
     * method for getting expertise level of a veterinary
     * @param expertiseLevel: int
     */
    public void setExpertiseLevel(int expertiseLevel) {
        this.expertiseLevel = expertiseLevel;
    }

    /**
     * Method for getting availability of BScDegree
     * @return boolean, BScDegree
     */
    public boolean isBScDegree() {
        return BScDegree;
    }

    /**
     * Method for getting date of graduation
     * @return LocalDate, date of graduation
     */
    public LocalDate getDateOfGraduation() {
        return dateOfGraduation;
    }


    /**
     * Method for getting expertise level of a veterinary
     * @return int, expertise level
     */
    public int getExpertiseLevel() {
        return expertiseLevel;
    }

    /**
     * Method for getting salary implemented from Payment interface
     */
    @Override
    public double getSalary() {
        return (grossSalary + (0.1) * grossSalary * this.yearsAfterGraduation());
    }



    /**
     * Method for printing Veterinary information
     * @return String, veterinary information
     */
    @Override
    public String toString() {
        String line0 = ("Employee Type : Veterinary\n");
        String line1 = ("Employee ID : " + super.getEmpID() + "\n");
        String line2 = ("Gender : " + super.getGender() + "\n");
        String line3 = ("Date of birth : " + super.getDateOfBirth() + "\n");
        String line4 = ("Salary : " + this.getSalary() + "\n");
        String line5 = ("BSc Degree : " + this.BScDegree + "\n");
        String line6 = ("Date of Graduation : " + this.dateOfGraduation + "\n");
        String line7 = ("Expertise Level : " + this.expertiseLevel + "\n");

        return (line0 + line1 + line2 + line3 + line4 + line5 + line6 + line7);
    }

    /**
     * Method for calculating years passed after graduation of a veterinary
     * @return int, years after graduation
     */
    public int yearsAfterGraduation(){
        return (LocalDate.now().getYear() - this.dateOfGraduation.getYear());
    }

    /**
     * Write veterinary details to a file
     * @throws IOException
     */
    public void writeToFile() throws IOException {
        String information = this.toString();
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("vets.dat", true)));
        out.writeUTF(information);
        out.close();

    }


}
