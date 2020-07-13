package by.krivorot.ishop.dao;

import by.krivorot.ishop.entity.Cart;
import by.krivorot.ishop.entity.User;

public interface CartDao {

	Cart getCart(User user) throws DaoException;
	boolean setCart(Cart cart) throws DaoException;

}
