package com.soule.data.dao;

import java.sql.Connection;
import java.sql.Statement;

import com.soule.comm.db.DBConnectionManager;
import com.soule.comm.db.DBConnectionPool;

public class BaseDao {
	
	private DBConnectionPool pool;
	private Connection connection = null;
	
	public BaseDao() {
		pool = DBConnectionManager.getInstance().getDefaultPool();
    }

    public BaseDao(DBConnectionPool pool) {
    	this.pool = pool;
    }
    
    public Connection getConnection(){
    	if(connection==null){
    		connection = pool.getConnection();
    	}
    	return this.connection;
    }    
    
    public void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }
    
    
}
