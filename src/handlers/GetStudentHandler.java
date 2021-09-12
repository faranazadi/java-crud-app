package src.handlers;

import java.io.BufferedWriter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import src.StudentDAO;
import src.entity.Student;

/**
 * Gets a specified student record from the students table using a GET request.
 * @author Faran Azadi 
 *
 */

public class GetStudentHandler implements HttpHandler {
	
	/**
	 * The handle method. Implements HttpHandler so this method is required.
	 */
	public void handle(HttpExchange he) throws IOException {
		StringBuilder response = new StringBuilder();
		Map<String, String> parms = GetStudentHandler.queryToMap(he.getRequestURI().getQuery());
		Gson gson = new Gson();
		StudentDAO dao = new StudentDAO();
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
		
		String studentNum = parms.get("StudentNumber");
		
		try {
			//Get the StudentNumber so we know which student object to get 
			Student student = dao.getStudent(studentNum);
			//The JSON format of the student java object
			String jsonStudent = gson.toJson(student);
			//Send the response header 200 (OK)
			he.sendResponseHeaders(200, 0);
			//Display the student in JSON format
			out.write(jsonStudent);
			out.close();
		} catch (SQLException e) {
		System.out.println(e.getMessage());
	}
}
	
	/*public static void writeResponse(HttpExchange httpExchange, String response) throws IOException {
	    httpExchange.sendResponseHeaders(200, response.length());
	    OutputStream os = httpExchange.getResponseBody();
	    os.write(response.getBytes());
	    os.close();
	  }*/
	
	  /**
	   * returns the url parameters in a map
	   * @param query
	   * @return map
	   */
	  public static Map<String, String> queryToMap(String query){
	    Map<String, String> result = new HashMap<String, String>();
	    for (String param : query.split("&")) {
	        String pair[] = param.split("=");
	        if (pair.length > 1) {
	            result.put(pair[0], pair[1]);
	        } else {
	            result.put(pair[0], "");
	        }
	    }
	    return result;
	  }	
}