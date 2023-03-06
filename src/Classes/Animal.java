package Classes;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Abstract class for storing animal information which are common for Sheep and Cow classes
 * @author Doğukan Baysal
 * @version 1.0
 */

public abstract class Animal implements Serializable {

    private int tagNo;
    private String gender;
    private LocalDate dateOfBirth;
    private boolean purchased;
    private HashMap<LocalDate, Double> milking;
    private ArrayList<Treatment> treatments;

    /**
     * Empty constructor
     */
    public Animal(){
        this.milking = new HashMap<>();
        this.treatments = new ArrayList<>();
    }

    /**
     * Constructor with parameters
     * @param tagNo: int
     * @param gender: String - male or female
     * @param dateOfBirth: LocalDate
     * @param purchased: boolean
     */
    public Animal(int tagNo, String gender, LocalDate dateOfBirth, boolean purchased) {
        this.treatments = new ArrayList<>();
        this.milking = new HashMap<>();
        this.tagNo = tagNo;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.purchased = purchased;
    }

    /**
     * Method for getting milking information of an animal
     * @return HasMap, milking
     */
    public HashMap getMilking() {
        return milking;
    }

    /**
     * Method for getting treatments of an animal
     * @return ArrayList, treatments
     */
    public ArrayList<Treatment> getTreatments() {
        return treatments;
    }

    /**
     * Method for calculating age of a cow object using date of birth and current date
     * @return age of a cow object: int
     */
    public int getAge(){
        LocalDate now = LocalDate.now();
        return (now.getYear() - dateOfBirth.getYear());
    }


    /**
     * Getter for tagNo
     * @return tagNo: int
     */
    public int getTagNo() {
        return tagNo;
    }

    /**
     * Getter for gender
     * @return gender: String
     */
    public String getGender() {
        return gender;
    }

    /**
     * Getter for date of birth
     * @return dateOfBirth: LocalDate
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Getter for purchased
     * @return purchased: boolean
     */
    public boolean isPurchased() {
        return purchased;
    }

    /**
     * Setter for tagNo
     * @param tagNo: int
     */
    public void setTagNo(int tagNo) {
        this.tagNo = tagNo;
    }

    /**
     * Setter for gender
     * @param gender: String
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Setter for date of birth
     * @param dateOfBirth: LocalDate
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Setter for purchased
     * @param purchased: boolean
     */
    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    /**
     * Method for setting milking
     * @param milking: HashMap
     */
    public void setMilking(HashMap milking) {
        this.milking = milking;
    }

    /**
     * Method for setting treatments
     * @param treatments: ArrayList<Treatment>
     */
    public void setTreatments(ArrayList<Treatment> treatments) {
        this.treatments = treatments;
    }

    /**
     * Method to be implemented in subclasses
     */
    public abstract String feeding();

    /**
     * Method for adding treatment for an animal object
     * @param hasTreatment: Treatment object
     */
    public void addTreatment(Treatment hasTreatment) {

        this.treatments.add(hasTreatment);
    }

    /**
     * Method for adding milking measurement to the hashmap
     * @param date: LocalDate
     * @param amount: Milk amount
     */
    public void addMilkingMeasurement(LocalDate date, double amount){
        this.milking.put(date, amount);
    }

    /**
     * Abstract method for writing Animal to a file
     * @throws IOException
     */
    public abstract void writeToFile() throws IOException;

    /**
     * Write to a specific file
     * @param path Path of file
     * @throws IOException
     */
    public abstract void writeToFile(String path) throws IOException;


}
