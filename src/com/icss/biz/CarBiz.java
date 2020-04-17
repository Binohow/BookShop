package com.icss.biz;
import java.util.List;
import java.util.Map;

import com.icss.dao.CarDao;
import com.icss.entity.Car;
public class CarBiz  {
	/**
	 * �����ݿ���ع��ﳵ����
	 * @return ���ﳵʵ��
	 * @throws Exception
	 */
	public List<Car> getCar(String uname)throws Exception{
		CarDao dao = new CarDao();
		try 
		{
		  List<Car> car = dao.getCar(uname);
		  return car;
		}finally
		{	
		  dao.closeConnection();
		}
	}
	/**
	 * ������û����ﳵ
	 * @param uname
	 */
	public void clearCar(String uname) throws Exception {
		CarDao dao = new CarDao();
		try 
		{
		  dao.beginTransaction();
		  dao.clearCar(uname);
		  dao.commit();
		}finally
		{	
		  dao.rollback();
		  dao.closeConnection();
		}
	}
	
	public void insertCar(Map<String,Integer> shopcar, String uname) throws Exception {
		CarDao dao = new CarDao();
		try 
		{
		  dao.beginTransaction();
		  dao.insertCar(shopcar, uname);
		  dao.commit();
		}finally
		{	
		  dao.rollback();
		  dao.closeConnection();
		}
	}
}
