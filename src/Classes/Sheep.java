package Classes;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Class for storing Sheep information in the system
 * @author Doğukan Baysal
 * @version 1.0
 */

public class Sheep extends Animal{

    /**
     * Empty constructor
     */
    public Sheep(){}

    /**
     * Constructor with parameters
     * @param tagNo: int
     * @param gender: String - male or female
     * @param dateOfBirth: LocalDate
     * @param purchased: boolean
     */
    public Sheep(int tagNo, String gender, LocalDate dateOfBirth, boolean purchased) {
        super(tagNo, gender, dateOfBirth, purchased);
    }

    /**
     * Method for feeding a sheep
     */
    @Override
    public String feeding() throws ArithmeticException {
        if(this.getAge()<=5 && this.getGender() == "male"){
            return ("Feed with grass");
        }

        else if(this.getAge()<=8 && this.getGender() == "female"){
            return ("Feed with grass");
        }

        else if(this.getAge()>5 && this.getGender() == "male"){
            return ("Feed with Total mixed Ration");
        }

        else {
            return ("Feed with Total mixed Ration");
        }

    }


    /**
     * For printing information of sheep object
     * @return String, Sheep information
     */
    @Override
    public String toString() {
        String line0 = ("Animal Type : Sheep \n");
        String line1 = ("Tag Number : " + this.getTagNo() + "\n");
        String line2 = ("Gender : " + this.getGender() + "\n");
        String line3 = ("Date of birth : " + this.getDateOfBirth() + "\n");
        String line5 = ("Purchased : " + this.isPurchased() + "\n");
        return (line0 + line1 + line2 + line3 + line5);
    }

    /**
     * Write sheep information to file
     * @throws IOException
     */
    public void writeToFile() throws IOException {
        String information = this.toString();
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("sheeps.dat", true)));
        out.writeUTF(information);
        out.close();

    }

    /**
     * Method for writing sheep information to a specific file
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
