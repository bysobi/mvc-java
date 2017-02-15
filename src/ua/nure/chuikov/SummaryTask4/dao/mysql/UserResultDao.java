package ua.nure.chuikov.SummaryTask4.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ua.nure.chuikov.SummaryTask4.dao.UserTestResultDao;
import ua.nure.chuikov.SummaryTask4.db.DBManager;
import ua.nure.chuikov.SummaryTask4.entity.UserTestResult;

public class UserResultDao implements UserTestResultDao {

    private DBManager dbManager;

    private static final String SQL_All_FROMTEST_RESULT = "SELECT `testId`, `userId`, `passageOfTime`, `result` FROM `user_test`";
    private static final String SQL_MAX_RESULT = "SELECT u.id, u.userLogin, t.testName, subQuery.max_result FROM user_test AS u_t "
	    + " JOIN (SELECT testId, MAX(result) as max_result FROM user_test GROUP BY testId) as subQuery ON subQuery.testId = u_t.testId AND"
	    + " subQuery.max_result = u_t.result JOIN test as t ON t.id = subQuery.testId JOIN user as u ON u.id = u_t.userId";

    // //////////////////////////////////////////////////////////
    // SQL queries
    // //////////////////////////////////////////////////////////
    public UserResultDao(DBManager dbManager) {
	this.dbManager = dbManager;
    }

    private UserTestResult extractUserREsult(ResultSet rs) throws SQLException {
	UserTestResult result = new UserTestResult();
	result.setUserId(rs.getInt("u.id"));
	result.setUserLogin(rs.getString("u.userLogin"));
	result.setResult(rs.getInt("max_result"));
	
	
	result.setTestName(rs.getString("t.testName"));
	return result;
    }

    @Override
    public int gettestMaxREsult(int i) {

	return 0;
    }

    @Override
    public ArrayList<UserTestResult> getAll() {
	Connection connection = null;
	ArrayList<UserTestResult> listUserInfo = new ArrayList<>();
	try {
	    connection = dbManager.getConnection();
	    //PreparedStatement prst = connection.prepareStatement(SQL_All_FROMTEST_RESULT);
	   PreparedStatement prst = connection.prepareStatement(SQL_MAX_RESULT);

	    // LOG.debug(prst);
	    ResultSet rs = prst.executeQuery();
	    while (rs.next()) {
		UserTestResult userInfo = extractUserREsult(rs);
		listUserInfo.add(userInfo);
	    }
	    rs.close();
	} catch (SQLException e) {
	    dbManager.rollBack(connection);
	} finally {
	    dbManager.commitAndClose(connection);
	}
	return listUserInfo;
    }

}
