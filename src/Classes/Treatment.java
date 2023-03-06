package Classes;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Class for storing general treatment information in the system
 * @author Doğukan Baysal
 * @version 1.0
 */

public class Treatment {
    private LocalDate dateOfTreatment;

    /**
     * Empty constructor
     */
    public Treatment(){}

    /**
     * Constructor with parameters
     * @param dateOfTreatment: LocalDate
     */
    public Treatment(LocalDate dateOfTreatment) {
        this.dateOfTreatment = dateOfTreatment;
    }

    /**
     * Method for setting date of treatment
     * @param dateOfTreatment: LocalDate
     */
    public void setDateOfTreatment(LocalDate dateOfTreatment) {
        this.dateOfTreatment = dateOfTreatment;
    }

    /**
     * Method for getting date of treatment
     * @return LocalDate,date of treatment
     */
    public LocalDate getDateOfTreatment() {
        return dateOfTreatment;
    }

    /**
     * Abstract method for writing Treatment to a file
     * @param empID Emp Id
     * @throws IOException
     */
    public void writeToFile(int empID) throws IOException{};
}


