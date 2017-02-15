package ua.nure.chuikov.SummaryTask4.web.command;

import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
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

public class PassingTestCommand extends AbstractCommand {
    private QuestionDaoMySql questionDao = new QuestionDaoMySql(DBManager.getInstance());
    private AnswerDaoMySql answerDao = new AnswerDaoMySql(DBManager.getInstance());
    private TestDaoMySql testDao = new TestDaoMySql(DBManager.getInstance());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
	    throws UnsupportedEncodingException {

	String paramId = request.getParameter("id");
	int testId = 0;

	if (paramId != null && !paramId.isEmpty()) {
	    try {
		testId = Integer.parseInt(paramId);
	    } catch (NumberFormatException e) {
	    }
	}
	Test test = testDao.getTestById(testId);
	if (test == null) {
	    request.setAttribute("errorMessage", " Тест не найден");
	    return Path.PAGE_ERROR;
	}

	List<Question> questionsList = new ArrayList<>();
	questionsList = questionDao.getAllQuestionByTestId(testId);

	List<Answer> answerForQuestion = null;
	List<Answer> listAnswers = new ArrayList<>();
	listAnswers = answerDao.getAllAnswers();
	for (Question question : questionsList) {

	    request.setAttribute("qustion", question.getTextOfQuestion());
	    for (Answer answer : listAnswers) {
		if (answer.getQustionId() == question.getId()) {
		    answerForQuestion = answerDao.getAnswerByQuestionId(answer.getQustionId());
		    break;
		}
	    }

	    /*
	     * listAnswersByQuestionId = answerDao.getAllAnswersByQuestionid();
	     * DoPassingTestCommand.getQuestionList(questionsList);
	     * DoPassingTestCommand.getAnswerList(listAnswers);
	     */
	    request.setAttribute("questionsList", questionsList);
	    request.setAttribute("listAnswers", listAnswers);
	    // request.setAttribute("answerNumber1", answerNumber1);
	}

	return Path.PAGE_PASSING_TEST;

    }
}
