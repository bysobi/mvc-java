package ua.nure.chuikov.SummaryTask4.web.command;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.chuikov.SummaryTask4.dao.mysql.UserDaoMysql;
import ua.nure.chuikov.SummaryTask4.entity.User;
import ua.nure.chuikov.SummaryTask4.web.path.Path;

public class DoLoginCommand extends AbstractCommand {
    private UserDaoMysql userDao;

    public DoLoginCommand(UserDaoMysql userDao) {
	this.userDao = userDao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
	    throws UnsupportedEncodingException {
	// getting values from form
	String login = request.getParameter("login");
	String password = request.getParameter("password");
	// Constant for jsp
	String forward = Path.PAGE_LOGIN;
	if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
	    request.setAttribute("errorMessage", "'Логин и пароль не могут быть пустыми");
	    return forward;
	}
	// search in database
	User user = userDao.getUserByLogin(login);
	if (user == null || !user.getUserPassword().equals(password)) {
	    request.setAttribute("errorMessage", "Вы забыли пароль! Попробуйте снова.");
	    return forward;
	}

	HttpSession session = request.getSession();
	session.setAttribute("aut_user", user);

	return Path.PAGE_WELCOME;
    }

}
