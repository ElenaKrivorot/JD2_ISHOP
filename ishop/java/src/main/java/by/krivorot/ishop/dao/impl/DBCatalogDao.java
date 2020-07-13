package by.krivorot.ishop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import by.krivorot.ishop.dao.CatalogDao;
import by.krivorot.ishop.dao.DaoException;
import by.krivorot.ishop.dao.DaoRuntimeException;
import by.krivorot.ishop.dao.con_pool.ConnectionPool;
import by.krivorot.ishop.dao.con_pool.ConnectionPoolException;
import by.krivorot.ishop.entity.Category;

public class DBCatalogDao implements CatalogDao {
	
	private static final Logger logger =  Logger.getLogger(DBCatalogDao.class);

	private static final String FIND_CATEGORIES = "SELECT * FROM product_category";
	private static final String FIND_SUBCATEGORIES_BY_CATEGORY_ID = "SELECT * FROM product_subcategory WHERE category_id=?";
	private static final String COLUMN_CATEGORY_ID = "category_id";
	private static final String COLUMN_SUBCATEGORY_ID = "subcategory_id";
	private static final String COLUMN_NAME = "name";
	private static final String ERROR_MESSAGE ="Wrong sql request to database.";

	@Override
	public List<Category> getCategories() throws DaoException {

		ConnectionPool cp = null;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			cp = ConnectionPool.getInstance();

			con = cp.takeConnection();
			ps = con.prepareStatement(FIND_CATEGORIES);

			rs = ps.executeQuery();

			if (!rs.next()) {
				return null;
			}

			List<Category> categories = new ArrayList<Category>();
			categories.add(new Category(rs.getInt(COLUMN_CATEGORY_ID), rs.getString(COLUMN_NAME)));
			
			while (rs.next()) {

				categories.add(new Category(rs.getInt(COLUMN_CATEGORY_ID), rs.getString(COLUMN_NAME)));
			}

			return categories;

		} catch (ConnectionPoolException e) {
			throw new DaoException(e);
		} catch (SQLException e) {			
			logger.log(Level.ERROR, ERROR_MESSAGE, e);
			throw new DaoRuntimeException(ERROR_MESSAGE, e);
		} finally {
			cp.closeConnection(con, ps, rs);
		}

	}

	@Override
	public List<Category> getSubcategories(int category_id) throws DaoException {

		ConnectionPool cp = null;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			cp = ConnectionPool.getInstance();

			con = cp.takeConnection();
			ps = con.prepareStatement(FIND_SUBCATEGORIES_BY_CATEGORY_ID);

			ps.setInt(1, category_id);

			rs = ps.executeQuery();

			if (!rs.next()) {
				return null;
			}

			List<Category> categories = new ArrayList<Category>();
			categories.add(new Category(rs.getInt(COLUMN_SUBCATEGORY_ID), rs.getString(COLUMN_NAME)));

			while (rs.next()) {

				categories.add(new Category(rs.getInt(COLUMN_SUBCATEGORY_ID), rs.getString(COLUMN_NAME)));
			}

			return categories;

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
