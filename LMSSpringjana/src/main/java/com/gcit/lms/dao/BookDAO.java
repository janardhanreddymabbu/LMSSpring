package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Templates;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Genre;
import com.gcit.lms.domain.LibraryBranch;
import com.gcit.lms.domain.Publisher;
import com.mysql.jdbc.Statement;

public class BookDAO extends BaseDAO implements ResultSetExtractor<List<Book>>  {

	public List<Book> readAll() throws ClassNotFoundException, SQLException {
		return template.query("select * from tbl_book", this);
	}

	public Integer getCount() throws ClassNotFoundException, SQLException {
		return template.queryForObject("select count(*) as count from tbl_book", Integer.class);
	}

	public List<Book> readAll(int pageNo) throws ClassNotFoundException, SQLException {
		setPageNo(pageNo);
		return template.query("select * from tbl_book limit "+pageNo+","+getPageSize(), this);
	}

	public List<Book> readAllnotinbookcopies(int branchId) throws ClassNotFoundException, SQLException {
		return template.query(
				"select * from tbl_book where bookId not  in (Select bookId from tbl_book_copies where branchId =? )",
				new Object[] { branchId }, this);
	}

//	public List<Book> searchAll(String name) throws ClassNotFoundException, SQLException {
//		setPageNo(getCount());
//		return template.query("select * from tbl_book where title LIKE " + "'%" + name + "%'", new Object[] { name },
//				new ResultSetExtractor<List<Book>>() {
//
//					List<Book> books = new ArrayList<>();
//
//					@Override
//					public List<Book> extractData(ResultSet rs) throws SQLException, DataAccessException {
//						while (rs.next()) {
//							Book book = new Book();
//							book.setTitle(rs.getString("title"));
//
//						}
//
//						return books;
//					}
//
//				});
//		
		
		public List<Book> searchAll(String name) throws ClassNotFoundException, SQLException {
			
			return template.query("select * from tbl_book where title LIKE " + "'%" + name + "%'", this);
	}

	public void deleteBook(Book book) throws ClassNotFoundException, SQLException {
		template.update("delete from tbl_book where bookId=?", new Object[] { book.getBookId() });
	}

	public void insertAuthor(Author author) throws ClassNotFoundException, SQLException {
		template.update("insert into tbl_author (authorName) values (?)", new Object[] { author.getAuthorName() });
	}

	public void insertBookAuthor(int BookId, String AuthorId) throws ClassNotFoundException, SQLException {
		template.update("insert into tbl_book_authors (bookId,authorId) values (?,?)",
				new Object[] { BookId, AuthorId });
	}

	public void deleteBookAuthor(int BookId) throws ClassNotFoundException, SQLException {
		template.update("DELETE FROM `library`.`tbl_book_authors` WHERE `bookId`=?", new Object[] { BookId });
	}

	public void insertBookGenre(int BookId, String GenreId) throws ClassNotFoundException, SQLException {
		template.update("insert into tbl_book_genres (genre_id,bookId) values (?,?)", new Object[] { GenreId, BookId });
	}

	public void deleteBookGenre(int BookId) throws ClassNotFoundException, SQLException {
		template.update("DELETE FROM tbl_book_genres WHERE bookId=?", new Object[] { BookId });
	}

	 public Integer insertBook(Book book) throws ClassNotFoundException,
	 SQLException{
//	 System.out.println(book.getTitle());
//	 System.out.println(book.getPublishername());
//	
//	 KeyHolder keyHolder=new GeneratedKeyHolder();
//	 template.update("insert into tbl_book (title,pubId) values(?,?)",
//	 new Object[] { book.getTitle(),book.getPublishername()},keyHolder);
//	 
//	 Integer bookid= (int)keyHolder.getKey().intValue();
//	 return bookid;
		 
		 KeyHolder holder = new GeneratedKeyHolder();

		 template.update(new PreparedStatementCreator() {           

		                 @Override
		                 public PreparedStatement createPreparedStatement(Connection connection)
		                         throws SQLException {
		                     PreparedStatement ps = connection.prepareStatement("insert into tbl_book (title,pubId) values(?,?)", Statement.RETURN_GENERATED_KEYS);
		                     ps.setString(1, book.getTitle());
		                     ps.setInt(2, Integer.parseInt(book.getPublishername()));
	
		                     return ps;
		                 }
		             }, holder);

		 Integer bookid= holder.getKey().intValue();
 return bookid;
	//	 Long newPersonId = holder.getKey().longValue();
	 
	 
	 
	 }

	
	
	
	public void updateBook(Book book) throws ClassNotFoundException, SQLException {
		/// code is dummy replace with exact content

		template.update("UPDATE `library`.`tbl_book` SET `title`=?, `pubId`=? WHERE `bookId`=?;",
				new Object[] { book.getTitle(), book.getPublishername(), book.getBookId() });

	}

	// public String readWithbookID(String query, int id) throws
	// ClassNotFoundException, SQLException{
	//
	// PreparedStatement pstmt = connection.prepareStatement("select * from
	// tbl_book where bookId ='"+id+"'" );
	//
	//
	//
	// ResultSet rs = pstmt.executeQuery();
	// Book b = new Book();
	// rs.next();
	//
	// b.setTitle(rs.getString("title"));
	// return b.getTitle();
	//
	//
	// }

	public Book readOne(Book book) throws ClassNotFoundException, SQLException {
		List<Book> books = template.query("select * from tbl_book where bookId=?", new Object[] { book.getBookId() },
				this);
		for (Book b : books) {

			return b;
		}
		return null;
	}

//	@Override
//	public List<Book> extractData(ResultSet rs) throws SQLException {
//		List<Book> books = new ArrayList<Book>();
//		while (rs.next()) {
//			Book b = new Book();
//			b.setBookId(rs.getInt("bookId"));
//			b.setTitle(rs.getString("title"));
//			Publisher p = new Publisher();
//			p.setPublisherId(rs.getInt("pubId"));
//			books.add(b);
//		}
//		return books;
//	}
	@Autowired
	PublisherDAO pdao;
	@Autowired
	AuthorDAO adao;
	@Autowired
	GenreDAO gdao;
	@Override
	public List<Book> extractData(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<Book>();
		
	//	PublisherDAO pdao = new PublisherDAO(connection);
	//	AuthorDAO adao = new AuthorDAO(connection);
	//	GenreDAO gdao = new GenreDAO(connection);
		while(rs.next()){
			Book b = new Book();
			b.setBookId(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			Publisher p = new Publisher();
			p.setPublisherId(rs.getInt("pubId"));
			List<Publisher> pubs;
			try {
				pubs = pdao.readAllbyId(p);
				b.setPublisher(pubs.get(0));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			b.setAuthors( adao.template.query("select * from tbl_author where authorId IN(select authorId from tbl_book_authors where bookId = ?)", new Object[]{b.getBookId()},adao));
			b.setGenres( gdao.template.query("select * from tbl_genre where genre_id IN(select genre_id from tbl_book_genres where bookId = ?)", new Object[]{b.getBookId()},gdao));
			books.add(b);
		}
		return books;
	}
	
	

}
