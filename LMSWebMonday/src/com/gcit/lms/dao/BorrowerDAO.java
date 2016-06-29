package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.Publisher;

@SuppressWarnings("unchecked")
public class BorrowerDAO extends BaseDAO{

	public BorrowerDAO(Connection conn) {
		super(conn);
	}
	public void insertBorrower(Borrower borrower) throws ClassNotFoundException, SQLException{
		save("insert into tbl_borrower (name,address,phone) values (?,?,?)", new Object[] {borrower.getName(),borrower.getAddress(),borrower.getPhone()});
	}
	
	public List<Borrower> readAll() throws ClassNotFoundException, SQLException{
		return read("select * from tbl_borrower", null);
	}
	
	public Borrower getBorrowerone(Borrower borrower) throws ClassNotFoundException, SQLException{
		List<Borrower> borrowers = read("select * from tbl_borrower where cardNo =?", new Object[] {borrower.getCardNo()});
		for(Borrower a: borrowers){
			
			return a;
		}
		return null;
	}
	

	@Override
	public List<Borrower> extractData(ResultSet rs) throws SQLException {
		List<Borrower> borrowers = new ArrayList<Borrower>();
	
		while(rs.next()){
			Borrower b = new Borrower();
			b.setCardNo(rs.getInt("cardNo"));
			b.setName(rs.getString("name"));
			b.setAddress(rs.getString("address"));
			b.setPhone(rs.getString("phone"));
			

			borrowers.add(b);
		}
		return borrowers;
	}

	@Override
	public List<?> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<Borrower> borrowers = new ArrayList<Borrower>();
		
		while(rs.next()){
			Borrower b = new Borrower();
			b.setCardNo(rs.getInt("cardNo"));
			b.setName(rs.getString("name"));
			
			

			borrowers.add(b);
		}
		return borrowers;
	}
	
}
