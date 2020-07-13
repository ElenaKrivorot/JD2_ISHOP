package by.krivorot.ishop.service;

import java.util.List;

import by.krivorot.ishop.entity.Category;

public interface CatalogService {
	
	List<Category> getCategories() throws ServiceException;
	List<Category> getSubcategories(int categoty_id) throws ServiceException;

}
