package by.krivorot.ishop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import by.krivorot.ishop.dao.DaoException;
import by.krivorot.ishop.dao.DaoRuntimeException;
import by.krivorot.ishop.dao.ProductDao;
import by.krivorot.ishop.dao.con_pool.ConnectionPool;
import by.krivorot.ishop.dao.con_pool.ConnectionPoolException;
import by.krivorot.ishop.entity.FullProduct;
import by.krivorot.ishop.entity.ShortProduct;

public class DBProductDao implements ProductDao {
	
	private static final Logger logger =  Logger.getLogger(DBProductDao.class);

	private static final String FIND_BY_PRODUCT_ID = "SELECT * FROM products WHERE product_id=?";
	private static final String FIND_BY_SUBCATEGORY_ID = "SELECT * FROM products WHERE subcategory_id=?";
	private static final String COLUMN_PRODUCT_ID ="product_id";
	private static final String COLUMN_NAME ="name";
	private static final String COLUMN_AUTHOR ="author";
	private static final String COLUMN_PRICE ="price";
	private static final String COLUMN_AMOUNT ="amount";
	private static final String COLUMN_IMAGE ="image";
	private static final String COLUMN_ANNOTATION ="annotation";
	private static final String COLUMN_YEAR ="year";
	private static final String COLUMN_PAGES ="pages";
	private static final String ERROR_MESSAGE ="Wrong sql request to database.";

	public ShortProduct getShortProduct(int product_id) throws DaoException {

		ConnectionPool cp = null;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			cp = ConnectionPool.getInstance();

			con = cp.takeConnection();
			ps = con.prepareStatement(FIND_BY_PRODUCT_ID);

			ps.setInt(1, product_id);

			rs = ps.executeQuery();

			if (!rs.next()) {
				return null;
			}

			ShortProduct shortProduct = new ShortProduct(rs.getInt(COLUMN_PRODUCT_ID), rs.getString(COLUMN_NAME),
					rs.getString(COLUMN_AUTHOR), rs.getInt(COLUMN_PRICE), rs.getInt(COLUMN_AMOUNT), rs.getString(COLUMN_IMAGE));

			return shortProduct;

		} catch (ConnectionPoolException e) {
			throw new DaoException(e);
		} catch (SQLException e) {			
			logger.log(Level.ERROR, ERROR_MESSAGE, e);
			throw new DaoRuntimeException(ERROR_MESSAGE, e);
		} finally {
			cp.closeConnection(con, ps, rs);
		}
	}

	public FullProduct getFullProduct(int product_id) throws DaoException {

		ConnectionPool cp = null;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			cp = ConnectionPool.getInstance();

			con = cp.takeConnection();
			ps = con.prepareStatement(FIND_BY_PRODUCT_ID);

			ps.setInt(1, product_id);

			rs = ps.executeQuery();

			if (!rs.next()) {
				return null;
			}

			FullProduct fullProduct = new FullProduct(rs.getInt(COLUMN_PRODUCT_ID), rs.getString(COLUMN_NAME),
					rs.getString(COLUMN_AUTHOR), rs.getInt(COLUMN_PRICE), rs.getInt(COLUMN_AMOUNT), rs.getString(COLUMN_IMAGE),
					rs.getString(COLUMN_ANNOTATION), rs.getInt(COLUMN_YEAR), rs.getInt(COLUMN_PAGES));

			return fullProduct;

		} catch (ConnectionPoolException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, ERROR_MESSAGE, e);
			throw new DaoRuntimeException(ERROR_MESSAGE, e);
		} finally {
			cp.closeConnection(con, ps, rs);
		}
	}

	public List<ShortProduct> findProductsBySubcategory(int subcategory_id) throws DaoException {

		ConnectionPool cp = null;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			cp = ConnectionPool.getInstance();

			con = cp.takeConnection();
			ps = con.prepareStatement(FIND_BY_SUBCATEGORY_ID);

			ps.setInt(1, subcategory_id);

			rs = ps.executeQuery();

			if (!rs.next()) {
				return null;
			}

			List<ShortProduct> products = new ArrayList<ShortProduct>();

			products.add(new ShortProduct(rs.getInt(COLUMN_PRODUCT_ID), rs.getString(COLUMN_NAME), rs.getString(COLUMN_AUTHOR),
					rs.getInt(COLUMN_PRICE), rs.getInt(COLUMN_AMOUNT), rs.getString(COLUMN_IMAGE)));

			while (rs.next()) {
				products.add(new ShortProduct(rs.getInt(COLUMN_PRODUCT_ID), rs.getString(COLUMN_NAME), rs.getString(COLUMN_AUTHOR),
						rs.getInt(COLUMN_PRICE), rs.getInt(COLUMN_AMOUNT), rs.getString(COLUMN_IMAGE)));
			}
			
			return products;

		} catch (ConnectionPoolException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, ERROR_MESSAGE, e);
			throw new DaoRuntimeException(ERROR_MESSAGE, e);
		} finally {
			cp.closeConnection(con, ps, rs);
		}
	}

}
