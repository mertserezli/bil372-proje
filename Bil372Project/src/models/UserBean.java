package models;

public class UserBean {
	private String UserName;
	private String Password;
	private String JobTitle;
	private String Image;
	private String FirstName;
	private String MiddleName;
	private String LastName;
	private String Email;
	public boolean valid;

	public String getPassword() {
		return Password;
	}

	public void setPassword(String newPassword) {
		Password = newPassword;
	}

	public String getUsername() {
		return UserName;
	}

	public void setUserName(String newUsername) {
		UserName = newUsername;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean newValid) {
		valid = newValid;
	}

	public String getJobTitle() {
		return JobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.JobTitle = jobTitle;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		this.Image = image;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		this.FirstName = firstName;
	}

	public String getMiddleName() {
		return MiddleName;
	}

	public void setMiddleName(String middleName) {
		this.MiddleName = middleName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		this.LastName = lastName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

}
