package ua.nure.chuikov.SummaryTask4.web.command;

import java.io.IOException;
import java.sql.Time;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.chuikov.SummaryTask4.dao.mysql.TestDaoMySql;
import ua.nure.chuikov.SummaryTask4.entity.Test;
import ua.nure.chuikov.SummaryTask4.web.path.Path;

public class DoEditTestCommand extends AbstractCommand {
    private TestDaoMySql testDao;

    public DoEditTestCommand(TestDaoMySql testDao) {
	this.testDao = testDao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

	String paramTestName = request.getParameter("testName");
	String paramSubjectName = request.getParameter("subjectName");
	String paramTimeToPass = request.getParameter("timeToPass");
	String paramTestId = request.getParameter("id");
	String paramTestComplexity = request.getParameter("complexity");

	boolean wasError = false;
	Time time = null;
	String errorTestName = null, errorSubjectName = null, errorTimeToPass = null;

	// check whether the test exists
	int id = 0;
	if (paramTestId != null && !paramTestId.isEmpty()) {
	    try {
		id = Integer.parseInt(paramTestId);
	    } catch (NumberFormatException e) {
	    }
	}

	// checking params
	if (paramTestName == null || paramTestName.isEmpty() || paramTestName.length() > 100) {
	    wasError = true;
	    errorTestName = "Поле должно содержать до 100 символов";
	}

	if (paramSubjectName == null || paramSubjectName.isEmpty() || paramSubjectName.length() > 50) {
	    wasError = true;
	    errorSubjectName = "Поле должно содержать до 50 символов";
	}
	if (paramTimeToPass != null && !paramTimeToPass.isEmpty()) {
	    try {
		time = Time.valueOf(paramTimeToPass);
	    } catch (Exception ex) {
		wasError = true;
		errorTimeToPass = "Неверный формат! Введите время в формате 00:00:00";
	    }

	} else {
	    wasError = true;
	    errorTimeToPass = "Поле должно быть задано";
	}

	Test test = testDao.getTestById(id);
	if (test == null) {
	    request.setAttribute("errorMessage", "Тест не найден");
	    return Path.PAGE_ERROR;
	}

	if (wasError) {
	    request.setAttribute("testName", paramTestName);
	    request.setAttribute("errorTestName", errorTestName);
	    request.setAttribute("paramSubjectName", paramSubjectName);
	    request.setAttribute("errorSubjectName", errorSubjectName);
	    request.setAttribute("paramTimeToPass", paramTimeToPass);
	    request.setAttribute("errorTimeToPass", errorTimeToPass);

	    request.setAttribute("id", id);
	    return Path.PAGE_EDIT_TEST;
	}

	// all ok, saving test
	test.setId(id);
	test.setSubjectName(paramSubjectName);
	test.setTestName(paramTestName);
	test.setTimeToPass(time);

	testDao.updateTest(id, test);

	try {
	    response.sendRedirect(Path.COMMAND_LIST_TESTS);
	} catch (IOException e) {
	    e.printStackTrace();
	}

	return null;
    }

}
