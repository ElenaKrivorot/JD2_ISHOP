package by.krivorot.ishop.service.impl;

import java.util.List;

import by.krivorot.ishop.dao.DaoException;
import by.krivorot.ishop.dao.DaoProvider;
import by.krivorot.ishop.dao.ProductDao;
import by.krivorot.ishop.entity.FullProduct;
import by.krivorot.ishop.entity.ShortProduct;
import by.krivorot.ishop.service.ProductService;
import by.krivorot.ishop.service.ServiceException;

public class ProductServiceImpl implements ProductService {
	
	private static final String GETTING_SHORT_PRODUCT_ERROR_MESSAGE ="Error while getting short product.";
	private static final String GETTING_FULL_PRODUCT_ERROR_MESSAGE ="Error while getting full product.";
	private static final String GETTING_LIST_PRODUCT_ERROR_MESSAGE ="Error while getting products list by subcategory.";

	@Override
	public ShortProduct getShortProduct(int product_id) throws ServiceException {
		
		DaoProvider provider = DaoProvider.getInstance();

		ProductDao productDao = provider.getProductDao();

        try {
            return productDao.getShortProduct(product_id);
        } catch (DaoException e) {
            throw new ServiceException(GETTING_SHORT_PRODUCT_ERROR_MESSAGE, e);
        }
		
	}

	@Override
	public FullProduct getFullProduct(int product_id) throws ServiceException {
		
		DaoProvider provider = DaoProvider.getInstance();

		ProductDao productDao = provider.getProductDao();

        try {
            return productDao.getFullProduct(product_id);
        } catch (DaoException e) {
            throw new ServiceException(GETTING_FULL_PRODUCT_ERROR_MESSAGE, e);
        }
	}

	@Override
	public List<ShortProduct> findProductsBySubcategory(int subcategory_id) throws ServiceException {
		
		DaoProvider provider = DaoProvider.getInstance();

		ProductDao productDao = provider.getProductDao();

        try {
            return productDao.findProductsBySubcategory(subcategory_id);
        } catch (DaoException e) {
            throw new ServiceException(GETTING_LIST_PRODUCT_ERROR_MESSAGE, e);
        }
	}

}
