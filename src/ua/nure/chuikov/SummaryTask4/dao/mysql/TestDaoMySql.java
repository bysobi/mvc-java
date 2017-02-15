package ua.nure.chuikov.SummaryTask4.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.chuikov.SummaryTask4.dao.TestDao;
import ua.nure.chuikov.SummaryTask4.db.DBManager;
import ua.nure.chuikov.SummaryTask4.entity.Test;

/**
 * This class is using for work with table `test`
 * 
 * @author Yarik
 * @version 1.0
 */
public class TestDaoMySql implements TestDao {
    private static final Logger TESTLOG = Logger.getLogger(TestDaoMySql.class);
    // //////////////////////////////////////////////////////////
    // SQL queries
    // //////////////////////////////////////////////////////////
    private static final String SQL_SELECT_TEST_BY_ID = "SELECT * FROM `test` WHERE id =?";
    private static final String SQL_SELECT_TEST_BY_SUDJECT_ID = "SELECT * FROM `test` WHERE subjectName = ?";//!!!!!!!!!
    private static final String SQL_SELECT_TEST_BY_NAME = "SELECT * FROM `test` WHERE testName = ?";
    private static final String SQL_SELECT_ALLTESTS = "SELECT * FROM `test` ";
    private static final String SQL_SELECT_TEST_BY_AMOUNT_OF_QUESTION = "SELECT * FROM `question` WHERE `testId`=?";
    private static final String SQL_CREATE_TEST = "INSERT INTO `test`( `subjectName`, `testName`, `timeToPass`) VALUES (?,?,?)";
    private static final String SQL_UPDATE_TEST = "UPDATE `test` SET `subjectName`=?,`testName`= ?,`timeToPass`= ? WHERE `id`= ?";
    private static final String SQL_GET_TEST_BY_COMPLEXITY = "SELECT `complexity` FROM `question` WHERE `testId` =?";
    private static final String SQL_DELETE_TEST_BY_ID = "DELETE FROM `test` WHERE id = ?";
    
    // доделать и проверить на работу бе жесткого указывания куда обращатся
    // запрос для выборки всех вопросв и ответов по ид теста
/*    private static final String SQL_SELECT_TEST_BY_ID_WITH_QUESTIONS_AND_ANSWERS = "SELECT `question`.`id`,`question`.`textOfQuestion`,"
	    + "`question`.`complexity`, `question`.`testId`, `answer`.`answerNumber`, `answer`.`textOfAnswer`, `answer`.`flag` FROM `question`"
	    + ",`answer` WHERE `question`.`id`= `answer`.`questionId` and `question`.`testId`=?";
*/
    private DBManager dbManager;

    public TestDaoMySql(DBManager dbManager) {
	this.dbManager = dbManager;
    }

    /**
     * This method extract from table `Test` value of complexity
     * 
     * @param rs
     *            sql query
     * @return complexity of test(Complexity is the sum of question complexity)
     * @see extractComplxity
     * 
     */
    private int extractComplexity(ResultSet rs) throws SQLException {
	int complexity = 0;
	complexity = rs.getInt("complexity");
	return complexity;
    }

    private Test extractTest(ResultSet rs) throws SQLException {
	Test test = new Test();
	test.setId(rs.getInt("id"));
	test.setSubjectName(rs.getString("subjectName"));
	test.setTestName(rs.getString("testName"));
	test.setTimeToPass(rs.getTime("timeToPass"));
	return test;

    }

    /**
     * This method is for getting test from database by his id
     * 
     * @param testId
     *            id of needed test
     * @return All information about test. His id, subjctId ,testName and
     *         timeToPass
     */
    @Override
    public Test getTestById(int testId) {
	Test test = null;
	Connection connection = null;
	try {
	    connection = dbManager.getConnection();
	    PreparedStatement prst = connection.prepareStatement(SQL_SELECT_TEST_BY_ID);
	    prst.setInt(1, testId);
	    //TESTLOG.debug(prst);
	    ResultSet rs = prst.executeQuery();
	    while (rs.next()) {
		test = extractTest(rs);
	    }
	    rs.close();
	} catch (SQLException e) {
	    dbManager.rollBack(connection);
	} finally {
	    dbManager.commitAndClose(connection);
	}
	return test;
    }

    /**
     * Method which get all information about test by his subjectId
     * 
     * @param subjectId
     * @return test by id
     * @throws SQLExeption
     */
    @Override
    public List<Test> getTestBySubject(int subjectId) throws SQLException {
	List<Test> testList = new ArrayList<>();
	Connection connectionn = null;
	Test test = null;
	try {
	    connectionn = dbManager.getConnection();
	    PreparedStatement prst = connectionn.prepareStatement(SQL_SELECT_TEST_BY_SUDJECT_ID);
	    prst.setInt(1, subjectId);
	    TESTLOG.debug(prst);
	    ResultSet rs = prst.executeQuery();
	    while (rs.next()) {
		test = extractTest(rs);
		testList.add(test);
	    }
	    rs.close();
	} catch (SQLException e) {
	    dbManager.rollBack(connectionn);
	} finally {
	    dbManager.commitAndClose(connectionn);
	}
	return testList;
    }

