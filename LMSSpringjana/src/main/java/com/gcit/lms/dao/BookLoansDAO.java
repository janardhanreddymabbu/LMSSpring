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
import com.gcit.lms.domain.BookLoans;
import com.gcit.lms.domain.Borrower;

@SuppressWarnings("unchecked")
public class BookLoansDAO extends BaseDAO  implements ResultSetExtractor<List<BookLoans>>{

	

	public void insertBookLoans(BookLoans bookloans) throws ClassNotFoundException, SQLException{
		template.update("INSERT INTO `library`.`tbl_book_loans` (`bookId`, `branchId`, `cardNo`, `dateOut`, `dueDate`) VALUES (?,?,?,?,?);", new Object[] {bookloans.getBookId(),bookloans.getBranchId(),bookloans.getCardNo(),bookloans.getDateOut(),bookloans.getDueDate()});
		template.update("UPDATE `library`.`tbl_book_copies` SET `noOfCopies`= noOfCopies-1 WHERE `bookId`=? and`branchId`=?;", new Object[] {bookloans.getBookId(),bookloans.getBranchId()});
	}
	
	public void updateBookLoans(BookLoans bookloans) throws ClassNotFoundException, SQLException{
		template.update("UPDATE `library`.`tbl_book_loans` SET `dueDate`=? WHERE `bookId`=? and`branchId`=? and`cardNo`=?;", new Object[] {bookloans.getDueDate(),bookloans.getBookId(),bookloans.getBranchId(),bookloans.getCardNo()});
		
	}
	public void deletBookLoans(BookLoans bookloans) throws ClassNotFoundException, SQLException{
		template.update("DELETE FROM `library`.`tbl_book_loans` WHERE `bookId`=? and`branchId`=? and`cardNo`=?", new Object[] {bookloans.getBookId(),bookloans.getBranchId(),bookloans.getCardNo()});
		template.update("UPDATE `library`.`tbl_book_copies` SET `noOfCopies`= noOfCopies+1 WHERE `bookId`=? and`branchId`=?;", new Object[] {bookloans.getBookId(),bookloans.getBranchId()});
	}
	
	public  List<BookLoans> getBorrowerone(BookLoans bookloans) throws ClassNotFoundException, SQLException{
		List<BookLoans> b = template.query("SELECT * FROM tbl_book_loans join tbl_book on tbl_book_loans.bookId= tbl_book.bookId join tbl_library_branch  on tbl_book_loans.branchId = tbl_library_branch.branchId and cardNo = ?", new Object[] {bookloans.getCardNo()},this);
		
		return b;
	}
	
	public  List<BookLoans> getBorrower() throws ClassNotFoundException, SQLException{
		List<BookLoans> b = template.query("SELECT * FROM tbl_book_loans join tbl_book on tbl_book_loans.bookId= tbl_book.bookId join tbl_library_branch  on tbl_book_loans.branchId = tbl_library_branch.branchId ", this);
		
		return b;
	}
	public  List<BookLoans> getBorroweronebybookId(BookLoans bookloans) throws ClassNotFoundException, SQLException{
		List<BookLoans> b = template.query("SELECT * FROM tbl_book_loans join tbl_book on tbl_book_loans.bookId= tbl_book.bookId join tbl_library_branch  on tbl_book_loans.branchId = tbl_library_branch.branchId and tbl_book_loans.bookId = ?", new Object[] {bookloans.getBookId()},this);
		
		return b;
	}
	
	@Override
	public List<BookLoans> extractData(ResultSet rs) throws SQLException {
		List<BookLoans> bookloans = new ArrayList<BookLoans>();
	
		while(rs.next()){
			BookLoans b = new BookLoans();
			b.setBookId(rs.getInt("bookId"));
			b.setBranchId(rs.getInt("branchId"));
			b.setCardNo(rs.getInt("cardNo"));
			b.setBooktitle(rs.getString("title"));
			b.setBranchname(rs.getString("branchName"));
			b.setDateOut(rs.getDate("dateOut"));
			b.setDueDate(rs.getDate("dueDate"));
			b.setDateIn(rs.getDate("dateIn"));
			
			bookloans.add(b);
		}
		return bookloans;
	}

	
}

