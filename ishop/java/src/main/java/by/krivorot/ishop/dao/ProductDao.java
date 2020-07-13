package by.krivorot.ishop.dao;

import java.util.List;

import by.krivorot.ishop.entity.FullProduct;
import by.krivorot.ishop.entity.ShortProduct;

public interface ProductDao {
	
	ShortProduct getShortProduct(int product_id) throws DaoException;
	FullProduct getFullProduct(int product_id) throws DaoException;
	List<ShortProduct> findProductsBySubcategory(int subcategory_id) throws DaoException;

}
