package GUI;

import Classes.FarmApp;
import CustomExceptions.AnimalNotFoundException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MilkingMeasurement extends JFrame{
    private JPanel mainPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JButton addButton;
    private JTextField textField3;

    public MilkingMeasurement(FarmApp f){
        this.setContentPane(mainPanel);
        this.setTitle("Get Milking Measurement");
        this.setVisible(true);
        this.pack();
        addButton.addActionListener(new ActionListener() {
            /**
             * Get milking measurements of an animal
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = -1;
                int tagNo = Integer.parseInt(textField1.getText());
                int checkAnimal = f.isTagNoExists(tagNo);
                Double amount = Double.parseDouble(textField2.getText());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate date = LocalDate.parse(textField3.getText(), formatter);

                if(checkAnimal == -1){
                    throw new AnimalNotFoundException();
                }
                else{
                    f.animals.get(checkAnimal).addMilkingMeasurement(date, amount);
                    JOptionPane.showMessageDialog(new Frame(), "Measurement successfully added");
                }
            }
        });
    }
}
