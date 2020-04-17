package com.icss.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

import com.icss.entity.Order;
import com.icss.entity.OrderDetail;
import com.icss.entity.User;

public class UserDao extends BaseDao{
	/**
	 * �˻���ֵ���߿ۿ�
	 * @param uname
	 * @param money ������ʾ��ֵ��������ʾ�ۿ�
	 * @throws Exception
	 */
	public void updataAccount(String uname, double money) throws Exception{
		String sql = "update tuser set account = account + ? where uname = ?";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setDouble(1, money);
		ps.setString(2, uname);
		ps.executeUpdate();
		ps.close();
	}
	/**
	 * ���ݿ�����Ӷ���
	 * @param order
	 * @throws Exception
	 */
	public void addOrder(Order order) throws Exception
	{
		String sql =  "insert into torder values (?,?,?,?)";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, order.getBuyid());
		ps.setString(2, order.getUname());
		ps.setDouble(3, order.getAllPay());
		ps.setTimestamp(4, new java.sql.Timestamp(order.getPayTime().getTime()));   //�˴�����ʹ��setTimestamp
		ps.executeUpdate();
		ps.close();
	}
	/**
	 * ���ݿ�����Ӷ�����ϸ
	 * @param detail
	 * @throws Exception
	 */
	public void addOrderDetail(OrderDetail detail) throws Exception
	{	
		// ��Ӷ�����ϸ
		String sql = "insert into torderdetail(buyid,isbn,price,num) values(?,?,?,?)";
		this.openConnection();
		PreparedStatement ps=this.conn.prepareStatement(sql);
		ps.setString(1, detail.getBuyid());
		ps.setString(2, detail.getIsbn());
		ps.setDouble(3, detail.getPrice());
		ps.setInt(4,detail.getNum());
		ps.executeUpdate();
		// �ۿ��
		BookDao bookDao = new BookDao();
		bookDao.setConn(this.getConn());   //�ѵ�ǰ�����Connection���ô��ݸ�bookDao����
		bookDao.updateBookNum(detail.getIsbn(), -detail.getNum());
	}
	/**
	 * �û���¼
	 * @param uname �û���
	 * @param pwd	����
	 * @return   �����ѵ�¼���û����󣨺���ɫ��Ϣ��
	 * @throws Exception  �쳣
	 */	
	public User login(String uname,String pwd) throws Exception {	
		User user = null;		
		
		this.openConnection();   //�����ݿ�
		String sql = "select * from tuser where uname=? and pwd=?";
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1,uname);   //ʹ�ô����uname�������滻sql�еĵ�һ����
		ps.setString(2, pwd);   //ʹ�ô����pwd�������滻sql�еĵڶ�����
		ResultSet rs = ps.executeQuery();   //С�ģ�java.sql.ResultSet,��Ҫ�����
		while(rs.next()) {
			//һ��һ�еı���ǰ���sql���Ĳ�ѯ���
			user = new User();
			user.setUname(rs.getString("uname"));  //ע�⣺С�����е�uname�����ݿ����е�����
			user.setPwd(rs.getString("pwd"));
			String role = rs.getString("role");			
			user.setRole(Integer.parseInt(role));
			user.setAccount(rs.getDouble("account"));
			user.setAddress(rs.getString("address"));
			user.setPhonenum(rs.getString("phonenum"));
			break;
		}
		//�ر���Դ
		rs.close();
		ps.close();
		//this.closeConnection();   ���ڴ˴��ر����ݿ�
		
		return user;		
	}
	
	/**
	 * �û�ע��
	 * @param user
	 * @throws RuntimeException
	 */
	public void regist(User user) throws SQLIntegrityConstraintViolationException,Exception {
		String sql = "insert into tuser values(?,?,?,?,?,?)";
		this.openConnection();  //�����ݿ�
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, user.getUname());
		ps.setString(2, user.getPwd());
		ps.setDouble(3, user.getAccount());
		ps.setInt(4, user.getRole());
		ps.setString(5, user.getAddress());
		ps.setString(6, user.getPhonenum());
		ps.executeUpdate();
		ps.close();		
		//throw new Exception("�쳣����...");
	}
	
	/**
	 * У���û��������ݿ����Ƿ����
	 * @param uname
	 * @return  true��ʾ�Ѵ���     false��ʾ������
	 * @throws Exception
	 */
	public boolean validUname(String uname ) throws Exception {		
		boolean bRet = false;
		
		String sql = "select uname from tuser where uname=?";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, uname);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			bRet = true;
		}
		rs.close();
		ps.close();
		
		return bRet;
	}

}
