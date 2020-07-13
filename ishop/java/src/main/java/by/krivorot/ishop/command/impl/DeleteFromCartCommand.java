package by.krivorot.ishop.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.krivorot.ishop.command.Command;
import by.krivorot.ishop.entity.Cart;

public class DeleteFromCartCommand implements Command {
	
	private static final String CART ="cart";
	private static final String PRODUCT_ID ="prod_id";
	private static final String GO_TO_CART ="Controller?command=go_to_cart";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Integer id;

		HttpSession session = request.getSession(false);

		Cart cart = (Cart) session.getAttribute(CART);

		id = Integer.parseInt(request.getParameter(PRODUCT_ID));

		cart.getProducts().remove(id);
		
		session.setAttribute(CART, cart);

		response.sendRedirect(GO_TO_CART);

	}

}
