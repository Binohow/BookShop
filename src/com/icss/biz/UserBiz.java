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
	 * ���ɶ����Լ�������ϸ
	 * @param uname
	 * @param allMoney
	 * @param books
	 */
	public void payMoney(User user, double allMoney, List<Book> books) throws Exception{
		// ���У��
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
			//�˻��ۿ�
			userdao.beginTransaction();  //��������
			userdao.updataAccount(user.getUname(), -allMoney);
			//���ɶ���
			Order order = new Order();
			String buyid = OrderUtil.createNewOrderNo();
			order.setAllPay(allMoney);
			order.setBuyid(buyid);
			order.setPayTime(new Date());
		    order.setUname(user.getUname());
		    userdao.addOrder(order);
			//���ɶ�����ϸ
		    for(Book bk : books)			
		    {
		    	OrderDetail detail = new OrderDetail();
		    	detail.setBuyid(buyid);
		    	detail.setIsbn(bk.getIsbn());
		    	detail.setNum(bk.getNum());
		    	detail.setPrice(bk.getPrice()*bk.getDiscount());
		    	userdao.addOrderDetail(detail);
		    }
		    userdao.commit();                        //������û�д����ύ����
		   } catch (Exception e) {
			userdao.rollback();                      //�ع�����
			Log.logger.error(e.getMessage(),e);
			throw e;
		   }finally {
			userdao.closeConnection();
		   }
		
		
	}
	/**
	 * �û���¼
	 * @param uname �û���
	 * @param pwd	����
	 * @return   �����ѵ�¼���û����󣨺���ɫ��Ϣ��
	 * @throws Exception  �쳣
	 */	
	public User login(String uname,String pwd) throws InputEmptyException,Exception {	
		//1. ���У��
		if(uname == null || pwd == null || uname.equals("") || pwd.equals("")) {
			throw new InputEmptyException("�û���������Ϊ��");
		}		
		//2.����ҵ������ͼ������UserDao 
		UserDao dao = new UserDao();
		User user = dao.login(uname, pwd);
		
		dao.closeConnection();       //�ر����ݿ�
		
		//3. ����		
		return user;		
	}
	
	/**
	 * �û�ע��
	 * @param user
	 * @throws RuntimeException
	 * SQLIntegrityConstraintViolationException,
	 */
	public void regist(User user) throws  Exception {
		
		if(user == null || user.getUname() == null || user.getUname().equals("")) {
		   throw new Exception("���user���û���Ϊ��");	
		}		
		//��������
		UserDao dao = new UserDao();
		try {
			dao.beginTransaction();  //��������
			dao.regist(user);
			dao.commit();            //�ύ����
		} catch (Exception e) {
			Log.logger.info(e.getMessage(),e);
			dao.rollback();          //�ع�����
			throw e;                 //�����׳��쳣
		}finally {
			dao.closeConnection();   //�ر����ݿ�
		}	
	}
	
	/**
	 * У���û��������ݿ����Ƿ����
	 * @param uname
	 * @return  true��ʾ�Ѵ���     false��ʾ������
	 * @throws Exception
	 */
	public boolean validUname(String uname ) throws Exception {
		UserDao dao = new UserDao();
		try {
			return dao.validUname(uname);
		} finally {
			dao.closeConnection();          //�ر����ݿ�
		}		
	}

}
