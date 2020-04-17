package com.icss.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

import com.icss.entity.Order;
import com.icss.entity.OrderDetail;
import com.icss.entity.User;

public class UserDao extends BaseDao{
	/**
	 * 账户充值或者扣款
	 * @param uname
	 * @param money 正数表示充值，负数表示扣款
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
	 * 数据库中添加订单
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
		ps.setTimestamp(4, new java.sql.Timestamp(order.getPayTime().getTime()));   //此处必须使用setTimestamp
		ps.executeUpdate();
		ps.close();
	}
	/**
	 * 数据库中添加订单明细
	 * @param detail
	 * @throws Exception
	 */
	public void addOrderDetail(OrderDetail detail) throws Exception
	{	
		// 添加订单明细
		String sql = "insert into torderdetail(buyid,isbn,price,num) values(?,?,?,?)";
		this.openConnection();
		PreparedStatement ps=this.conn.prepareStatement(sql);
		ps.setString(1, detail.getBuyid());
		ps.setString(2, detail.getIsbn());
		ps.setDouble(3, detail.getPrice());
		ps.setInt(4,detail.getNum());
		ps.executeUpdate();
		// 扣库存
		BookDao bookDao = new BookDao();
		bookDao.setConn(this.getConn());   //把当前对象的Connection引用传递给bookDao对象
		bookDao.updateBookNum(detail.getIsbn(), -detail.getNum());
	}
	/**
	 * 用户登录
	 * @param uname 用户名
	 * @param pwd	密码
	 * @return   返回已登录的用户对象（含角色信息）
	 * @throws Exception  异常
	 */	
	public User login(String uname,String pwd) throws Exception {	
		User user = null;		
		
		this.openConnection();   //打开数据库
		String sql = "select * from tuser where uname=? and pwd=?";
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1,uname);   //使用传入的uname变量，替换sql中的第一个？
		ps.setString(2, pwd);   //使用传入的pwd变量，替换sql中的第二个？
		ResultSet rs = ps.executeQuery();   //小心，java.sql.ResultSet,不要导错包
		while(rs.next()) {
			//一行一行的遍历前面的sql语句的查询结果
			user = new User();
			user.setUname(rs.getString("uname"));  //注意：小括号中的uname是数据库中列的名字
			user.setPwd(rs.getString("pwd"));
			String role = rs.getString("role");			
			user.setRole(Integer.parseInt(role));
			user.setAccount(rs.getDouble("account"));
			user.setAddress(rs.getString("address"));
			user.setPhonenum(rs.getString("phonenum"));
			break;
		}
		//关闭资源
		rs.close();
		ps.close();
		//this.closeConnection();   不在此处关闭数据库
		
		return user;		
	}
	
	/**
	 * 用户注册
	 * @param user
	 * @throws RuntimeException
	 */
	public void regist(User user) throws SQLIntegrityConstraintViolationException,Exception {
		String sql = "insert into tuser values(?,?,?,?,?,?)";
		this.openConnection();  //打开数据库
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, user.getUname());
		ps.setString(2, user.getPwd());
		ps.setDouble(3, user.getAccount());
		ps.setInt(4, user.getRole());
		ps.setString(5, user.getAddress());
		ps.setString(6, user.getPhonenum());
		ps.executeUpdate();
		ps.close();		
		//throw new Exception("异常测试...");
	}
	
	/**
	 * 校验用户名在数据库中是否存在
	 * @param uname
	 * @return  true表示已存在     false表示不存在
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
