package Hotell;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static boolean exit = false;
	interface Lambda {

		
	    public String print();
	}
	public static void main(String arg[]) throws SQLException {
		Lambda msg = () -> {
    		return "**************  WELCOME TO IBIS HOTEL   ***************";
    	};
    	Lambda msg1 = () -> {
    		return "**************  Please choose :   1.Admin    2.Customer  ****************";
    	};

		try {
			System.out.println(msg.print());
			System.out.println();
			System.out.println(msg1.print());
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				Admin.Adminmenu();
				break;
			default:
				Customer.Customermenu();
			}
		} catch (InputMismatchException e) {
			{
				System.out.println("Invalid input!");
				sc.next();
				main(arg);
			}

		}

	}

	
}