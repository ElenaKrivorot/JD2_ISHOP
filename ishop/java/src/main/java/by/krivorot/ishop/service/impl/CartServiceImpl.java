package by.krivorot.ishop.service.impl;

import by.krivorot.ishop.dao.CartDao;
import by.krivorot.ishop.dao.DaoException;
import by.krivorot.ishop.dao.DaoProvider;
import by.krivorot.ishop.entity.Cart;
import by.krivorot.ishop.entity.User;
import by.krivorot.ishop.service.CartService;
import by.krivorot.ishop.service.ServiceException;

public class CartServiceImpl implements CartService{
	
	private static final String GETTING_ERROR_MESSAGE ="Error while getting cart.";
	private static final String SETTING_ERROR_MESSAGE ="Error while setting cart.";
	
	@Override
	public Cart getCart(User user) throws ServiceException {
	       
		DaoProvider provider = DaoProvider.getInstance();

        CartDao cartDAO = provider.getCartDao();

        try {
            return cartDAO.getCart(user);
        } catch (DaoException e) {
            throw new ServiceException(GETTING_ERROR_MESSAGE, e);
        }
	}
	
	@Override
	public boolean setCart(Cart cart) throws ServiceException {
	       
		DaoProvider provider = DaoProvider.getInstance();

        CartDao cartDAO = provider.getCartDao();

        try {
            return cartDAO.setCart(cart);
        } catch (DaoException e) {
            throw new ServiceException(SETTING_ERROR_MESSAGE, e);
        }
	}

}
