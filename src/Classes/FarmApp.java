package Classes;
import GUI.FarmAppMenu;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Thread class for menu UI creation
 */
class MenuThread extends Thread{
    public FarmApp f;
    public MenuThread(FarmApp f){
        this.f = f;
        this.start();
    }
    @Override
    public void run() {
        new FarmAppMenu(f);

    }
}

/**
 * Security thread for checking if the file is altered
 */
class Security extends Thread{

    public Security(){
        this.start();
    }

    @Override
    public void run() {
        if(new File("md5.txt").exists()){
            try{
                Scanner s = new Scanner(new DataInputStream(new FileInputStream("md5.txt")));
                String md5 =  s.nextLine();

                if(md5.equals(FarmAppMenu.MD5()))
                    JOptionPane.showMessageDialog(new Frame(), "File is safe");
                else
                    JOptionPane.showMessageDialog(new Frame(), "File has been changed from outside");
            }
            catch(Exception ex){
                System.out.println(ex);
            }
        }

    }
}


/**
 * Class for the main operations. Entry point of the system.
 * @author Doğukan Baysal
 * @version 1.0
 */
public class FarmApp {

    public ArrayList<Animal> animals;
    public ArrayList<Employee> employee;
    public DataStorage db;

    public static Scanner input = new Scanner(System.in);

    /**
     * Empty constructor
     */
    public FarmApp() {
        this.animals = new ArrayList<>();
        this.employee = new ArrayList<>();
    }


    /**
     * Constructor with parameters
     * @param animals: List of animal objects
     * @param employee: List of employee objects
     */
    public FarmApp(ArrayList<Animal> animals, ArrayList<Employee> employee) {
        this.animals = animals;
        this.employee = employee;
    }

    /**
     * Main of the program
     * @param args
     * @hidden
     */
    public static void main(String[] args)  {

        EventQueue queue = Toolkit.getDefaultToolkit().getSystemEventQueue();
        queue.push(new CustomEventQueue());


        FarmApp f = new FarmApp();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmappdb", "cng443user", "1234");
            DataStorage db = new DataStorage(connection, f);

            db.retrieveAnimalFromDb();
            db.retrieveEmployeeFromDb();
            connection.close();

        }
        catch (Exception ex){
            System.out.println(ex);
        }

        new Security();
        new MenuThread(f);




    }




    /**
     * Check if animal exist with given tagNo
     * @param tagNo: int
     * @return boolean, if tagNo exists return index else return -1
     */
    public int isTagNoExists(int tagNo){
        int i;
        for(i=0; i<animals.size(); ++i){
            if(animals.get(i).getTagNo() == tagNo){
                return i;
            }
        }
        return -1;
    }


    /**
     * Check if employee exists with given EmpID
     * @param EmpID: int
     * @return boolean, if EmpID exists return index else return -1
     */
    public int isEmpIDExists(int EmpID){
        int i;
        for(i=0; i<employee.size(); ++i){
            if(employee.get(i).getEmpID() == EmpID){
                return i;
            }
        }

        return -1;
    }
}