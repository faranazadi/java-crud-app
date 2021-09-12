package src;

import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;

import src.handlers.DeleteHandler;
import src.handlers.GetAllHandler;
import src.handlers.GetAllJsonHandler;
import src.handlers.GetStudentHandler;
import src.handlers.HomeHandler;
import src.handlers.InsertHandler;
import src.handlers.UpdateHandler;

/**
 * Controller class to start up the server and set up all the handlers.
 * 
 * @author Faran Azadi - 16038320
 *
 */

public class Controller {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			//Create the HttpServer on port 8005
			HttpServer server = HttpServer.create(new InetSocketAddress(8005), 0);
		
			//Set up all the handlers
			server.createContext("/", new HomeHandler());
			server.createContext("/get-all", new GetAllHandler());
			server.createContext("/getallJSON", new GetAllJsonHandler());
			server.createContext("/getstudent", new GetStudentHandler());
			server.createContext("/insertstudent", new InsertHandler());
			server.createContext("/deletestudent", new DeleteHandler());
			server.createContext("/updatestudent", new UpdateHandler());
			
			//Start the server
			server.setExecutor(null);
			server.start();
			System.out.println("Server running on port 8005");

		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
}
