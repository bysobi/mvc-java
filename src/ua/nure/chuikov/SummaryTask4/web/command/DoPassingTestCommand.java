package ua.nure.chuikov.SummaryTask4.web.command;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import ua.nure.chuikov.SummaryTask4.dao.mysql.QuestionDaoMySql;
import ua.nure.chuikov.SummaryTask4.entity.Answer;
import ua.nure.chuikov.SummaryTask4.entity.Question;
import ua.nure.chuikov.SummaryTask4.web.path.Path;

public class DoPassingTestCommand extends AbstractCommand {
    private QuestionDaoMySql questionDao;

    public DoPassingTestCommand(QuestionDaoMySql questionDao) {
	this.questionDao = questionDao;
    }

    static List<Question> questionsList = new ArrayList<>();
    static List<Answer> answersList = new ArrayList<>();

    public static List<Question> getQuestionList(List<Question> question) {
	questionsList = question;
	return questionsList;
    }

    public static List<Answer> getAnswerList(List<Answer> ans) {
	answersList = ans;
	return answersList;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
	    throws UnsupportedEncodingException {


	List<Question> questionListInTest = new ArrayList<>();

	for (Question q : DoPassingTestCommand.questionsList) {
	    questionListInTest.add(q);

	}
	for (Answer answer : DoPassingTestCommand.answersList) {
	    for (Question quest : questionListInTest) {
		if (answer.getQustionId() == quest.getId()) {
		    
		    System.out.println(answer.getAnswerNumber() + " " + answer.getTextOfAnswer()+ " "+ answer.getFlag());
		}
	    }
	}
	
	//String paramFlag = request.getParameter("answerNumber");
	/*System.out.println(paramFlag);
	*/
	/*
	 * request.setAttribute("questionsList", questionsList);
	 * 
	 * try { response.sendRedirect(Path.COMMAND_QUESTIONS_LISTS); } catch
	 * (IOException e) { e.printStackTrace(); }
	 */

	return Path.PAGE_USER_INFO;
    }

}
