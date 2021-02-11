package Hotell;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin extends DB{
	static String url = "jdbc:mysql://localhost:3306/hotell?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	static String username = "root";
	static String password = "Roozbeh12345";
	static Statement ST = null;
	static Statement ST1 = null;
	static boolean exit = false;
	static Scanner sc = new Scanner(System.in);

	public static void connection() throws SQLException {

		try {
			Connection cn = DriverManager.getConnection(url, username, password);

			ST = cn.createStatement();
			ST1 = cn.createStatement();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static void Adminmenu() throws SQLException {

		try {
			while (!exit) {

				System.out.println("**********    Please choose between 1-6!   **********");
				System.out.println("1. Show all customers");
				System.out.println("2. Show all rooms");
				System.out.println("3. Search customer");
				System.out.println("4. Update customer information");
				System.out.println("5. Check in customer");
				System.out.println("6. Check out customer");
				System.out.println("7. Exit");

				int choice = sc.nextInt();

				switch (choice) {

				case 1:
					Showcus();
					break;

				case 2:

					Showroom();
					break;

				case 3:
					Search();
					break;
				case 4:
					Update();
					break;
				case 5:
					Checkin();
					break;
				case 6:
					Checkout();
					break;
				default:
					Main.main(null);
					break;

				}

			}
		} catch (InputMismatchException e) {
			{
				System.out.println("Invalid input!");
				sc.next();
				Adminmenu();
			}
		}
	}

	static void Showcus() throws SQLException {
		connection();
		try {
			ResultSet result = ST.executeQuery("SELECT *" + "FROM customer  ");

			// hämtar antal kolumner
			int columnCount = result.getMetaData().getColumnCount();
			// hämtar alla kolmnnamn
			String[] columnNames = new String[columnCount];
			for (int i = 0; i < columnCount; i++) {
				columnNames[i] = result.getMetaData().getColumnName(i + 1);
			}

			// lägger kolumnnamn i string array
			for (String columnName : columnNames) {
				System.out.print(PadRight(columnName));
			}

			while (result.next()) {
				System.out.println();
				// hämta data för alla kolumner för den nuvarande raden
				for (String columnName : columnNames) {
					String value = result.getString(columnName);

					System.out.print(PadRight(value));
				}
			}

			System.out.println();
		}

		catch (InputMismatchException e) {
			{
				System.out.println("Invalid input!");
				sc.next();
				Showcus();
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
				System.out.print(PadRight(columnName));
			}

			while (result.next()) {
				System.out.println();
				// hämta data för alla kolumner för den nuvarande raden
				for (String columnName : columnNames) {
					String value = result.getString(columnName);
					if (value == null)
						value = "Nothing";
					System.out.print(PadRight(value));
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

	static void Search() throws SQLException {
		connection();
		try {
			System.out.println("What do you want to search with?");
			System.out.println("1.socialnumber");
			System.out.println("2. roomnumber");

			int val = sc.nextInt();

			switch (val) {

			case 1:
				System.out.println("Enter socialnumber :");
				int id = sc.nextInt();

				ResultSet result = ST.executeQuery("SELECT *"

						+ "FROM customer  where socialnumber=" + id);

				int columnCount = result.getMetaData().getColumnCount();

				String[] columnNames = new String[columnCount];
				for (int i = 0; i < columnCount; i++) {
					columnNames[i] = result.getMetaData().getColumnName(i + 1);
				}

				for (String columnName : columnNames) {
					System.out.print(PadRight(columnName));
				}

				while (result.next()) {
					System.out.println();

					for (String columnName : columnNames) {
						String value = result.getString(columnName);

						if (value == null)
							value = "NEJ";

						System.out.print(PadRight(value));
					}
				}

				System.out.println();

				break;

			default:

				System.out.println("Enter roomnumber :");
				int roomnum = sc.nextInt();

				ResultSet result1 = ST.executeQuery("SELECT *"

						+ "FROM customer  where roomnumber=" + roomnum);

				int columnCount1 = result1.getMetaData().getColumnCount();

				String[] columnNames1 = new String[columnCount1];
				for (int i = 0; i < columnCount1; i++) {
					columnNames1[i] = result1.getMetaData().getColumnName(i + 1);
				}

				for (String columnName : columnNames1) {
					System.out.print(PadRight(columnName));
				}

				while (result1.next()) {
					System.out.println();

					for (String columnName : columnNames1) {
						String value = result1.getString(columnName);

						System.out.print(PadRight(value));
					}
				}

				System.out.println();

				break;

			}

		}

		catch (InputMismatchException e) {
			{
				System.out.println("Invalid input!");
				sc.next();
				Search();
			}
		}

	}

	static void Update() throws SQLException {
		connection();
		try {
			Showcus();
			System.out.println(
					"\r\n  *****************Enter socialnumber for customer you want to update :*****************");
			int social = sc.nextInt();
			sc.nextLine();
			ResultSet result = ST.executeQuery("SELECT *"

					+ "FROM customer  where socialnumber=" + social);

			while(result.next()) {
				int check = result.getInt(4);
			
				if (check ==social) {

					System.out.println("*****   What do you want to update?  *****");
					System.out.println("1.Name");
					System.out.println("2.Familyname");
					System.out.println("3.Socialnumber");
					System.out.println("4.Roomnumber");

					int val = sc.nextInt();
					switch (val) {
					case 1:
						System.out.println("Enter new name:");
						sc.nextLine();
						String newname = sc.nextLine();

						PreparedStatement statement1 = ST.getConnection()
								.prepareStatement("UPDATE customer SET name = ?  WHERE socialnumber = ?");

						statement1.setString(1, newname);
						statement1.setInt(2, social);

						statement1.executeUpdate();

						break;

					case 2:
						System.out.println("Enter new familyname");

						sc.nextLine();
						String newfamilyname = sc.nextLine();

						PreparedStatement statement2 = ST.getConnection()
								.prepareStatement("UPDATE customer SET familyname = ?  WHERE socialnumber = ?");

						statement2.setString(1, newfamilyname);
						statement2.setInt(2, social);

						statement2.executeUpdate();

						break;

					case 3:
						System.out.println("Enter new socialnumber:");

						sc.nextLine();
						String newsn = sc.nextLine();

						PreparedStatement statement3 = ST.getConnection()
								.prepareStatement("UPDATE customer SET socialnumber = ?  WHERE socialnumber = ?");

						statement3.setString(1, newsn);
						statement3.setInt(2, social);

						statement3.executeUpdate();

						break;

					default:
						System.out.println("Enter new roomnumber");

						int roomnum = sc.nextInt();

						PreparedStatement statement4 = ST.getConnection()
								.prepareStatement("UPDATE customer SET roomnumber = ?  WHERE socialnumber = ?");

						statement4.setInt(1, roomnum);
						statement4.setInt(2, social);

						statement4.executeUpdate();

						break;
					}
					
			}
				else {
					
					Update();
				}
			
			
			}
		} catch (InputMismatchException e) {
			{
				System.out.println("Invalid input!");
				sc.next();
				Update();
			}
		}

	}

	static void Checkin() throws SQLException {
		connection();
		System.out.println("******Check in new customers :******");
		System.out.println("Please enter roomid to continue: ");
		Showroom();
		try {

			int choice = sc.nextInt();
			ResultSet result = ST.executeQuery("SELECT *"

					+ "FROM room  where id=" + choice);

			while (result.next()) {
				String check = result.getString(7);

				if (check.equalsIgnoreCase("yes")) {

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

						System.out.print("Roomnumber: Please choose between 1020-1024 ");
						int roomnum = sc.nextInt();
						sc.nextLine();

						ST1.executeUpdate(
								"INSERT INTO customer(id,name, familyname,socialnumber,nationality,roomnumber) VALUES('"
										+ id + "','" + name + "', '" + familyname

										+ "' , '" + scn + "',  '" + nat + "' ,   '" + roomnum + "'       );");
						ST1.executeUpdate(
								"INSERT INTO customer(id,name, familyname,socialnumber,nationality,roomnumber) VALUES('"
										+ id1 + "','" + name1 + "', '" + familyname1

										+ "' , '" + scn1 + "',  '" + nat1 + "' ,   '" + roomnum + "'       );");

						PreparedStatement statement1 = ST1.getConnection()
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

						System.out.print("Roomnumber: Please choose between 2020-2024");
						int roomnum1 = sc.nextInt();
						sc.nextLine();

						ST1.executeUpdate(
								"INSERT INTO customer(id,name, familyname,socialnumber,nationality,roomnumber) VALUES('"
										+ id11 + "','" + name11 + "', '" + familyname11

										+ "' , '" + scn11 + "',  '" + nat11 + "' ,   '" + roomnum1 + "'       );");

						PreparedStatement statement11 = ST1.getConnection()
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

						System.out.print("roomnumber:  Please choose between 3020-3024");
						int roomnum11 = sc.nextInt();
						sc.nextLine();

						ST1.executeUpdate(
								"INSERT INTO customer(id,name, familyname,socialnumber,nationality,roomnumber) VALUES('"
										+ id111 + "','" + name111 + "', '" + familyname111

										+ "' , '" + scn111 + "',  '" + nat111 + "' ,   '" + roomnum11 + "'       );");

						PreparedStatement statement111 = ST1.getConnection()
								.prepareStatement("UPDATE room SET available = ? , cusid= ?  WHERE roomnumber = ?");
						String a11 = "No";
						statement111.setString(1, a11);
						statement111.setInt(2, id111);
						statement111.setInt(3, roomnum11);

						statement111.executeUpdate();
						break;

					default:
						System.out.println("Please choose between rooms ides!");
						Checkin();

					}

					System.out.println("****************Succesfull Checkin!*****************");
				} else {
					System.out.println("The room is not availbale !  Please choose another roomid!");

					Checkin();
				}

			}

		} catch (InputMismatchException e) {
			{
				System.out.println("Invalid input!");
				sc.next();
				Checkin();
			}
		}

	}

	static void Checkout() throws SQLException {
		connection();

		try {
			Showcus();

			System.out.println("   \n  **********Please enter room number to check out :**********");
			int ID = sc.nextInt();
			PreparedStatement statement2 = ST.getConnection()
					.prepareStatement("UPDATE room SET cusid = ?  WHERE roomnumber = ?");

			statement2.setString(1, null);
			statement2.setInt(2, ID);

			statement2.executeUpdate();

			PreparedStatement statement21 = ST.getConnection()
					.prepareStatement("UPDATE room SET available = ?  WHERE roomnumber = ?");

			statement21.setString(1, "yes");
			statement21.setInt(2, ID);

			statement21.executeUpdate();
			ResultSet result1 = ST.executeQuery("SELECT *"

					+ "FROM hotell.order  where id=" + ID);

			while (result1.next()) {
				int sum = result1.getInt(2);
				System.out.println(" Your total order amount to pay is : " + sum + " SEK");
			}

			int result = ST.executeUpdate("DELETE FROM customer WHERE roomnumber =" + ID + ";");

			if (result == 1) {
				System.out.println("Customer with Roomnumber: " + ID + " is now checked out");
			} else {
				System.out.println("\nNo customer has found!  Please enter a valid room number:");
				Checkout();
			}

		}

		catch (InputMismatchException e) {
			{
				System.out.println("Invalid input!");
				sc.next();
				Checkout();
			}
		}

	}

	static String PadRight(String string) {
		int totalStringLength = 40;
		int charsToPadd = totalStringLength - string.length();

		// incase the string is the same length or longer than our maximum lenght
		if (string.length() >= totalStringLength)
			return string;

		StringBuilder stringBuilder = new StringBuilder(string);
		for (int i = 0; i < charsToPadd; i++) {
			stringBuilder.append(" ");
		}

		return stringBuilder.toString();
	}

}
