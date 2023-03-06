package GUI;
import Classes.*;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.security.MessageDigest;
import java.util.Scanner;


public class FarmAppMenu extends JFrame{
    private JPanel mainPanel;
    private JRadioButton addAnimalToTheRadioButton;
    private JRadioButton deleteAnimalFromTheRadioButton;
    private JRadioButton getDetailsOfARadioButton;
    private JRadioButton deleteEmployeeFromTheRadioButton;
    private JRadioButton getDetailsOfARadioButton1;
    private JRadioButton addEmployeeToTheRadioButton;
    private JRadioButton addTreatmentToARadioButton;
    private JRadioButton getDetailsOfARadioButton2;
    private JRadioButton getDetailsOfARadioButton3;
    private JRadioButton getAllInstancesInRadioButton;
    private JRadioButton getFeedingInformationOfRadioButton;
    private JRadioButton getEmployeeSalaryRadioButton;
    private JRadioButton addMilkingMeasurementToRadioButton;
    private JRadioButton exitRadioButton;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JButton executeButton;
    private JComboBox comboBox5;
    private JComboBox comboBox6;
    private JComboBox comboBox7;
    private JComboBox comboBox8;

    JMenuBar menuBar = new JMenuBar();
    JMenu fileOperations = new JMenu("Export");
    JMenu displayTabular = new JMenu("Display");
    JMenuItem exportAnimal = new JMenuItem("Animal");
    JMenuItem displayAnimal = new JMenuItem("Animal");




