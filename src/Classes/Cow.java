package Classes;
import java.io.*;
import java.time.LocalDate;

/**
 * Class for storing Cow information in the system
 * @author Doğukan Baysal
 * @version 1.0
 */

public class Cow extends Animal{
    private double weight;

    /**
     * Empty constructor
     */
    public Cow(){}

    /**
     * Constructor with parameters
     * @param tagNo: int
     * @param gender: String - male or female
     * @param dateOfBirth: LocalDate
     * @param purchased: boolean
     * @param weight: double
     */
    public Cow(int tagNo, String gender, LocalDate dateOfBirth, boolean purchased, double weight) {
        super(tagNo, gender, dateOfBirth, purchased);
        this.weight = weight;
    }

    /**
     * Method to set weight of a cow
     * @param weight: double
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Method for getting weight of a cow
     * @return double, weight of a cow
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Method for feeding a cow
     */
    @Override
    public String feeding() {
        if(this.getAge() < 3) {
            return ("Feed with only grass");
        }

        else if(this.getAge() > 5 && this.weight<500 && this.getAge()<=10){
            return ("Feed with Total mixed ration (TMR) that includes hay, fermented grass (silage),\n" +
                    "maize silage and high energy grains like brewers grains, soy bean, cotton seed and citrus pulp\n");
            }

        else if(this.getAge() > 10){
            return "Feed with grains and oilseed";
        }

        else{
            return "Feed with grass and grains";
        }

    }


    /**
     * For printing information of cow object
     * @return String, Cow information
     */
    @Override
    public String toString() {
        String line0 = ("Animal Type : Cow \n");
        String line1 = ("Tag Number : " + this.getTagNo() + "\n");
        String line2 = ("Gender : " + this.getGender() + "\n");
        String line3 = ("Date of birth : " + this.getDateOfBirth() + "\n");
        String line5 = ("Purchased : " + this.isPurchased() + "\n");
        String line6 = ("Weight : " + this.weight + "\n");

        return (line0 + line1 + line2 + line3 + line5 + line6);
    }

    /**
     * Method for writing cow information to file
     * @throws IOException
     */
    public void writeToFile() throws IOException {
        String information = this.toString();
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("cows.dat", true)));
        out.writeUTF(information);
        out.close();

    }

    /**
     * Method for writing cow information to a specific file
     * @param path Path of file
     * @throws IOException
     */
    public void writeToFile(String path) throws IOException {
        String information = this.toString();
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path, true)));
        out.writeUTF(information);
        out.close();

    }
}
