package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class doctors {
    private Connection connection;



    public doctors(Connection connection){
        this.connection = connection;

    }


    public void viewDoctor(){
        String query = "SELECT * from doctors";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Doctors");
            System.out.println("+-------+--------------------------+---------------+");
            System.out.println("|   Id  | Name                     |Specialization |");
            System.out.println("+-------+--------------------------+---------------+");
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");

                System.out.printf("|%-7s|%-26s|%-14s",id,name,specialization);
                System.out.println("+-------+--------------------------+---------------+");
            }

        }catch (SQLException e){
            System.out.println("error" + e.getMessage());
        }
    }

    public boolean getDoctorById(int id){
        try{
            String query = "SELECT * FROM doctors WHERE id=?";
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
