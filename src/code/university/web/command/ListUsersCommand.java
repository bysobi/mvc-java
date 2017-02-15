package code.university.web.command;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import code.university.dao.DepartmentDao;
import code.university.dao.mysql.DepartmentDaoMysql;
import code.university.dao.mysql.UserDaoMysql;
import code.university.entity.Departement;
import code.university.entity.User;
import code.university.web.path.Path;

public class ListUsersCommand extends AbstractCommand {
    private UserDaoMysql userDao;
    private DepartmentDaoMysql departmentDao;

    public ListUsersCommand(UserDaoMysql userDao, DepartmentDaoMysql departmentDao) {
	this.userDao = userDao;
	this.departmentDao = departmentDao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
	    throws UnsupportedEncodingException {

	try {
	List<User> users = new ArrayList<>();
	List<Departement> departements = new ArrayList<>();
	try {
	    users = userDao.getAllUsers();
	    departements = departmentDao.getAll();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	request.setAttribute("users", users);
	request.setAttribute("departements", departements);
	return Path.PAGE_LIST_USERS;
	} catch (Exception e){
	    return Path.PAGE_ERROR;
	}
    }
}