package com.icss.biz;

import java.util.Date;
import java.util.List;

//import java.sql.SQLIntegrityConstraintViolationException;

import com.icss.dao.UserDao;
import com.icss.entity.Book;
import com.icss.entity.Order;
import com.icss.entity.OrderDetail;
import com.icss.entity.User;
import com.icss.exception.InputEmptyException;
import com.icss.util.Log;
import com.icss.util.OrderUtil;
import com.mysql.cj.x.protobuf.MysqlxCrud.OrderOrBuilder;

public class UserBiz {
	
	/**
	 * 生成订单以及订单明细
	 * @param uname
	 * @param allMoney
	 * @param books
	 */
	public void payMoney(User user, double allMoney, List<Book> books) throws Exception{
		// 入参校验
		if(user.getUname() == null || user.getUname().equals(""))
		{
			throw new Exception("");
		}
		if(allMoney<=0)
		{
			throw new Exception("");
		}
		if(books==null || books.size()==0)
		{
			throw new Exception("");
		}
		UserDao userdao = new UserDao();
		try {
			//账户扣款
			userdao.beginTransaction();  //开启事务
			userdao.updataAccount(user.getUname(), -allMoney);
			//生成订单
			Order order = new Order();
			String buyid = OrderUtil.createNewOrderNo();
			order.setAllPay(allMoney);
			order.setBuyid(buyid);
			order.setPayTime(new Date());
		    order.setUname(user.getUname());
		    userdao.addOrder(order);
			//生成订单明细
		    for(Book bk : books)			
		    {
		    	OrderDetail detail = new OrderDetail();
		    	detail.setBuyid(buyid);
		    	detail.setIsbn(bk.getIsbn());
		    	detail.setNum(bk.getNum());
		    	detail.setPrice(bk.getPrice()*bk.getDiscount());
		    	userdao.addOrderDetail(detail);
		    }
		    userdao.commit();                        //交易中没有错误，提交事务
		   } catch (Exception e) {
			userdao.rollback();                      //回滚事务
			Log.logger.error(e.getMessage(),e);
			throw e;
		   }finally {
			userdao.closeConnection();
		   }
		
		
	}
	/**
	 * 用户登录
	 * @param uname 用户名
	 * @param pwd	密码
	 * @return   返回已登录的用户对象（含角色信息）
	 * @throws Exception  异常
	 */	
	public User login(String uname,String pwd) throws InputEmptyException,Exception {	
		//1. 入参校验
		if(uname == null || pwd == null || uname.equals("") || pwd.equals("")) {
			throw new InputEmptyException("用户名或密码为空");
		}		
		//2.根据业务流程图，调用UserDao 
		UserDao dao = new UserDao();
		User user = dao.login(uname, pwd);
		
		dao.closeConnection();       //关闭数据库
		
		//3. 返回		
		return user;		
	}
	
	/**
	 * 用户注册
	 * @param user
	 * @throws RuntimeException
	 * SQLIntegrityConstraintViolationException,
	 */
	public void regist(User user) throws  Exception {
		
		if(user == null || user.getUname() == null || user.getUname().equals("")) {
		   throw new Exception("入参user或用户名为空");	
		}		
		//开启事务
		UserDao dao = new UserDao();
		try {
			dao.beginTransaction();  //开启事务
			dao.regist(user);
			dao.commit();            //提交事务
		} catch (Exception e) {
			Log.logger.info(e.getMessage(),e);
			dao.rollback();          //回滚事务
			throw e;                 //二次抛出异常
		}finally {
			dao.closeConnection();   //关闭数据库
		}	
	}
	
	/**
	 * 校验用户名在数据库中是否存在
	 * @param uname
	 * @return  true表示已存在     false表示不存在
	 * @throws Exception
	 */
	public boolean validUname(String uname ) throws Exception {
		UserDao dao = new UserDao();
		try {
			return dao.validUname(uname);
		} finally {
			dao.closeConnection();          //关闭数据库
		}		
	}

}
