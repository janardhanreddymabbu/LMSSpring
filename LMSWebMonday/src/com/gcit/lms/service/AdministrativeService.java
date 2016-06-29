package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoansDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.LibraryBranchDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.BookCopies;
import com.gcit.lms.domain.BookLoans;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.Genre;
import com.gcit.lms.domain.LibraryBranch;
import com.gcit.lms.domain.Publisher;

public class AdministrativeService {
	
	ConnectionUtil util = new ConnectionUtil();
	
	public void createAuthor(Author author) throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(conn);
			adao.insertAuthor(author);
			conn.commit();
		}catch (Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
	}
	
	public void editAuthor(Author editauthor) throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(conn);
			adao.editAuthor(editauthor);
			conn.commit();
		}catch (Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
	}
	public void editPublisher(Publisher editpublisher) throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			PublisherDAO pdao = new PublisherDAO(conn);
			pdao.editPublisher(editpublisher);
			conn.commit();
		}catch (Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
	}
	
	public Borrower viewBorrower(Borrower borrower) throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		
			BorrowerDAO bdao = new BorrowerDAO(conn);
			Borrower borrower1= new Borrower();
			borrower1 = bdao.getBorrowerone(borrower);
			
			
			return borrower1;
		
		
		
	}
	
	public Integer getAuthorsCount() throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.getCount();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public Integer getLibraryBranchsCount() throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			LibraryBranchDAO ldao = new LibraryBranchDAO(conn);
			return ldao.getCount();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public Integer getBooksCount() throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			BookDAO bdao = new BookDAO(conn);
			return bdao.getCount();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public List<Author> viewAuthors(int pageNo) throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.readAll(pageNo);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public List<LibraryBranch> viewLibraryBranchs(int pageNo) throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			LibraryBranchDAO ldao = new LibraryBranchDAO(conn);
			return ldao.readAll(pageNo);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}
	
	
	
	public List<Book> viewBooks(int pageNo) throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			BookDAO bdao = new BookDAO(conn);
			return bdao.readAll(pageNo);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public void editLibrarybranch(LibraryBranch editlibrarybranch) throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			LibraryBranchDAO ldao = new LibraryBranchDAO(conn);
			ldao.editLibrarybranch(editlibrarybranch);
			conn.commit();
		}catch (Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
	}
	
	
	public void deleteAuthor(Author deleteauthor) throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(conn);
			adao.deleteAuthor(deleteauthor);
			conn.commit();
		}catch (Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
	}
	public void deleteBook(Book deletebook) throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			BookDAO bdao = new BookDAO(conn);
			bdao.deleteBook(deletebook);
			conn.commit();
		}catch (Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
	}
	public void deletePublisher(Publisher deletepublisher) throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			PublisherDAO pdao = new PublisherDAO(conn);
			pdao.deletePublisher(deletepublisher);
			conn.commit();
		}catch (Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
	}
	
	public void createGenre(Genre genre) throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			GenreDAO gdao = new GenreDAO(conn);
			gdao.insertGenre(genre);
			conn.commit();
		}catch (Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
	}
	public void createPublisher(Publisher publisher) throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			PublisherDAO pdao = new PublisherDAO (conn);
			
			pdao.insertPublisher(publisher);
			conn.commit();
		}catch (Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
	}
	
	public void createBorrower(Borrower borrower) throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			BorrowerDAO bdao = new BorrowerDAO (conn);
			
			bdao.insertBorrower(borrower);
			conn.commit();
		}catch (Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
	}
	
	public void createBookCopies(BookCopies bookcopies) throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			BookCopiesDAO bdao = new BookCopiesDAO (conn);
			
			bdao.insertBookCopies(bookcopies);
			conn.commit();
		}catch (Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
	}
	
	public void updateBookCopies(BookCopies bookcopies) throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			BookCopiesDAO bdao = new BookCopiesDAO (conn);
			
			bdao.updateBookCopies(bookcopies);
			conn.commit();
		}catch (Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
	}
	
	public void insertBookLoans(BookLoans bookloans) throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			BookLoansDAO bdao = new BookLoansDAO (conn);
			
			bdao.insertBookLoans(bookloans);
			conn.commit();
		}catch (Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
	}
	
	public List<Author> searchAuthors(String authorname) throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.searchAll(authorname);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}

	public List<Book> searchBooks(String bookname) throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			BookDAO bdao = new BookDAO(conn);
			return bdao.searchAll(bookname);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}
	
	
	public void updateBookLoans(BookLoans bookloans) throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			BookLoansDAO bdao = new BookLoansDAO (conn);
			
			bdao.updateBookLoans(bookloans);
			conn.commit();
		}catch (Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
	}
	
	public void deleteBookLoans(BookLoans bookloans) throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			BookLoansDAO bdao = new BookLoansDAO (conn);
			
			bdao.deletBookLoans(bookloans);
			conn.commit();
		}catch (Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
	}
	
	public void createLibraryBranch(LibraryBranch librarybranch) throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			LibraryBranchDAO ldao = new LibraryBranchDAO (conn);
			
			ldao.insertLibraryBranch(librarybranch);
			conn.commit();
		}catch (Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
	}
	
	
	public Integer createBook(Book book) throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		int bookId = 0;
		try{
			BookDAO bdao = new BookDAO(conn);
			//Integer bookId = bdao.saveWithID(query, vals)
			bookId=bdao.insertBook(book);
//			for(Author a: book.getAuthors()){
//				//badao.save(bookId, authorId
//			}
			
			conn.commit();
			
		}catch (Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
		return bookId;
	}
	
	public  void updateBook(Book book) throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		
		try{
			BookDAO bdao = new BookDAO(conn);
			//Integer bookId = bdao.saveWithID(query, vals)
			bdao.updateBook(book);

			conn.commit();
			
		}catch (Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
		
	}
	public void createBookAuthor(int BookId,String AuthorId) throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			BookDAO bdao = new BookDAO (conn);
			
			bdao.insertBookAuthor(BookId,AuthorId);
			conn.commit();
		}catch (Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
	}
	
	public void deleteBookAuthhorsbyBookId(int BookId) throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			BookDAO bdao = new BookDAO (conn);
			
			bdao.deleteBookAuthor(BookId);
			conn.commit();
		}catch (Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
	}
	
	
	
	public void createBookGenre(int BookId,String GenreId) throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			BookDAO bdao = new BookDAO (conn);
			
			bdao.insertBookGenre(BookId,GenreId);
			conn.commit();
		}catch (Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
	}
	
	public void deleteBookGenresbyBookId(int BookId) throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			BookDAO bdao = new BookDAO (conn);
			
			bdao.deleteBookGenre(BookId);
			conn.commit();
		}catch (Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
	}
	
