package models;

public class CompanyBean {
	private String CompID;
	private String Name;
	private String Description;
	private String[] AdministratorUserName;

	public String getCompID() {
		return CompID;
	}

	public void setCompID(String compID) {
		CompID = compID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String[] getAdministratorUserName() {
		return AdministratorUserName;
	}

	public void setAdministratorUserName(String[] administratorUserName) {
		AdministratorUserName = administratorUserName;
	}

}
