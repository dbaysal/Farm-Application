package GUI;

import Classes.FarmApp;
import Classes.Treatment;
import CustomExceptions.AnimalNotFoundException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GetTreatments extends JFrame {
    private JTextField textField1;
    private JButton getTreatmentsButton;
    private JPanel mainPanel;

    public GetTreatments(FarmApp f){
        this.setContentPane(mainPanel);
        this.setTitle("Get Treatments");
        this.setVisible(true);
        this.pack();
        getTreatmentsButton.addActionListener(new ActionListener() {
            /**
             * Get treatments of an animal
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
                    String s = "";
                    for(Treatment t : f.animals.get(checkCow).getTreatments())
                        s += t + "---------------------------------------\n";
                    JOptionPane.showMessageDialog(new Frame(), s);
                }
            }
        });
    }
}