    /**
     * This method is for getting test from database by his name
     * 
     * @param name
     *            of test
     * @return test by name
     */
    @Override
    public Test getTestByName(String testName) throws SQLException {
	Test test = null;
	Connection connection = null;
	try {
	    connection = dbManager.getConnection();
	    PreparedStatement prst = connection.prepareStatement(SQL_SELECT_TEST_BY_NAME);
	    //TESTLOG.debug(prst);
	    prst.setString(1, testName);
	    ResultSet rs = prst.executeQuery();
	    while (rs.next()) {
		test = extractTest(rs);
	    }
	    rs.close();
	} catch (SQLException e) {
	    dbManager.rollBack(connection);
	} finally {
	    dbManager.commitAndClose(connection);
	}
	return test;
    }

    /**
     * This method is for getting amount of questions in the test
     * 
     * @param test
     *            id
     * @return amount of question
     */

    @Override
    public int getTestByAmountOfQuestions(int testId) throws SQLException {
	Connection connection = null;
	int amount = 0;
	try {
	    connection = dbManager.getConnection();
	    PreparedStatement prst = connection.prepareStatement(SQL_SELECT_TEST_BY_AMOUNT_OF_QUESTION);
	    prst.setInt(1, testId);
	    TESTLOG.debug(prst);
	    ResultSet rs = prst.executeQuery();
	    while (rs.next()) {
		amount++;
	    }
	    rs.close();
	} catch (SQLException e) {
	    dbManager.rollBack(connection);
	} finally {
	    dbManager.commitAndClose(connection);
	}
	return amount;
    }

    /**
     * This method is for getting by complexity
     * 
     * @param test
     *            id
     * @return test complexity
     */
    @Override
    public int getTestByComplixity(int testId) {
	Connection connection = null;
	int complexity = 0;
	try {
	    connection = dbManager.getConnection();
	    PreparedStatement prst = connection.prepareStatement(SQL_GET_TEST_BY_COMPLEXITY);
	    // LOG
	    prst.setInt(1, testId);
	    ResultSet rs = prst.executeQuery();
	    while (rs.next()) {
		complexity += extractComplexity(rs);
	    }

	} catch (SQLException e) {
	    dbManager.rollBack(connection);
	} finally {
	    dbManager.commitAndClose(connection);
	}
	return complexity;
    }

    /**
     * Test for getting all test from database
     * 
     * @return collection of tests
     */
    @Override
    public List<Test> getAllTest() {
	List<Test> testList = new ArrayList<>();
	Connection connection = null;
	try {
	    connection = dbManager.getConnection();
	    PreparedStatement prst = connection.prepareStatement(SQL_SELECT_ALLTESTS);
	    //TESTLOG.debug(prst);
	    ResultSet rs = prst.executeQuery();
	    while (rs.next()) {
		Test t = extractTest(rs);
		testList.add(t);
	    }
	    rs.close();
	} catch (SQLException e) {
	    dbManager.rollBack(connection);
	} finally {
	    dbManager.commitAndClose(connection);
	}
	return testList;
    }

    /**
     * This method is for updating test
     * 
     * @param testId
     *            Test id
     * @param test
     *            new test with updates
     */
    @Override
    public void updateTest(int testId, Test test) {
	Connection connection = null;
	int index = 1;
	try {
	    connection = dbManager.getConnection();
	    PreparedStatement prst = connection.prepareStatement(SQL_UPDATE_TEST);
	    prst.setString(index++, test.getSubjectName());
	    prst.setString(index++, test.getTestName());
	    prst.setTime(index++, test.getTimeToPass());
	    prst.setInt(index++, testId);
	    prst.executeUpdate();

	} catch (SQLException e) {
	    dbManager.rollBack(connection);
	} finally {
	    dbManager.commitAndClose(connection);
	}
    }

    /**
     * This method is for creating new test
     * 
     * @param new
     *            test
     * @return id of new test in database
     */
    @Override
    public int createTest(Test newTest) {
	Connection connection = null;
	int genetedId = 0;
	try {
	    connection = dbManager.getConnection();
	    PreparedStatement prst = connection.prepareStatement(SQL_CREATE_TEST,
		    PreparedStatement.RETURN_GENERATED_KEYS);
	    int index = 1;
	    prst.setString(index++, newTest.getSubjectName());
	    prst.setString(index++, newTest.getTestName());
	    prst.setTime(index++, newTest.getTimeToPass());
	    // LOG
	    prst.executeUpdate();
	    ResultSet rs = prst.getGeneratedKeys();
	    if (rs.next()) {
		genetedId = rs.getInt(1);
	    }
	    rs.close();
	} catch (SQLException e) {
	    dbManager.rollBack(connection);
	} finally {
	    dbManager.commitAndClose(connection);
	}
	return genetedId;
    }

    // метод для проработки
    @Override
    public Test getFullTest(int id) {
	/*
	 * Connection conn = null; Question quest = null; Answer answ = null;
	 * try{ conn = dbManager.getConnection(); PreparedStatement prst =
	 * conn.prepareStatement(SQL_SELECT_ALLTESTS); prst.setInt(1, id);
	 * ResultSet rs = prst.executeQuery(); wh }catch(SQLException e){
	 * dbManager.rollBack(conn); }finally { dbManager.commitAndClose(conn);
	 * }
	 */

	return null;
    }

    @Override
    public void removeTestById(int id) {
	Connection connection = null;
	try {
	    connection = dbManager.getConnection();
	    PreparedStatement prst = connection.prepareStatement(SQL_DELETE_TEST_BY_ID);
	    prst.setInt(1, id);
	    //LOG.debug(prst);
	    prst.executeUpdate();
	} catch (SQLException e) {
	    dbManager.rollBack(connection);
	} finally {
	    dbManager.commitAndClose(connection);
	}
    }
}
