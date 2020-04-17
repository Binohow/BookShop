package com.icss.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.icss.entity.Car;


public class CarDao extends BaseDao{
	/**
	 * �����ݿ���ع��ﳵ����
	 * @return ���ﳵʵ��
	 * @throws Exception
	 */	
public List<Car> getCar(String uname)  throws Exception {
	List<Car> car = new ArrayList<Car>();
	String sql = "select * from tcar where uname=?";
	this.openConnection();
	PreparedStatement ps = this.conn.prepareStatement(sql);
	ps.setString(1, uname);
	ResultSet rs = ps.executeQuery();
	while(rs.next()) {
		Car item = new Car();
		item.setIsbn(rs.getString("isbn"));
		item.setCount(rs.getInt("count"));
		car.add(item);
	}
	rs.close();
	ps.close();
	return car;
}
/**
 * ������û����ﳵ
 * @param uname
 */
public void clearCar(String uname) throws Exception{
	String sql = "DELETE FROM tcar WHERE uname = ?";
	this.openConnection();
	PreparedStatement ps = this.conn.prepareStatement(sql);
	ps.setString(1, uname);
	ps.executeUpdate();
	ps.close();
 }

public void insertCar(Map<String,Integer> shopcar, String uname)  throws Exception{
	String sql = "INSERT INTO tcar VALUES (?, ?, ?)";
	Set<String> isbns = shopcar.keySet();
	PreparedStatement ps = this.conn.prepareStatement(sql);
	this.openConnection();
	for(String isbn : isbns)
	{
		ps.setString(1, uname);
		ps.setString(2, isbn);
		ps.setInt(3, shopcar.get(isbn));
		ps.executeUpdate();
	}
	ps.close();
}

}
