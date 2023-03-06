package GUI;

import Classes.*;
import Classes.FarmApp;
import CustomExceptions.NotUniqueTagException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddSheep extends JFrame{
    private JPanel mainPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton addButton;

    public AddSheep(FarmApp f){
        this.setContentPane(mainPanel);
        this.setTitle("Add Sheep");
        this.setVisible(true);
        this.pack();
        addButton.addActionListener(new ActionListener() {
            /**
             * Add sheep to the system
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int tagNo = -1;
                String gender;
                LocalDate dateOfBirth;
                boolean purchased;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                tagNo = Integer.parseInt(textField1.getText());

                int i = f.isTagNoExists(tagNo);
                if(i != -1){
                    throw new NotUniqueTagException();
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
                    purchased = true;
                else
                    purchased = false;
                Sheep sheep = new Sheep(tagNo, gender, dateOfBirth, purchased);
                f.animals.add(sheep);
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmappdb", "cng443user", "1234");
                    DataStorage db = new DataStorage(connection, f);
                    db.addAnimalToDb(sheep);
                }
                catch (Exception ex){
                    System.out.println(ex);
                }
                JOptionPane.showMessageDialog(new Frame(), "Sheep successfully added to the system");
            }
        });
    }
}
