package Classes;
import GUI.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class for File Operations
 * @author Doğukan Baysal
 * @version 1.0
 */
public class FileOperations {


    /**
     * Method for retrieving Veterinary information
     * @param f FarmApp
     */
    public static void retrieveVets(FarmApp f){
        if(new File("vets.dat").exists()){
            try{
                Scanner s_vet = new Scanner(new DataInputStream(new BufferedInputStream(new FileInputStream("vets.dat"))));
                ArrayList<String> info = new ArrayList<>();
                String line = "";
                String[] x = new String[2];
                if(s_vet.hasNext())
                    s_vet.nextLine();
                while(s_vet.hasNext()){
                    line = s_vet.nextLine();
                    while(!line.contains("Veterinary")){
                        x = line.split(": ");
                        info.add(x[1]);
                        if(!s_vet.hasNext())
                            break;
                        line = s_vet.nextLine();
                    }
                    Veterinary vet = new Veterinary();
                    vet.setEmpID(Integer.parseInt(info.get(0)));
                    vet.setGender(info.get(1));
                    vet.setDateOfBirth(LocalDate.parse(info.get(2)));
                    vet.setBScDegree(Boolean.getBoolean(info.get(4)));
                    vet.setDateOfGraduation(LocalDate.parse(info.get(5)));
                    vet.setExpertiseLevel(Integer.parseInt(info.get(6)));
                    f.employee.add(vet);
                    info.clear();
                }
                s_vet.close();
            }
            catch(Exception ex){
                System.out.println(ex);
            }

        }


    }

    /**
     * Method for retrieving FarmWorker information
     * @param f FarmApp
     */
    public static void retrieveWorkers(FarmApp f){
        if(new File("workers.dat").exists()){
            try{
                Scanner s_worker = new Scanner(new DataInputStream(new BufferedInputStream(new FileInputStream("workers.dat"))));
                ArrayList<String> info = new ArrayList<>();
                String line = "";
                String[] x = new String[2];
                if(s_worker.hasNext())
                    s_worker.nextLine();
                while(s_worker.hasNext()){
                    line = s_worker.nextLine();
                    while(!line.contains("FarmWorker")){
                        x = line.split(": ");
                        info.add(x[1]);
                        if(!s_worker.hasNext())
                            break;
                        line = s_worker.nextLine();
                    }
                    FarmWorker worker = new FarmWorker();
                    worker.setEmpID(Integer.parseInt(info.get(0)));
                    worker.setGender(info.get(1));
                    worker.setDateOfBirth(LocalDate.parse(info.get(2)));
                    worker.setPreviousFarmName(info.get(4));
                    worker.setWorkexperience(Integer.parseInt(info.get(5)));
                    f.employee.add(worker);
                    info.clear();
                }
                s_worker.close();
            }
            catch(Exception ex){
                System.out.println(ex);
            }

        }



    }

    /**
     * Method for retrieving Cow information
     * @param f FarmApp
     */
    public static void retrieveCows(FarmApp f) {
        if(new File("cows.dat").exists()){
            try {
                Scanner s_cow = new Scanner(new DataInputStream(new BufferedInputStream(new FileInputStream("cows.dat"))));
                ArrayList<String> info = new ArrayList<>();
                String line = "";
                String[] x = new String[2];
                if (s_cow.hasNext())
                    s_cow.nextLine();
                while (s_cow.hasNext()) {
                    line = s_cow.nextLine();
                    while (!line.contains("Cow")) {
                        x = line.split(": ");
                        info.add(x[1]);
                        if (!s_cow.hasNext())
                            break;
                        line = s_cow.nextLine();
                    }
                    Cow cow = new Cow();
                    cow.setTagNo(Integer.parseInt(info.get(0)));
                    cow.setGender(info.get(1));
                    cow.setDateOfBirth(LocalDate.parse(info.get(2)));
                    cow.setPurchased(Boolean.parseBoolean(info.get(4)));
                    cow.setWeight(Double.parseDouble(info.get(5)));
                    f.animals.add(cow);
                    info.clear();
                }
                s_cow.close();
            } catch (Exception ex) {
                System.out.println(ex);

            }

        }


    }

    /**
     * Method for retrieving Sheep information
     * @param f FarmApp
     */
    public static void retrieveSheeps(FarmApp f){
        if (new File("sheeps.dat").exists()){

            try {

                Scanner s_sheep = new Scanner(new DataInputStream(new BufferedInputStream(new FileInputStream("sheeps.dat"))));
                ArrayList<String> info = new ArrayList<>();
                String line = "";
                String[] x = new String[2];
                if (s_sheep.hasNext())
                    s_sheep.nextLine();
                while (s_sheep.hasNext()) {
                    line = s_sheep.nextLine();
                    while (!line.contains("Sheep")) {
                        x = line.split(": ");
                        info.add(x[1]);
                        if (!s_sheep.hasNext())
                            break;
                        line = s_sheep.nextLine();
                    }
                    Sheep sheep = new Sheep();
                    sheep.setTagNo(Integer.parseInt(info.get(0)));
                    sheep.setGender(info.get(1));
                    sheep.setDateOfBirth(LocalDate.parse(info.get(2)));
                    sheep.setPurchased(Boolean.parseBoolean(info.get(4)));
                    f.animals.add(sheep);
                    info.clear();
                }
                s_sheep.close();
            } catch (Exception ex) {
                System.out.println(ex);

            }
        }
    }

