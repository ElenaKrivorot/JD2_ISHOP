package by.krivorot.ishop.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import by.krivorot.ishop.command.Command;
import by.krivorot.ishop.service.ServiceException;
import by.krivorot.ishop.service.ServiceProvider;
import by.krivorot.ishop.service.UserService;
import by.krivorot.ishop.service.validation.Validator;

public class RegistrationCommand implements Command{
	
	private static final Logger logger =  Logger.getLogger(RegistrationCommand.class);
	
	private static final String REQUEST_PARAMETER_EMAIL = "email";
	private static final String FIRST_PASSWORD = "password1";
	private static final String SECOND_PASSWORD = "password2";
	private static final String LAST_REQUEST ="lastRequest";
	private static final String PARAMETER_NULL_FIELDS ="&regstatus=nullFields";
	private static final String PARAMETER_WRONG_EMAIL ="&regstatus=wrongEmail";
	private static final String PARAMETER_WRONG_SECOND_PASS ="&regstatus=wrongSecondPass";
	private static final String PARAMETER_USER_ALREADY_EXISTS ="&regstatus=error";
	private static final String ERROR_MESSAGE ="Error while registration";
	private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";
	private static final String GO_TO_LOGIN_OK ="Controller?command=go_to_login&regstatus=ok";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
		String email = request.getParameter(REQUEST_PARAMETER_EMAIL);
		String firstPassword = request.getParameter(FIRST_PASSWORD);
		String secondPassword = request.getParameter(SECOND_PASSWORD);


		HttpSession session = request.getSession(false);
		ServiceProvider provider = ServiceProvider.getInstance();
		UserService userService = provider.getUserServiceImpl();
		
		
		if(!Validator.nullValidator(email, firstPassword)) {
			response.sendRedirect(session.getAttribute(LAST_REQUEST).toString() + PARAMETER_NULL_FIELDS);
			return;
		}
		
		if(!Validator.registrationValidator(firstPassword, secondPassword)) {
			response.sendRedirect(session.getAttribute(LAST_REQUEST).toString() + PARAMETER_WRONG_SECOND_PASS);
			return;
		}
		
		if(!Validator.emailValidator(email)) {
			response.sendRedirect(session.getAttribute(LAST_REQUEST).toString() + PARAMETER_WRONG_EMAIL);
			return;
		}
				
		try {
			
			if (!userService.registration(email, firstPassword)) {
								
				response.sendRedirect(session.getAttribute(LAST_REQUEST).toString() + PARAMETER_USER_ALREADY_EXISTS);
                return;
			} 
						
			response.sendRedirect(GO_TO_LOGIN_OK);
			
		} catch (ServiceException e) {
			logger.log(Level.ERROR, ERROR_MESSAGE, e);
			RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_PAGE);
	        dispatcher.forward(request, response);
		}
		
	}
	
}
