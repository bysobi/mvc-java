package code.university.web.command;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import code.university.dao.mysql.UserDaoMysql;
import code.university.entity.User;
import code.university.web.path.Path;

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
	    request.setAttribute("errorMessage", "������, �� ������� ����� ������������");
	    return Path.PAGE_ERROR;
	}
	if (user == null) {
	    request.setAttribute("errorMessage", "������������ �� ������");
	    return Path.PAGE_ERROR;
	}

	// prevent self removing
	User curentUser = (User) request.getSession(false).getAttribute("aut_user");
	if (curentUser.getId() != user.getId()) {
	    // trying to remove user
	    try {
		userDao.removeUserById(id);
	    } catch (Exception exc) {
		request.setAttribute("errorMessage", "������, �� ������� ������� ������������");
		return Path.PAGE_ERROR;
	    }
	    // need to tell application that user is deleted
	    // user may be logged in
	    // ??
	    // ����� ������� ����� ������ ������ ��� ��������� �� ����
	    // ������������, ������ � �������� �� ����������
	}

	try {
	    response.sendRedirect(Path.COMMAND_LIST_USERS);
	} catch (IOException e) {
	    e.printStackTrace();
	}

	return null;
    }

}
