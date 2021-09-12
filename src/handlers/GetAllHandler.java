package src.handlers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import src.entity.Student;
import src.StudentDAO;

/**
 * Gets all records in the student table and displays them in the body.
 * @author Faran Azadi 
 *
 */
public class GetAllHandler implements HttpHandler {
	/**
	 * The handle method. Implements HttpHandler so this method is required.
	 */
	public void handle(HttpExchange he) throws IOException {
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
		StudentDAO dao = new StudentDAO();
		//Create array list of type Student
		ArrayList<Student> allStudents = new ArrayList<>();
		try {
			//Call the getAllStudents method from the StudentDAO class
			allStudents = dao.getAllStudents();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		//Store details like name, gender etc...
		String students = allStudents.toString();
		//Send the response header 200 (OK)
		he.sendResponseHeaders(200, 0);
		//Display all the students on the page
		out.write(students);
		out.close();
	}
}
