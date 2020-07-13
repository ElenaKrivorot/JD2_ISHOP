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
import by.krivorot.ishop.service.CartService;
import by.krivorot.ishop.service.ServiceException;
import by.krivorot.ishop.service.ServiceProvider;

public class SignOutCommand implements Command {
	
	private static final Logger logger =  Logger.getLogger(SignOutCommand.class);
	
	private static final String CART ="cart";
	private static final String USER ="user";
	private static final String GO_TO_MAIN ="Controller?command=go_to_main";
	private static final String ERROR_MESSAGE ="Error while setting cart";
	private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		
		ServiceProvider provider = ServiceProvider.getInstance();	
		CartService cartService = provider.getCartServiceImpl();
		
		Cart cart = (Cart) session.getAttribute(CART);
				
		try {
			cartService.setCart(cart);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, ERROR_MESSAGE, e);
			RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_PAGE);
	        dispatcher.forward(request, response);	
		}
		
		if (session.getAttribute(USER) != null) {
			session.removeAttribute(USER);
		}

		response.sendRedirect(GO_TO_MAIN);

	}

}
