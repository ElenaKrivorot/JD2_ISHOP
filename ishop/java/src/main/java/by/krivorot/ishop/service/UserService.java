package by.krivorot.ishop.service;

import by.krivorot.ishop.entity.User;
import by.krivorot.ishop.entity.UserData;

public interface UserService {

	User signIn(String email, String password) throws ServiceException;

	boolean registration(String email, String password) throws ServiceException;

	UserData getUserData(int id) throws ServiceException;

	boolean updateUserData(UserData data, int id) throws ServiceException;
}
