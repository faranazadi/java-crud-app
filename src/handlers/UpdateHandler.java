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

public class UpdateHandler implements HttpHandler{

	@Override
	public void handle(HttpExchange he) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(he.getRequestBody()));
		StudentDAO dao = new StudentDAO();
		Gson gson = new Gson();
		Student student = gson.fromJson(in, Student.class);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
		
		try {
			dao.updateStu(student);
			he.sendResponseHeaders(200, 0);
			out.write("Student has been successfully updated in the database!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		in.close();
		out.close();
	}

}
