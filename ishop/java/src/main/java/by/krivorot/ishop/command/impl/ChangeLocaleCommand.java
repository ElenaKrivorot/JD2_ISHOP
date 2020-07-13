package by.krivorot.ishop.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.krivorot.ishop.command.Command;

public class ChangeLocaleCommand implements Command {
	
	private static final String LAST_REQUEST ="lastRequest";
	private static final String GO_TO_MAIN ="Controller?command=go_to_main";
	private static final String LOCALE = "local";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession(true);
	        session.setAttribute(LOCALE, request.getParameter(LOCALE));

	        if (session.getAttribute(LAST_REQUEST) != null)
	        {
	            response.sendRedirect(session.getAttribute(LAST_REQUEST).toString());
	        }
	        else {
	            response.sendRedirect(GO_TO_MAIN);
	        }

	}

}
