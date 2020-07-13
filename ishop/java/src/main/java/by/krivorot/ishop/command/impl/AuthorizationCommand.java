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
import by.krivorot.ishop.entity.Cart;
import by.krivorot.ishop.entity.User;
import by.krivorot.ishop.service.CartService;
import by.krivorot.ishop.service.ServiceException;
import by.krivorot.ishop.service.ServiceProvider;
import by.krivorot.ishop.service.UserService;
import by.krivorot.ishop.service.validation.Validator;

public class AuthorizationCommand implements Command {
	
	private static final Logger logger =  Logger.getLogger(AuthorizationCommand.class);

	private static final String REQUEST_PARAMETER_EMAIL = "email";
	private static final String REQUEST_PARAMETER_PASSWORD = "password";
	private static final String LAST_REQUEST ="lastRequest";
	private static final String CART ="cart";
	private static final String GO_TO_MAIN ="Controller?command=go_to_main";
	private static final String USER ="user";
	private static final String PARAMETER_NULL_FIELDS ="&authstatus=nullFields";
	private static final String PARAMETER_WRONG_EMAIL ="&authstatus=wrongEmail";
	private static final String PARAMETER_USER_NOT_FOUND ="&authstatus=wrong";
	private static final String ERROR_MESSAGE ="Error while authorization";
	private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter(REQUEST_PARAMETER_EMAIL);
		String password = request.getParameter(REQUEST_PARAMETER_PASSWORD);
		

		ServiceProvider provider = ServiceProvider.getInstance();
		UserService userService = provider.getUserServiceImpl();
		CartService cartService = provider.getCartServiceImpl();

		User user;

		HttpSession session = request.getSession(true);

		if (!Validator.nullValidator(email, password)) {
			response.sendRedirect(session.getAttribute(LAST_REQUEST).toString() + PARAMETER_NULL_FIELDS);
			return;
		}

		if (!Validator.emailValidator(email)) {
			response.sendRedirect(session.getAttribute(LAST_REQUEST).toString() + PARAMETER_WRONG_EMAIL);
			return;
		}

		try {
			user = userService.signIn(email, password);
			if (user == null) {
				response.sendRedirect(session.getAttribute(LAST_REQUEST).toString() + PARAMETER_USER_NOT_FOUND);
				return;				
			}
			
			Cart cart;
			
			cart = cartService.getCart(user);
			
			session.setAttribute(USER, user);
			session.setAttribute(CART, cart);

			response.sendRedirect(GO_TO_MAIN);

		} catch (ServiceException e) {
			logger.log(Level.ERROR, ERROR_MESSAGE, e);
			RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_PAGE);
	        dispatcher.forward(request, response);
		}

	}

}
