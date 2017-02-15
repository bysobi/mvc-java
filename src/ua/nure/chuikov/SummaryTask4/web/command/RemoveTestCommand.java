package ua.nure.chuikov.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.chuikov.SummaryTask4.dao.mysql.TestDaoMySql;
import ua.nure.chuikov.SummaryTask4.entity.Test;
import ua.nure.chuikov.SummaryTask4.web.path.Path;

public class RemoveTestCommand extends AbstractCommand {
    private TestDaoMySql testDao;

    public RemoveTestCommand(TestDaoMySql testDao) {
	this.testDao = testDao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

	String paramTestId = request.getParameter("id");
	int testId = 0;

	if (paramTestId != null && !paramTestId.isEmpty()) {
	    try {
		testId = Integer.parseInt(paramTestId);
	    } catch (NumberFormatException e) {
	    }
	}

	Test test = testDao.getTestById(testId);

	if (test == null) {
	    request.setAttribute("errorMessage", "Тест не найден");
	    return Path.PAGE_ERROR;
	}
	testDao.removeTestById(testId);
	// all ok,trying to remove теsт
	/*
	 * if (!DaoContainer.BUS_DAO.removeBus(busId)) {
	 * request.setAttribute("errorMessage",
	 * "ошибка, не удалось удалить автобус"); return Path.PAGE_ERROR; }
	 */
	try {
	    response.sendRedirect(Path.COMMAND_LIST_TESTS);
	} catch (IOException e) {
	    e.printStackTrace();
	}

	return null;
    }

}
