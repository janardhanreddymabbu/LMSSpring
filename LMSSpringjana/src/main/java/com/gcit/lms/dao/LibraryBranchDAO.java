package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.LibraryBranch;

@SuppressWarnings("unchecked")
public class LibraryBranchDAO extends BaseDAO implements ResultSetExtractor<List<LibraryBranch>>{

	
	public void insertLibraryBranch(LibraryBranch librarybranch) throws ClassNotFoundException, SQLException{
		template.update("insert into tbl_library_branch (branchName,branchAddress) values (?,?)", new Object[] {librarybranch.getBranchName(),librarybranch.getBranchAddress()});
	}
	public List<LibraryBranch> readAll() throws ClassNotFoundException, SQLException{
		return template.query("select * from tbl_library_branch", this);
	}
	
	public Integer getCount() throws ClassNotFoundException, SQLException{
		return template.queryForObject("select count(*) as count from tbl_library_branch",  Integer.class);
	}
	
	public List<LibraryBranch> readAll(int pageNo) throws ClassNotFoundException, SQLException{
		setPageNo(pageNo);
		return template.query("select * from tbl_library_branch limit "+pageNo+","+getPageSize(), this);
	}
	
	public LibraryBranch readOne(LibraryBranch LibraryBranch) throws ClassNotFoundException, SQLException{
		List<LibraryBranch> LibraryBranchs = template.query("select * from tbl_library_branch where branchId =?", new Object[] {LibraryBranch.getBranchId()},this);
		for(LibraryBranch lb: LibraryBranchs){
			
			return lb;
		}
		return null;
	}
	
//public String readWithbranchID(String query, int id) throws ClassNotFoundException, SQLException{
//		
//		PreparedStatement pstmt = connection.prepareStatement("select * from tbl_library_branch where branchId ='"+id+"'");
//		
//	
//	
//		ResultSet rs = pstmt.executeQuery();
//		LibraryBranch l = new LibraryBranch();
//		rs.next();
//		
//		l.setBranchName(rs.getString("branchName"));
//		return l.getBranchName();
//		
//		
//	}
	
	public void editLibrarybranch(LibraryBranch librarybranch) throws ClassNotFoundException, SQLException{
		template.update("update  tbl_library_branch set branchName = ?,branchAddress =? where branchId = ?", new Object[] {librarybranch.getBranchName(), librarybranch.getBranchAddress(),librarybranch.getBranchId()});
	}

	@Override
	public List<LibraryBranch> extractData(ResultSet rs) throws SQLException {
		List<LibraryBranch> libraryBranchs = new ArrayList<LibraryBranch>();
		
		while(rs.next()){
			LibraryBranch l = new LibraryBranch();
			l.setBranchId(rs.getInt("branchId"));
			l.setBranchName(rs.getString("branchName"));
			l.setBranchAddress(rs.getString("branchAddress"));
//			try {
//				a.setBooks(bdao.readFirstLevel("select * from tbl_book where bookId IN(select bookId from tbl_book_authors where authorId = ?", new Object[]{a.getAuthorId()}));
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			libraryBranchs.add(l);
		}
		return libraryBranchs;
	}

	
}