package ua.nure.chuikov.SummaryTask4.web.command;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.chuikov.SummaryTask4.web.path.Path;

public class LoginCommand extends AbstractCommand{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
	    throws UnsupportedEncodingException {
	return Path.PAGE_LOGIN;
    }

}
