package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Autowired
	AuthorDAO adao;

	@Autowired
	BookCopiesDAO bcdao;

	@Autowired
	BookDAO bdao;

	@Autowired
	BookLoansDAO bldao;

	@Autowired
	BorrowerDAO brdao;

	@Autowired
	GenreDAO gdao;
	
	@Autowired
	LibraryBranchDAO lbdao;
	@Autowired
	PublisherDAO pdao;
	
	
	
	
	@Transactional
	public void createAuthor(Author author) throws ClassNotFoundException, SQLException{
	
			adao.insertAuthor(author);
		}
	@Transactional
	public void editAuthor(Author editauthor) throws ClassNotFoundException, SQLException{
		
			adao.editAuthor(editauthor);
			
	}
	@Transactional
	public void editPublisher(Publisher editpublisher) throws ClassNotFoundException, SQLException{
		
		
			pdao.editPublisher(editpublisher);
		
	}
	@Transactional
	public Borrower viewBorrower(Borrower borrower) throws ClassNotFoundException, SQLException{
		
			Borrower borrower1= new Borrower();
			borrower1 = brdao.getBorrowerone(borrower);
			
			
			return borrower1;
}
	@Transactional
	public Integer getAuthorsCount() throws ClassNotFoundException, SQLException{
		if (adao == null)
		{System.out.println("null is there");
		}
		
		
			return adao.getCount();
		
	}
	@Transactional
	public Integer getLibraryBranchsCount() throws ClassNotFoundException, SQLException{
		
			return lbdao.getCount();
		
	}
	@Transactional
	public Integer getBooksCount() throws ClassNotFoundException, SQLException{
		
			return bdao.getCount();
		
	}
	@Transactional
	public List<Author> viewAuthors(int pageNo) throws ClassNotFoundException, SQLException{
		
			return adao.readAll(pageNo);
		
	}
	@Transactional
	public List<LibraryBranch> viewLibraryBranchs(int pageNo) throws ClassNotFoundException, SQLException{
		
			return lbdao.readAll(pageNo);
		
	}
	
	
	@Transactional
	public List<Book> viewBooks(int pageNo) throws ClassNotFoundException, SQLException{
		
			return bdao.readAll(pageNo);
		
	}
	@Transactional
	public void editLibrarybranch(LibraryBranch editlibrarybranch) throws ClassNotFoundException, SQLException{
		
			lbdao.editLibrarybranch(editlibrarybranch);
			
	}
	
	@Transactional
	public void deleteAuthor(Author deleteauthor) throws ClassNotFoundException, SQLException{
		
		
			adao.deleteAuthor(deleteauthor);
			
	}
	@Transactional
	public void deleteBook(Book deletebook) throws ClassNotFoundException, SQLException{
		
			bdao.deleteBook(deletebook);
		
	}
	@Transactional
	public void deletePublisher(Publisher deletepublisher) throws ClassNotFoundException, SQLException{
		
			pdao.deletePublisher(deletepublisher);
			
	}
	@Transactional
	public void createGenre(Genre genre) throws ClassNotFoundException, SQLException{
		
			gdao.insertGenre(genre);
			
	}
	@Transactional
	public void createPublisher(Publisher publisher) throws ClassNotFoundException, SQLException{
		
			
			pdao.insertPublisher(publisher);
			
		
	}
	@Transactional
	public void createBorrower(Borrower borrower) throws ClassNotFoundException, SQLException{
		
			
			brdao.insertBorrower(borrower);
			
		
	}
	@Transactional
	public void createBookCopies(BookCopies bookcopies) throws ClassNotFoundException, SQLException{
		
			
			bcdao.insertBookCopies(bookcopies);
			
	}
	@Transactional
	public void updateBookCopies(BookCopies bookcopies) throws ClassNotFoundException, SQLException{
		
			
			bcdao.updateBookCopies(bookcopies);
			
	}
	
	@Transactional
	public void insertBookLoans(BookLoans bookloans) throws ClassNotFoundException, SQLException{
		
			
			bldao.insertBookLoans(bookloans);
			
	}
	
	public List<Author> searchAuthors(String authorname) throws ClassNotFoundException, SQLException{
		
			return adao.searchAll(authorname);
		
	}
	@Transactional
	public List<Book> searchBooks(String bookname) throws ClassNotFoundException, SQLException{
		
			return bdao.searchAll(bookname);
		
	}
	
	@Transactional
	public void updateBookLoans(BookLoans bookloans) throws ClassNotFoundException, SQLException{
		
			
			bldao.updateBookLoans(bookloans);
			
	}
	@Transactional
	public void deleteBookLoans(BookLoans bookloans) throws ClassNotFoundException, SQLException{
		
			
			bldao.deletBookLoans(bookloans);
		
	}
	@Transactional
	public void createLibraryBranch(LibraryBranch librarybranch) throws ClassNotFoundException, SQLException{
		
			
			lbdao.insertLibraryBranch(librarybranch);
			
	}
	
	@Transactional
	public Integer createBook(Book book) throws ClassNotFoundException, SQLException{
		
		
		
		int	bookId=bdao.insertBook(book);

		return bookId;
	}
	@Transactional
	public  void updateBook(Book book) throws ClassNotFoundException, SQLException{
		
			bdao.updateBook(book);

		
		
	}
	@Transactional
	public void createBookAuthor(int BookId,String AuthorId) throws ClassNotFoundException, SQLException{
		
			
			bdao.insertBookAuthor(BookId,AuthorId);
		
	}
	@Transactional
	public void deleteBookAuthhorsbyBookId(int BookId) throws ClassNotFoundException, SQLException{
		
			bdao.deleteBookAuthor(BookId);
			
	}
	
	
	@Transactional
	public void createBookGenre(int BookId,String GenreId) throws ClassNotFoundException, SQLException{
		
			
			bdao.insertBookGenre(BookId,GenreId);
			
	}
	@Transactional
	public void deleteBookGenresbyBookId(int BookId) throws ClassNotFoundException, SQLException{
		
			
			bdao.deleteBookGenre(BookId);
			
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
	
	@Transactional
	public List<Author> viewAuthors() throws ClassNotFoundException, SQLException{
		
			return adao.readAll();
		
	}
	@Transactional
	public List<Borrower> viewBorrowers() throws ClassNotFoundException, SQLException{
		
			return brdao.readAll();
		
	}
	@Transactional
	public List<LibraryBranch> viewLibraryBranchs() throws ClassNotFoundException, SQLException{
		
			return lbdao.readAll();
	
	}
	@Transactional
	public List<BookCopies> viewBookCopiesbybranchId(Integer branchId) throws ClassNotFoundException, SQLException{
		BookCopies bc = new BookCopies();
		bc.setBranchId(branchId);
		
			
			return bcdao.readAllbyBranchId(bc);
		
	}
	@Transactional
	public List<BookCopies> viewBookCopiesbybranchIdavailable(Integer branchId,Integer CardNo) throws ClassNotFoundException, SQLException{
		BookCopies bc = new BookCopies();
		bc.setBranchId(branchId);
		
			return bcdao.readAllbyBranchIdavailable(bc,CardNo);
		
	}
	
	
	@Transactional
	public Author viewAuthorByID(Integer authorID) throws ClassNotFoundException, SQLException{
		Author a = new Author();
		
		a.setAuthorId(authorID);
		
			return adao.readOne(a);
		
	}
	@Transactional
	public Publisher viewPublisherByID(Integer publisherID) throws ClassNotFoundException, SQLException{
		Publisher p = new Publisher();
		
		p.setPublisherId(publisherID);
		
			return pdao.readOne(p);
		
		
	}
	@Transactional
	public Book viewBookByID(Integer bookID) throws ClassNotFoundException, SQLException{
		Book b = new Book();
		
		b.setBookId(bookID);
		
			return bdao.readOne(b);
		
	}
	@Transactional
	public Borrower viewBorrowerByCardNo(String CardNo) throws ClassNotFoundException, SQLException{
		
	
		Borrower b = new Borrower();
		
		b.setCardNo( Integer.parseInt(CardNo));
		
		
			return brdao.getBorrowerone(b);
		
	}
	@Transactional
	public List<BookLoans> viewBookLoansByCardNo(String CardNo) throws ClassNotFoundException, SQLException{
		
		
		BookLoans b = new BookLoans();
		
		b.setCardNo( Integer.parseInt(CardNo));
		
		
			return bldao.getBorrowerone(b);
		
		
	}
	@Transactional	
public List<BookLoans> viewBookLoans() throws ClassNotFoundException, SQLException{
		
	return bldao.getBorrower();
		
		
	}
	
	@Transactional	
public List<BookLoans> viewBookLoansByBookId(String bookId) throws ClassNotFoundException, SQLException{
		
		
		BookLoans b = new BookLoans();
		
		b.setBookId( Integer.parseInt(bookId));
		
		
			return bldao.getBorroweronebybookId(b);
	
	}
	@Transactional
	public LibraryBranch viewBranchByID(Integer branchID) throws ClassNotFoundException, SQLException{
		LibraryBranch lb = new LibraryBranch();
		
		lb.setBranchId(branchID);
		
			return lbdao.readOne(lb);
		
	}
	
	
	@Transactional
	public List<Publisher> viewPublishers() throws ClassNotFoundException, SQLException{
		
			return pdao.readAll();
		
	}
	
	@Transactional
	public List<Genre> viewGenres() throws ClassNotFoundException, SQLException{
		
			return gdao.readAll();
		
	}
	@Transactional
	public List<Book> viewBooks() throws ClassNotFoundException, SQLException{
		
			return bdao.readAll();
		
	}
	@Transactional
	public List<Book> viewBooksnotinbookcopies(int branchId) throws ClassNotFoundException, SQLException{
		
			return bdao.readAllnotinbookcopies(branchId);
		
	}

}
