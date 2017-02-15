package code.university.web.command;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import code.university.dao.mysql.UserDaoMysql;
import code.university.entity.User;
import code.university.web.path.Path;

public class DoLoginCommand extends AbstractCommand {
    private UserDaoMysql userDao;

    public DoLoginCommand(UserDaoMysql userDao) {
	this.userDao = userDao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
	    throws UnsupportedEncodingException {
	// getting values from form
	String email = request.getParameter("email");
	String password = request.getParameter("password");
	// Constant for jsp
	String forward = Path.PAGE_LOGIN;
	if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
	    request.setAttribute("errorMessage", "Invalid Auth Data");
	    return forward;
	}
	// search in database
	User user = userDao.getUserByLogin(email);
	if (user == null || !user.getPassword().equals(this.MD5(password))) {
	    request.setAttribute("errorMessage", "Invalid Auth Data");
	    return forward;
	}
	
	HttpSession session = request.getSession();
	session.setAttribute("aut_user", user);

	return Path.PAGE_WELCOME;
    }

}
