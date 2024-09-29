package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class patient {

    private Connection connection;
    private Scanner scanner;


    public patient(Connection connection,Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;
    }

    public void addPatient(){
        System.out.println("Enter Name");
        String name = scanner.next();
        System.out.println("Enter age");
        int age = scanner.nextInt();
        System.out.println("Enter Patient gender");
        String gender = scanner.next();

        try{
            String query = "INSERT INTO patients(name, age, gender) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,age);
            preparedStatement.setString(3,gender);
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows>0){
                System.out.println("patient added");
            }
            else {
                System.out.println("failed");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void viewPatient(){
        String query = "SELECT * from patients";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Patients");
            System.out.println("+-------+--------------------------+-------+-------+");
            System.out.println("|   Id  | Name                     |Age    |Gender |");
            System.out.println("+-------+--------------------------+-------+-------+");
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                System.out.printf("|%-7s|%-26s|%-7s|%-7s",id,name,age,gender);
                System.out.println("");
                System.out.println("+-------+--------------------------+-------+-------+");
            }

        }catch (SQLException e){
            System.out.println("error" + e.getMessage());
        }
    }

    public boolean getPatientById(int id){
        try{
            String query = "SELECT * FROM patients WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }else{
                return false;
            }


        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;

    }
}
