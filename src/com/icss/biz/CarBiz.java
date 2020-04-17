package com.icss.biz;
import java.util.List;
import java.util.Map;

import com.icss.dao.CarDao;
import com.icss.entity.Car;
public class CarBiz  {
	/**
	 * 从数据库加载购物车数据
	 * @return 购物车实体
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
	 * 清除该用户购物车
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
