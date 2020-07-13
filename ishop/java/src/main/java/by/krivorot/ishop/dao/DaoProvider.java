package by.krivorot.ishop.dao;

import by.krivorot.ishop.dao.impl.DBCartDao;
import by.krivorot.ishop.dao.impl.DBCatalogDao;
import by.krivorot.ishop.dao.impl.DBProductDao;
import by.krivorot.ishop.dao.impl.DBUserDao;

public class DaoProvider {
private static final DaoProvider instance = new DaoProvider();
	
	private UserDao userDao = new DBUserDao();
	private CartDao cartDao = new DBCartDao();
	private ProductDao productDao = new DBProductDao();
	private CatalogDao catalogDao = new DBCatalogDao();
		
	private DaoProvider() {}
	
	public static DaoProvider getInstance() {
		return instance;
	}
		
	public UserDao getUserDao() {
		return userDao;
	}
	
	public CartDao getCartDao() {
		return cartDao;
	}
	
	public ProductDao getProductDao() {
		return productDao;
	}
	
	public CatalogDao getCatalogDao() {
		return catalogDao;
	}
		
}
