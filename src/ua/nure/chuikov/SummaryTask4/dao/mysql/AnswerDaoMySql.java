package ua.nure.chuikov.SummaryTask4.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.chuikov.SummaryTask4.dao.AnswerDao;
import ua.nure.chuikov.SummaryTask4.db.DBManager;
import ua.nure.chuikov.SummaryTask4.entity.Answer;
import ua.nure.chuikov.SummaryTask4.entity.Test;

public class AnswerDaoMySql implements AnswerDao {

    private static final Logger LOG = Logger.getLogger(AnswerDaoMySql.class);

    // //////////////////////////////////////////////////////////
    // SQL queries
    // //////////////////////////////////////////////////////////
    private static final String SQL_GET_ANSWERS_BY_QUESTION_ID = "SELECT `id`,`questionId`,`answerNumber`,"
	    + "`textOfAnswer`,`flag` FROM `answer` WHERE `questionId` = ?";
    private static final String SQL_UPDATE_ANSWER_BY_QUESTION_ID = "UPDATE `answer` SET `answerNumber`=?,"
	    + "`textOfAnswer`=?,`flag`=? WHERE `questionId` = ? AND `answerNumber`=?";
    private static final String SQL_ADD_ANSWER_BY_QUESTION_ID = "INSERT INTO `answer`( `questionId`, `answerNumber`,"
	    + "`textOfAnswer`, `flag`) VALUES (?,?,?,?) ";
    private static final String SQL_SELECT_ALL_ANSWERS = "SELECT * FROM `answer`";
    private static final String SQL_SELECT_ALL_ANSWERS_BY_QUESTION_ID = "SELECT * FROM `answer` WHERE `questionId` =? ";

    private DBManager dbmanager;

    public AnswerDaoMySql(DBManager dbManager) {
	this.dbmanager = dbManager;
    }

    private Answer extractAnswer(ResultSet rs) throws SQLException {
	Answer answer = new Answer();
	answer.setId(rs.getInt("id"));
	answer.setQustionId(rs.getInt("questionId"));
	answer.setAnswerNumber(rs.getInt("answerNumber"));
	answer.setTextOfAnswer(rs.getString("textOfAnswer"));
	answer.setFlag(rs.getBoolean("flag"));
	return answer;
    }
/**
 * Getting answers by question id
 * @param int testID
 * @return List of answers
 * */
    @Override
    public List<Answer> getAnswerByQuestionId(int testId) {
	Connection conn = null;
	List<Answer> listAnswer = new ArrayList<>();
	try {
	    conn = dbmanager.getConnection();
	    PreparedStatement prst = conn.prepareStatement(SQL_GET_ANSWERS_BY_QUESTION_ID);
	    prst.setInt(1, testId);
	   // LOG.debug(prst);
	    ResultSet rs = prst.executeQuery();
	    while (rs.next()) {
		Answer a = extractAnswer(rs);
		listAnswer.add(a);
	    }
	    rs.close();
	} catch (SQLException e) {
	    dbmanager.rollBack(conn);
	} finally {
	    dbmanager.commitAndClose(conn);
	}

	return listAnswer;
    }

    /**
     * update answers
     * @param int questionid, int answerNumber, Answer answer
     * */ 
    @Override
    public void updateAnswer(int questionid, int answerNumber, Answer answer) {
	Connection conn = null;
	try {
	    conn = dbmanager.getConnection();
	    PreparedStatement prst = conn.prepareStatement(SQL_UPDATE_ANSWER_BY_QUESTION_ID);
	    int index = 1;
	    prst.setInt(index++, answer.getAnswerNumber());
	    prst.setString(index++, answer.getTextOfAnswer());
	    prst.setBoolean(index++, answer.getFlag());
	    prst.setInt(index++, questionid);
	    prst.setInt(index++, answerNumber);
	    //LOG.debug(prst);
	    prst.executeUpdate();

	} catch (SQLException e) {
	    dbmanager.rollBack(conn);
	} finally {
	    dbmanager.commitAndClose(conn);
	}
    }
/**
 * adding answers to question by questions id
 * @param Answer answer
 * */
    @Override
    public void addAnswerForQuestuinWithID(Answer answer) {
	Connection conn = null;
	int generatedId = 0;// в коде работает, значение устанавливается
	try {
	    conn = dbmanager.getConnection();
	    PreparedStatement prst = conn.prepareStatement(SQL_ADD_ANSWER_BY_QUESTION_ID,
		    PreparedStatement.RETURN_GENERATED_KEYS);
	    int index = 1;
	    prst.setInt(index++, answer.getQustionId());
	    prst.setInt(index++, answer.getAnswerNumber());
	    prst.setString(index++, answer.getTextOfAnswer());
	    prst.setBoolean(index++, answer.getFlag());
	    //LOG.debug(prst);
	    prst.executeUpdate();
	    ResultSet rs = prst.getGeneratedKeys();
	    if (rs.next()) {
		generatedId = rs.getInt(1);
	    }
	    rs.close();

	} catch (SQLException e) {
	    dbmanager.rollBack(conn);
	} finally {
	    dbmanager.commitAndClose(conn);
	}
    }
    /**
     * Method for getting all answers from DB 
     * */
    @Override
    public List<Answer> getAllAnswers() {
	List<Answer> answersList = new ArrayList<>();
	Connection connection = null;
	try {
	    connection = dbmanager.getConnection();
	    PreparedStatement prst = connection.prepareStatement(SQL_SELECT_ALL_ANSWERS);
	    // TESTLOG.debug(prst);
	    ResultSet rs = prst.executeQuery();
	    while (rs.next()) {
		Answer answer = extractAnswer(rs);
		answersList.add(answer);
	    }
	    rs.close();
	} catch (SQLException e) {
	    dbmanager.rollBack(connection);
	} finally {
	    dbmanager.commitAndClose(connection);
	}
	return answersList;
    }

    @Override
    public List<Answer> getAllAnswersByQuestionid(int i) {
	List<Answer> answersList = new ArrayList<>();
	Connection connection = null;
	try {
	    connection = dbmanager.getConnection();
	    PreparedStatement prst = connection.prepareStatement(SQL_SELECT_ALL_ANSWERS_BY_QUESTION_ID);
	    // TESTLOG.debug(prst);
	    prst.setInt(1, i);
	    ResultSet rs = prst.executeQuery();
	    while (rs.next()) {
		Answer answer = extractAnswer(rs);
		answersList.add(answer);
	    }
	    rs.close();
	} catch (SQLException e) {
	    dbmanager.rollBack(connection);
	} finally {
	    dbmanager.commitAndClose(connection);
	}
	return answersList;
    }

}
