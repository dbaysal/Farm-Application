package GUI;

import Classes.*;
import CustomExceptions.AnimalNotFoundException;
import CustomExceptions.EmployeeNotFoundException;
import CustomExceptions.WrongEmployeeTypeException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AddHealthTreatment extends JFrame{
    private JPanel mainPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JComboBox comboBox1;
    private JButton addMedicationButton;
    private JButton addTreatmentButton;

    private ArrayList<Medication> medications = new ArrayList<>();

    public AddHealthTreatment(FarmApp f){
        this.setContentPane(mainPanel);
        this.setTitle("Add Health Treatment");
        this.setVisible(true);
        this.pack();

        addMedicationButton.addActionListener(new ActionListener() {
            /**
             * Add healthtreatment to a specific animal with given vet details
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddMed(medications);
            }
        });
        addTreatmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

                    if (!(f.employee.get(checkEmp) instanceof Veterinary))
                        throw new WrongEmployeeTypeException();

                    boolean emergency;
                    if(comboBox1.getSelectedIndex() == 0)
                        emergency = true;
                    else
                        emergency = false;
                    Veterinary vet = (Veterinary) f.employee.get(checkEmp);

                    HealthTreatment t = new HealthTreatment(dateOfTreatment, emergency, vet);

                    for(Medication med : medications)
                        t.addMedications(med);

                    Animal animal = f.animals.get(checkAnimal);
                    animal.addTreatment(t);
                    JOptionPane.showMessageDialog(new Frame(), "Health treatment successfully added to animal");

                }
            }
        });
    }
}
