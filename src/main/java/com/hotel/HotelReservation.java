package com.hotel;
         
	import java.sql.DriverManager;
	import java.sql.SQLException;
	import java.sql.Connection;
	import java.util.Scanner;
	import java.sql.Statement;
	import java.sql.ResultSet;

	public class HotelReservation {
		
		   private static Connection connection;
		   private static Statement statement;
		   private static ResultSet resultSet;
	 
	    public static void reserveRoom(Scanner scanner) {
	        try {
	            System.out.print("Enter guest name: ");
	            String guestName = scanner.next();
	            scanner.nextLine();
	            System.out.print("Enter room number: ");
	            int roomNumber = scanner.nextInt();
	            System.out.print("Enter contact number: ");
	            String contactNumber = scanner.next();
	            
	            connection=ConnectionProvider.getConnection();

	            String sql ="INSERT INTO reservation (guest_name, room_number, contact_number) " + " VALUES ('" + guestName + "', " + roomNumber + ", '" + contactNumber + "') ";
	            	
	                       
	                statement = connection.createStatement();
	                int affectedRows = statement.executeUpdate(sql);

	                if (affectedRows > 0) 
	                    System.out.println("Reservation successful!");
	                else 
	                    System.out.println("Reservation failed.");
	            
	       }catch (SQLException e) {
	            e.printStackTrace();
	        }
}
 public static void viewReservations() throws SQLException {
	        
	    	try{
	    		 connection=ConnectionProvider.getConnection();
	    		 
	    		 String sql = " SELECT id, guest_name, room_number, contact_number, reservation_date FROM reservation ";
	        	
	    		  statement = connection.createStatement();
	              resultSet = statement.executeQuery(sql);
	             
	            System.out.println("Current Reservations:");
	            System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
	            System.out.println(" Id | guest_name           | room_Number   | contact_Number      | reservation_date        |");
	            System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");

	            while (resultSet.next()) {
	            	
	                int Id = resultSet.getInt("id");
	                String guest_name = resultSet.getString("guest_name");
	                int room_number = resultSet.getInt("room_number");
	                String contact_number = resultSet.getString("contact_number");
	                String reservation_date = resultSet.getTimestamp("reservation_date").toString();

	                // Format and display the reservation data in a table-like format
	                System.out.printf("| %-14d | %-15s | %-13d | %-20s | %-19s   |\n",Id, guest_name, room_number, contact_number, reservation_date);
	                       
	            }
	            System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
	   
	    }catch(SQLException e) {
	    	e.printStackTrace();
	     }
}
 public static void getRoomNumber(Scanner scanner) {
	        try {
	            System.out.print("Enter reservation ID: ");
	            int reservationId = scanner.nextInt();
	            System.out.print("Enter guest name: ");
	            String guestName = scanner.next();
                 
	            connection=ConnectionProvider.getConnection();
	            
	            String sql = " SELECT room_number FROM reservation " + " WHERE id = " + reservationId + " AND guest_name = '" + guestName + "' ";

	                statement = connection.createStatement();
	                resultSet = statement.executeQuery(sql); 

	                if (resultSet.next()) {
	                    int roomNumber = resultSet.getInt("room_number");
	                    System.out.println("Room number for Reservation ID " + reservationId +  " and Guest " + guestName + " is: " + roomNumber); 
	                }else 
	                    System.out.println("Reservation not found for the given ID and guest name.");
	                
	            
	        }catch (SQLException e) {
	            e.printStackTrace();
	         }
}
public static void updateReservation(Scanner scanner) {
	       
	       try {
	    	    connection=ConnectionProvider.getConnection();
	    	    
	            System.out.print("Enter reservation ID to update: ");
	            int reservationId = scanner.nextInt();
	            scanner.nextLine();                       // Consume the newline character

	            if (!reservationExists(reservationId)) {
	                System.out.println("Reservation not found for the given ID.");
	                return;
	            }

	            System.out.print("Enter new guest name: ");
	            String newGuestName = scanner.nextLine();
	            System.out.print("Enter new room number: ");
	            int newRoomNumber = scanner.nextInt();
	            System.out.print("Enter new contact number: ");
	            String newContactNumber = scanner.next();

	            String sql = " UPDATE reservation SET guest_name = '" + newGuestName + "', "    +  " room_number = " + newRoomNumber + ", "     +  " contact_number = '" + newContactNumber + "' "    +    " WHERE id = " + reservationId;    
	                    
	            statement = connection.createStatement();
	            int affectedRows = statement.executeUpdate(sql);

	                if (affectedRows > 0) 
	                    System.out.println("Reservation updated successfully!");
	                else 
	                    System.out.println("Reservation update failed.");
	                  
	       }catch (SQLException e) {
	           e.printStackTrace();
	        }
}
public static void deleteReservation(Scanner scanner) {
	       
	      try {
	    	    connection=ConnectionProvider.getConnection();
	    	    
	            System.out.print("Enter reservation ID to delete: ");
	            int reservationId = scanner.nextInt();

	            if (!reservationExists(reservationId)) {
	                System.out.println("Reservation not found for the given ID.");
	                return;
	            }
	            String sql = "DELETE FROM reservation WHERE id = " + reservationId;

	             statement = connection.createStatement();
	            int affectedRows = statement.executeUpdate(sql);

	                if (affectedRows > 0) 
	                    System.out.println("Reservation deleted successfully!");
	                else 
	                    System.out.println("Reservation deletion failed.");
	                    
	       }catch (SQLException e) {
	            e.printStackTrace();
	        }
}
public static boolean reservationExists(int reservationId) {
	       
	       try {
	    	    connection=ConnectionProvider.getConnection();
	    	    
	            String sql = "SELECT id FROM reservation WHERE id = " +reservationId;

	             statement = connection.createStatement();
	             resultSet = statement.executeQuery(sql);
	             return resultSet.next();  //   resultSet.next() returns true value if id= reservationId in database otherwise  It returns false value
	            
	        }catch (SQLException e) {
	            e.printStackTrace();
	            return false;              //exception occured
	        } 
}
public static void exit() throws InterruptedException {
	       
	   System.out.print("Exiting System");
	        int i = 1;
	        while(i<=10){
	            System.out.print(".");
	            Thread.sleep(500);
	            i++;
	        }
	        System.out.println("ThankYou For Using Hotel Reservation System!!!");
	 }

}
