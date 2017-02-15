package code.university.web.command;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import code.university.web.path.Path;

public class InvalideCommand extends AbstractCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
	    throws UnsupportedEncodingException {
	request.setAttribute("errorMessage", "invalide command");
	return Path.PAGE_INVALID_COMMAND;
    }

}
