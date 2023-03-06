package GUI;

import Classes.FarmApp;
import Classes.Treatment;
import CustomExceptions.AnimalNotFoundException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GetTreatmentsWithDate extends JFrame {
    private JPanel mainPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JButton getTreatmentsButton;

    public GetTreatmentsWithDate(FarmApp f){
        this.setContentPane(mainPanel);
        this.setTitle("Get Treatments With Date");
        this.setVisible(true);
        this.pack();
        getTreatmentsButton.addActionListener(new ActionListener() {
            /**
             * Get treatments of an animal with date
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = -1;
                int tagNo = Integer.parseInt(textField1.getText());
                int checkCow = f.isTagNoExists(tagNo);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate date= LocalDate.parse(textField2.getText(), formatter);

                if(checkCow == -1){
                    throw new AnimalNotFoundException();
                }

                else{
                    boolean check = false;
                    String s = "";
                    for(Treatment t : f.animals.get(checkCow).getTreatments()){
                        if(t.getDateOfTreatment().equals(date)){
                            s += t + "---------------------------------------\n";
                            check = true;
                        }


                    }
                    if(check)
                        JOptionPane.showMessageDialog(new Frame(), s);
                    else
                        JOptionPane.showMessageDialog(new Frame(), "There is no treatment on given date");
                }
            }
        });
    }
}
