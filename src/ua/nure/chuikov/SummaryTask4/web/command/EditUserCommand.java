package ua.nure.chuikov.SummaryTask4.web.command;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.chuikov.SummaryTask4.dao.mysql.UserDaoMysql;
import ua.nure.chuikov.SummaryTask4.entity.User;
import ua.nure.chuikov.SummaryTask4.web.path.Path;

public class EditUserCommand extends AbstractCommand {
    private UserDaoMysql userDao;

    public EditUserCommand(UserDaoMysql userDao) {
	this.userDao = userDao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
	    throws UnsupportedEncodingException {
	User user = null;

	String paramId = request.getParameter("id");
	int userId = 0;

	if (paramId != null && !paramId.isEmpty()) {
	    try {
		userId = Integer.parseInt(paramId);
	    } catch (NumberFormatException e) {
	    }
	}
	try {
	    user = userDao.getUserById(userId);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	request.setAttribute("lastName", user.getLastName());
	request.setAttribute("secondName", user.getSecondName());
	request.setAttribute("firstName", user.getFirstName());
	request.setAttribute("userLogin", user.getUserLogin());
	request.setAttribute("userPassword", user.getUserPassword());
	request.setAttribute("id", user.getId());
	request.setAttribute("role", user.getRole());
	request.setAttribute("isActive", user.isActive());
	return Path.PAGE_EDIT_USER;
    }

}
