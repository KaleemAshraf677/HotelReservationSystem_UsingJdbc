package com.hotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;


public class MainMenu {

		   public static void main(String[] args) throws ClassNotFoundException, SQLException {
		       
			   try{
				    System.out.println("WELCOME TO HOTEL MANAGEMENT SYSTEM.............");
		           
				    boolean flag=true;
				    
				    while(flag){
		            
		                Scanner scanner = new Scanner(System.in);
		                System.out.println("1. Reserve a room");
		                System.out.println("2. View Reservations");
		                System.out.println("3. Get Room Number");
		                System.out.println("4. Update Reservations");
		                System.out.println("5. Delete Reservations");
		                System.out.println("0. Exit");
		                System.out.print("Choose an option: ");
		                int choice = scanner.nextInt();
		               
		                switch (choice) {
		                    case 1:
		                             HotelReservation.reserveRoom(scanner);
		                             break;
		                    case 2:
		                             HotelReservation.viewReservations();
		                             break;
		                    case 3:
		                             HotelReservation.getRoomNumber(scanner);
		                             break;
		                    case 4:
		                             HotelReservation.updateReservation(scanner);
		                             break;
		                    case 5:
		                             HotelReservation.deleteReservation(scanner);
		                             break;
		                    case 0:
		                             HotelReservation.exit();
		                             flag=false;
		                             break;
		                    default:
		                             System.out.println("Invalid choice. Try again.");
		                }
		            }

		        }catch (Exception e){
		            e.printStackTrace();
		        }
		          
	}

}
