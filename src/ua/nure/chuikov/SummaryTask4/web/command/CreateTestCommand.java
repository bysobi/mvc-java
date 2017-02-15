package ua.nure.chuikov.SummaryTask4.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.chuikov.SummaryTask4.web.path.Path;

public class CreateTestCommand extends AbstractCommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		return Path.PAGE_NEW_TEST;
	}

}
