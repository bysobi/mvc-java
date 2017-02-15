package ua.nure.chuikov.SummaryTask4.web.command;

import java.util.Map;
import java.util.TreeMap;

import ua.nure.chuikov.SummaryTask4.dao.mysql.AnswerDaoMySql;
import ua.nure.chuikov.SummaryTask4.dao.mysql.QuestionDaoMySql;
import ua.nure.chuikov.SummaryTask4.dao.mysql.TestDaoMySql;
import ua.nure.chuikov.SummaryTask4.dao.mysql.UserDaoMysql;
import ua.nure.chuikov.SummaryTask4.dao.mysql.UserResultDao;
import ua.nure.chuikov.SummaryTask4.db.DBManager;

public class CommandContainer {
    private static Map<String, AbstractCommand> commands = new TreeMap<String, AbstractCommand>();

    static {
	UserDaoMysql userDao = new UserDaoMysql(DBManager.getInstance());
	TestDaoMySql testDao = new TestDaoMySql(DBManager.getInstance());
	AnswerDaoMySql answerDao = new AnswerDaoMySql(DBManager.getInstance());
	QuestionDaoMySql questionDao = new QuestionDaoMySql(DBManager.getInstance());
	UserResultDao resultDao = new UserResultDao(DBManager.getInstance());

	commands.put("doLogin", new DoLoginCommand(userDao));
	commands.put("login", new LoginCommand());
	commands.put("welcome", new WelcomeCommand());
	commands.put("invalid", new InvalideCommand());
	commands.put("setLocale", new SetLocaleCommand(userDao));
	
	commands.put("doRegisterUser", new DoRegisterUserCommand(userDao));
	commands.put("registerUser", new RegisterUserCommand());
	commands.put("listUsers", new ListUsersCommand(userDao));
	commands.put("editUser", new EditUserCommand(userDao));
	commands.put("doEditUser", new DoEditUserCommand(userDao));
	commands.put("removeUser", new RemoveUserCommand(userDao));

	commands.put("listTest", new ListTestsCommand(testDao));
	commands.put("editTest", new EditTestCommand(testDao));
	commands.put("doEditTest", new DoEditTestCommand(testDao));
	commands.put("createTest", new CreateTestCommand());
	commands.put("doCreateTest", new DoCreateTestCommand(testDao));
	commands.put("removeTest", new RemoveTestCommand(testDao));
	
	commands.put("passingTest", new PassingTestCommand());
	/*commands.put("passingTest", new PassingTestCommand(answerDao));
	commands.put("passingTest", new PassingTestCommand(testDao));
*/
	commands.put("getResultTest", new ListResultCommand(resultDao));
	commands.put("doPassingTest", new DoPassingTestCommand(questionDao));
	commands.put("usersListTest", new UsersListTestsCommand(testDao));
	commands.put("userInfo", new UserInfoAndListTestsCommand(testDao));
	commands.put("addingQuestionAndAnswers", new AddingQuestionAndAnswersCommand(testDao));

	commands.put("doAddingQuestionAndAnswers", new DoAddingQuestionAndAnswersCommand(answerDao));
	commands.put("doAddingQuestionAndAnswers", new DoAddingQuestionAndAnswersCommand(questionDao));

	commands.put("logout", new LogoutCommand());
	commands.put("setLocale", new SetLocaleCommand(userDao));
	commands.put("notActive", new AccountNotActiveCommand());

    }

    public static AbstractCommand getCommand(String name) {
	if (name == null || !commands.containsKey(name)) {
	    return commands.get("invalid");
	}
	return commands.get(name);
    }
}
