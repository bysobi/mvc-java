package ua.nure.chuikov.SummaryTask4.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.chuikov.SummaryTask4.web.path.Path;

public class LogoutCommand extends AbstractCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
	HttpSession session = request.getSession(false);
	if (session != null) {
	    session.invalidate();
	}

	return Path.PAGE_WELCOME;
    }

}
