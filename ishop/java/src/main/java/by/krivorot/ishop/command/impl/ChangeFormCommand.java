package by.krivorot.ishop.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.krivorot.ishop.command.Command;

public class ChangeFormCommand implements Command {

	private static final String LOGIN_PAGE = "WEB-INF/jsp/login.jsp";
	private static final String FORM = "form";
	private static final String REGISTRATION = "registration";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute(FORM, REGISTRATION);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(LOGIN_PAGE);
        dispatcher.forward(request, response);
	}

}
