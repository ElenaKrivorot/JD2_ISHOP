package by.krivorot.ishop.service;

import by.krivorot.ishop.entity.Cart;
import by.krivorot.ishop.entity.User;

public interface CartService {

	Cart getCart(User user) throws ServiceException;
	boolean setCart(Cart cart) throws ServiceException;

}
