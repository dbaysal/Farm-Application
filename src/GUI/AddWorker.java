package GUI;

import Classes.DataStorage;
import Classes.FarmApp;
import Classes.FarmWorker;
import CustomExceptions.NotUniqueIdException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddWorker extends JFrame{
    private JPanel mainPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JComboBox comboBox1;
    private JButton addButton;

    public AddWorker(FarmApp f){
        this.setContentPane(mainPanel);
        this.setTitle("Add Worker");
        this.setVisible(true);
        this.pack();
        addButton.addActionListener(new ActionListener() {
            /**
             * Add farmworker to the system
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                int EmpID = -1;
                String gender;
                LocalDate dateOfBirth;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                EmpID = Integer.parseInt(textField1.getText());
                int i = f.isEmpIDExists(EmpID);
                if(i != -1){
                    throw new NotUniqueIdException();
                }


                int s1 = comboBox1.getSelectedIndex();
                if(s1 == 0)
                    gender = "male" ;
                else
                    gender = "female";


                String temp = textField2.getText();
                dateOfBirth = LocalDate.parse(temp, formatter);


                String prev_farm = textField3.getText();
                int work = Integer.parseInt(textField4.getText());


                FarmWorker worker = new FarmWorker(EmpID, gender, dateOfBirth, prev_farm, work);

                f.employee.add(worker);
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmappdb", "cng443user", "1234");
                    DataStorage db = new DataStorage(connection, f);
                    db.addEmployeeToDb(worker);
                }
                catch (Exception ex){
                    System.out.println(ex);
                }
                JOptionPane.showMessageDialog(new Frame(), "Farm Worker successfully added to the system");

            }
        });
    }
}
