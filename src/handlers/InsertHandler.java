package src.handlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.SQLException;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import src.StudentDAO;
import src.entity.Student;

/**
 * This class handles inserting a student record into the students table.
 * Goes from JSON format to Java format.
 * @author Faran Azadi 
 *
 */

public class InsertHandler implements HttpHandler{

	@Override
	public void handle(HttpExchange he) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(he.getRequestBody()));
		Gson gson = new Gson();
		//Convert from JSON to Java 
		Student student = gson.fromJson(in, Student.class);
		StudentDAO dao = new StudentDAO();
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
		
		try {
			//Call the insertStu from the StudentDAO class
			dao.insertStu(student);
			//Send the response header 200 (OK)
			he.sendResponseHeaders(200, 0);
			//Let the user know the student was inserted successfully
			out.write("Student has been successfully added to database!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		in.close();
		out.close();
	}
}
