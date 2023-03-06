package GUI;

import Classes.*;

import CustomExceptions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VetDetails extends JFrame{
    private JPanel mainPanel;
    private JTextField textField1;
    private JButton getDetailsButton;

    public VetDetails(FarmApp f){
        this.setContentPane(mainPanel);
        this.setTitle("Get Veterinary Details");
        this.setVisible(true);
        this.pack();
        getDetailsButton.addActionListener(new ActionListener() {
            /**
             * Get vet details
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                int index = -1;
                int empId = Integer.parseInt(textField1.getText());
                int checkVet = f.isEmpIDExists(empId);

                if(checkVet == -1){
                    throw new EmployeeNotFoundException();
                }

                else{
                    if(!(f.employee.get(checkVet) instanceof Veterinary))
                        throw new WrongEmployeeTypeException();

                    JOptionPane.showMessageDialog(new Frame(), f.employee.get(checkVet));
                }

            }
        });
    }
}
