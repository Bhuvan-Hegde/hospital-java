package HospitalManagementSystem;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;


public class hospitalManagementSystem {

    private static final String url = "jdbc:mysql://localhost:3306/hospital";
    private static final String userName = "root";
    private static final String password = "root1";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        Scanner scanner = new Scanner(System.in);
        try{
            Connection connection = DriverManager.getConnection(url , userName, password);
            patient patient = new patient(connection, scanner);
            doctors doctors = new doctors(connection);
            while (true){

                System.out.println("Hospital Management System");
                System.out.println("1. ADD PATIENT");
                System.out.println("2. VIEW PATIENT");
                System.out.println("3. VIEW DOCTORS");
                System.out.println("4. BOOK APPOINTMENTS");
                System.out.println("Enter Your Choice");
                int choice = scanner.nextInt();
                switch (choice){
                    case 1:
                        //add
                        patient.addPatient();
                        break;
                    case 2:
                        //viewP
                        patient.viewPatient();
                        break;
                    case 3:
                        //viewd
                        doctors.viewDoctor();
                        break;
                    case 4:
                        //app

                    default:
                        System.out.println("Wrong choice");
                }


            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
