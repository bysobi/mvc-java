package ua.nure.chuikov.SummaryTask4.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.nure.chuikov.SummaryTask4.dao.QuestionDao;
import ua.nure.chuikov.SummaryTask4.db.DBManager;
import ua.nure.chuikov.SummaryTask4.entity.Question;

public class QuestionDaoMySql implements QuestionDao {

    // //////////////////////////////////////////////////////////
    // SQL queries
    // //////////////////////////////////////////////////////////
    private static final String SQL_SELECT_ALL_QUESTIONS_BY_TEST_ID = "SELECT * FROM `question` where `testid` = ? ";
    private static final String SQL_ADD_QUESTION = "INSERT INTO `question`(`textOfQuestion`, `complexity`, `testId`) VALUES (?,?,?)";
    private static final String SQL_UPDATE_QUESTION = "UPDATE `question` SET `textOfQuestion`=?,`complexity`=?, `testId` =? WHERE `id`=?";
    private static final String SQL_REMOVE_QUESTION = "DELETE FROM `question` WHERE `id` = ?";
    private static final String SQL_GET_QUESTION_BY_TEXT = "SELECT * FROM `question` where `textOfQuestion` = ? ";

    private DBManager dbManager;

    public QuestionDaoMySql(DBManager dbManager) {
	this.dbManager = dbManager;
    }

    private static Question extractQuestion(ResultSet rs) throws SQLException {
	Question question = new Question();
	question.setId(rs.getInt("id"));
	question.setTextOfQuestion(rs.getString("textOfQuestion"));
	question.setComplixity(rs.getInt("complexity"));
	question.setTestId(rs.getInt("testId"));
	return question;
    }

    @Override
    public int addQuestion(Question question) {
	Connection connection = null;
	int generatedId = 0;
	try {
	    connection = dbManager.getConnection();
	    PreparedStatement prst = connection.prepareStatement(SQL_ADD_QUESTION,
		    PreparedStatement.RETURN_GENERATED_KEYS);
	    prst.setString(1, question.getTextOfQuestion());
	    prst.setInt(2, question.getComplixity());
	    prst.setInt(3, question.getTestId());
	    // LOG.debug(prst);
	    prst.executeUpdate();
	    ResultSet rs = prst.getGeneratedKeys();
	    if (rs.next()) {
		generatedId = rs.getInt(1);
	    }
	    rs.close();
	} catch (SQLException e) {
	    dbManager.rollBack(connection);
	} finally {
	    dbManager.commitAndClose(connection);
	}
	return generatedId;
    }

    @Override
    public void updateQuestion(int questionId, Question question) {
	Connection connection = null;
	try {
	    connection = dbManager.getConnection();
	    int index = 1;
	    PreparedStatement prst = connection.prepareStatement(SQL_UPDATE_QUESTION);
	    prst.setString(index++, question.getTextOfQuestion());
	    prst.setInt(index++, question.getComplixity());
	    prst.setInt(index++, question.getTestId());
	    prst.setInt(index++, questionId);
	    // LOG
	    prst.executeUpdate();
	} catch (SQLException e) {
	    dbManager.rollBack(connection);
	} finally {
	    dbManager.commitAndClose(connection);
	}
    }

    @Override // Executed
    public List<Question> getAllQuestionByTestId(int testId) {
	Connection connection = null;
	List<Question> listQuestions = new ArrayList<>();
	Question question = null;
	try {
	    connection = dbManager.getConnection();
	    PreparedStatement prst = connection.prepareStatement(SQL_SELECT_ALL_QUESTIONS_BY_TEST_ID);
	    prst.setInt(1, testId);
	    // add log
	    ResultSet rs = prst.executeQuery();
	    while (rs.next()) {
		question = extractQuestion(rs);
		listQuestions.add(question);
	    }
	    rs.close();
	} catch (SQLException e) {
	    dbManager.rollBack(connection);
	} finally {
	    dbManager.commitAndClose(connection);
	}
	return listQuestions;
    }

    @Override
    public void removeQuestionById(int questionId) {
	Connection connection = null;
	try {
	    connection = dbManager.getConnection();
	    PreparedStatement prst = connection.prepareStatement(SQL_REMOVE_QUESTION);
	    prst.setInt(1, questionId);
	    // LOG
	    prst.executeUpdate();
	} catch (SQLException e) {
	    dbManager.rollBack(connection);
	} finally {
	    dbManager.commitAndClose(connection);
	}
    }
    //delete
    @Override
    public Question getQuestionByText(String textOfQuestion) {
	Connection connection = null;
	try {
	    connection = dbManager.getConnection();
	    PreparedStatement prst = connection.prepareStatement(SQL_GET_QUESTION_BY_TEXT);
	    prst.setString(1, textOfQuestion);
	    prst.executeQuery();
	} catch (SQLException exc) {
	    dbManager.rollBack(connection);
	} finally {
	    dbManager.commitAndClose(connection);
	}
	return null;
    }

}
