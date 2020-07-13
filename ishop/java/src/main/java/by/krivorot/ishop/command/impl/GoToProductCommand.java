package by.krivorot.ishop.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import by.krivorot.ishop.command.Command;
import by.krivorot.ishop.entity.FullProduct;
import by.krivorot.ishop.service.ProductService;
import by.krivorot.ishop.service.ServiceException;
import by.krivorot.ishop.service.ServiceProvider;

public class GoToProductCommand implements Command {
	
	private static final Logger logger =  Logger.getLogger(GoToProductCommand.class);

	private static final String PRODUCT_PAGE = "WEB-INF/jsp/product.jsp";
	private static final String PRODUCT_ID ="prod_id";
	private static final String PRODUCT = "product";
	private static final String PARAMETER_PRODUCT_EMPTY ="&product=empty";
	private static final String ERROR_MESSAGE ="Error while getting product data";
	private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id;
		
		id = Integer.parseInt(request.getParameter(PRODUCT_ID));		

		FullProduct product;

		ServiceProvider provider = ServiceProvider.getInstance();
		ProductService productService = provider.getProductServiceImpl();

		try {

			product = productService.getFullProduct(id);
			if (product == null) {
				response.sendRedirect(PRODUCT_PAGE + PARAMETER_PRODUCT_EMPTY);
				return;				
			}
			
			request.setAttribute(PRODUCT, product);			

		} catch (ServiceException e) {
			logger.log(Level.ERROR, ERROR_MESSAGE, e);
			RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_PAGE);
	        dispatcher.forward(request, response);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(PRODUCT_PAGE);
		dispatcher.forward(request, response);
	}

}
