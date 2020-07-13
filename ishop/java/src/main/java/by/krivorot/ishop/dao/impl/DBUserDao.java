package by.krivorot.ishop.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import by.krivorot.ishop.dao.DaoException;
import by.krivorot.ishop.dao.DaoRuntimeException;
import by.krivorot.ishop.entity.User;
import by.krivorot.ishop.entity.UserData;
import by.krivorot.ishop.dao.UserDao;
import by.krivorot.ishop.dao.bcrypt.BCrypt;
import by.krivorot.ishop.dao.con_pool.ConnectionPool;
import by.krivorot.ishop.dao.con_pool.ConnectionPoolException;

public class DBUserDao implements UserDao {
	
	private static final Logger logger =  Logger.getLogger(DBUserDao.class);
	
	private static final String FIND_USER_BY_EMAIL = "SELECT * FROM users WHERE email=?";	
	private static final String FIND_USER_BY_ID = "SELECT * FROM users WHERE user_id=?";
	private static final String ADD_USER = "INSERT INTO users(email,password,status,role_id,discount_id) VALUES(?,?,?,?,?)";
	private static final String UPDATE_USER = "UPDATE users SET name=?,surrname=?,gender=?,date_of_birth=?,telephone=? WHERE user_id=?";
	private static final String ADD_CART = "INSERT INTO basket(user_id) SELECT user_id FROM users WHERE email=?";
	private static final String COLUMN_ID = "user_id";
	private static final String COLUMN_NAME = "name";
	private static final String COLUMN_SURNAME = "surrname";
	private static final String COLUMN_PASSWORD = "password";
	private static final String COLUMN_GENDER = "gender";
	private static final String COLUMN_DATE_OF_BIRTH = "date_of_birth";
	private static final String COLUMN_TELEPHONE = "telephone";
	private static final String USER_STATUS_ACTIVE = "active";
	private static final String ERROR_MESSAGE ="Wrong sql request to database.";

	public User signIn(String email, String password) throws DaoException {

		ConnectionPool cp = null;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			cp = ConnectionPool.getInstance();

			con = cp.takeConnection();
			ps = con.prepareStatement(FIND_USER_BY_EMAIL);

			ps.setString(1, email);
						
			rs = ps.executeQuery();
			rs.next();
			
			if (!BCrypt.checkpw(password, rs.getString(COLUMN_PASSWORD))) {				
				return null;
			}

			User user = new User(rs.getInt(COLUMN_ID), rs.getString(COLUMN_NAME));
			return user;

		} catch (ConnectionPoolException e) {
			throw new DaoException(e);
		} catch (SQLException e) {		
			logger.log(Level.ERROR, ERROR_MESSAGE, e);
			throw new DaoRuntimeException(ERROR_MESSAGE, e);
		} finally {
			cp.closeConnection(con, ps, rs);
		}

	}

	
	public synchronized boolean registration(String email, String password) throws DaoException {

		if (!checkEmailUniqueness(email)) {
			return false;
		}
		
		ConnectionPool cp = null;

		Connection con = null;
		PreparedStatement ps = null;
		

		try {

			cp = ConnectionPool.getInstance();

			con = cp.takeConnection();
			ps = con.prepareStatement(ADD_USER);

			ps.setString(1, email);
			ps.setString(2, BCrypt.hashpw(password, BCrypt.gensalt()));				
			ps.setString(3, USER_STATUS_ACTIVE);
			ps.setInt(4, 2);
			ps.setInt(5, 1);

			ps.executeUpdate();
						
			ps = con.prepareStatement(ADD_CART);
			ps.setString(1, email);
			
			ps.executeUpdate();
			
			return true;

		} catch (ConnectionPoolException e) {			
			throw new DaoException(e);
		} catch (SQLException e) {			
			logger.log(Level.ERROR, ERROR_MESSAGE, e);
			throw new DaoRuntimeException(ERROR_MESSAGE, e);
		} finally {			
			cp.closeConnection(con, ps);
		}

	}

	public boolean checkEmailUniqueness(String email) throws DaoException {
		ConnectionPool cp = null;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			cp = ConnectionPool.getInstance();

			con = cp.takeConnection();
			ps = con.prepareStatement(FIND_USER_BY_EMAIL);

			ps.setString(1, email);

			rs = ps.executeQuery();

			if (rs.next()) {
				return false;
			}

			return true;

		} catch (ConnectionPoolException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, ERROR_MESSAGE, e);
			throw new DaoRuntimeException(ERROR_MESSAGE, e);
		} finally {
			cp.closeConnection(con, ps, rs);
		}
	}
	
	public UserData getUserData(int id) throws DaoException {

		ConnectionPool cp = null;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			cp = ConnectionPool.getInstance();

			con = cp.takeConnection();
			ps = con.prepareStatement(FIND_USER_BY_ID);

			ps.setInt(1, id);
						
			rs = ps.executeQuery();
			rs.next();
			
			UserData data = new UserData(rs.getString(COLUMN_NAME),rs.getString(COLUMN_SURNAME),rs.getString(COLUMN_GENDER),rs.getDate(COLUMN_DATE_OF_BIRTH).toLocalDate(),rs.getString(COLUMN_TELEPHONE));
			
			return data;
			
		} catch (ConnectionPoolException e) {
			throw new DaoException(e);
		} catch (SQLException e) {				
			logger.log(Level.ERROR, ERROR_MESSAGE, e);
			throw new DaoRuntimeException(ERROR_MESSAGE, e);
		} finally {
			cp.closeConnection(con, ps, rs);
		}

	}
	
	public boolean updateUserData(UserData data, int id) throws DaoException {

		ConnectionPool cp = null;

		Connection con = null;
		PreparedStatement ps = null;

		try {

			cp = ConnectionPool.getInstance();

			con = cp.takeConnection();
			ps = con.prepareStatement(UPDATE_USER);

			ps.setString(1, data.getName());
			ps.setString(2, data.getSurname());				
			ps.setString(3, data.getGender());			
			ps.setDate(4, Date.valueOf(data.getDateOfBirth().plusDays(1)));			
			ps.setString(5, data.getTelephone());
			ps.setInt(6, id);

			ps.executeUpdate();
							
			return true;

		} catch (ConnectionPoolException e) {			
			throw new DaoException(e);
		} catch (SQLException e) {			
			logger.log(Level.ERROR, ERROR_MESSAGE, e);
			throw new DaoRuntimeException(ERROR_MESSAGE, e);
		} finally {
			cp.closeConnection(con, ps);
		}

	}

}
