package GUI;

import Classes.DataStorage;
import Classes.FarmApp;
import Classes.Sheep;
import CustomExceptions.AnimalNotFoundException;
import CustomExceptions.WrongAnimalTypeException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;

public class DeleteSheep extends JFrame{
    private JTextField textField1;
    private JButton deleteButton;
    private JPanel mainPanel;

    public DeleteSheep(FarmApp f){
        this.setContentPane(mainPanel);
        this.setTitle("Delete Sheep");
        this.setVisible(true);
        this.pack();
        deleteButton.addActionListener(new ActionListener() {
            /**
             * Delete sheep from system
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = -1;
                int tagNo = Integer.parseInt(textField1.getText());
                int checkSheep = f.isTagNoExists(tagNo);

                if(checkSheep == -1){
                    throw new AnimalNotFoundException();
                }

                else{
                    if(!(f.animals.get(checkSheep) instanceof Sheep))
                        throw new WrongAnimalTypeException();

                    f.animals.remove(checkSheep);

                    try{
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmappdb", "cng443user", "1234");
                        DataStorage db = new DataStorage(connection, f);
                        db.removeAnimalFromDb(tagNo);
                    }
                    catch (Exception ex){
                        System.out.println(ex);
                    }

                    JOptionPane.showMessageDialog(new Frame(), "Sheep successfully removed from the system");
                }
            }
        });
    }
}
