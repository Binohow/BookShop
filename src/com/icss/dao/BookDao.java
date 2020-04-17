package com.icss.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.icss.entity.Book;
import com.sun.org.apache.regexp.internal.recompile;

public class BookDao extends BaseDao{
	/**
	 * 添加图书到数据库
	 * @param book
	 * @throws Exception
	 */
	public void addBook(Book book)throws Exception {
		String sql = "insert into tbook values(?,?,?,?,?,?,?,?,?,?)";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, book.getIsbn());
		ps.setString(2, book.getBname());
		ps.setString(3, book.getAuthor());
		ps.setString(4, book.getPress());
		if(book.getPdate() != null) {
			ps.setDate(5, new java.sql.Date(book.getPdate().getTime()));	
		}else {
			ps.setDate(5,null);
		}		
		ps.setDouble(6, book.getPrice());
		ps.setString(7, book.getPic());
		ps.setString(8, book.getInfo());
		ps.setInt(9, book.getNum());
		ps.setDouble(10, book.getDiscount());
		ps.executeUpdate();
		ps.close();
	}
	/**
	 * 增加库存，或减少库存
	 * @param isbn
	 * @param num   正数表示增加库存，负数表示减少库存
	 * @throws Exception
	 */
	public void updateBookNum(String isbn, int num) throws Exception{
		String sql = "update tbook set num=num+? where isbn=?";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setInt(1, num);
		ps.setString(2, isbn);
		ps.executeUpdate();
		ps.close();
		
	}
	/**
	 * 读取图书信息
	 * @param isbn
	 * @return
	 * @throws Exception
	 */
	public Book getBookInfo(String isbn) throws Exception{
		Book bk = null;
		
		String sql = "select * from tbook where isbn=?";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, isbn);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			bk = new Book();
			bk.setIsbn(rs.getString("isbn"));
			bk.setAuthor(rs.getString("author"));
			bk.setBname(rs.getString("bname"));
			bk.setInfo(rs.getString("info"));
			bk.setPdate(rs.getDate("pdate"));
			bk.setPic(rs.getString("pic"));
			bk.setPress(rs.getString("press"));
			bk.setPrice(rs.getDouble("price"));		
			bk.setDiscount(rs.getDouble("discount"));
			break;
		}
		rs.close();
		ps.close();
		
		return bk;
	}


	/**
	 * 读取主页图书数据
	 * @return
	 * @throws Exception
	 */
	public List<Book> getMainBooks() throws Exception{
		List<Book> books;
		
		String sql = "select * from tbook";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		books = new ArrayList<>();             //一定要创建对象
		while(rs.next()) {
			Book bk = new Book();
			bk.setIsbn(rs.getString("isbn"));
			bk.setAuthor(rs.getString("author"));
			bk.setBname(rs.getString("bname"));
			bk.setInfo(rs.getString("info"));
			bk.setPdate(rs.getDate("pdate"));
			bk.setPic(rs.getString("pic"));
			bk.setPress(rs.getString("press"));
			bk.setPrice(rs.getDouble("price"));			
			books.add(bk);
		}
		rs.close();
		ps.close();
		
		return books;
		
	}
/**
 * 利用in语句返回购物车信息
 * @param isbns
 * @return
 * @throws Exception
 */
	public List<Book> getBooks(Set<String> isbns) throws Exception {
		List<Book> books = new ArrayList<Book>();
		String strIsbn = "";
		int i=0;
		for(String isbn : isbns) {
			if(i==0) {
				strIsbn = "'" + isbn + "'";
			}else {
				strIsbn = strIsbn + ",'" + isbn + "'";
			}
			i++;			
		}
		String sql = "select * from tbook where isbn in (" + strIsbn + ")";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery(sql);
		while(rs.next())
		{
			Book bk = new Book();
			bk.setIsbn(rs.getString("isbn"));
			bk.setAuthor(rs.getString("author"));
			bk.setBname(rs.getString("bname"));
			bk.setInfo(rs.getString("info"));
			bk.setPdate(rs.getDate("pdate"));
			bk.setPic(rs.getString("pic"));
			bk.setPress(rs.getString("press"));
			bk.setPrice(rs.getDouble("price"));			
			books.add(bk);
		}
		rs.close();
		ps.close();
		
		return books;
	}
	public List<Book> searchBook(String bname) throws Exception{
		List<Book> books;
		books = new ArrayList<>();
		String sql = "select * from tbook where bname like ?";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, "%"+bname+"%");
		ResultSet rs = ps.executeQuery();
		Book bk = null;
		while(rs.next()) {
			bk = new Book();
			bk.setIsbn(rs.getString("isbn"));
			bk.setAuthor(rs.getString("author"));
			bk.setBname(rs.getString("bname"));
			bk.setInfo(rs.getString("info"));
			bk.setPdate(rs.getDate("pdate"));
			bk.setPic(rs.getString("pic"));
			bk.setPress(rs.getString("press"));
			bk.setPrice(rs.getDouble("price"));
			books.add(bk);
		}
		rs.close();
		ps.close();

		return books;
	}
}
