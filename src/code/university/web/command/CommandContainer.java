package code.university.web.command;

import java.util.Map;
import java.util.TreeMap;

import code.university.dao.mysql.DepartmentDaoMysql;
import code.university.dao.mysql.UserDaoMysql;
import code.university.db.DBManager;

public class CommandContainer {
    private static Map<String, AbstractCommand> commands = new TreeMap<String, AbstractCommand>();

    static {
	UserDaoMysql userDao = new UserDaoMysql(DBManager.getInstance());
	DepartmentDaoMysql departmentDao = new DepartmentDaoMysql(DBManager.getInstance());
	
	commands.put("doLogin", new DoLoginCommand(userDao));
	commands.put("login", new LoginCommand());
	commands.put("welcome", new WelcomeCommand());
	commands.put("invalid", new InvalideCommand());
	
	commands.put("doRegisterUser", new DoRegisterUserCommand(userDao));
	commands.put("registerUser", new RegisterUserCommand());
	commands.put("listUsers", new ListUsersCommand(userDao, departmentDao));
	commands.put("editUser", new EditUserCommand(userDao));
	commands.put("doEditUser", new DoEditUserCommand(userDao));
	commands.put("removeUser", new RemoveUserCommand(userDao));

	commands.put("logout", new LogoutCommand());
	commands.put("notActive", new AccountNotActiveCommand());

    }

    public static AbstractCommand getCommand(String name) {
	if (name == null || !commands.containsKey(name)) {
	    return commands.get("invalid");
	}
	return commands.get(name);
    }
}
