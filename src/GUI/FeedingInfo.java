package GUI;

import Classes.FarmApp;
import CustomExceptions.AnimalNotFoundException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FeedingInfo extends JFrame{
    private JPanel mainPanel;
    private JTextField textField1;
    private JButton getInformationButton;

    public FeedingInfo(FarmApp f){
        this.setContentPane(mainPanel);
        this.setTitle("Get Feeding Information");
        this.setVisible(true);
        this.pack();
        getInformationButton.addActionListener(new ActionListener() {
            /**
             * Get feeding information
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = -1;
                int tagNo = Integer.parseInt(textField1.getText());
                int checkAnimal = f.isTagNoExists(tagNo);

                if(checkAnimal == -1){
                    throw new AnimalNotFoundException();
                }
                else{
                    JOptionPane.showMessageDialog(new Frame(), f.animals.get(checkAnimal).feeding());
                }
            }
        });
    }
}
