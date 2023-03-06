package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import Classes.*;
import CustomExceptions.*;



public class AddCleaningTreatment extends JFrame{
    private JPanel mainPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextArea textArea1;
    private JButton addButton;
    private JScrollPane scroll;

    private FarmApp f;


    public AddCleaningTreatment(FarmApp f){
        this.setContentPane(mainPanel);
        this.setTitle("Add Cleaning Treatment");
        this.setVisible(true);
        this.pack();
        this.f = f;


        addButton.addActionListener(new ActionListener(){
            /**
             * Add Cleaning treatment to specified animal with farmworker details
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e){

                    int checkAnimal = f.isTagNoExists(Integer.parseInt(textField1.getText()));
                    int checkEmp = f.isEmpIDExists(Integer.parseInt(textField2.getText()));
                    int i;

                    LocalDate dateOfTreatment;
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                    if (checkAnimal == -1) {
                        throw new AnimalNotFoundException();
                    } else if (checkEmp == -1) {
                        throw new EmployeeNotFoundException();
                    } else {
                        String temp = textField3.getText();
                        dateOfTreatment = LocalDate.parse(temp, formatter);

                        if (!(f.employee.get(checkEmp) instanceof FarmWorker))
                            throw new WrongEmployeeTypeException();


                        String materials = textArea1.getText();

                        CleaningTreatment c = new CleaningTreatment(dateOfTreatment, materials, (FarmWorker) f.employee.get(checkEmp));
                        f.animals.get(checkAnimal).addTreatment(c);

                        JOptionPane.showMessageDialog(new Frame(), "Cleaning treatment successfully added to animal");

                    }

            }
        });
    }


}
