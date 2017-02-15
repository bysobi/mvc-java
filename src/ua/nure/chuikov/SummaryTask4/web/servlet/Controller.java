package ua.nure.chuikov.SummaryTask4.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.chuikov.SummaryTask4.web.command.AbstractCommand;
import ua.nure.chuikov.SummaryTask4.web.command.CommandContainer;

public class Controller extends HttpServlet {

     private static final long serialVersionUID = 6153110417191043151L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
	    throws IOException, ServletException {
	String commandName = request.getParameter("command");

	AbstractCommand command = CommandContainer.getCommand(commandName);

	String forward = command.execute(request, response);

	if (forward != null && !forward.isEmpty()) {
	    request.getRequestDispatcher(forward).forward(request, response);
	}

    }
}