//	public void createPublisher(Publisher publisher) throws ClassNotFoundException, SQLException{
//		Connection conn = util.getConnection();
//		try{
//			PublisherDAO pdao = new PublisherDAO (conn);
//			
//			pdao.insertPublisher(publisher);
//			conn.commit();
//		}catch (Exception e){
//			e.printStackTrace();
//			conn.rollback();
//		}finally{
//			conn.close();
//		}
//	}
	
	
	public List<Author> viewAuthors() throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.readAll();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public List<Borrower> viewBorrowers() throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			BorrowerDAO bdao = new BorrowerDAO(conn);
			return bdao.readAll();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public List<LibraryBranch> viewLibraryBranchs() throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			LibraryBranchDAO ldao = new LibraryBranchDAO(conn);
			return ldao.readAll();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public List<BookCopies> viewBookCopiesbybranchId(Integer branchId) throws ClassNotFoundException, SQLException{
		BookCopies bc = new BookCopies();
		bc.setBranchId(branchId);
		Connection conn = util.getConnection();
		try{
			BookCopiesDAO bdao = new BookCopiesDAO(conn);
			return bdao.readAllbyBranchId(bc);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}
	public List<BookCopies> viewBookCopiesbybranchIdavailable(Integer branchId,Integer CardNo) throws ClassNotFoundException, SQLException{
		BookCopies bc = new BookCopies();
		bc.setBranchId(branchId);
		Connection conn = util.getConnection();
		try{
			BookCopiesDAO bdao = new BookCopiesDAO(conn);
			return bdao.readAllbyBranchIdavailable(bc,CardNo);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}
	
	
	
	public Author viewAuthorByID(Integer authorID) throws ClassNotFoundException, SQLException{
		Author a = new Author();
		
		a.setAuthorId(authorID);
		Connection conn = util.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.readOne(a);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}
	public Publisher viewPublisherByID(Integer publisherID) throws ClassNotFoundException, SQLException{
		Publisher p = new Publisher();
		
		p.setPublisherId(publisherID);
		Connection conn = util.getConnection();
		try{
			PublisherDAO pdao = new PublisherDAO(conn);
			return pdao.readOne(p);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public Book viewBookByID(Integer bookID) throws ClassNotFoundException, SQLException{
		Book b = new Book();
		
		b.setBookId(bookID);
		Connection conn = util.getConnection();
		try{
			BookDAO bdao = new BookDAO(conn);
			return bdao.readOne(b);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public Borrower viewBorrowerByCardNo(String CardNo) throws ClassNotFoundException, SQLException{
		
	
		Borrower b = new Borrower();
		
		b.setCardNo( Integer.parseInt(CardNo));
		
		Connection conn = util.getConnection();
		try{
			BorrowerDAO bdao = new BorrowerDAO(conn);
			return bdao.getBorrowerone(b);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public List<BookLoans> viewBookLoansByCardNo(String CardNo) throws ClassNotFoundException, SQLException{
		
		
		BookLoans b = new BookLoans();
		
		b.setCardNo( Integer.parseInt(CardNo));
		
		Connection conn = util.getConnection();
		try{
			BookLoansDAO bdao = new BookLoansDAO(conn);
			return bdao.getBorrowerone(b);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}
	
public List<BookLoans> viewBookLoans() throws ClassNotFoundException, SQLException{
		
		
		
		
		
		
		Connection conn = util.getConnection();
		try{
			BookLoansDAO bdao = new BookLoansDAO(conn);
			return bdao.getBorrower();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}
	
	
public List<BookLoans> viewBookLoansByBookId(String bookId) throws ClassNotFoundException, SQLException{
		
		
		BookLoans b = new BookLoans();
		
		b.setBookId( Integer.parseInt(bookId));
		
		Connection conn = util.getConnection();
		try{
			BookLoansDAO bdao = new BookLoansDAO(conn);
			return bdao.getBorroweronebybookId(b);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public LibraryBranch viewBranchByID(Integer branchID) throws ClassNotFoundException, SQLException{
		LibraryBranch lb = new LibraryBranch();
		
		lb.setBranchId(branchID);
		Connection conn = util.getConnection();
		try{
			LibraryBranchDAO ldao = new LibraryBranchDAO(conn);
			return ldao.readOne(lb);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}
	
	
	
	public List<Publisher> viewPublishers() throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			PublisherDAO pdao = new PublisherDAO(conn);
			return pdao.readAll();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}
	
	
	public List<Genre> viewGenres() throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			GenreDAO gdao = new GenreDAO(conn);
			return gdao.readAll();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}
	public List<Book> viewBooks() throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			BookDAO bdao = new BookDAO(conn);
			return bdao.readAll();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}
	
	public List<Book> viewBooksnotinbookcopies(int branchId) throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			BookDAO bdao = new BookDAO(conn);
			return bdao.readAllnotinbookcopies(branchId);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}

}
