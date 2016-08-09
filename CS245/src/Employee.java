
public class Employee {
	
	private String id;
	private String lastName;
	private String hireDate;
	private String birthDate;
	private String sex;
	private String jobStatus;
	private String payType;
	private double salary;
	private double yearsOfService;
	

	public Employee(String id, String lastName,String hireDate,String birthDate,String sex,String jobStatus, String payType,double salary,double yearsOfService){
		this.id = id;
		this.lastName = lastName;
		this.hireDate = hireDate;
		this.birthDate = birthDate;
		this.sex = sex;
		this.jobStatus = jobStatus;
		this.payType = payType;
		this.salary = salary;
		this.yearsOfService = yearsOfService;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getHireDate() {
		return hireDate;
	}
	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public double getYearsOfService() {
		return yearsOfService;
	}
	public void setYearsOfService(double yearsOfService) {
		this.yearsOfService = yearsOfService;
	}
	
}
