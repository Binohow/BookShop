package com.icss.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.icss.util.Log;

/**
 * �����࣬��װ���ݿ�Ĺ��Բ���
 * @author Domino
 *
 */
public abstract class BaseDao {
	protected Connection conn;   //�˴�һ��Ҫʹ��protected�ؼ���
	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	
	
	public void openConnection() throws ClassNotFoundException,SQLException{
		try {
			//�����ݿ� ,��¼�������ݿ��connection 
			if(this.conn == null || this.conn.isClosed()) {
				Class.forName("com.mysql.cj.jdbc.Driver");      //��������
				String url = "jdbc:mysql://47.95.200.95:3306/teambk?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
				String username = "binhow";
				String password = "123456";
				conn = DriverManager.getConnection(url, username, password);
				Log.logger.info("�����ݿ�����.....");	
			}else {
				Log.logger.info("�����Ѿ��򿪵����ݿ�����.....");
			}			
		}catch (ClassNotFoundException e) {
		    Log.logger.error("������ݿ��������Ƿ����", e);
		    throw e;
		}catch (SQLException e) {
			 Log.logger.error("������ݿ�����Ӵ��Ƿ���ȷ", e);
			 throw e;
		}
	}
	
	/**
	 * ��������
	 * @throws Exception
	 */
	public void beginTransaction() throws Exception{
		this.openConnection();
		this.conn.setAutoCommit(false);  //Ĭ�����Զ��ύģʽ��false��ʾ�յ��ύ����
		Log.logger.info("��������...");
	}
	
	/**
	 * �ύ����
	 * @throws Exception
	 */
	public void commit()throws Exception {
		if(this.conn != null) {
			this.conn.commit();
			Log.logger.info("�ύ����...");
		}
	}
	
	/**
	 * �ع�����
	 * @throws Exception
	 */
	public void rollback() throws Exception{
		if(this.conn != null) {
			this.conn.rollback();
			Log.logger.info("�ع�����...");
		}
	}
	
	
	public void closeConnection() {
		if(this.conn != null) {
			try {
				this.conn.close();
				Log.logger.info("�ر����ݿ�����...");
			} catch (Exception e) {
				Log.logger.error(e.getMessage(),e);
			}			
		}		
	}

}
