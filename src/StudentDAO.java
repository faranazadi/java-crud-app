package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import src.entity.Student;

/**
 * Student Data Access Object class. Contains all of the CRUD operations on the
 * database. All code that is incomplete or not working has been commented out.
 * 
 * @author Faran Azadi - 16038320
 *
 */

public class StudentDAO {

	/**
	 * Establishes a connection to the SQLite database.
	 * 
	 * @return dbConnection the actual connection to the database 
	 */
	public Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			String dbURL = "jdbc:sqlite:studentdb.sqlite";
			dbConnection = DriverManager.getConnection(dbURL);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;
	}

	/*
	 * public void closeConnection() { try { if (resultSet != null) {
	 * resultSet.close(); } if (statement != null) { statement.close(); } if
	 * (dbConnection != null) { dbConnection.close(); } } catch (SQLException e)
	 * { e.printStackTrace(); } }
	 */

	/**
	 * Gets all student records stored in the students table.
	 * @return allStudents the array list of all the students
	 * @throws SQLException
	 */
	public ArrayList<Student> getAllStudents() throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String query = "SELECT * FROM students;";
		Student temp = null;
		ArrayList<Student> allStudents = new ArrayList<>();

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println("Query being executed: " + query);
			// Execute SQL query
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {

				String name = resultSet.getString("Name");
				String gender = resultSet.getString("Gender");
				String dob = resultSet.getString("DOB");
				String address = resultSet.getString("Address");
				String postcode = resultSet.getString("Postcode");

				int stuID = resultSet.getInt("StudentNumber");
				String courseTitle = resultSet.getString("CourseTitle");
				String startDate = resultSet.getString("StartDate");
				Float bursary = resultSet.getFloat("Bursary");
				String email = resultSet.getString("Email");

				// Create temp student based on fields grabbed from database
				temp = new Student(name, gender, dob, address, postcode, stuID, courseTitle, startDate, bursary, email);

				// Add the temp student created above to the array list
				allStudents.add(temp);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			// closeConnection();
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return allStudents;
	}

	/**
	 * Gets a particular student record stored in the students table.
	 * @param id the StudentNumber of the particular student
	 * @return
	 * @throws SQLException
	 */
	public Student getStudent(String id) throws SQLException {
		Student temp = null;
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet resultset = null;
		String query = "SELECT * FROM students WHERE StudentNumber = " + id + ";";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println("Query being executed: " + query);
			// Execute SQL query
			resultset = statement.executeQuery(query);
			while (resultset.next()) {
				String name = resultset.getString("Name");
				String gender = resultset.getString("Gender");
				String dob = resultset.getString("DOB");
				String address = resultset.getString("Address");
				String postcode = resultset.getString("Postcode");

				int stuID = resultset.getInt("StudentNumber");
				String courseTitle = resultset.getString("CourseTitle");
				String startDate = resultset.getString("StartDate");
				Float bursary = resultset.getFloat("Bursary");
				String email = resultset.getString("Email");

				// Create temp student based on fields grabbed from database
				temp = new Student(name, gender, dob, address, postcode, stuID, courseTitle, startDate, bursary, email);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			// closeConnection();
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return temp;
	}

	/*
	 * public Boolean insertStu(Student stu) throws SQLException { boolean
	 * inserted = false; try { String sql =
	 * "INSERT INTO students (Name, Email) VALUES (\"" + stu.getName() + "\"," +
	 * "\"" + stu.getEmail() + "\")"; dbConnection = getDBConnection();
	 * statement = dbConnection.createStatement();
	 * System.out.println("Query being executed: " + sql); // Execute SQL query
	 * inserted = statement.execute(sql); } catch (SQLException e) {
	 * System.out.println(e.getMessage()); } finally { closeConnection(); }
	 * return inserted; }
	 */

	/**
	 * Inserts a new student row into the students table using
	 * PreparedStatements.
	 * 
	 * @param stu
	 *            the student to be added to the table
	 * @throws SQLException
	 */
	public void insertStu(Student stu) throws SQLException {
		String sql = "INSERT INTO students (Name, Gender, DOB, Address, Postcode, StudentNumber, CourseTitle, StartDate, Bursary, Email) VALUES(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = null;
		Connection dbConnection = this.getDBConnection();
		try {
			ps = dbConnection.prepareStatement(sql);
			ps.setString(1, stu.getName());
			ps.setString(2, stu.getGender());
			ps.setString(3, stu.getDob());
			ps.setString(4, stu.getAddress());
			ps.setString(5, stu.getPostcode());
			ps.setInt(6, stu.getStudentNumber());
			ps.setString(7, stu.getCourseTitle());
			ps.setString(8, stu.getStartDate());
			ps.setFloat(9, stu.getBursary());
			ps.setString(10, stu.getEmail());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			// closeConnection();
			if (ps != null) {
				ps.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
	}

	/**
	 * Deletes a particular student record from the students table.
	 * @param stuID the unique StudentNumber of the record to be deleted
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteStu(String stuID) throws SQLException {
		boolean deleted = false;
		Connection dbConnection = null;
		Statement statement = null;
		String query = "DELETE FROM students WHERE StudentNumber = " + stuID + ";";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println("Query being executed: " + query);
			// Execute SQL query
			deleted = statement.execute(query);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			// closeConnection();
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return deleted;
	}

	/**
	 * Updates a particular row in the students table. Makes use of PreparedStatements again.
	 * @param stu the row in the table to be updated.
	 * @throws SQLException
	 */
	public void updateStu(Student stu) throws SQLException {
		String sql = "UPDATE students SET Name = ?, Gender = ?, DOB = ?, Address = ?, Postcode = ?, StudentNumber = ?, CourseTitle = ?, StartDate = ?, Bursary = ?, Email = ? WHERE StudentNumber = ?";
		PreparedStatement ps = null;
		Connection dbConnection = this.getDBConnection();
		try {
			ps = dbConnection.prepareStatement(sql);
			ps.setString(1, stu.getName());
			ps.setString(2, stu.getGender());
			ps.setString(3, stu.getDob());
			ps.setString(4, stu.getAddress());
			ps.setString(5, stu.getPostcode());
			ps.setInt(6, stu.getStudentNumber());
			ps.setString(7, stu.getCourseTitle());
			ps.setString(8, stu.getStartDate());
			ps.setFloat(9, stu.getBursary());
			ps.setString(10, stu.getEmail());
			ps.setInt(11, stu.getStudentNumber());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			// closeConnection();
			if (ps != null) {
				ps.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
	}

	public Boolean checkLoginCredentials(String username, String password) throws SQLException {

		return false;
	}

	public boolean checkApiKey(String key) throws SQLException {

		return false;
	}
}
