package ua.nure.chuikov.SummaryTask4.web.command;

import java.util.Map;
import java.util.TreeMap;

import ua.nure.chuikov.SummaryTask4.dao.mysql.UserDaoMysql;
import ua.nure.chuikov.SummaryTask4.db.DBManager;

public class CommandContainer {
    private static Map<String, AbstractCommand> commands = new TreeMap<String, AbstractCommand>();

    static {
	UserDaoMysql userDao = new UserDaoMysql(DBManager.getInstance());

	commands.put("doLogin", new DoLoginCommand(userDao));
	commands.put("login", new LoginCommand());
	commands.put("welcome", new WelcomeCommand());
	commands.put("invalid", new InvalideCommand());
	
	commands.put("doRegisterUser", new DoRegisterUserCommand(userDao));
	commands.put("registerUser", new RegisterUserCommand());
	commands.put("listUsers", new ListUsersCommand(userDao));
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
