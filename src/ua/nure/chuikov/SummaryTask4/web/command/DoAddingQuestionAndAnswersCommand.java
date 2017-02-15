package ua.nure.chuikov.SummaryTask4.web.command;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.chuikov.SummaryTask4.dao.mysql.AnswerDaoMySql;
import ua.nure.chuikov.SummaryTask4.dao.mysql.QuestionDaoMySql;
import ua.nure.chuikov.SummaryTask4.dao.mysql.TestDaoMySql;
import ua.nure.chuikov.SummaryTask4.db.DBManager;
import ua.nure.chuikov.SummaryTask4.entity.Answer;
import ua.nure.chuikov.SummaryTask4.entity.Question;
import ua.nure.chuikov.SummaryTask4.entity.Test;
import ua.nure.chuikov.SummaryTask4.web.path.Path;

public class DoAddingQuestionAndAnswersCommand extends AbstractCommand {
    private AnswerDaoMySql answerDao = new AnswerDaoMySql(DBManager.getInstance());
    private QuestionDaoMySql questionDao = new QuestionDaoMySql(DBManager.getInstance());
    private TestDaoMySql testDao = new TestDaoMySql(DBManager.getInstance());

    public DoAddingQuestionAndAnswersCommand(AnswerDaoMySql answerDao) {
	this.answerDao = answerDao;
    }

    public DoAddingQuestionAndAnswersCommand(QuestionDaoMySql questionDao) {
	this.questionDao = questionDao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
	    throws UnsupportedEncodingException {
	String paramTextOfQuestion = request.getParameter("textOfQuestion");
	String paramTextOfAnswerNumber1 = request.getParameter("textOfAnswerNumber1");
	String paramTextOfAnswerNumber2 = request.getParameter("textOfAnswerNumber2");
	String paramTextOfAnswerNumber3 = request.getParameter("textOfAnswerNumber3");
	String paramTextOfAnswerNumber4 = request.getParameter("textOfAnswerNumber4");
	// String paramTestName = (String) request.getAttribute("testName");
	String paramFlag1 = request.getParameter("flag1");
	String paramFlag2 = request.getParameter("flag2");
	String paramFlag3 = request.getParameter("flag3");
	String paramFlag4 = request.getParameter("flag4");

	String paramTestName = request.getParameter("testId");

	int testId = 0;
	Test test = new Test();
	try {
	    test = testDao.getTestByName(paramTestName);
	} catch (SQLException e) {
	    e.printStackTrace();
	}

	boolean ansFlag1 = false, ansFlag2 = false, ansFlag3 = false, ansFlag4 = false;
	boolean wasError = false;
	String errorTextOfQuestion = null, errorTextOfAnswer1 = null, errorTextOfAnswer2 = null,
		errorTextOfAnswer3 = null, errorTextOfAnswer4 = null, errorSelectingTest = null, errorFlag = null;

	try {
	    testId = test.getId();
	} catch (NullPointerException e) {
	    wasError = true;
	    errorSelectingTest = "Пожалуйста выберите тест";
	}
	if (paramTextOfQuestion == null || paramTextOfQuestion.isEmpty() || paramTextOfQuestion.length() > 300) {
	    wasError = true;
	    errorTextOfQuestion = "Поле должно содержать до 300 символов";
	}
	if (paramTextOfAnswerNumber1 == null || paramTextOfAnswerNumber1.isEmpty()
		|| paramTextOfAnswerNumber1.length() > 50) {
	    wasError = true;
	    errorTextOfAnswer1 = "Поле должно содержать до 50 символов";
	}
	if (paramTextOfAnswerNumber2 == null || paramTextOfAnswerNumber2.isEmpty()
		|| paramTextOfAnswerNumber2.length() > 50) {
	    wasError = true;
	    errorTextOfAnswer2 = "Поле должно содержать до 50 символов";
	}
	if (paramTextOfAnswerNumber3 == null || paramTextOfAnswerNumber3.isEmpty()
		|| paramTextOfAnswerNumber3.length() > 50) {
	    wasError = true;
	    errorTextOfAnswer3 = "Поле должно содержать до 50 символов";
	}
	if (paramTextOfAnswerNumber4 == null || paramTextOfAnswerNumber4.isEmpty()
		|| paramTextOfAnswerNumber4.length() > 50) {
	    wasError = true;
	    errorTextOfAnswer4 = "Поле должно содержать до 50 символов";
	}
	if (paramFlag1 != null && !paramFlag1.isEmpty()) {
	    ansFlag1 = true;
	}
	if (paramFlag2 != null && !paramFlag2.isEmpty()) {
	    ansFlag2 = true;
	}
	if (paramFlag3 != null && !paramFlag3.isEmpty()) {
	    ansFlag3 = true;
	}
	if (paramFlag4 != null && !paramFlag4.isEmpty()) {
	    ansFlag4 = true;
	}
	if (paramFlag1 == null && paramFlag2 == null && paramFlag3 == null && paramFlag4 == null) {
	    wasError = true;
	    errorFlag = "Необходимо установить минимум 1 правильный ответ";
	}
	List<Test> tests = testDao.getAllTest();
	
	if (wasError) {
	    request.setAttribute("paramTextOfQuestion", paramTextOfQuestion);
	    request.setAttribute("errorTextOfQuestion", errorTextOfQuestion);
	    request.setAttribute("paramTextOfAnswerNumber1", paramTextOfAnswerNumber1);
	    request.setAttribute("errorTextOfAnswer1", errorTextOfAnswer1);
	    request.setAttribute("paramTextOfAnswerNumber2", paramTextOfAnswerNumber2);
	    request.setAttribute("errorTextOfAnswer2", errorTextOfAnswer2);
	    request.setAttribute("paramTextOfAnswerNumber3", paramTextOfAnswerNumber3);
	    request.setAttribute("errorTextOfAnswer3", errorTextOfAnswer3);
	    request.setAttribute("paramTextOfAnswerNumber4", paramTextOfAnswerNumber4);
	    request.setAttribute("errorTextOfAnswer4", errorTextOfAnswer4);
	    request.setAttribute("paramFlag1", ansFlag1);
	    request.setAttribute("paramFlag2", ansFlag2);
	    request.setAttribute("paramFlag3", ansFlag3);
	    request.setAttribute("paramFlag4", ansFlag4);
	    request.setAttribute("errorFlag", errorFlag);
	    request.setAttribute("errorSelectingTest",errorSelectingTest );
	    request.setAttribute("tests", tests);

	    return Path.PAGE_ADDING_QUESTOIN_AND_ANSWERS;
	}
	Question question = new Question();
	question.setTextOfQuestion(paramTextOfQuestion);
	question.setTestId(testId);

	int questionId = questionDao.addQuestion(question);
	try {

	    // !!!!!!!!!!!!!
	    answerDao.addAnswerForQuestuinWithID(new Answer(questionId, 1, paramTextOfAnswerNumber1, ansFlag1));
	    answerDao.addAnswerForQuestuinWithID(new Answer(questionId, 2, paramTextOfAnswerNumber2, ansFlag2));
	    answerDao.addAnswerForQuestuinWithID(new Answer(questionId, 3, paramTextOfAnswerNumber3, ansFlag3));
	    answerDao.addAnswerForQuestuinWithID(new Answer(questionId, 4, paramTextOfAnswerNumber4, ansFlag4));

	} catch (Exception e1) {
	    request.setAttribute("errorMessage", "ОШИБКА!!! Oтветы не сохранени");
	    return Path.PAGE_ERROR;
	}
	try {
	    response.sendRedirect(Path.COMMAND_LIST_TESTS);
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return null;
    }

}
