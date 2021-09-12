package src;
import java.sql.SQLException;
import java.util.ArrayList;

import src.entity.Student;

/**
 * A class to contain all of the tests on the database.
 * 
 * @author Faran Azadi - 16038320
 *
 */

public class DatabaseTester {

	public static void main(String[] args) {
		//ArrayList and StudentDAO object for getAllStudents test
		ArrayList<Student> allStudents = new ArrayList<>();
		StudentDAO dao = new StudentDAO();
		
		//Student object for get single student test
		Student singleStu = null;
		
		//Student to be added for the insertStu test
		Student newStudent = new Student("Yusof Smith", "F", "01/01/1996", "1 Street", "M15 5TY", 1234, "Comp Sci", "11/01/2018", 1234567, "ybandar1234@gmail.com");
		
		try {
			//Get all students test
			//System.out.println("getAllStudents test");
			//allStudents = dao.getAllStudents();
			//System.out.println(allStudents);
			
			//System.out.println("---------------------------------");
			
			//Get single student test
			System.out.println("getStudent test");
			singleStu = dao.getStudent("1234");
			System.out.println(singleStu);
			
			//System.out.println("---------------------------------");
			
			//Insert student test
			//System.out.println("insertStu test");
			//dao.insertStu(newStudent);
			
			//System.out.println("---------------------------------");
			
			//Delete student test
			//System.out.println("deleteStu test");
			//dao.deleteStu("1234");
			
			//System.out.println("---------------------------------");
			
			//Update student test
			//System.out.println("updateStu test");
			//dao.updateStu(newStudent);
			
		} catch (SQLException e) {
			System.out.println("SQL exception: "+e.getMessage());
			
		}
	}
	
	//Add other tests here
}
