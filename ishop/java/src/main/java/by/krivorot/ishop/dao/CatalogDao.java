package by.krivorot.ishop.dao;

import java.util.List;

import by.krivorot.ishop.entity.Category;

public interface CatalogDao {
	
	List<Category> getCategories() throws DaoException;
	List<Category> getSubcategories(int categoty_id) throws DaoException;

}
