package com.icss.biz;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.icss.dao.BookDao;
import com.icss.entity.Book;

public class BookBiz {
	/**
	 * 添加图书到数据库
	 * @param book
	 * @throws Exception
	 */
	public void addBook(Book book)throws Exception {
		BookDao dao = new BookDao();
		try {
			dao.beginTransaction();
			dao.addBook(book);
			dao.commit();
		} finally {
			dao.rollback();
			dao.closeConnection();
		}		
	}
	
	/**
	 * 读取主页图书数据
	 * @return
	 * @throws Exception
	 */
	public List<Book> getMainBooks() throws Exception{
		BookDao dao = new BookDao();
		try {
		  return dao.getMainBooks();	
		} finally {
			dao.closeConnection();
		}		
	}
	
	/**
	 * 读取图书信息
	 * @param isbn
	 * @return
	 * @throws Exception
	 */
	public Book getBookInfo(String isbn) throws Exception{
		if(isbn == null) {
			throw new Exception("入参isbn为空...");
		}
		BookDao dao = new BookDao();
		try {
			return dao.getBookInfo(isbn);
		} finally {
			dao.closeConnection();
		}		
	}
	/**
	 * 购物车数据提取
	 * @param isbns
	 * @return
	 * @throws Exception
	 */
	public List<Book> getBooks(Set<String> isbns) throws Exception {
		BookDao dao = new BookDao();
		List<Book> books = new ArrayList<Book>();
		try {
            if(isbns==null||isbns.size()>0)
            {
            	books = dao.getBooks(isbns);
            }
		} finally {
			dao.closeConnection();
		}
		return books;
	}
	
	public List<Book> searchBook(String name) throws Exception{
		if(name == null) {
			throw new Exception("输入的书名为空...");
		}
		BookDao dao = new BookDao();
		try {
			return dao.searchBook(name);
		} finally {
			dao.closeConnection();
		}
	}
}
