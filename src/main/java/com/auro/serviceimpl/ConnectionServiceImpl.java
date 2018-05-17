package com.auro.serviceimpl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import com.auro.service.ConnectionService;

@Service
public class ConnectionServiceImpl implements ConnectionService {

	protected static DataSource ds;

	@Override
	public Connection getJNDIConnection() throws SQLException {
		try {
			InitialContext ic = new InitialContext();
			ds = (DataSource) ic.lookup("jndi/aurods");
		} catch (NamingException e) {
			e.printStackTrace();
			System.out.println(e);
		}

		return ds.getConnection();
	}

	@Override
	public ConnectionServiceImpl getInstance() {
		return null;
	}

}
