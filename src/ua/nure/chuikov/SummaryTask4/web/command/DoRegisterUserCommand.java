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
	try {
        	String f_name_eng = request.getParameter("f_name_eng");
        	String s_name_eng = request.getParameter("s_name_eng");
        	String m_name_eng = request.getParameter("m_name_eng");
        	String f_name_ukr = request.getParameter("f_name_ukr");
        	String s_name_ukr = request.getParameter("s_name_ukr");
        	String m_name_ukr = request.getParameter("m_name_ukr");
        	String email = request.getParameter("email");
        	String phone = request.getParameter("phone");
        	String sec_email = request.getParameter("sec_email");
        	Integer deparment = 2;
        	String password = request.getParameter("password");
        	String confirm_password = request.getParameter("confirm_password");
   //     	// String paramsRoleId = request.getParameter("roleId");
         
        	if (f_name_eng == null || f_name_eng.isEmpty() || f_name_eng.length() > 250) {
        	    throw new Error("Invalid first name(english)");
        	}

        	if (s_name_eng == null || s_name_eng.isEmpty() || s_name_eng.length() > 250) {
        	    throw new Error("Invalid second name(english)");
        	}
        	
        	if (m_name_eng == null || m_name_eng.isEmpty() || m_name_eng.length() > 250) {
        	    throw new Error("Invalid middle name(english)");
        	}
        	
        	if (f_name_ukr == null || f_name_ukr.isEmpty() || f_name_ukr.length() > 250) {
        	    throw new Error("Invalid first name(ukrainian)");
        	}

        	if (s_name_ukr == null || s_name_ukr.isEmpty() || s_name_ukr.length() > 250) {
        	    throw new Error("Invalid second name(ukrainian)");
        	}
        	
        	if (m_name_ukr == null || m_name_ukr.isEmpty() || m_name_ukr.length() > 250) {
        	    throw new Error("Invalid middle name(ukrainian)");
        	}
        	
        	if (email == null || email.isEmpty() || email.length() > 250) {
        	    throw new Error("Invalid Email");
        	}

        	if (phone == null || phone.isEmpty() || phone.length() > 250) {
        	    throw new Error("Invalid Phone Number");
        	}

        	if (sec_email == null || sec_email.isEmpty() || sec_email.length() > 250) {
        	    throw new Error("Invalid Second Email");
        	}
        	
        	if (password == null || password.isEmpty() || password.length() > 250) {
        	    throw new Error("Invalid password");
        	}
        	
        	if (!password.equals(confirm_password)) {
        	    throw new Error("Password do not match");
        	}
        	    	
        	User user = new User();
        	user.setFirstNameEng(f_name_eng);
        	user.setSecondNameEng(s_name_eng);
        	user.setMiddleNameEng(m_name_eng);
        	user.setFirstNameUkr(f_name_ukr);
        	user.setSecondNameUkr(s_name_ukr);
        	user.setMiddleNameUkr(m_name_ukr);
        	user.setEmail(email);
        	user.setPhone(phone);
        	user.setSecondEmail(sec_email);
        	user.setDepartment(deparment);
        	user.setPassword(this.MD5(password));
        	user.setRole(RoleEnum.ROLE_STUDENT);
        	
        	userDao.createUser(user);

		return Path.PAGE_LOGIN;
	} catch (SQLException e) {
	    request.setAttribute("errorMessage", "Some Error Occurred");
	    return Path.PAGE_ERROR;
	} catch (Error e) {
	    request.setAttribute("errorMessage", e.getMessage());
	    return Path.PAGE_REGISTRATION;
	}

    }
    
}
