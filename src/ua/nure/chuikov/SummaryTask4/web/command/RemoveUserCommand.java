package ua.nure.chuikov.SummaryTask4.web.command;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.chuikov.SummaryTask4.dao.mysql.UserDaoMysql;
import ua.nure.chuikov.SummaryTask4.entity.User;
import ua.nure.chuikov.SummaryTask4.web.path.Path;

public class RemoveUserCommand extends AbstractCommand {
    private UserDaoMysql userDao;

    public RemoveUserCommand(UserDaoMysql userDao) {
	this.userDao = userDao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

	String paramId = request.getParameter("id");
	int id = 0;

	if (paramId != null && !paramId.isEmpty()) {
	    try {
		id = Integer.parseInt(paramId);
	    } catch (NumberFormatException e) {
	    }
	}

	User user;
	try {
	    user = userDao.getUserById(id);
	} catch (SQLException e1) {
	    request.setAttribute("errorMessage", "ошибка, не удалось найти пользователя");
	    return Path.PAGE_ERROR;
	}
	if (user == null) {
	    request.setAttribute("errorMessage", "пользователь не найден");
	    return Path.PAGE_ERROR;
	}

	// prevent self removing
	User curentUser = (User) request.getSession(false).getAttribute("aut_user");
	if (curentUser.getId() != user.getId()) {
	    // trying to remove user
	    try {
		userDao.removeUserById(id);
	    } catch (Exception exc) {
		request.setAttribute("errorMessage", "ошибка, не удалось удалить пользователя");
		return Path.PAGE_ERROR;
	    }
	    // need to tell application that user is deleted
	    // user may be logged in
	    // ??
	    // можно сделать чтобы фильтр каждый раз подгружал из базы
	    // пользователя, заодно и проверял на активность
	}

	try {
	    response.sendRedirect(Path.COMMAND_LIST_USERS);
	} catch (IOException e) {
	    e.printStackTrace();
	}

	return null;
    }

}
