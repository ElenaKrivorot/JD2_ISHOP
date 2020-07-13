package by.krivorot.ishop.controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import by.krivorot.ishop.dao.con_pool.ConnectionPool;


public class ContextListener implements ServletContextListener {
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
				
		ConnectionPool.getInstance().dispose();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		ConnectionPool.getInstance();
	}

}
