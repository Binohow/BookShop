package com.icss.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.icss.util.Log;

/**
 * 抽象父类，封装数据库的共性操作
 * @author Domino
 *
 */
public abstract class BaseDao {
	protected Connection conn;   //此处一定要使用protected关键字
	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	
	
	public void openConnection() throws ClassNotFoundException,SQLException{
		try {
			//打开数据库 ,记录重用数据库的connection 
			if(this.conn == null || this.conn.isClosed()) {
				Class.forName("com.mysql.cj.jdbc.Driver");      //加载驱动
				String url = "jdbc:mysql://47.95.200.95:3306/teambk?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
				String username = "binhow";
				String password = "123456";
				conn = DriverManager.getConnection(url, username, password);
				Log.logger.info("打开数据库连接.....");	
			}else {
				Log.logger.info("重用已经打开的数据库连接.....");
			}			
		}catch (ClassNotFoundException e) {
		    Log.logger.error("检查数据库驱动包是否存在", e);
		    throw e;
		}catch (SQLException e) {
			 Log.logger.error("检查数据库的连接串是否正确", e);
			 throw e;
		}
	}
	
	/**
	 * 开启事务
	 * @throws Exception
	 */
	public void beginTransaction() throws Exception{
		this.openConnection();
		this.conn.setAutoCommit(false);  //默认是自动提交模式，false表示收到提交事务
		Log.logger.info("开启事务...");
	}
	
	/**
	 * 提交事务
	 * @throws Exception
	 */
	public void commit()throws Exception {
		if(this.conn != null) {
			this.conn.commit();
			Log.logger.info("提交事务...");
		}
	}
	
	/**
	 * 回滚事务
	 * @throws Exception
	 */
	public void rollback() throws Exception{
		if(this.conn != null) {
			this.conn.rollback();
			Log.logger.info("回滚事务...");
		}
	}
	
	
	public void closeConnection() {
		if(this.conn != null) {
			try {
				this.conn.close();
				Log.logger.info("关闭数据库连接...");
			} catch (Exception e) {
				Log.logger.error(e.getMessage(),e);
			}			
		}		
	}

}
