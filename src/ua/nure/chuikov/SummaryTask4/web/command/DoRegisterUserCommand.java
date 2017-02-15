package ua.nure.chuikov.SummaryTask4.web.command;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.chuikov.SummaryTask4.dao.mysql.UserDaoMysql;
import ua.nure.chuikov.SummaryTask4.entity.RoleEnum;
import ua.nure.chuikov.SummaryTask4.entity.User;
import ua.nure.chuikov.SummaryTask4.web.path.Path;

public class DoRegisterUserCommand extends AbstractCommand {
    private UserDaoMysql userDao;

    public DoRegisterUserCommand(UserDaoMysql userDao) {
	this.userDao = userDao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
	    throws UnsupportedEncodingException {

	String paramFirstName = request.getParameter("firstName");
	String paramSecondName = request.getParameter("secondName");
	String paramLastName = request.getParameter("lastName");
	String paramUserLogin = request.getParameter("userLogin");
	String paramUserPassword = request.getParameter("userPassword");
	String paramsPasswordConfirm = request.getParameter("passwordConfirm");

	// String paramsRoleId = request.getParameter("roleId");

	// int roleID = 0;
	boolean wasError = false;
	String errorFirstName = null, errorSecondName = null, errorLastName = null, errorUserLogin = null,
		errorPassword = null, errorPasswordConfirm = null, errorPasswordsNotEquel = null;
	// errorRole = null;
	// params validation
	if (paramLastName == null || paramLastName.isEmpty() || paramLastName.length() > 50) {
	    wasError = true;
	    errorLastName = "Поле должно содержать до 50 символов";
	}

	if (paramFirstName == null || paramFirstName.isEmpty() || paramFirstName.length() > 50) {
	    wasError = true;
	    errorFirstName = "Поле должно содержать до 50 символов";
	}
	if (paramSecondName == null || paramSecondName.isEmpty() || paramSecondName.length() > 50) {
	    wasError = true;
	    errorSecondName = "Поле должно содержать до 50 символов";
	}
	if (paramUserLogin == null || paramUserLogin.isEmpty() || paramUserLogin.length() > 50) {
	    wasError = true;
	    errorSecondName = "Поле должно содержать до 50 символов ";
	}
	if (userDao.getUserByLogin(paramUserLogin) != null) {
	    wasError = true;
	    errorUserLogin = "Этот логин уже занят другим пользователем";
	}
	if (paramUserPassword == null || paramUserPassword.isEmpty() || paramUserPassword.length() > 50) {
	    wasError = true;
	    errorPassword = "Поле должно содержать до 50 символов";
	}
	if (paramsPasswordConfirm == null || paramsPasswordConfirm.isEmpty() || paramsPasswordConfirm.length() > 50) {
	    wasError = true;
	    errorPasswordConfirm = "Поле должно содержать до 50 символов";

	}
	if (!paramUserPassword.equals(paramsPasswordConfirm)) {
	    wasError = true;
	    errorPasswordsNotEquel = "Пароли не совпадают";
	}

	if (wasError) {
	    request.setAttribute("firstName", paramFirstName);
	    request.setAttribute("errorFirstName", errorFirstName);
	    request.setAttribute("secondName", paramSecondName);
	    request.setAttribute("errorSecondName", errorSecondName);
	    request.setAttribute("lastName", paramLastName);
	    request.setAttribute("errorLastName", errorLastName);
	    request.setAttribute("userLogin", paramUserLogin);
	    request.setAttribute("errorUserLogin", errorUserLogin);
	    request.setAttribute("userPassword", paramUserPassword);
	    request.setAttribute("errorPassword", errorPassword);
	    request.setAttribute("passwordCnfirm", paramsPasswordConfirm);
	    request.setAttribute("errorPasswordConfirm", errorPasswordConfirm);
	    request.setAttribute("errorPasswordsNotEquel", errorPasswordsNotEquel);
	    return Path.PAGE_REGISTRATION;

	}
	// everything is ok, creating new user
	User user = new User();
	user.setFirstName(paramFirstName);
	user.setSecondName(paramSecondName);
	user.setLastName(paramLastName);
	user.setUserLogin(paramUserLogin);
	user.setUserPassword(paramUserPassword);
	user.setActive(true);
	user.setLocale("ru");
	user.setRole(RoleEnum.ROLE_STUDENT);
	try {
	    userDao.createUser(user);
	} catch (SQLException e) {
	    request.setAttribute("errorMessage", "не удалось сохранить нового пользователя");
	    return Path.PAGE_ERROR;
	}
	/*
	 * try { response.sendRedirect(Path.PAGE_LOGIN); } catch (IOException
	 * ex) { ex.printStackTrace(); }
	 */return Path.PAGE_LOGIN;
    }

}