    /**
     * Method for retrieving CleaningTreatment information
     * @param f FarmApp
     */
        public static void retrieveCleaningTreatments (FarmApp f){
            if(new File("cleaningTreatments.dat").exists()){
                try {
                    Scanner s_cleaning = new Scanner(new DataInputStream(new BufferedInputStream(new FileInputStream("cleaningTreatments.dat"))));
                    ArrayList<String> info = new ArrayList<>();
                    String line = "";
                    String[] x = new String[2];
                    if (s_cleaning.hasNext())
                        s_cleaning.nextLine();
                    while (s_cleaning.hasNext()) {
                        line = s_cleaning.nextLine();
                        while (!line.contains("Cleaning Treatment")) {
                            x = line.split(": ");
                            info.add(x[1]);
                            if (!s_cleaning.hasNext())
                                break;
                            line = s_cleaning.nextLine();
                        }
                        CleaningTreatment c_treatment = new CleaningTreatment();
                        c_treatment.setDateOfTreatment(LocalDate.parse(info.get(0)));
                        c_treatment.setMaterialsused(info.get(1));
                        FarmWorker worker = (FarmWorker) getEmployeeById(f, Integer.parseInt(info.get(3)));
                        Animal animal = getAnimalById(f, Integer.parseInt(info.get(9)));

                        c_treatment.setOgivenBy(worker);
                        animal.addTreatment(c_treatment);

                        info.clear();
                    }
                    s_cleaning.close();
                } catch (Exception ex) {
                    System.out.println(ex);
                }

            }


        }

    /**
     * Method for getting Animal with given id
     * @param f FarmApp
     * @param tagNo tag No
     * @return Animal obj
     */
        public static Animal getAnimalById(FarmApp f, int tagNo){
            for(Animal animal : f.animals) {
                if (animal.getTagNo() == tagNo)
                    return animal;
            }
            return null;
        }

    /**
     * Method for getting Employee with given id
     * @param f FarmApp
     * @param Id Emp id
     * @return Employee obj
     */
        public static Employee getEmployeeById(FarmApp f, int Id){
            for(Employee employee : f.employee) {
                if (employee.getEmpID() == Id)
                    return employee;
            }
            return null;
        }


    /**
     * Method for retrieving HealthTreatment information
     * @param f FarmApp
     */
    public static void retrieveHealthTreatments (FarmApp f){
        if(new File("healthTreatments.dat").exists()){

            try {
                Scanner s_health = new Scanner(new DataInputStream(new BufferedInputStream(new FileInputStream("healthTreatments.dat"))));
                ArrayList<String> info = new ArrayList<>();
                String line = "";
                String[] x = new String[2];
                if (s_health.hasNext())
                    s_health.nextLine();
                while (s_health.hasNext()) {
                    line = s_health.nextLine();
                    while (!line.contains("Medication")) {
                        x = line.split(": ");
                        info.add(x[1]);
                        if (!s_health.hasNext())
                            break;
                        line = s_health.nextLine();
                    }
                    HealthTreatment h_treatment = new HealthTreatment();
                    h_treatment.setDateOfTreatment(LocalDate.parse(info.get(0)));
                    h_treatment.setEmergency(Boolean.parseBoolean(info.get(1)));
                    info.clear();

                    while (!line.contains("Employee Type")) {
                        if(line.contains("Medication : ")){
                            line = s_health.nextLine();
                            continue;
                        }
                        x = line.split(": ");
                        info.add(x[1]);
                        if (!s_health.hasNext())
                            break;
                        line = s_health.nextLine();
                    }


                    for(int i=0; i<info.size(); i += 5){
                        Medication med = new Medication();
                        med.setDetails(info.get(i));
                        med.setDuration(Integer.parseInt(info.get(i+1)));
                        med.setStartDate(LocalDate.parse(info.get(i+2)));
                        med.setDosage(Double.parseDouble(info.get(i+3)));
                        med.setNotes(info.get(i+4));
                        h_treatment.addMedications(med);
                    }

                    info.clear();
                    line = s_health.nextLine();

                    while (!line.contains("Treatment Type")) {
                        x = line.split(": ");
                        info.add(x[1]);
                        if (!s_health.hasNext())
                            break;
                        line = s_health.nextLine();
                    }



                    Veterinary vet = (Veterinary)getEmployeeById(f, Integer.parseInt(info.get(0)));
                    Animal animal = getAnimalById(f, Integer.parseInt(info.get(7)));
                    h_treatment.setHgivenBy(vet);

                    animal.addTreatment(h_treatment);
                    info.clear();


                }
                s_health.close();
            } catch (Exception ex) {
                System.out.println(ex);
            }

        }


    }


    }




