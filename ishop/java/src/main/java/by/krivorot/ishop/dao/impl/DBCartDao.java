package by.krivorot.ishop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import by.krivorot.ishop.dao.CartDao;
import by.krivorot.ishop.dao.DaoException;
import by.krivorot.ishop.dao.DaoRuntimeException;
import by.krivorot.ishop.dao.con_pool.ConnectionPool;
import by.krivorot.ishop.dao.con_pool.ConnectionPoolException;
import by.krivorot.ishop.entity.Cart;
import by.krivorot.ishop.entity.User;

public class DBCartDao implements CartDao {
	
	private static final Logger logger =  Logger.getLogger(DBCartDao.class);

	private static final String FIND_BY_BASKET_ID = "SELECT * FROM basket_has_products WHERE basket_id= (SELECT basket_id FROM basket WHERE user_id=?)";
	private static final String DELETE_BY_BASKET_ID = "DELETE FROM basket_has_products WHERE basket_id=?";
	private static final String ADD_BY_BASKET_ID = "INSERT INTO basket_has_products(basket_id,product_id) VALUES(?,?)";
	private static final String COLUMN_PRODUCT_ID ="product_id";
	private static final String COLUMN_CART_ID ="basket_id";
	private static final String ERROR_MESSAGE ="Wrong sql request to database.";
	
	

	public Cart getCart(User user) throws DaoException {

		ConnectionPool cp = null;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			cp = ConnectionPool.getInstance();

			con = cp.takeConnection();
			ps = con.prepareStatement(FIND_BY_BASKET_ID);

			ps.setInt(1, user.getId());

			rs = ps.executeQuery();

			if (!rs.next()) {
				return null;
			}

			Cart cart = new Cart(rs.getInt(COLUMN_CART_ID));
			cart.setProducts(rs.getInt(COLUMN_PRODUCT_ID));

			while (rs.next()) {

				cart.setProducts(rs.getInt(COLUMN_PRODUCT_ID));

			}

			return cart;

		} catch (ConnectionPoolException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, ERROR_MESSAGE, e);
			throw new DaoRuntimeException(ERROR_MESSAGE, e);
		} finally {
			cp.closeConnection(con, ps, rs);
		}

	}

	public boolean setCart(Cart cart) throws DaoException {

		ConnectionPool cp = null;

		Connection con = null;
		PreparedStatement ps = null;	
		
		try {
			cp = ConnectionPool.getInstance();

			con = cp.takeConnection();
			ps = con.prepareStatement(DELETE_BY_BASKET_ID);

			ps.setInt(1, cart.getId());

			ps.executeUpdate();

			Iterator<Integer> iterator = cart.getProducts().iterator();

			while (iterator.hasNext()) {

				ps = con.prepareStatement(ADD_BY_BASKET_ID);

				ps.setInt(1, cart.getId());
				ps.setInt(2, iterator.next());

				ps.executeUpdate();
			}

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
