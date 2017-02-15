package code.university.web.command;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import code.university.web.path.Path;

public class LoginCommand extends AbstractCommand{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
	    throws UnsupportedEncodingException {
	return Path.PAGE_LOGIN;
    }

}
