package GUI;

import Classes.Cow;
import Classes.DataStorage;
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

public class AddCow extends JFrame{
    private JPanel mainPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton addButton;


    public AddCow(FarmApp f){
        this.setContentPane(mainPanel);
        this.setTitle("Add Cow");
        this.setVisible(true);
        this.pack();

        addButton.addActionListener(new ActionListener() {
            /**
             * Add cow to the system
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                int tagNo = -1;
                String gender;
                LocalDate dateOfBirth;
                boolean purchased;
                double weight;
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
                weight = Integer.parseInt(textField3.getText());
                Cow cow = new Cow(tagNo, gender, dateOfBirth, purchased, weight);
                f.animals.add(cow);

                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmappdb", "cng443user", "1234");
                    DataStorage db = new DataStorage(connection, f);
                    db.addAnimalToDb(cow);
                }
                catch (Exception ex){
                    System.out.println(ex);
                }

                JOptionPane.showMessageDialog(new Frame(), "Cow successfully added to the system");
            }
        });
    }


}

