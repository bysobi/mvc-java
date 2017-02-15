package code.university.entity;

public class User {
    private int id;
    private String f_name_ukr;
    private String s_name_ukr;
    private String m_name_ukr;
    private String f_name_eng;
    private String s_name_eng;
    private String m_name_eng;
    private String email;
    private String phone;
    private String sec_email;
    private Integer department;
    private Boolean enabled;
    private String created_at;
    private String updated_at;
    private String password;

    private RoleEnum role;
        
    public RoleEnum getRole(){
	return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public User() {
    }

    public User(
	    String f_name_ukr,
	    String s_name_ukr, 
	    String m_name_ukr,
	    String f_name_eng,
	    String s_name_eng,
	    String m_name_eng,
	    String email,
	    String phone,
	    String sec_email,
	    Integer department,
	    Integer enabled,
	    String password,
	    RoleEnum  role
	    ) {
	this.f_name_ukr = f_name_ukr;
	this.s_name_ukr = s_name_ukr;
	this.m_name_ukr = m_name_ukr;
	this.f_name_eng = f_name_eng;
	this.s_name_eng = s_name_eng;
	this.m_name_eng = m_name_eng;
	this.email = email;
	this.phone = phone;
	this.sec_email = sec_email;
	this.department = department;
	this.role = role;
    }
    

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public void setFirstNameUkr(String data){
	this.f_name_ukr = data;
    }
    
    public void setSecondNameUkr(String data){
	this.s_name_ukr = data;
    }
    
    public void setMiddleNameUkr(String data){
	this.m_name_ukr = data;
    }
    
    public void setFirstNameEng(String data){
	this.f_name_eng = data;
    }
    
    public void setSecondNameEng(String data){
	this.s_name_eng = data;
    }
    
    public void setMiddleNameEng(String data){
	this.m_name_eng = data;
    }
    
    public void setEmail(String data){
	this.email = data;
    }
    
    public void setPhone(String data){
	this.phone = data;
    }
    
    public void setSecondEmail(String data){
	this.sec_email = data;
    }
    
    public void setDepartment(Integer data){
	this.department = data;
    }
    
    public void setPassword(String data){
	this.password = data;
    }
    
    public boolean isActive(){
	return this.enabled;
    }
    
    
    public String getFirstNameUkr(){
	return this.f_name_ukr;
    }
    
    public String getSecondNameUkr(){
	return this.s_name_ukr;
    }
    
    public String getMiddleNameUkr(){
	return this.m_name_ukr;
    }
    
    public String getFirstNameEng(){
	return this.f_name_eng;
    }
    
    public String getSecondNameEng(){
	return this.s_name_eng;
    }
    
    public String getMiddleNameEng(){
	return this.m_name_eng;
    }
    
    public String getEmail(){
	return this.email;
    }
    
    public String getPhone(){
	return this.phone;
    }
    
    public String getSecondEmail(){
	return this.sec_email;
    }
    
    public Integer getDepartment(){
	return this.department;
    }
    
    public String getPassword(){
	return this.password;
    }
    

//    @Override
//    public String toString() {
//	return "User [id=" + id + ", firstName=" + firstName + ", secondName=" + secondName + ", lastName=" + lastName
//		+ ", userLogin=" + userLogin + ", userPassword=" + userPassword +  ", locale="
//		+ locale + ", isActive=" + isActive + ", role=" + role + "]";
//    }

    
}
