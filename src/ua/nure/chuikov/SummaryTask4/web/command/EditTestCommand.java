package ua.nure.chuikov.SummaryTask4.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.chuikov.SummaryTask4.dao.mysql.TestDaoMySql;
import ua.nure.chuikov.SummaryTask4.entity.Test;
import ua.nure.chuikov.SummaryTask4.web.path.Path;

public class EditTestCommand extends AbstractCommand {
    private TestDaoMySql testDao;

    public EditTestCommand(TestDaoMySql testDao) {
	this.testDao = testDao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
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

	request.setAttribute("testName", test.getTestName());
	request.setAttribute("subjectName", test.getSubjectName());
	request.setAttribute("timeToPass", test.getTimeToPass());
	request.setAttribute("id", paramId);
	
	return Path.PAGE_EDIT_TEST;
    }

}
