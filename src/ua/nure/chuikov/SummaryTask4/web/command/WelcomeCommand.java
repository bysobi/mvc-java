package ua.nure.chuikov.SummaryTask4.web.command;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.chuikov.SummaryTask4.web.path.Path;

public class WelcomeCommand extends AbstractCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
	    throws UnsupportedEncodingException {
	// TODO Auto-generated method stub
	return Path.PAGE_WELCOME;
    }

}
