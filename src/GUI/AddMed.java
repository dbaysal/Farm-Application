package GUI;

import Classes.FarmApp;
import Classes.Medication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AddMed extends JFrame {
    private JPanel mainPanel;
    private JButton addButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JScrollPane scroll1;
    private JScrollPane scroll2;
    ArrayList<Medication> medications = new ArrayList<>();


    public AddMed(ArrayList<Medication> medications){
        this.medications = medications;
        this.setContentPane(mainPanel);
        this.setTitle("Add Medication");
        this.setVisible(true);
        this.pack();
        addButton.addActionListener(new ActionListener() {
            /**
             * Add medication to a healthtreatment
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                medications.add(createMed());
                JOptionPane.showMessageDialog(new Frame(),"Medication successfully added");
            }
        });
    }

    public Medication createMed(){

        String details;
        int duration;
        LocalDate startDate;
        double Dosage;
        String notes;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        details = textArea1.getText();

        duration = Integer.parseInt(textField3.getText());

        String temp = textField2.getText();
        startDate = LocalDate.parse(temp, formatter);

        Dosage = Double.parseDouble(textField1.getText());

        notes = textArea2.getText();
        Medication med = new Medication(details, duration, startDate, Dosage, notes);
        return med;
    }
}
