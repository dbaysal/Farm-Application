package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataStorage {
    public Connection conn;
    public FarmApp f;

    public DataStorage(Connection conn, FarmApp f){
        this.conn = conn;
        this.f = f;
    }

    /**
     * Method for removing animal from database
     * @param tagNo tagNo for animal
     */
    public void removeAnimalFromDb(int tagNo) throws SQLException {
        PreparedStatement preparedStatement = this.conn.prepareStatement("DELETE FROM animal WHERE tagNo=?");
        preparedStatement.setString(1, String.valueOf(tagNo));
        preparedStatement.executeUpdate();

    }

    /**
     * Method for removing employee from database
     * @param empId id of the employee
     */
    public void removeEmployeeFromDb(int empId) throws SQLException {
        PreparedStatement preparedStatement = this.conn.prepareStatement("DELETE FROM employee WHERE empID=?");
        preparedStatement.setString(1, String.valueOf(empId));
        preparedStatement.executeUpdate();
    }

    /**
     * Retrive animal from db
     * @throws SQLException
     */

    public void retrieveAnimalFromDb() throws SQLException {

        PreparedStatement preparedStatement = this.conn.prepareStatement("SELECT * FROM animal");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            if(resultSet.getString(5).equals("c")){
                Boolean purchased;
                String gender = "";
                if(resultSet.getInt(4) == 0)
                    purchased = false;
                else
                    purchased = true;
                if(resultSet.getString(2).equals("m"))
                    gender = "male";
                else
                    gender = "female";
                Cow cow = new Cow(resultSet.getInt(1), gender, resultSet.getDate(3).toLocalDate(),
                        purchased, resultSet.getDouble(6));
                this.f.animals.add(cow);
            }
            else{

                Boolean purchased;
                if(resultSet.getInt(4) == 0)
                    purchased = false;
                else
                    purchased = true;
                Sheep sheep = new Sheep(resultSet.getInt(1), resultSet.getString(2), resultSet.getDate(3).toLocalDate(),
                        purchased);
                this.f.animals.add(sheep);

            }

        }


    }

    /**
     * Retrieve employees from database
     * @throws SQLException
     */
    public void retrieveEmployeeFromDb() throws SQLException {

        PreparedStatement preparedStatement = this.conn.prepareStatement("SELECT * FROM employee");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            if(resultSet.getString(4).equals("v")){
                Boolean BScDegree;
                String gender = "";
                if(resultSet.getInt(5) == 0)
                    BScDegree = false;
                else
                    BScDegree = true;
                if(resultSet.getString(2).equals("m"))
                    gender = "male";
                else
                    gender = "female";
                Veterinary vet = new Veterinary(resultSet.getInt(1), gender,
                        resultSet.getDate(3).toLocalDate(), BScDegree, resultSet.getDate(6).toLocalDate(),
                resultSet.getInt(7));
                this.f.employee.add(vet);
            }
            else{
                String gender = "";
                if(resultSet.getString(2).equals("m"))
                    gender = "male";
                else
                    gender = "female";
                FarmWorker worker = new FarmWorker(resultSet.getInt(1), gender, resultSet.getDate(3).toLocalDate(),
                        resultSet.getString(8), resultSet.getInt(9));
                this.f.employee.add(worker);

            }

        }

        }

    /**
     * Add animal to database
     * @param animal animal object
     * @throws SQLException
     */
    public void addAnimalToDb(Animal animal) throws SQLException {

        if(animal instanceof Cow){
            PreparedStatement preparedStatement = this.conn.prepareStatement("INSERT INTO animal(tagNo, gender, dateOfBirth, purchased, type, Weight)" +
                    " VALUES(?, ?, ?, ?, ?, ?)");
            Cow cow = (Cow) animal;
            int purchased = 0;
            if(cow.isPurchased())
                purchased = 1;

            preparedStatement.setString(1, Integer.toString(cow.getTagNo()));
            preparedStatement.setString(2, String.valueOf(cow.getGender().charAt(0)));
            preparedStatement.setString(3, cow.getDateOfBirth().toString());
            preparedStatement.setString(4, Integer.toString(purchased));
            preparedStatement.setString(5, "c");
            preparedStatement.setString(6, Double.toString(cow.getWeight()));
            preparedStatement.executeUpdate();

        }

        else{

            PreparedStatement preparedStatement = this.conn.prepareStatement("INSERT INTO animal(tagNo, gender, dateOfBirth, purchased, type)" +
                    " VALUES(?, ?, ?, ?, ?)");
            Sheep sheep = (Sheep) animal;
            int purchased = 0;
            if(sheep.isPurchased())
                purchased = 1;

            preparedStatement.setString(1, Integer.toString(sheep.getTagNo()));
            preparedStatement.setString(2, String.valueOf(sheep.getGender().charAt(0)));
            preparedStatement.setString(3, sheep.getDateOfBirth().toString());
            preparedStatement.setString(4, Integer.toString(purchased));
            preparedStatement.setString(5, "s");
            preparedStatement.executeUpdate();

        }
        }

    /**
     * Add employee to database
     * @param employee employee object
     * @throws SQLException
     */
    public void addEmployeeToDb(Employee employee) throws SQLException {
            if(employee instanceof Veterinary){
                Veterinary vet = (Veterinary) employee;
                PreparedStatement preparedStatement = this.conn.prepareStatement("INSERT INTO employee(empID, gender, dateOfBirth, type, BScDegree, dateOfGraduation, expertiseLevel)" +
                        " VALUES(?, ?, ?, ?, ?, ?, ?)");
                int bsc = 0;
                if(vet.isBScDegree())
                    bsc = 1;
                preparedStatement.setString(1, Integer.toString(vet.getEmpID()));
                preparedStatement.setString(2, String.valueOf(vet.getGender().charAt(0)));
                preparedStatement.setString(3, vet.getDateOfBirth().toString());
                preparedStatement.setString(4, "v");
                preparedStatement.setString(5, Integer.toString(bsc));
                preparedStatement.setString(6, vet.getDateOfGraduation().toString());
                preparedStatement.setString(7, Integer.toString(vet.getExpertiseLevel()));
                preparedStatement.executeUpdate();

            }
            else{
                FarmWorker worker = (FarmWorker) employee;
                PreparedStatement preparedStatement = this.conn.prepareStatement("INSERT INTO employee(empID, gender, dateOfBirth, type, previousFarmName, workExperience)" +
                        " VALUES(?, ?, ?, ?, ?, ?)");
                preparedStatement.setString(1, Integer.toString(worker.getEmpID()));
                preparedStatement.setString(2, String.valueOf(worker.getGender().charAt(0)));
                preparedStatement.setString(3, worker.getDateOfBirth().toString());
                preparedStatement.setString(4, "f");
                preparedStatement.setString(5, worker.getPreviousFarmName());
                preparedStatement.setString(6, Integer.toString(worker.getWorkexperience()));
                preparedStatement.executeUpdate();
            }

        }


    }

