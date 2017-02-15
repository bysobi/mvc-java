package ua.nure.chuikov.SummaryTask4.web.command;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.chuikov.SummaryTask4.dao.mysql.UserDaoMysql;
import ua.nure.chuikov.SummaryTask4.entity.User;
import ua.nure.chuikov.SummaryTask4.web.path.Path;

public class DoEditUserCommand extends AbstractCommand {
    private UserDaoMysql userDao;

    public DoEditUserCommand(UserDaoMysql userDao) {
	this.userDao = userDao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
	String paramLastName = request.getParameter("lastName");
	String paramSecondName = request.getParameter("secondName");
	String paramFirstName = request.getParameter("firstName");
	String paramUserLogin = request.getParameter("userLogin");
	String paramUserPassword = request.getParameter("userPassword");
	String paramUserId = request.getParameter("id");
	// String paramRoleId = request.getParameter("role");
	String paramIsActive = request.getParameter("isActive");

	User user = null;

	int userId = 0;
	boolean isActive = false;
	// RoleEnum role;
	boolean wasError = false;
	String errorLastName = null, errorSecondName = null, errorFirstName = null, errorUserLogin = null,
		errorUserPassword = null;
	// checking params
	if (paramUserId != null && !paramUserId.isEmpty()) {
	    try {
		userId = Integer.parseInt(paramUserId);
	    } catch (NumberFormatException e) {
	    }
	}
	try {
	    user = userDao.getUserById(userId);
	} catch (SQLException e1) {
	    request.setAttribute("errorMessage", "не удалось сохранить редактированного пользователя");
	    return Path.PAGE_ERROR;
	}
	if (user == null) {
	    request.setAttribute("errorMessage", "пользователь не найден");
	    return Path.PAGE_ERROR;
	}

	if (paramLastName == null || paramLastName.isEmpty() || paramLastName.length() > 50) {
	    wasError = true;
	    errorLastName = "Поле должно содержать до 50 символов";
	}
	if (paramSecondName == null || paramSecondName.isEmpty() || paramSecondName.length() > 50) {
	    wasError = true;
	    errorSecondName = "Поле должно содержать до 50 символов";
	}
	if (paramFirstName == null || paramFirstName.isEmpty() || paramFirstName.length() > 50) {
	    wasError = true;
	    errorFirstName = "Поле должно содержать до 50 символов";
	}
	if (paramUserLogin == null || paramUserLogin.isEmpty() || paramUserLogin.length() > 50) {
	    wasError = true;
	    errorUserLogin = "Поле должно содержать до 50 символов";
	}

	User other_user = null;
	try {
	    other_user = userDao.getUserByLogin(paramUserLogin);
	} catch (UnsupportedEncodingException e1) {
		wasError = true;
		errorUserLogin = "Этот логин уже занят другим пользователем";
    e1.printStackTrace();
	}
	if (other_user != null && other_user.getId() != user.getId()) {
	    wasError = true;
	    errorUserLogin = "Этот логин уже занят другим пользователем";
	}
	if (paramUserPassword == null || paramUserPassword.isEmpty() || paramUserPassword.length() > 50) {
	    wasError = true;
	    errorUserPassword = "Поле должно содержать до 50 символов";
	}
	/*
	 * if (paramRoleId != null && !paramRoleId.isEmpty()) { try { roleId =
	 * Integer.parseInt(paramRoleId); } catch (NumberFormatException e) { }
	 * } Role role = DaoContainer.ROLE_DAO.loadRoleById(roleId); if (role ==
	 * null) { wasError = true; errorRole = "роль не найдена"; }
	 */
	if (paramIsActive != null && !paramIsActive.isEmpty()) {
	    isActive = true;
	}

	if (wasError) {
	    request.setAttribute("lastName", paramLastName);
	    request.setAttribute("errorLastName", errorLastName);
	    request.setAttribute("secondName", paramSecondName);
	    request.setAttribute("errorSecondName", errorSecondName);
	    request.setAttribute("firstName", paramFirstName);
	    request.setAttribute("errorFirstName", errorFirstName);
	    request.setAttribute("userlogin", paramUserLogin);
	    request.setAttribute("errorLogin", errorUserLogin);
	    request.setAttribute("password", paramUserPassword);
	    request.setAttribute("errorPassword", errorUserPassword);
	    request.setAttribute("userId", paramUserId);
	    /*
	     * request.setAttribute("userRoleId", paramRoleId);
	     * request.setAttribute("errorRole", errorRole);
	     */
	    // request.setAttribute("roles",
	    // DaoContainer.ROLE_DAO.listAllRoles());
	    request.setAttribute("isActive", isActive);

	    return Path.PAGE_EDIT_USER;
	}

	// all ok, updating user
	user.setFirstName(paramFirstName);
	user.setLastName(paramLastName);
	user.setSecondName(paramSecondName);
	user.setUserLogin(paramUserLogin);
	user.setUserPassword(paramUserPassword);
	// user.setRoleId(roleId);
	user.setActive(isActive);

	/*
	 * if (!DaoContainer.USER_DAO.updateUser(user)) {
	 * request.setAttribute("errorMessage",
	 * "не удалось сохранить редактированного пользователя"); return
	 * Path.PAGE_ERROR; }
	 */
	try {
	    userDao.updateUserById(userId, user);
	} catch (SQLException e1) {
		request.setAttribute("errorMessage", "ОШИБКА!!! Пользователь не сохранен");
		return Path.PAGE_ERROR;
		}
	
	try {
	    response.sendRedirect(Path.COMMAND_LIST_USERS);
	} catch (IOException e) {
	    e.printStackTrace();
	}

	return null;
    }

}
