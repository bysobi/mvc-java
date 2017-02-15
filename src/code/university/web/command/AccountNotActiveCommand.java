package code.university.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import code.university.web.path.Path;

public class AccountNotActiveCommand extends AbstractCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
	request.setAttribute("errorMessage", "������, ��� ������� �� �������");
	return Path.PAGE_ERROR;
    }

}
