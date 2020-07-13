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
import by.krivorot.ishop.entity.User;
import by.krivorot.ishop.entity.UserData;
import by.krivorot.ishop.service.ServiceException;
import by.krivorot.ishop.service.ServiceProvider;
import by.krivorot.ishop.service.UserService;

public class GoToPersonalCommand implements Command {
	
	private static final Logger logger =  Logger.getLogger(GoToPersonalCommand.class);

	private static final String PERSONAL_PAGE = "WEB-INF/jsp/personal.jsp";
	private static final String USER ="user";
	private static final String DATA ="data";
	private static final String ERROR_MESSAGE ="Error while getting user data";
	private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceProvider provider = ServiceProvider.getInstance();
		UserService userService = provider.getUserServiceImpl();

		HttpSession session = request.getSession(false);

		User user = (User) session.getAttribute(USER);
		UserData data;

		try {
			data = userService.getUserData(user.getId());

			request.setAttribute(DATA, data);

		} catch (ServiceException e) {
			logger.log(Level.ERROR, ERROR_MESSAGE, e);
			RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_PAGE);
	        dispatcher.forward(request, response);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(PERSONAL_PAGE);
		dispatcher.forward(request, response);

	}

}
