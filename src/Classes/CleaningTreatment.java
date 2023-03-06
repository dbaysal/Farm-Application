package Classes;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Class for storing cleaning treatment information in the system which is also a Treatment
 * @author Doğukan Baysal
 * @version 1.0
 */

public class CleaningTreatment extends Treatment {
    private String materialsused;
    private FarmWorker ogivenBy;

    /**
     * Empty constructor
     */
    public CleaningTreatment(){}

    /**
     * Constructor with parameters
     * @param dateOfTreatment: LocalDate
     * @param materialsused: String
     * @param ogivenBy: FarmWorker responsible for the treatment
     */
    public CleaningTreatment(LocalDate dateOfTreatment, String materialsused, FarmWorker ogivenBy) {
        super(dateOfTreatment);
        this.materialsused = materialsused;
        this.ogivenBy = ogivenBy;
    }

    /**
     * Method for setting FarmWorker for a cleaning treatment
     * @param ogivenBy: FarmWorker
     */
    public void setOgivenBy(FarmWorker ogivenBy) {
        this.ogivenBy = ogivenBy;
    }

    /**
     * Method for setting materials used for a cleaning treatment
     * @param materialsused: String
     */
    public void setMaterialsused(String materialsused) {
        this.materialsused = materialsused;
    }

    /**
     * Method for getting materials used for a cleaning treatment
     * @return String, materials used
     */
    public String getMaterialsused() {
        return materialsused;
    }

    /**
     * Method for getting FarmWorker of a cleaning treatment
     * @return FarmWorker, ogivenBy
     */
    public FarmWorker getOgivenBy() {
        return ogivenBy;
    }

    /**
     * Method for printing Cleaning Treatment information
     * @return String, Cleaning Treatment information
     */
    @Override
    public String toString() {
        String line0 = ("Treatment Type : Cleaning Treatment\n");
        String line1 = ("Date of treatment : " + super.getDateOfTreatment() + "\n");
        String line2 = ("Materials Used : " + this.materialsused + "\n");

        return (line0 + line1 + line2 + ogivenBy.toString());
    }

    /**
     * Write CleaningTreatment to file
     * @param emp_id Employee id
     * @param tagNo tag no of animal
     * @throws IOException
     */
    public void writeToFile(int emp_id, int tagNo) throws IOException {
        String information = this.toString() +  ("TagNo : " + tagNo + "\n");
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("cleaningTreatments.dat", true)));
        out.writeUTF(information);
        out.close();
    }
}
