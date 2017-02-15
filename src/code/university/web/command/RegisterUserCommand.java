package code.university.web.command;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import code.university.web.path.Path;

public class RegisterUserCommand extends AbstractCommand {

    public String execute(HttpServletRequest request, HttpServletResponse response) {
	return Path.PAGE_REGISTRATION;
    }

}
