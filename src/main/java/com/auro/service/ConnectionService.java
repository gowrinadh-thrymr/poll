package com.auro.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.auro.serviceimpl.ConnectionServiceImpl;

public interface ConnectionService {

	Connection getJNDIConnection() throws SQLException;
	
	ConnectionServiceImpl getInstance();
}
