package src.entity;
/**
 * A class to represent a student. A student is a person so extends the Person class.
 * 
 * @author Faran Azadi 
 *
 */
public class Student extends Person {
	
	//Attributes a student has
	private int studentNumber; 
	private String courseTitle; 
	private String startDate; 
	private float bursary; 
	private String email;
	
	/**
	 * Constructor
	 * 
	 * @param name what the student is called.
	 * @param gender what gender the student is.
	 * @param dob when the student was born.
	 * @param address where the student lives.
	 * @param postcode the post code of the student.
	 * @param studentNumber the unique identifier.
	 * @param courseTitle the course the student is on.
	 * @param startDate the date the student started
	 * @param bursary the amount of money entitled to
	 * @param email the student's email address
	 */
	public Student(String name, String gender, String dob, String address, String postcode, int studentNumber,
			String courseTitle, String startDate, float bursary, String email) {
		super(name, gender, dob, address, postcode); //Inherit these from the person class
		
		this.studentNumber = studentNumber;
		this.courseTitle = courseTitle;
		this.startDate = startDate;
		this.bursary = bursary;
		this.email = email;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public int getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(int studentNumber) {
		this.studentNumber = studentNumber;
	}
	
	public String getCourseTitle() {
		return courseTitle;
	}
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public float getBursary() {
		return bursary;
	}
	public void setBursary(float bursary) {
		this.bursary = bursary;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
