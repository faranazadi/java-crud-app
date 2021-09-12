package src.handlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import src.StudentDAO;
import src.entity.Student;

/**
 * Deletes a specified student using a POST request. 
 * @author Faran Azadi
 *
 */

public class DeleteHandler implements HttpHandler {

	/**
	 * The handle method. Implements HttpHandler so this method is required.
	 */
	@Override
	public void handle(HttpExchange he) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(he.getRequestBody()));
		//Store the student id the user wishes to delete
		String stuID = in.lines().collect(Collectors.joining());
		StudentDAO dao = new StudentDAO();
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
		
		try {
			//Call the method from the StudentDAO class using the id entered by the user
			dao.deleteStu(stuID);
			//Send the response header 200 (OK)
			he.sendResponseHeaders(200, 0);
			//Notify the user that the operation has been successful
			out.write("Student has been successfully removed from the database!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		in.close();
		out.close();
	}

}
