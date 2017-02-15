package code.university.web.command;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import code.university.dao.mysql.UserDaoMysql;
import code.university.entity.RoleEnum;
import code.university.entity.User;
import code.university.web.path.Path;

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
        	if(email == "custom"){
        	    email = request.getParameter("custom_email");
        	}
        	String phone = request.getParameter("phone");
        	String sec_email = request.getParameter("secondary_email");
        	sec_email = sec_email + request.getParameter("domain_email");
        	Integer deparment = 2;
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
        	user.setRole(RoleEnum.ROLE_STUDENT);
        	
        	userDao.createUser(user);

		return Path.PAGE_REGISTRATION;
	} catch (SQLException e) {
	    request.setAttribute("errorMessage", "Some Error Occurred");
	    return Path.PAGE_ERROR;
	} catch (Error e) {
	    request.setAttribute("errorMessage", e.getMessage());
	    return Path.PAGE_REGISTRATION;
	}

    }
    
}
