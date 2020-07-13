package by.krivorot.ishop.service.impl;

import by.krivorot.ishop.dao.DaoException;
import by.krivorot.ishop.dao.DaoProvider;
import by.krivorot.ishop.dao.UserDao;
import by.krivorot.ishop.entity.User;
import by.krivorot.ishop.entity.UserData;
import by.krivorot.ishop.service.ServiceException;
import by.krivorot.ishop.service.UserService;

public class UserServiceImpl implements UserService{
	
	private static final String SIGN_IN_ERROR_MESSAGE ="Error while signIn User.";
	private static final String REGISTRATION_ERROR_MESSAGE ="Error while registration User.";
	private static final String GETTING_USER_DATA_ERROR_MESSAGE ="Error while getting user data";
	
	public User signIn(String email, String password) throws ServiceException {
       
		DaoProvider provider = DaoProvider.getInstance();

        UserDao userDAO = provider.getUserDao();

        try {
            return userDAO.signIn(email, password);
        } catch (DaoException e) {
            throw new ServiceException(SIGN_IN_ERROR_MESSAGE, e);
        }
	}
	
	public boolean registration(String email, String password) throws ServiceException {
	       
		DaoProvider provider = DaoProvider.getInstance();

        UserDao userDAO = provider.getUserDao();

        try {
            return userDAO.registration(email, password);
        } catch (DaoException e) {
            throw new ServiceException(REGISTRATION_ERROR_MESSAGE, e);
        }
	}
	
	public UserData getUserData(int id) throws ServiceException {
	       
		DaoProvider provider = DaoProvider.getInstance();

        UserDao userDAO = provider.getUserDao();

        try {
            return userDAO.getUserData(id);
        } catch (DaoException e) {
            throw new ServiceException("Error while getting user data", e);
        }
	}

	@Override
	public boolean updateUserData(UserData data, int id) throws ServiceException {
		DaoProvider provider = DaoProvider.getInstance();

        UserDao userDAO = provider.getUserDao();

        try {
            return userDAO.updateUserData(data, id);
        } catch (DaoException e) {
            throw new ServiceException(GETTING_USER_DATA_ERROR_MESSAGE, e);
        }
	}
}
