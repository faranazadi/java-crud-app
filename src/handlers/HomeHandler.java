package src.handlers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * This class handles when the user goes to the homepage.
 * @author Faran Azadi 
 *
 */

public class HomeHandler implements HttpHandler {
	
	/**
	 * The handle method. Implements HttpHandler so this method is required.
	 */	
	public void handle(HttpExchange he) throws IOException {

		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
		//Send the response header 200 (OK)
		he.sendResponseHeaders(200, 0);
		//Display this message
		out.write("Welcome to the student webservice");
		out.close();

	}

}
