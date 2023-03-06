package GUI;

import Classes.FarmApp;
import Classes.FarmWorker;
import Classes.Veterinary;
import CustomExceptions.EmployeeNotFoundException;
import CustomExceptions.WrongEmployeeTypeException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorkerDetails extends JFrame{
    private JTextField textField1;
    private JButton getDetailsButton;
    private JPanel mainPanel;

    public WorkerDetails(FarmApp f){
        this.setContentPane(mainPanel);
        this.setTitle("Get Worker Details");
        this.setVisible(true);
        this.pack();
        getDetailsButton.addActionListener(new ActionListener() {
            /**
             * Get farmworker details
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = -1;
                int empId = Integer.parseInt(textField1.getText());
                int checkWorker = f.isEmpIDExists(empId);

                if(checkWorker == -1){
                    throw new EmployeeNotFoundException();
                }

                else{
                    if(!(f.employee.get(checkWorker) instanceof FarmWorker))
                        throw new WrongEmployeeTypeException();

                    JOptionPane.showMessageDialog(new Frame(), f.employee.get(checkWorker));
                }
            }
        });
    }
}
