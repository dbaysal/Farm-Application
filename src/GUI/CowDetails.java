package GUI;

import Classes.Cow;
import Classes.FarmApp;
import CustomExceptions.AnimalNotFoundException;
import CustomExceptions.WrongAnimalTypeException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CowDetails extends JFrame{
    private JPanel mainPanel;
    private JTextField textField1;
    private JButton getDetailsButton;

    public CowDetails(FarmApp f){
        this.setContentPane(mainPanel);
        this.setTitle("Get Cow Details");
        this.setVisible(true);
        this.pack();
        getDetailsButton.addActionListener(new ActionListener() {
            /**
             * Get cow details
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = -1;
                int tagNo = Integer.parseInt(textField1.getText());
                int checkCow = f.isTagNoExists(tagNo);

                if(checkCow == -1){
                    throw new AnimalNotFoundException();
                }

                else{
                    if(!(f.animals.get(checkCow) instanceof Cow))
                        throw new WrongAnimalTypeException();

                    JOptionPane.showMessageDialog(new Frame(), f.animals.get(checkCow));
                }
            }
        });
    }
}
