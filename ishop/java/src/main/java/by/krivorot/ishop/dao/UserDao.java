package by.krivorot.ishop.dao;

import by.krivorot.ishop.entity.User;
import by.krivorot.ishop.entity.UserData;

public interface UserDao {
	User signIn(String email, String password) throws DaoException;
	boolean registration(String email, String password) throws DaoException;
	UserData getUserData(int id) throws DaoException;
	boolean updateUserData(UserData data, int id) throws DaoException;
}
