package src.handlers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import src.StudentDAO;
import src.entity.Student;

/**
 * Returns all records in the students table in JSON format.
 * @author Faran Azadi 
 *
 */
public class GetAllJsonHandler implements HttpHandler {

	/**
	 * The handle method. Implements HttpHandler so this method is required.
	 */
	public void handle(HttpExchange he) throws IOException {

		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
		Gson gson = new Gson();

		StudentDAO dao = new StudentDAO();
		//Create an array list of type Student
		ArrayList<Student> allStudents = new ArrayList<>();
		try {
			//Call the getAllStudents method from the StudentDAO class
			allStudents = dao.getAllStudents();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		//Convert the students to JSON format
		String allStudentsJSON = gson.toJson(allStudents);
		//Send the response header 200 (OK)
		he.sendResponseHeaders(200, 0);
		//Display the students in JSON format
		out.write(allStudentsJSON);
		out.close();
	}

}
