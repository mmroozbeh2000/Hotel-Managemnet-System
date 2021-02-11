package Hotell;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Customer extends DB {

	ArrayList<Integer> order = new ArrayList<Integer>();
	static String url = "jdbc:mysql://localhost:3306/hotell?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	static String username = "root";
	static String password = "Roozbeh12345";
	static Statement ST = null;
	static boolean exit = false;
	static Scanner sc = new Scanner(System.in);

	public static void connection() throws SQLException {

		try {
			Connection cn = DriverManager.getConnection(url, username, password);

			ST = cn.createStatement();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public Customer(int id, String name, String familyname, int socialnumber, String nationality, int roomnumber) {

	}

	public static void Customermenu() throws SQLException {

		while (!exit) {
			connection();
			System.out.println("**********    Please choose between 1-4!   **********");
			System.out.println("1. Show all Rooms availability");
			System.out.println("2. Book ");
			System.out.println("3. Order food");
			System.out.println("4. Exit");

			int choice = sc.nextInt();
			switch (choice) {

			case 1:
				Showroom();

				break;

			case 2:
				Book();
				break;

			case 3:
				orderfood();
				break;

			default:
				Main.main(null);
				break;

			}
		}

	}

	static void Showroom() throws SQLException {
		connection();
		try {
			ResultSet result = ST.executeQuery("SELECT *" + "FROM room  ");

			// hämtar antal kolumner
			int columnCount = result.getMetaData().getColumnCount();
			// hämtar alla kolmnnamn
			String[] columnNames = new String[columnCount];
			for (int i = 0; i < columnCount; i++) {
				columnNames[i] = result.getMetaData().getColumnName(i + 1);
			}

			// lägger kolumnnamn i string array
			for (String columnName : columnNames) {
				System.out.print(Admin.PadRight(columnName));
			}

			while (result.next()) {
				System.out.println();
				// hämta data för alla kolumner för den nuvarande raden
				for (String columnName : columnNames) {
					String value = result.getString(columnName);
					if (value == null)
						value = "Nothing";
					System.out.print(Admin.PadRight(value));
				}
			}

			System.out.println();
		}

		catch (InputMismatchException e) {
			{
				System.out.println("Invalid input!");
				sc.next();
				Showroom();
			}
		}

	}

	static void Book() throws SQLException {

		connection();
		System.out.println("/n****** WELCOME TO BOOKING SYSTEM :******");
		System.out.println("Please enter roomid to continue: ");
		Admin.Showroom();
		try {
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
				System.out.print(" First Customer id :  ");
				int id = sc.nextInt();
				System.out.print("First customer name: ");
				sc.nextLine();
				String name = sc.nextLine();
				System.out.println("First customer familyname :");
				String familyname = sc.nextLine();
				System.out.print("First customer socialnumber : ");
				int scn = sc.nextInt();
				System.out.print("First customer nationality : ");
				sc.nextLine();
				String nat = sc.nextLine();
				System.out.print(" Second Customer id :  ");
				int id1 = sc.nextInt();
				System.out.print("Second customer name: ");
				sc.nextLine();

				String name1 = sc.nextLine();
				System.out.println("Second  customer familyname :");
				sc.nextLine();
				String familyname1 = sc.nextLine();
				System.out.print("Second customer socialnumber : ");
				int scn1 = sc.nextInt();
				System.out.print("Second customer nationality : ");
				sc.nextLine();
				String nat1 = sc.nextLine();

				System.out.print("roomnuber: choose between 1020-1024 ");
				int roomnum = sc.nextInt();
				sc.nextLine();

				ST.executeUpdate(
						"INSERT INTO customer(id,name, familyname,socialnumber,nationality,roomnumber) VALUES('" + id
								+ "','" + name + "', '" + familyname

								+ "' , '" + scn + "',  '" + nat + "' ,   '" + roomnum + "'       );");
				ST.executeUpdate(
						"INSERT INTO customer(id,name, familyname,socialnumber,nationality,roomnumber) VALUES('" + id1
								+ "','" + name1 + "', '" + familyname1

								+ "' , '" + scn1 + "',  '" + nat1 + "' ,   '" + roomnum + "'       );");

				PreparedStatement statement1 = ST.getConnection()
						.prepareStatement("UPDATE room SET available = ? , cusid= ?  WHERE roomnumber = ?");
				String a = "No";
				statement1.setString(1, a);
				statement1.setInt(2, id);
				statement1.setInt(3, roomnum);

				statement1.executeUpdate();

				break;

			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
				System.out.print(" Customer id :  ");
				int id11 = sc.nextInt();
				System.out.print("customer name: ");
				sc.nextLine();
				String name11 = sc.nextLine();
				System.out.println("customer familyname :");
				String familyname11 = sc.nextLine();
				System.out.print("customer socialnumber : ");
				int scn11 = sc.nextInt();
				System.out.print("customer nationality : ");
				sc.nextLine();
				String nat11 = sc.nextLine();

				System.out.print("roomnuber: choose between 2020-2024");
				int roomnum1 = sc.nextInt();
				sc.nextLine();

				ST.executeUpdate(
						"INSERT INTO customer(id,name, familyname,socialnumber,nationality,roomnumber) VALUES('" + id11
								+ "','" + name11 + "', '" + familyname11

								+ "' , '" + scn11 + "',  '" + nat11 + "' ,   '" + roomnum1 + "'       );");

				PreparedStatement statement11 = ST.getConnection()
						.prepareStatement("UPDATE room SET available = ? , cusid= ?  WHERE roomnumber = ?");
				String a1 = "No";
				statement11.setString(1, a1);
				statement11.setInt(2, id11);
				statement11.setInt(3, roomnum1);

				statement11.executeUpdate();
				break;

			case 11:
			case 12:
			case 13:
			case 14:
			case 15:
				System.out.print(" Customer id :  ");
				int id111 = sc.nextInt();
				System.out.print("customer name: ");
				sc.nextLine();
				String name111 = sc.nextLine();
				System.out.println("customer familyname :");
				String familyname111 = sc.nextLine();
				System.out.print("customer socialnumber : ");
				int scn111 = sc.nextInt();
				System.out.print("customer nationality : ");
				sc.nextLine();
				String nat111 = sc.nextLine();

				System.out.print("roomnuber: choose between 3020-3024");
				int roomnum11 = sc.nextInt();
				sc.nextLine();

				ST.executeUpdate(
						"INSERT INTO customer(id,name, familyname,socialnumber,nationality,roomnumber) VALUES('" + id111
								+ "','" + name111 + "', '" + familyname111

								+ "' , '" + scn111 + "',  '" + nat111 + "' ,   '" + roomnum11 + "'       );");

				PreparedStatement statement111 = ST.getConnection()
						.prepareStatement("UPDATE room SET available = ? , cusid= ?  WHERE roomnumber = ?");
				String a11 = "No";
				statement111.setString(1, a11);
				statement111.setInt(2, id111);
				statement111.setInt(3, roomnum11);

				statement111.executeUpdate();
				break;

			default:
				System.out.println("Please choose between rooms id!");
				Book();

			}

			System.out.println("Succesfull Checkin!");
		} catch (InputMismatchException e) {
			{
				System.out.println("Invalid input!");
				sc.next();
				Book();
			}
		}

	}

	public static void orderfood() throws SQLException {
		connection();

		int amount;
		System.out.println("Please enter your roomnumber :");
		int roomnum = sc.nextInt();

		System.out.println("Please choose from menu:");
		System.out.println("1. Sandwich  80 sek");
		System.out.println("2.Pasta 45 sek");
		System.out.println("3.Noodles 60 sek");
		System.out.println("4.cola 20 sek");
		int choice = sc.nextInt();
		switch (choice) {
		case 1:

			amount = 80;
			System.out.println("Do you want to continue to order ? 1.Yes/2.No");
			int choice1 = sc.nextInt();
			if (choice1 == 1) {
				orderfood();
				amount = 100;
				ST.executeUpdate("INSERT INTO hotell.order(id,sum) VALUES('" + roomnum + "','" + amount + "'       );");
				System.out.println("Thank you ! your order is done!");

			} else {

				amount = 80;
				ST.executeUpdate("INSERT INTO hotell.order(id,sum) VALUES('" + roomnum + "','" + amount + "'       );");
			}
			System.out.println("Thank you ! your order is done!");

			break;

		}

	}

}
