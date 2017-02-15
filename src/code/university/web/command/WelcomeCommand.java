package code.university.web.command;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import code.university.web.path.Path;

public class WelcomeCommand extends AbstractCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
	    throws UnsupportedEncodingException {
	// TODO Auto-generated method stub
	return Path.PAGE_WELCOME;
    }

}
