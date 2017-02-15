package ua.nure.chuikov.SummaryTask4.entity;

public class User {
    private int id;
    private String firstName;
    private String secondName;
    private String lastName;
    private String userLogin;
    private String userPassword;
    private String locale;
    private boolean isActive;
 
    private RoleEnum role;
    

    public RoleEnum getRole(){
	return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public User() {
    }

    public User(String firstName, String secondName, String lastName, String userLogin, String userPassword,
	    RoleEnum  role, String lacale, boolean isActive) {
	this.firstName = firstName;
	this.secondName = secondName;
	this.lastName = lastName;
	this.userLogin = userLogin;
	this.userPassword = userPassword;
	this.role = role;
	this.locale = lacale;
	this.isActive = isActive;
    }
    

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getSecondName() {
	return secondName;
    }

    public void setSecondName(String secondName) {
	this.secondName = secondName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public String getUserLogin() {
	return userLogin;
    }

    public void setUserLogin(String userLogin) {
	this.userLogin = userLogin;
    }

    public String getUserPassword() {
	return userPassword;
    }

    public void setUserPassword(String userPassword) {
	this.userPassword = userPassword;
    }

   
 
    public String getLocale() {
	return locale;
    }

    public void setLocale(String locale) {
	this.locale = locale;
    }

   

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
	this.isActive = isActive;
    }

    @Override
    public String toString() {
	return "User [id=" + id + ", firstName=" + firstName + ", secondName=" + secondName + ", lastName=" + lastName
		+ ", userLogin=" + userLogin + ", userPassword=" + userPassword +  ", locale="
		+ locale + ", isActive=" + isActive + ", role=" + role + "]";
    }

    
}
