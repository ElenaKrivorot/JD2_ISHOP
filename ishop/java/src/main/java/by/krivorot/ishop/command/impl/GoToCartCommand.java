package by.krivorot.ishop.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import by.krivorot.ishop.command.Command;
import by.krivorot.ishop.entity.Cart;
import by.krivorot.ishop.entity.ShortProduct;
import by.krivorot.ishop.service.ProductService;
import by.krivorot.ishop.service.ServiceException;
import by.krivorot.ishop.service.ServiceProvider;


public class GoToCartCommand implements Command {
	
	private static final Logger logger =  Logger.getLogger(GoToCartCommand.class);

	private static final String CART_PAGE = "WEB-INF/jsp/cart.jsp";
	private static final String CART ="cart";
	private static final String PRODUCT_LIST = "productList";
	private static final String ERROR_MESSAGE ="Error while getting cart product list";
	private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		Cart cart = (Cart) session.getAttribute(CART);		

		List<Integer> products_id = cart.getProducts();

		Iterator<Integer> iterator = products_id.iterator();
		
		List<ShortProduct> products;

		if (!iterator.hasNext()) {
			products = null;
		} else {
			products = new ArrayList<ShortProduct>();

			ServiceProvider provider = ServiceProvider.getInstance();
			ProductService productService = provider.getProductServiceImpl();

			try {
				while (iterator.hasNext()) {

					products.add(productService.getShortProduct(iterator.next()));
				}
				
			} catch (ServiceException e) {
				logger.log(Level.ERROR, ERROR_MESSAGE, e);
				RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_PAGE);
		        dispatcher.forward(request, response);
			}

		}
		
		request.setAttribute(PRODUCT_LIST, products);		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(CART_PAGE);
		dispatcher.forward(request, response);

	}

}
