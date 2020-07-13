package by.krivorot.ishop.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import by.krivorot.ishop.command.Command;
import by.krivorot.ishop.entity.ShortProduct;
import by.krivorot.ishop.service.ProductService;
import by.krivorot.ishop.service.ServiceException;
import by.krivorot.ishop.service.ServiceProvider;

public class GoToProductListCommand implements Command {
	
	private static final Logger logger =  Logger.getLogger(GoToProductListCommand.class);
	
	private static final String PRODUCTS_PAGE = "WEB-INF/jsp/products.jsp";	
	private static final String CATEGORY_ID = "category_id";
	private static final String PRODUCT_LIST = "productList";
	private static final String ERROR_MESSAGE ="Error while getting products by subcategory";
	private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id;

		id = Integer.parseInt(request.getParameter(CATEGORY_ID));

		List<ShortProduct> products = null;

		ServiceProvider provider = ServiceProvider.getInstance();
		ProductService productService = provider.getProductServiceImpl();

		try {

			products = productService.findProductsBySubcategory(id);

		} catch (ServiceException e) {
			logger.log(Level.ERROR, ERROR_MESSAGE, e);
			RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_PAGE);
	        dispatcher.forward(request, response);
		}

		request.setAttribute(PRODUCT_LIST, products);

		RequestDispatcher dispatcher = request.getRequestDispatcher(PRODUCTS_PAGE);
        dispatcher.forward(request, response);
       		
	}

}