    public FarmAppMenu(FarmApp f){
        displayTabular.add(displayAnimal);
        fileOperations.add(exportAnimal);
        menuBar.add(fileOperations);
        menuBar.add(displayTabular);
        this.setJMenuBar(menuBar);




        exportAnimal.addActionListener(new ActionListener() {
            /**
             * Open file chooser to select export location
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                int r = j.showSaveDialog(null);
                if(r == JFileChooser.APPROVE_OPTION){
                    String path = (j.getSelectedFile().getAbsolutePath());
                    for(Animal animal : f.animals) {
                        try {
                            animal.writeToFile(path);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }

            }
        });

        displayAnimal.addActionListener(new ActionListener() {
            /**
             * Display cow table
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                new TabularCow(f);
            }
        });

        ButtonGroup bg = new ButtonGroup();
        bg.add(addAnimalToTheRadioButton); bg.add(deleteAnimalFromTheRadioButton); bg.add(getDetailsOfARadioButton);
        bg.add(deleteEmployeeFromTheRadioButton); bg.add(getDetailsOfARadioButton1); bg.add(addEmployeeToTheRadioButton);
        bg.add(addTreatmentToARadioButton); bg.add(getDetailsOfARadioButton2); bg.add(getDetailsOfARadioButton3);
        bg.add(getAllInstancesInRadioButton); bg.add(getFeedingInformationOfRadioButton); bg.add(getEmployeeSalaryRadioButton);
        bg.add(addMilkingMeasurementToRadioButton); bg.add(exitRadioButton);

        this.setContentPane(mainPanel);
        this.setTitle("FarmApp");
        this.setVisible(true);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addWindowListener(new java.awt.event.WindowAdapter() {
              public void windowClosing(java.awt.event.WindowEvent e) {
                  exit(f);
              }
        });


        executeButton.addActionListener(new ActionListener() {
            /**
             * Execute selected operation
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e){


                    if(addAnimalToTheRadioButton.isSelected()){
                        if(comboBox1.getSelectedIndex() == 0)
                            new AddCow(f);
                        else
                            new AddSheep(f);
                    }

                    else if(deleteAnimalFromTheRadioButton.isSelected()){
                        if(comboBox2.getSelectedIndex() == 0)
                            new DeleteCow(f);
                        else
                            new DeleteSheep(f);
                    }

                    else if(getDetailsOfARadioButton.isSelected()){
                        if(comboBox3.getSelectedIndex() == 0)
                            new CowDetails(f);
                        else
                            new SheepDetails(f);
                    }

                    else if(addEmployeeToTheRadioButton.isSelected()){
                        if(comboBox4.getSelectedIndex() == 0)
                            new AddVet(f);
                        else
                            new AddWorker(f);
                    }

                    else if(deleteEmployeeFromTheRadioButton.isSelected()){
                        if(comboBox5.getSelectedIndex() == 0)
                            new DeleteVet(f);
                        else
                            new DeleteWorker(f);
                    }

                    else if(getDetailsOfARadioButton1.isSelected()){
                        if(comboBox6.getSelectedIndex() == 0)
                            new VetDetails(f);
                        else
                            new WorkerDetails(f);
                    }

                    else if(getAllInstancesInRadioButton.isSelected()){
                        if(comboBox7.getSelectedIndex() == 0){
                            String s = "";
                            for(Animal animal : f.animals){
                                if(animal instanceof Cow)
                                    s += animal + "-------------------------------\n";
                            }
                            JOptionPane.showMessageDialog(new Frame(), s);
                        }

                        else if(comboBox7.getSelectedIndex() == 1){
                            String s = "";
                            for(Animal animal : f.animals){
                                if(animal instanceof Sheep)
                                    s += animal + "-------------------------------\n";
                            }
                            JOptionPane.showMessageDialog(new Frame(), s);
                        }

                        else if(comboBox7.getSelectedIndex() == 2){
                            String s = "";
                            for(Employee employee : f.employee){
                                if(employee instanceof Veterinary)
                                    s += employee + "-------------------------------\n";
                            }
                            JOptionPane.showMessageDialog(new Frame(), s);
                        }

                        else if(comboBox7.getSelectedIndex() == 3){
                            String s = "";
                            for(Employee employee : f.employee){
                                if(employee instanceof FarmWorker)
                                    s += employee + "-------------------------------\n";
                            }
                            JOptionPane.showMessageDialog(new Frame(), s);
                        }
                    }

                    else if(addTreatmentToARadioButton.isSelected()){
                        if(comboBox8.getSelectedIndex() == 0)
                            new AddCleaningTreatment(f);
                        else
                            new AddHealthTreatment(f);
                    }

                    else if(getDetailsOfARadioButton2.isSelected())
                        new GetTreatments(f);

                    else if(getDetailsOfARadioButton3.isSelected())
                        new GetTreatmentsWithDate(f);

                    else if(getFeedingInformationOfRadioButton.isSelected())
                        new FeedingInfo(f);

                    else if(getEmployeeSalaryRadioButton.isSelected())
                        new EmployeeSalary(f);

                    else if(addMilkingMeasurementToRadioButton.isSelected())
                        new MilkingMeasurement(f);

                    else if(exitRadioButton.isSelected()){
                        exit(f);
                    }


                    else{
                        JOptionPane.showMessageDialog(new Frame(), "Please select an option");
                    }
            }
        });
    }

    /**
     * Method to be execyted when the menu is closed
     * @param f farmapp
     */
    public static void exit(FarmApp f){
        try{
            File animals = new File("animals.txt");
            File md5 = new File("md5.txt");
            if(animals.exists())
                animals.delete();
            if(md5.exists())
                md5.delete();

            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("animals.txt"));
            BufferedWriter w = new BufferedWriter(new FileWriter("md5.txt"));

            for(Animal animal : f.animals){
                out.writeObject(animal);
            }
            w.write(MD5());
            w.close();
            out.close();

        }
        catch(Exception ex){
            System.out.println(ex);
        }


        System.exit(0);

    }

    /**
     * Generate MD5 of a file
     * @return MD5 string if file exist else null
     */
    public static String MD5(){
        try{
            FileInputStream fis = new FileInputStream("animals.txt");
            BufferedInputStream bis = new BufferedInputStream(fis);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int ch;
            while ((ch = bis.read()) != -1) {
                baos.write(ch);
            }
            byte buffer[] = baos.toByteArray();
            // Get a MessageDigest for the appropriate algorithm.
            MessageDigest algorithm = MessageDigest.getInstance("MD5"); // SHA-1 or MD5

            // Ensure the digest's buffer is empty.
            algorithm.reset();

            // Fill the digest's buffer with data to compute a message digest from.
            algorithm.update(buffer);

            // Generate the digest. This does any necessary padding required by the
            // algorithm.
            byte digest[] = algorithm.digest();

            // Save or print digest bytes.
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < digest.length; i++) {
                hexString.append(Integer.toHexString(0xFF & digest[i]));
                hexString.append(" ");
            }
            return hexString.toString();

        }
        catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }
}
