package by.krivorot.ishop.command.impl;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import by.krivorot.ishop.command.Command;
import by.krivorot.ishop.entity.User;
import by.krivorot.ishop.entity.UserData;
import by.krivorot.ishop.service.ServiceException;
import by.krivorot.ishop.service.ServiceProvider;
import by.krivorot.ishop.service.UserService;


public class EditUserDataCommand implements Command {
	
	private static final Logger logger =  Logger.getLogger(EditUserDataCommand.class);
	
	private static final String NAME = "name";
	private static final String SURNAME = "surname";
	private static final String GENDER = "gender";
	private static final String DATE = "dateOfBirth";
	private static final String TELEPHONE = "telephone";
	private static final String USER ="user";
	private static final String LAST_REQUEST ="lastRequest";
	private static final String GO_TO_PERSONAL_DATA ="Controller?command=go_to_personal";
	private static final String PARAMETER_STATUS ="&status=error";
	private static final String ERROR_MESSAGE ="Error while edit user data";
	private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter(NAME);
		String surname = request.getParameter(SURNAME);
		String gender = request.getParameter(GENDER);
		String strdate = request.getParameter(DATE);
		String telephone = request.getParameter(TELEPHONE);
							
		HttpSession session = request.getSession(false);
		ServiceProvider provider = ServiceProvider.getInstance();
		UserService userService = provider.getUserServiceImpl();
							
		try {
			LocalDate date = LocalDate.parse(strdate);
			
			User user = (User) session.getAttribute(USER);
			UserData data = new UserData(name,surname,gender,date,telephone);
			
			if (!userService.updateUserData(data, user.getId())) {
								
				response.sendRedirect(session.getAttribute(LAST_REQUEST).toString() + PARAMETER_STATUS);
                return;
			} 
						
			response.sendRedirect(GO_TO_PERSONAL_DATA);
			
		} catch (ServiceException e) {
			logger.log(Level.ERROR, ERROR_MESSAGE, e);
			RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_PAGE);
			dispatcher.forward(request, response);
		} 		
	

	}

}
