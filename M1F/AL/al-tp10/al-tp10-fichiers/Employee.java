public class Employee implements IEmployee {
	private String name;
	private String address;
	private String phoneNumber;
	private String SSN;
	private String department;
	private float salary;

	public Employee(String name, String address, String phoneNumber, String SSN, 
	  String department, float salary) {
		this.name=name; this.address=address; this.phoneNumber=phoneNumber; 
		this.SSN=SSN; this.department=department; this.salary=salary;
	}
	
	public String getName() { return name; }
	public String getAddress() { return address; }
	public String getPhoneNumber() { return phoneNumber; }
	public String getSSN() { return SSN; }
	public String getDepartment() { return department; }
	public float getSalary() { return salary; }

	public void setName(String name) { this.name = name; }
	public void setAddress(String address) { this.address = address; }
	public void setPhoneNumber(String phoneNumber) { 
		this.phoneNumber = phoneNumber; }
	public void setSSN(String ssn) { this.SSN = SSN; }
	public void setDepartment(String department) { this.department = department; }
	public void setSalary(float salary) { this.salary = salary; }
}

