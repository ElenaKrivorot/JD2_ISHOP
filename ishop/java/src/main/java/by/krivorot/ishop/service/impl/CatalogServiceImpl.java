package by.krivorot.ishop.service.impl;

import java.util.List;

import by.krivorot.ishop.dao.CatalogDao;
import by.krivorot.ishop.dao.DaoException;
import by.krivorot.ishop.dao.DaoProvider;
import by.krivorot.ishop.entity.Category;
import by.krivorot.ishop.service.CatalogService;
import by.krivorot.ishop.service.ServiceException;

public class CatalogServiceImpl implements CatalogService {
	
	private static final String GETTING_CATEGORIES_ERROR_MESSAGE ="Error while getting categories.";
	private static final String GETTING_SUBCATEGORIES_ERROR_MESSAGE ="Error while getting subcategories.";

	@Override
	public List<Category> getCategories() throws ServiceException {
		
		DaoProvider provider = DaoProvider.getInstance();

        CatalogDao catalogDAO = provider.getCatalogDao();

        try {
            return catalogDAO.getCategories();
        } catch (DaoException e) {
            throw new ServiceException(GETTING_CATEGORIES_ERROR_MESSAGE, e);
        }
	}

	@Override
	public List<Category> getSubcategories(int categoty_id) throws ServiceException {

		DaoProvider provider = DaoProvider.getInstance();

        CatalogDao catalogDAO = provider.getCatalogDao();

        try {
            return catalogDAO.getSubcategories(categoty_id);
        } catch (DaoException e) {
            throw new ServiceException(GETTING_SUBCATEGORIES_ERROR_MESSAGE, e);
        }
	}

}
