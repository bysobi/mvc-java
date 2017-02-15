package ua.nure.chuikov.SummaryTask4.web.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import ua.nure.chuikov.SummaryTask4.dao.mysql.UserDaoMysql;

import ua.nure.chuikov.SummaryTask4.entity.User;
import ua.nure.chuikov.SummaryTask4.web.path.Path;

public class SetLocaleCommand extends AbstractCommand {
    private UserDaoMysql userDao;

    public SetLocaleCommand(UserDaoMysql userDao) {
	this.userDao = userDao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
	String paramLocale = request.getParameter("locale");
	// if parameter locale is set, applying locale
	if (paramLocale != null && !paramLocale.isEmpty()) {
	    List<String> locales = (List<String>) request.getServletContext().getAttribute("locales");
	    if (locales.contains(paramLocale)) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("aut_user");
		if (user != null) {
		    user.setLocale(paramLocale);
		    try {
			userDao.updateUserById(user.getId(), user);
		    } catch (SQLException e) {
			e.printStackTrace();
		    }

		}
		// session.setAttribute("javax.servlet.jsp.jstl.fmt.locale",
		// paramLocale);
		Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", paramLocale);
	    }
	}
	try {
	    response.sendRedirect(Path.PAGE_WELCOME);
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return null;
    }

}
