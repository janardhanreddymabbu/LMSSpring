package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.BookCopies;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.LibraryBranch;

@SuppressWarnings("unchecked")
public class BookCopiesDAO extends BaseDAO  implements ResultSetExtractor<List<BookCopies>>{

	
	public void insertBookCopies(BookCopies bookcopies) throws ClassNotFoundException, SQLException{
		template.update("insert into tbl_book_copies (bookId,branchId,noOfCopies) values (?,?,?)", new Object[] {bookcopies.getBookId(),bookcopies.getBranchId(),bookcopies.getNoOfCopies()});
	}
	public void updateBookCopies(BookCopies bookcopies) throws ClassNotFoundException, SQLException{
		template.update("UPDATE tbl_book_copies SET noOfCopies=? WHERE bookId=? and branchId =?", new Object[] {bookcopies.getNoOfCopies(),bookcopies.getBookId(),bookcopies.getBranchId()});
	}
	
	
	public List<BookCopies> readAllbyBranchId(BookCopies bookcopies) throws ClassNotFoundException, SQLException{
		
		
		return template.query("SELECT tbl_book_copies.bookId,tbl_book_copies.branchId,noOfCopies,branchName,title as bookTitle FROM library.tbl_book_copies join tbl_book on tbl_book_copies.bookId =tbl_book.bookId join tbl_library_branch on tbl_book_copies.branchId =tbl_library_branch.branchId where tbl_book_copies.branchId = ?", new Object[] {bookcopies.getBranchId()},this);
	}
	
	
	
	
	
//public List<BookCopies> readAllbyBranchIdavailable(BookCopies bookcopies, Integer CardNo) throws ClassNotFoundException, SQLException{
//		
//		
//		return template.query("select * from tbl_book_copies where branchId =? and noOfCopies>0 and bookId not in (SELECT bookId FROM tbl_book_loans where cardNo=?)", new Object[] {bookcopies.getBranchId(),CardNo},this);
//	}
public List<BookCopies> readAllbyBranchIdavailable(BookCopies bookcopies, Integer CardNo) throws ClassNotFoundException, SQLException{
	
	
	return template.query("select tbl_book_copies.bookId,tbl_book_copies.branchId,noOfCopies,branchName,title as bookTitle FROM library.tbl_book_copies join tbl_book on tbl_book_copies.bookId =tbl_book.bookId join tbl_library_branch on tbl_book_copies.branchId =tbl_library_branch.branchId where tbl_book_copies.branchId =? and tbl_book_copies.noOfCopies>0 and tbl_book_copies.bookId not in (SELECT bookId FROM tbl_book_loans where cardNo=?)", new Object[] {bookcopies.getBranchId(),CardNo},this);
}	

	

	@Override
	public List<BookCopies> extractData(ResultSet rs) throws SQLException {
		List<BookCopies> BookCopiess = new ArrayList<BookCopies>();
//		LibraryBranchDAO ldao = new LibraryBranchDAO(connection);
//		BookDAO bdao = new BookDAO(connection);
		
		while(rs.next()){
			BookCopies b = new BookCopies();
			b.setBookId(rs.getInt("bookId"));
			b.setBranchId(rs.getInt("branchId"));
			b.setNoOfCopies(rs.getInt("noOfCopies"));
			b.setBranchName(rs.getString("branchName"));
			b.setBookTitle(rs.getString("bookTitle"));
//			try {
//				b.setBranchName(ldao.readWithbranchID("select branchName from tbl_library_branch where branchId  = ?)", b.getBranchId()));
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			try {
//				b.setBookTitle(bdao.readWithbookID("select title from tbl_book where bookId  = ?)", b.getBookId()));
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

			BookCopiess.add(b);
		}
		return BookCopiess;
	}

	
}
