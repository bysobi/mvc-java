package ua.nure.chuikov.SummaryTask4.web.command;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.chuikov.SummaryTask4.dao.mysql.UserResultDao;
import ua.nure.chuikov.SummaryTask4.entity.UserTestResult;
import ua.nure.chuikov.SummaryTask4.web.path.Path;

public class ListResultCommand extends AbstractCommand {
    private UserResultDao resultDao;

    public ListResultCommand(UserResultDao resultDao) {
	this.resultDao = resultDao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
	    throws UnsupportedEncodingException {
	List<UserTestResult> testResult = resultDao.getAll();
	
	request.setAttribute("tests", testResult);
	
	return Path.PAGE_RESULT_TESTS_LIST;
    }
}
