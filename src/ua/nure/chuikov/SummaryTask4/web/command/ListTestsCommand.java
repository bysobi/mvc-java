package ua.nure.chuikov.SummaryTask4.web.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.chuikov.SummaryTask4.dao.mysql.TestDaoMySql;
import ua.nure.chuikov.SummaryTask4.entity.Test;
import ua.nure.chuikov.SummaryTask4.web.path.Path;

public class ListTestsCommand extends AbstractCommand {
    private TestDaoMySql testDao;

    public ListTestsCommand(TestDaoMySql testDao) {
	this.testDao = testDao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
	List<Test> tests = testDao.getAllTest();
	//getting complexity of tests
	for(Test test : tests){
	    test.setComplexity(testDao.getTestByComplixity(test.getId()));
	}
	request.setAttribute("tests", tests);
	return Path.PAGE_TESTS_LIST;
    }

}
