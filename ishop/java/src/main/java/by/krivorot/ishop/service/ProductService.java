package by.krivorot.ishop.service;

import java.util.List;

import by.krivorot.ishop.entity.FullProduct;
import by.krivorot.ishop.entity.ShortProduct;

public interface ProductService {
	
	ShortProduct getShortProduct(int product_id) throws ServiceException;
	FullProduct getFullProduct(int product_id) throws ServiceException;
	List<ShortProduct> findProductsBySubcategory(int subcategory_id) throws ServiceException;

}
