package ua.nure.chuikov.SummaryTask4.web.command;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.chuikov.SummaryTask4.dao.mysql.UserDaoMysql;
import ua.nure.chuikov.SummaryTask4.entity.User;
import ua.nure.chuikov.SummaryTask4.web.path.Path;

public class ListUsersCommand extends AbstractCommand {
    private UserDaoMysql userDao;

    public ListUsersCommand(UserDaoMysql userDao) {
	this.userDao = userDao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
	    throws UnsupportedEncodingException {

	List<User> users = new ArrayList<>();
	try {
	    users = userDao.getAllUsers();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	request.setAttribute("users", users);
	return Path.PAGE_LIST_USERS;
    }

}