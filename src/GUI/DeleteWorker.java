package GUI;

import Classes.DataStorage;
import Classes.FarmApp;
import Classes.FarmWorker;
import CustomExceptions.EmployeeNotFoundException;
import CustomExceptions.WrongEmployeeTypeException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;

public class DeleteWorker extends JFrame{
    private JPanel mainPanel;
    private JTextField textField1;
    private JButton deleteButton;

    public DeleteWorker(FarmApp f){
        this.setContentPane(mainPanel);
        this.setTitle("Delete Farm Worker");
        this.setVisible(true);
        this.pack();
        deleteButton.addActionListener(new ActionListener() {
            /**
             * Delete farmworker from the system
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
                    f.employee.remove(checkWorker);
                    try{
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmappdb", "cng443user", "1234");
                        DataStorage db = new DataStorage(connection, f);
                        db.removeEmployeeFromDb(empId);
                    }
                    catch (Exception ex){
                        System.out.println(ex);
                    }
                    JOptionPane.showMessageDialog(new Frame(), "Farm Worker successfully removed from the system");
                }
            }
        });
    }
}
