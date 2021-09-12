package src.entity;
/**
 * A class to represent a person with appropriate attributes such as name, gender, DOB etc.
 * 
 * @author Faran Azadi 
 *
 */

public class Person {

	//Attributes a person has
	private String name;
    private String gender;
    private String dob;
    private String address;
    private String postcode;
    

    /**
     * Constructor
     * 
     * @param name the person's name
     * @param gender the person's gender
     * @param dob the person's date of birth
     * @param address the person's address
     * @param postcode the person's postcode
     */
    public Person(String name, String gender, String dob, String address, String postcode) {
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.postcode = postcode;
    }

    /**
     * Gets a persons name.
     * @return returns a person's name.
     */
    public String getName() {
		return name;
	}

    /**
     * Sets a person's name. 
     * @param name the name the person will be called.
     */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * Gets a person's gender.
	 * @return the person's gender.
	 */
	public String getGender() {
		return gender;
	}


	/**
	 * Sets a person's gender.
	 * @param gender the gender the person will be.    
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}


	/**
	 * Gets a person's date of birth.
	 * @return the person's date of birth.
	 */
	public String getDob() {
		return dob;
	}

	/**
	 * Sets a person's date of birth.
	 * @param dob sets a person's date of birth.   
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}


	/**
	 * Gets a person's address.
	 * @return the person's address.  
	 */
	public String getAddress() {
		return address;
	}


	/**
	 * Sets the person's address.
	 * @param address where the person lives 
	 */
	public void setAddress(String address) {
		this.address = address;
	}


	/**
	 * Gets a person's post code.
	 * @return the post code of the person.
	 */
	public String getPostcode() {
		return postcode;
	}


	/**
	 * Sets the person's postcode.
	 * @param postcode the postcode of the person
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	public String toString(){
        return "Name :"+getName() + "Gender: " + getGender() + "Date of birth: " + getDob() + "Address: " + getAddress() + "Postcode: " + getPostcode();
    }
}
