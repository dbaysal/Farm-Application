package Classes;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;

/**
 * Class for storing Medication information in the system
 * @author Doğukan Baysal
 * @version 1.0
 */

public class Medication {
    private String details;
    private int duration;
    private LocalDate startDate;
    private double Dosage;
    private String notes;


    /**
     * Empty constructor
     */
    public Medication(){}

    /**
     * Constructor with parameters
     * @param details: String
     * @param duration: int
     * @param startDate: LocalDate
     * @param dosage: double
     * @param notes: String
     */
    public Medication(String details, int duration, LocalDate startDate, double dosage, String notes) {
        this.details = details;
        this.duration = duration;
        this.startDate = startDate;
        Dosage = dosage;
        this.notes = notes;
    }

    /**
     * Method for getting details
     * @return String, details
     */
    public String getDetails() {
        return details;
    }

    /**
     * Method for getting duration
     * @return int, duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Method for getting starting date
     * @return LocalDate, start date
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Method for getting dosage
     * @return double, Dosage
     */
    public double getDosage() {
        return Dosage;
    }

    /**
     * Method for getting notes
     * @return String, notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Method for setting details
     * @param details: String
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * Method for setting duration
     * @param duration: int
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Method for setting starting date
     * @param startDate: LocalDate
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Method for setting dosage
     * @param dosage: double
     */
    public void setDosage(double dosage) {
        Dosage = dosage;
    }

    /**
     * Method for setting notes
     * @param notes: String
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Method for printing Medication information
     * @return: String, Medication information
     */
    @Override
    public String toString() {
        String line0 = ("Medication details : " + this.details + "\n");
        String line1 = ("Medication duration : " + this.duration + "\n");
        String line2 = ("Medication start date : " + this.startDate + "\n");
        String line3 = ("Medication dosage : " + this.Dosage + "\n");
        String line4 = ("Medication notes : " + this.notes + "\n");

        return (line0 + line1 + line2 + line3 + line4);
    }


}
