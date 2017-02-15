package ua.nure.chuikov.SummaryTask4.web.command;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.chuikov.SummaryTask4.dao.mysql.TestDaoMySql;
import ua.nure.chuikov.SummaryTask4.entity.Test;
import ua.nure.chuikov.SummaryTask4.web.path.Path;

public class AddingQuestionAndAnswersCommand extends AbstractCommand {
    private TestDaoMySql testDao;

    public AddingQuestionAndAnswersCommand(TestDaoMySql testDao) {
	this.testDao = testDao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
	    throws UnsupportedEncodingException {
	List<Test> tests = testDao.getAllTest();
	request.setAttribute("tests", tests);

	return Path.PAGE_ADDING_QUESTOIN_AND_ANSWERS;
    }

}
