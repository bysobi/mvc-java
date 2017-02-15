package ua.nure.chuikov.SummaryTask4.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.chuikov.SummaryTask4.web.path.Path;

public class AccountNotActiveCommand extends AbstractCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
	request.setAttribute("errorMessage", "Ошибка, ваш аккаунт не активен");
	return Path.PAGE_ERROR;
    }

}
