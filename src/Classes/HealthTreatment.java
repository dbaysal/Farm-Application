package Classes;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class for storing health treatment information in the system which is also a Treatment
 * @author Doğukan Baysal
 * @version 1.0
 */

public class HealthTreatment extends Treatment {
    private boolean emergency;
    private ArrayList<Medication> hasMedications;
    private Veterinary hgivenBy;

    /**
     * Empty constructor
     */
    public HealthTreatment(){
        this.hasMedications = new ArrayList<>();
    }

    /**
     * Constructor with parameters
     * @param dateOfTreatment: LocalDate
     * @param emergency: boolean
     * @param hgivenBy: Veterinary responsible from the treatment
     */
    public HealthTreatment(LocalDate dateOfTreatment, boolean emergency, Veterinary hgivenBy) {
        super(dateOfTreatment);
        this.emergency = emergency;
        this.hgivenBy = hgivenBy;
        this.hasMedications = new ArrayList<>();
    }

    /**
     * Method for setting Veterinary of a Health Treatment
     * @param hgivenBy: Veterinary
     */
    public void setHgivenBy(Veterinary hgivenBy) {
        this.hgivenBy = hgivenBy;
    }

    /**
     * Method for setting medications of a Health Treatment
     * @param medications: ArrayList<Medication>
     */
    public void setMedications(ArrayList<Medication> medications) {
        this.hasMedications = medications;
    }

    /**
     * Method for setting emergency of a health treatment
     * @param emergency: boolean
     */
    public void setEmergency(boolean emergency) {
        this.emergency = emergency;
    }

    /**
     * Method for getting medications
     * @return ArrayList, medications
     */
    public ArrayList<Medication> getMedications() {
        return hasMedications;
    }

    /**
     * Method for getting emergency of a health treatment
     * @return boolean, emergency type
     */
    public boolean isEmergency() {
        return emergency;
    }

    /**
     * Method for getting Veterinary information
     * @return Veterinary, hgivenBy
     */
    public Veterinary getHgivenBy() {
        return hgivenBy;
    }

    /**
     * Method for printing Health Treatment information
     * @return String, Health treatment information
     */
    @Override
    public String toString() {
        int i;
        String lines = "";

        String line0 = ("Treatment Type : Health Treatment\n");
        String line1 = ("Date of treatment : " + super.getDateOfTreatment() + "\n");
        String line2 = ("Emergency : " + this.emergency + "\n");

        for(i=0; i<hasMedications.size(); ++i){
            lines += ("Medication : " + (i+1) + "\n");
            lines += hasMedications.get(i).toString();
        }

        return (line0 + line1 + line2 + lines + hgivenBy.toString());
    }


    /**
     * Method for adding medication to hasMedications ArrayList of health treatment object
     * @param hasMedications: Medication object
     */
    public void addMedications(Medication hasMedications) {
        this.hasMedications.add(hasMedications);
    }


    /**
     * Add medication details for a health treatment with specifying medication inputs
     */
    public void addMedicationsWithInput(){
        Scanner input = new Scanner(System.in);
        String details;
        int duration;
        LocalDate startDate;
        double Dosage;
        String notes;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Enter medication details");
        details = input.next();


        System.out.println("Enter medication duration");
        duration = input.nextInt();


        System.out.println("Enter medication start date in format dd/MM/yyyy");
        String temp = input.next();
        startDate = LocalDate.parse(temp, formatter);


        System.out.println("Enter medication dosage");
        Dosage = input.nextDouble();


        System.out.println("Enter medication notes");
        notes = input.next();

        Medication med = new Medication(details, duration, startDate, Dosage, notes);

        this.addMedications(med);

        System.out.println("Medication successfully added to treatment");

    }

    /**
     * Write HealthTreatment information to file
     * @param emp_id Emp id
     * @throws IOException
     */
    public void writeToFile(int emp_id) throws IOException {
        String information = this.toString() + ("Id : " + emp_id + "\n") ;
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("healthTreatments.dat", true)));
        out.writeUTF(information);
        out.close();
    }
}
