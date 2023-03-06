package GUI;

import Classes.DataStorage;
import Classes.FarmApp;
import Classes.Veterinary;
import CustomExceptions.NotUniqueIdException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddVet extends JFrame{
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton addButton;
    private JPanel mainPanel;

    public AddVet(FarmApp f){
        this.setContentPane(mainPanel);
        this.setTitle("Add Veterinary");
        this.setVisible(true);
        this.pack();
        addButton.addActionListener(new ActionListener() {
            /**
             * Add vet to the system
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int EmpID = -1;
                String gender;
                LocalDate dateOfBirth;
                boolean BScDegree;
                LocalDate dateOfGraduation;
                int expertiseLevel = -1;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                boolean check = true;

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

                int s2 = comboBox2.getSelectedIndex();

                if(s2 == 0)
                    BScDegree = true;
                else
                    BScDegree = false;


                temp = textField3.getText();
                dateOfGraduation  = LocalDate.parse(temp, formatter);


                expertiseLevel = Integer.parseInt(textField4.getText());

                Veterinary vet = new Veterinary(EmpID, gender, dateOfBirth, BScDegree, dateOfGraduation, expertiseLevel);

                f.employee.add(vet);

                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmappdb", "cng443user", "1234");
                    DataStorage db = new DataStorage(connection, f);
                    db.addEmployeeToDb(vet);
                }
                catch (Exception ex){
                    System.out.println(ex);
                }

                JOptionPane.showMessageDialog(new Frame(), "Vet successfully added to the system");
            }
        });
    }
}
