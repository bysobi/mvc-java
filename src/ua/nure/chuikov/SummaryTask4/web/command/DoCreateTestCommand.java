package ua.nure.chuikov.SummaryTask4.web.command;

import java.io.IOException;
import java.sql.Time;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.chuikov.SummaryTask4.dao.mysql.TestDaoMySql;
import ua.nure.chuikov.SummaryTask4.entity.Test;
import ua.nure.chuikov.SummaryTask4.web.path.Path;

public class DoCreateTestCommand extends AbstractCommand {
    private TestDaoMySql testDao;

    public DoCreateTestCommand(TestDaoMySql testDao) {
	this.testDao = testDao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

	String paramTestName = request.getParameter("testName");
	String paramSubjectName = request.getParameter("subjectName");
	String paramTimeToPass = request.getParameter("timeToPass");
	String paramTestComplexity = request.getParameter("complexity");

	boolean wasError = false;
	Time time = null;
	String errorTestName = null, errorSubjectName = null, errorTimeToPass = null;

	// checking params
	if (paramTestName == null || paramTestName.isEmpty() || paramTestName.length() > 100) {
	    wasError = true;
	    errorTestName = "Поле должно содержать до 100 символов";
	}

	if (paramSubjectName == null || paramSubjectName.isEmpty() || paramSubjectName.length() > 50) {
	    wasError = true;
	    errorSubjectName = "Поле должно содержать 8 символов";
	}
	if (paramTimeToPass == null || paramTimeToPass.isEmpty() || paramTimeToPass.length() < 8) {
	    wasError = true;
	    errorTimeToPass = "Поле должно содержать до 50 символов! Введите время в формате 00:00:00";

	}
	if (paramTimeToPass != null && !paramTimeToPass.isEmpty()) {
	    try {
		time = Time.valueOf(paramTimeToPass);
	    } catch (Exception ex) {
		wasError = true;
		errorTimeToPass = "Неверный формат! Введите время в формате 00:00:00";
	    }

	}

	if (wasError) {
	    request.setAttribute("testName", paramTestName);
	    request.setAttribute("errorTestName", errorTestName);
	    request.setAttribute("subjectName", paramSubjectName);
	    request.setAttribute("errorSubjectName", errorSubjectName);
	    request.setAttribute("timaToPass", paramTimeToPass);
	    request.setAttribute("errorTimeToPass", errorTimeToPass);
	    return Path.PAGE_NEW_TEST;
	}

	// all ok, saving new test
	Test test = new Test();
	test.setTestName(paramTestName);
	test.setSubjectName(paramSubjectName);
	test.setTimeToPass(time);

	testDao.createTest(test);

	try {
	    response.sendRedirect(Path.COMMAND_LIST_TESTS);
	} catch (IOException e) {
	    e.printStackTrace();
	}

	return null;
    }

}
