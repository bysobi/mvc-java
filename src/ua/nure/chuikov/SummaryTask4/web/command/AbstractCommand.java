package ua.nure.chuikov.SummaryTask4.web.command;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractCommand {
    public abstract String execute(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException;
}
