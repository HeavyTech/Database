import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class jdbcdemo{

	public static void main(String args[]) throws SQLException{

		try {

			String url = "jdbc:mysql://localhost:3306/finalproject";
			String username = "root";
			String password = "";
			Connection connection = null;


			System.out.println("Connecting database...");
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Database connected!");
			Statement stmt = connection.createStatement();

			try{

				String drop = "DROP TABLE IF EXISTS Employee;";
				stmt.execute(drop);
				String table ="CREATE TABLE Employee( id integer(30) AUTO_INCREMENT, lastname varchar(50),HireDate date,Birthday date, Sex varchar(10),Jobstatus varchar(50),Paytype varchar(50),annualsalary varchar(50),YearsofService int(11),PRIMARY KEY (id))";
				stmt.executeUpdate(table);
				System.out.println("Table creation process successfully!");


				String load = "LOAD DATA LOCAL INFILE 'C:/Users/Heavytech/Desktop/Employees_updatedFinal.txt' INTO TABLE Employee FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\r\n'";
				stmt.executeUpdate(load);

			}
			catch(SQLException s){
				System.out.println("Table already exists!");
			}
			showMenu(connection, stmt);

			connection.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}


	private static void showMenu(Connection connection, Statement stmt) throws SQLException{
		Scanner scanner = new Scanner(System.in);
		while(true){

			System.out.println("What would you like to do?");
			System.out.println("******Employee******");
			System.out.println("1.Add Employee");
			System.out.println("2.Delete Employee");
			System.out.println("3.Modify Employee");
			System.out.println("4.View Employee");
			System.out.println("5.View all Employees");
			System.out.println("6.View Females && 40k");
			System.out.println("7.View Employees over 40, Full time, and Salary + 3 Years of Works");
			System.out.println("8.Search for Employee who have Last Name and more than 100K");
			System.out.println("9.Exit Program");
			System.out.println("********************");
			int choice = scanner.nextInt();


			switch(choice){

			case 1: System.out.println("Add a record into database");
			addEmployee(connection);
			break;
			case 2 :deleteEmployee(connection);
			break;
			case 3 : modifyEmployee(connection);
			break;
			case 4: viewEmployee(connection);
			break;
			case 5: viewDataBase(connection);
			break;
			case 6:	viewFemale40(connection);
			break;
			case 7 : view40Years(connection);
			break;
			case 8 : searchSameLastName(connection);
			break;

			case 9: System.out.println("Good Bye"); 
			System.exit(0);
			break;
			}

		}

	}
	private static void searchSameLastName(Connection connection) throws SQLException {
		Statement stmt = connection.createStatement();
		String query =  "SELECT * FROM Employee WHERE lastname IN (SELECT lastname FROM Employee GROUP BY lastname HAVING COUNT(lastname) > 1) AND AnnualSalary >= 100000;";


		ArrayList<String[]> results = new ArrayList<String[]>();
		ResultSet rs = stmt.executeQuery(query);
		int columnCount = rs.getMetaData().getColumnCount();
		while(rs.next()){

			String[]row = new String[columnCount];
			for(int i =0 ; i< columnCount; i++){
				row[i] = rs.getString(i + 1);
			}
			results.add(row);

		}

		for(String[] arr : results){
			System.out.println(Arrays.toString(arr));
		}	
	}


	private static void modifyEmployee(Connection connection ) throws SQLException{
		Statement stmt = connection.createStatement();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter an ID you wish to edit");
		int employeeID = scanner.nextInt();

		String query = "SELECT * FROM Employee WHERE id="+employeeID+";";
		ArrayList<String[]> results = new ArrayList<String[]>();
		ResultSet rs = stmt.executeQuery(query);
		int columnCount = rs.getMetaData().getColumnCount();
		while(rs.next()){

			String[]row = new String[columnCount];
			for(int i =0 ; i< columnCount; i++){
				row[i] = rs.getString(i + 1);
			}
			results.add(row);

		}

		for(String[] arr : results){
			System.out.println(Arrays.toString(arr));
		}	
		boolean exit = false;
		while(!exit){
			System.out.println("What Modification would you like to do?");
			System.out.println();
			System.out.println("*******Modification*******");
			System.out.println("1.Last Name");
			System.out.println("2.Hire Date yyyy-mm-dd");
			System.out.println("3.Birthday yyyy-mm-dd");
			System.out.println("4.Sex");
			System.out.println("5.Job Status");
			System.out.println("6.Salary/Hours Based");
			System.out.println("7.Annual Salary");
			System.out.println("8. Years of Service");
			System.out.println("9.Exit Modification");
			int choice = scanner.nextInt();

			switch(choice){

			case 1:System.out.println("Enter Last Name to Modify");
				     String lastName = scanner.next();
			         String updateName = "UPDATE Employee SET lastname="+"'"+lastName+"'"+" WHERE id="+employeeID+";";
			         stmt.execute(updateName);
			         break;
			case 2: System.out.println("Enter Hire Date yyyy-mm-dd");
			         String HireDate = scanner.next();	         
		           	 String updateHireDate = "UPDATE Employee SET HireDate="+"'"+HireDate+"'"+"  WHERE id="+employeeID+";";
			         stmt.execute(updateHireDate);
			        break;
			case 3: System.out.println("Enter a BirthDay yyyy-mm-dd");
					String birthday = scanner.next();
					String updateBirthday = "UPDATE Employee SET Birthday="+"'"+birthday+"'"+" WHERE id="+employeeID+";";
					stmt.execute(updateBirthday);
					break;
			case 4: System.out.println("Enter Sex");
					String sex = scanner.next();
					String updateSex ="UPDATE Employee SET Sex ="+"'"+sex+"'"+" WHERE id="+employeeID+";";
					stmt.execute(updateSex);
					break;
			case 5 : System.out.println("Enter Job Status");
					String jobStatus = scanner.next();
					String updateJobStatus ="UPDATE Employee SET Jobstatus ="+"'"+jobStatus+"'"+" WHERE id="+employeeID+";";
					stmt.execute(updateJobStatus);
					break;
			case 6 : System.out.println("Enter Salary Based or Hourly (S/H)");
					String hourly = scanner.next();
					String updateHourly = "UPDATE Employee SET Paytype ="+"'"+hourly+"'"+" WHERE id="+employeeID+";";
					stmt.execute(updateHourly);
					break;
			case 7: System.out.println("Enter your Annual Salary");
					String annualSalary = scanner.next();
					String updatedAnnualSalary = "UPDATE Employee SET annualsalary ="+"'"+annualSalary+"'"+" WHERE id="+employeeID+";";
					stmt.execute(updatedAnnualSalary);
					break;
			case 8: System.out.println("Enter Years of Service");
					String yOfService = scanner.next();
					String updatedYOfService= "UPDATE Employee SET yearsofService ="+"'"+yOfService+"'"+" WHERE id="+employeeID+";";
					stmt.execute(updatedYOfService);
					break;
			
			case 9: System.out.println("*****Exit Modification*****");
					exit = true;
					break;
			}

		}

	}
	private static void addEmployee(Connection connection) throws SQLException{

		Scanner scanner = new Scanner(System.in);


		System.out.println("Enter Last Name");
		String lastName = scanner.next();
		System.out.println("Enter Hire Date 'yyyy-mm-dd");
		String hireDate = scanner.next();
		System.out.println("Enter Birthday 'yyyy-mm-dd");
		String birthday = scanner.next();
		System.out.println("Enter M/F");
		String sex = scanner.next();
		System.out.println("Enter Job Status");
		String jobStatus = scanner.next();
		System.out.println("Enter Pay time Salary/Hourly");
		String payType = scanner.next();
		System.out.println("Enter Annual Salary");
		String annualSalary = scanner.next();
		System.out.println("Enter Years of Service");
		int yearsOfService = scanner.nextInt();


		PreparedStatement  pt = connection.prepareStatement("INSERT INTO Employee ( lastname, HireDate, Birthday, Sex,Jobstatus,Paytype,annualsalary,YearsofService) VALUES (?, ?, ?, ?,?,?,?,?);");

		pt.setString(1, lastName);
		pt.setString(2, hireDate);
		pt.setString(3,birthday);
		pt.setString(4, sex);
		pt.setString(5, jobStatus);
		pt.setString(6, payType);
		pt.setString(7, "$"+annualSalary);
		pt.setInt(8, yearsOfService);

		pt.execute();

	}

	private static void deleteEmployee(Connection connection) throws SQLException{

		Scanner scanner = new Scanner(System.in);

		Statement stmt = connection.createStatement();

		System.out.println("Please enter a four digit ID to Delete User");
		String user = scanner.next();

		String query = "DELETE FROM Employee WHERE id="+user+";";


		stmt.execute(query);
		//		if(stmt.execute(query)){
		//			System.out.println("Delete was successful");
		//		}
		//		else{
		//			System.out.println("Invalid ID ");
		//		}

	}

	private static void viewEmployee(Connection connection)throws SQLException{
		Scanner scanner = new Scanner(System.in);
		Statement stmt = connection.createStatement();
		System.out.println("Enter Employee ID To view ");
		String viewEmployeeID = scanner.next();

		String query = "SELECT * FROM Employee WHERE id="+viewEmployeeID+";";


		ArrayList<String[]> results = new ArrayList<String[]>();
		ResultSet rs = stmt.executeQuery(query);
		int columnCount = rs.getMetaData().getColumnCount();
		while(rs.next()){

			String[]row = new String[columnCount];
			for(int i =0 ; i< columnCount; i++){
				row[i] = rs.getString(i + 1);
			}
			results.add(row);

		}
		for(String[] arr : results){
			System.out.println(Arrays.toString(arr));
		}	



	}
	private static void viewFemale40(Connection connection) throws SQLException{

		Statement stmt = connection.createStatement();


		String query = "SELECT * FROM Employee WHERE Sex='F' AND annualsalary > 40000;";
		ArrayList<String[]> results = new ArrayList<String[]>();
		ResultSet rs = stmt.executeQuery(query);
		int columnCount = rs.getMetaData().getColumnCount();
		while(rs.next()){

			String[]row = new String[columnCount];
			for(int i =0 ; i< columnCount; i++){
				row[i] = rs.getString(i + 1);
			}
			results.add(row);
		}
		for(String[] arr : results){
			System.out.println(Arrays.toString(arr));
		}	

	}

	private static void view40Years(Connection connection) throws SQLException{

		Statement stmt = connection.createStatement();

		String query ="SELECT * FROM Employee WHERE TIMESTAMPDIFF(YEAR,Birthday,CURDATE()) > 40 AND Jobstatus ='FT' AND Paytype ='S' AND YearsofService > 3;";

		ArrayList<String[]> results = new ArrayList<String[]>();
		ResultSet rs = stmt.executeQuery(query);
		int columnCount = rs.getMetaData().getColumnCount();
		while(rs.next()){

			String[]row = new String[columnCount];
			for(int i =0 ; i< columnCount; i++){
				row[i] = rs.getString(i + 1);
			}
			results.add(row);
		}
		for(String[] arr : results){
			System.out.println(Arrays.toString(arr));
		}	
	}

	private static void viewDataBase(Connection connection) throws SQLException {
		System.out.println("*****Employee Database*****");

		Statement stmt = connection.createStatement();

		String query = "SELECT * FROM Employee;";
		ArrayList<String[]> results = new ArrayList<String[]>();
		ResultSet rs = stmt.executeQuery(query);
		int columnCount = rs.getMetaData().getColumnCount();
		while(rs.next()){

			String[]row = new String[columnCount];
			for(int i =0 ; i< columnCount; i++){
				row[i] = rs.getString(i + 1);
			}
			results.add(row);
		}
		for(String[] arr : results){
			System.out.println(Arrays.toString(arr));
		}	
	}

}
