package GUI;

import Classes.FarmApp;
import CustomExceptions.EmployeeNotFoundException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeSalary extends JFrame{
    private JPanel mainPanel;
    private JTextField textField1;
    private JButton getSalaryButton;

    public EmployeeSalary(FarmApp f){
        this.setContentPane(mainPanel);
        this.setTitle("Get Employee Salary");
        this.setVisible(true);
        this.pack();
        getSalaryButton.addActionListener(new ActionListener() {
            /**
             * Get employee salary
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = -1;
                int empId = Integer.parseInt(textField1.getText());
                int checkEmp = f.isEmpIDExists(empId);

                if(checkEmp == -1){
                    throw new EmployeeNotFoundException();
                }

                else{
                    JOptionPane.showMessageDialog(new Frame(), f.employee.get(checkEmp).getSalary());
                }
            }
        });
    }
}
