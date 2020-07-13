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
import by.krivorot.ishop.service.CatalogService;
import by.krivorot.ishop.service.ServiceException;
import by.krivorot.ishop.service.ServiceProvider;
import by.krivorot.ishop.entity.Category;

public class GoToCatalogCommand implements Command {
	
	private static final Logger logger =  Logger.getLogger(GoToCatalogCommand.class);

	private static final String CATALOG_PAGE = "WEB-INF/jsp/catalog.jsp";
	private static final String CATEGORY = "category";
	private static final String SUBCATEGORY = "subcategory";
	private static final String TYPE = "type";
	private static final String CATEGORIES = "categories";
	private static final String CATALOG_LEVEL = "catalog_level";
	private static final String CATEGORY_ID = "category_id";
	private static final String ERROR_MESSAGE ="Error while getting catalog";
	private static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String catalogLevel = request.getParameter(CATALOG_LEVEL);

		ServiceProvider provider = ServiceProvider.getInstance();
		CatalogService catalogService = provider.getCatalogServiceImpl();

		List<Category> category = null;

		try {
			if (catalogLevel.equals(CATEGORY)) {
				category = catalogService.getCategories();
				request.setAttribute(TYPE, CATEGORY);
			}

			if (catalogLevel.equals(SUBCATEGORY)) {

				int id = Integer.parseInt(request.getParameter(CATEGORY_ID));
				category = catalogService.getSubcategories(id);
				request.setAttribute(TYPE, SUBCATEGORY);
			}

			request.setAttribute(CATEGORIES, category);

		} catch (ServiceException e) {
			logger.log(Level.ERROR, ERROR_MESSAGE, e);
			RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_PAGE);
	        dispatcher.forward(request, response);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(CATALOG_PAGE);
		dispatcher.forward(request, response);
	}

}
