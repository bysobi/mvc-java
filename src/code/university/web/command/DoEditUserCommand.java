package code.university.web.command;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import code.university.dao.mysql.UserDaoMysql;
import code.university.entity.RoleEnum;
import code.university.entity.User;
import code.university.web.path.Path;

public class DoEditUserCommand extends AbstractCommand {
    private UserDaoMysql userDao;

    public DoEditUserCommand(UserDaoMysql userDao) {
	this.userDao = userDao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
	
	String f_name_eng = request.getParameter("f_name_eng");
	String s_name_eng = request.getParameter("s_name_eng");
	String m_name_eng = request.getParameter("m_name_eng");
	String f_name_ukr = request.getParameter("f_name_ukr");
	String s_name_ukr = request.getParameter("s_name_ukr");
	String m_name_ukr = request.getParameter("m_name_ukr");
	String email = request.getParameter("f_name_eng");
	String phone = request.getParameter("phone");
	String sec_email = request.getParameter("sec_email");
	String deparment = request.getParameter("deparment");
	
	String password = request.getParameter("password");
	
	String id = request.getParameter("id");

	User user = null;

	int userId = 0;
	boolean isActive = false;
	// RoleEnum role;
	boolean wasError = false;
	String errorLastName = null, errorSecondName = null, errorFirstName = null, errorUserLogin = null,
		errorUserPassword = null;
	// checking params
	if (id != null && !id.isEmpty()) {
	    try {
		userId = Integer.parseInt(id);
	    } catch (NumberFormatException e) {
	    }
	}
	try {
	    user = userDao.getUserById(userId);
	} catch (SQLException e1) {
	    request.setAttribute("errorMessage", "�� ������� ��������� ���������������� ������������");
	    return Path.PAGE_ERROR;
	}
	if (user == null) {
	    request.setAttribute("errorMessage", "������������ �� ������");
	    return Path.PAGE_ERROR;
	}

	user.setFirstNameEng(f_name_eng);
	user.setSecondNameEng(s_name_eng);
	user.setMiddleNameEng(m_name_eng);
	user.setFirstNameUkr(f_name_ukr);
	user.setSecondNameUkr(s_name_ukr);
	user.setMiddleNameUkr(m_name_ukr);
	user.setEmail(email);
	user.setPassword(password);
	user.setRole(RoleEnum.ROLE_STUDENT);
	
	try {
	    userDao.updateUserById(userId, user);
	} catch (SQLException e1) {
		request.setAttribute("errorMessage", "������!!! ������������ �� ��������");
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
