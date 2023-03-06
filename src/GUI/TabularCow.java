package GUI;

import Classes.Animal;
import Classes.FarmApp;

import javax.swing.*;

public class TabularCow extends JFrame{
    private JTable table1;
    private JPanel mainPanel = new JPanel();

    public TabularCow(FarmApp f){

        this.setContentPane(mainPanel);
        this.setTitle("Tabular Cow");
        this.setVisible(true);
        this.setSize(800, 400);

        String[] columns = {"TagNo", "Gender", "Date of Birth", "Age", "Purchased"};
        String[][] data = new String[f.animals.size()][5];
        int i=0;
        for(Animal animal : f.animals){

            data[i][0] = Integer.toString(animal.getTagNo());
            data[i][1] = animal.getGender();
            data[i][2] = animal.getDateOfBirth().toString();
            data[i][3] = Integer.toString(animal.getAge());
            data[i][4] = Boolean.toString(animal.isPurchased());
            i++;
        }


        table1 = new JTable(data, columns);
        table1.setSize(800, 400);
        table1.setVisible(true);
        mainPanel.add(table1);
        mainPanel.add(new JScrollPane(table1));



    }
}
