package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.BookCopies;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.LibraryBranch;

@SuppressWarnings("unchecked")
public class BookCopiesDAO extends BaseDAO{

	public BookCopiesDAO(Connection conn) {
		super(conn);
	}
	public void insertBookCopies(BookCopies bookcopies) throws ClassNotFoundException, SQLException{
		save("insert into tbl_book_copies (bookId,branchId,noOfCopies) values (?,?,?)", new Object[] {bookcopies.getBookId(),bookcopies.getBranchId(),bookcopies.getNoOfCopies()});
	}
	public void updateBookCopies(BookCopies bookcopies) throws ClassNotFoundException, SQLException{
		save("UPDATE tbl_book_copies SET noOfCopies=? WHERE bookId=? and branchId =?", new Object[] {bookcopies.getNoOfCopies(),bookcopies.getBookId(),bookcopies.getBranchId()});
	}
	
	
	public List<BookCopies> readAllbyBranchId(BookCopies bookcopies) throws ClassNotFoundException, SQLException{
		
		
		return read("select * from tbl_book_copies where branchId =?", new Object[] {bookcopies.getBranchId()});
	}
	
public List<BookCopies> readAllbyBranchIdavailable(BookCopies bookcopies, Integer CardNo) throws ClassNotFoundException, SQLException{
		
		
		return read("select * from tbl_book_copies where branchId =? and noOfCopies>0 and bookId not in (SELECT bookId FROM tbl_book_loans where cardNo=?)", new Object[] {bookcopies.getBranchId(),CardNo});
	}
	

	

	@Override
	public List<BookCopies> extractData(ResultSet rs) throws SQLException {
		List<BookCopies> BookCopiess = new ArrayList<BookCopies>();
		LibraryBranchDAO ldao = new LibraryBranchDAO(connection);
		BookDAO bdao = new BookDAO(connection);
		
		while(rs.next()){
			BookCopies b = new BookCopies();
			b.setBookId(rs.getInt("bookId"));
			b.setBranchId(rs.getInt("branchId"));
			b.setNoOfCopies(rs.getInt("noOfCopies"));
			try {
				b.setBranchName(ldao.readWithbranchID("select branchName from tbl_library_branch where branchId  = ?)", b.getBranchId()));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				b.setBookTitle(bdao.readWithbookID("select title from tbl_book where bookId  = ?)", b.getBookId()));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			BookCopiess.add(b);
		}
		return BookCopiess;
	}

	@Override
	public List<?> extractDataFirstLevel(ResultSet rs) throws SQLException {
List<BookCopies> BookCopiess = new ArrayList<BookCopies>();
		
		while(rs.next()){
			BookCopies b = new BookCopies();
			b.setBookId(rs.getInt("bookId"));
			b.setBranchId(rs.getInt("branchId"));

			BookCopiess.add(b);
		}
		return BookCopiess;
	}
}
