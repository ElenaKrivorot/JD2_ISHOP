package by.krivorot.ishop.service;

import by.krivorot.ishop.service.impl.CartServiceImpl;
import by.krivorot.ishop.service.impl.CatalogServiceImpl;
import by.krivorot.ishop.service.impl.ProductServiceImpl;
import by.krivorot.ishop.service.impl.UserServiceImpl;

public class ServiceProvider {

private static final ServiceProvider instance = new ServiceProvider();
	
	private UserService userService = new UserServiceImpl();
	private CartService cartService = new CartServiceImpl();
	private ProductService productService = new ProductServiceImpl();
	private CatalogService catalogService = new CatalogServiceImpl();
		
	private ServiceProvider() {}
	
	public static ServiceProvider getInstance() {
		return instance;
	}
		
	public UserService getUserServiceImpl() {
		return userService;
	}
	
	public CartService getCartServiceImpl() {
		return cartService;
	}
	
	public ProductService getProductServiceImpl() {
		return productService;
	}
	
	public CatalogService getCatalogServiceImpl() {
		return catalogService;
	}
}
