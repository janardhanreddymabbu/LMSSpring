package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.LibraryBranch;
import com.gcit.lms.domain.Publisher;

public class BookDAO extends BaseDAO {
	public BookDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public List<Book> readAll() throws ClassNotFoundException, SQLException{
		return read("select * from tbl_book", null);
	}
	public Integer getCount() throws ClassNotFoundException, SQLException{
		return readCount("select count(*) as count from tbl_book", null);
	}
	
	public List<Book> readAll(int pageNo) throws ClassNotFoundException, SQLException{
		setPageNo(pageNo);
		return read("select * from tbl_book", null);
	}
	public List<Book> readAllnotinbookcopies(int branchId) throws ClassNotFoundException, SQLException{
		return read("select * from tbl_book where bookId not  in (Select bookId from tbl_book_copies where branchId =? )", new Object[] {branchId});
	}
	
	public List<Book>searchAll(String name)throws ClassNotFoundException,SQLException{
		setPageNo(getCount());
		return Search("select * from tbl_book where title LIKE ? ",new Object[]{name});
	}
	
	public void deleteBook(Book book) throws ClassNotFoundException, SQLException{
		save("delete from tbl_book where bookId=?", new Object[] {book.getBookId()});
	}
	
	public void insertAuthor(Author author) throws ClassNotFoundException, SQLException{
		save("insert into tbl_author (authorName) values (?)", new Object[] {author.getAuthorName()});
	}
	
	public void insertBookAuthor(int BookId,String AuthorId) throws ClassNotFoundException, SQLException{
		save("insert into tbl_book_authors (bookId,authorId) values (?,?)",new Object[] {BookId,AuthorId });
	}
	public void deleteBookAuthor(int BookId) throws ClassNotFoundException, SQLException{
		save("DELETE FROM `library`.`tbl_book_authors` WHERE `bookId`=?",new Object[] {BookId });
	}
	
	
	public void insertBookGenre(int BookId,String GenreId) throws ClassNotFoundException, SQLException{
		save("insert into tbl_book_genres (genre_id,bookId) values (?,?)",new Object[] {GenreId,BookId });
	}
	
	public void deleteBookGenre(int BookId) throws ClassNotFoundException, SQLException{
		save("DELETE FROM tbl_book_genres WHERE bookId=?",new Object[] {BookId });
	}
	
	public Integer insertBook(Book book) throws ClassNotFoundException, SQLException{
		/// code is dummy replace with exact content 
		
		return saveWithID("insert into tbl_book (title,pubId) values (?,?)", new Object[] {book.getTitle(),book.getPublishername()});

		}
	
	public void updateBook(Book book) throws ClassNotFoundException, SQLException{
		/// code is dummy replace with exact content 
		
		 save("UPDATE `library`.`tbl_book` SET `title`=?, `pubId`=? WHERE `bookId`=?;", new Object[] {book.getTitle(),book.getPublishername(),book.getBookId()});

		}
	
public String readWithbookID(String query, int id) throws ClassNotFoundException, SQLException{
		
		PreparedStatement pstmt = connection.prepareStatement("select * from tbl_book where bookId  ='"+id+"'" );
		
	
	
		ResultSet rs = pstmt.executeQuery();
		Book b = new Book();
		rs.next();
		
		b.setTitle(rs.getString("title"));
		return b.getTitle();
		
		
	}



public Book readOne(Book book) throws ClassNotFoundException, SQLException{
	List<Book> books = read("select * from tbl_book where bookId=?", new Object[] {book.getBookId()});
	for(Book b: books){
		
		return b;
	}
	return null;
}

	@Override
	public List<Book> extractData(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<Book>();
		PublisherDAO pdao = new PublisherDAO(connection);
		AuthorDAO adao = new AuthorDAO(connection);
		GenreDAO gdao = new GenreDAO(connection);
		while(rs.next()){
			Book b = new Book();
			b.setBookId(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			try {
				List<Publisher> pubs = pdao.read("select * from tbl_publisher where publisherId = ?", new Object[]{rs.getInt("pubId")});
				b.setPublisher(pubs.get(0));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				b.setAuthors(adao.readFirstLevel("select * from tbl_author where authorId IN(select authorId from tbl_book_authors where bookId = ?)", new Object[]{b.getBookId()}));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				b.setGenres(gdao.readFirstLevel("select * from tbl_genre where genre_id IN(select genre_id from tbl_book_genres where bookId = ?)", new Object[]{b.getBookId()}));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			books.add(b);
		}
		return books;
	}

	@Override
	public List<?> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<Book>();
		while(rs.next()){
			Book b = new Book();
			b.setBookId(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			books.add(b);
		}
		return books;
	}
}
