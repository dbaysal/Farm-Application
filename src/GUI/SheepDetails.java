package GUI;
import Classes.*;
import Classes.FarmApp;
import CustomExceptions.AnimalNotFoundException;
import CustomExceptions.WrongAnimalTypeException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SheepDetails extends JFrame{
    private JPanel mainPanel;
    private JTextField textField1;
    private JButton getDetailsButton;

    public SheepDetails(FarmApp f){
        this.setContentPane(mainPanel);
        this.setTitle("Get Sheep Details");
        this.setVisible(true);
        this.pack();
        getDetailsButton.addActionListener(new ActionListener() {
            /**
             * Get sheep details
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = -1;
                int tagNo = Integer.parseInt(textField1.getText());
                int checkSheep = f.isTagNoExists(tagNo);

                if(checkSheep == -1){
                    throw new AnimalNotFoundException();
                }

                else{
                    if(!(f.animals.get(checkSheep) instanceof Sheep))
                        throw new WrongAnimalTypeException();

                    JOptionPane.showMessageDialog(new Frame(), f.animals.get(checkSheep));
                }
            }
        });
    }
}
