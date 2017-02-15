package ua.nure.chuikov.SummaryTask4.entity;


public class UserTestResult {
    int userId;
    String userLogin;
    String testName;
    //Time paasageTime;
    int result;

   

    public String getTestName() {
        return testName;
    }



    public void setTestName(String testName) {
        this.testName = testName;
    }



    @Override
    public String toString() {
	return "UserTestResult [userId = " + userId + ", userId = " + userLogin + ", testName = "+ testName+ ", result="
		+ result + "]";
    }

   
   
    public int getUserId() {
        return userId;
    }



    public void setUserId(int userId) {
        this.userId = userId;
    }



    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }


    public int getResult() {
	return result;
    }

    public void setResult(int result) {
	this.result = result;
    }
}
