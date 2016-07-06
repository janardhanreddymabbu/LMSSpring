package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.Publisher;

@SuppressWarnings("unchecked")
public class BorrowerDAO extends BaseDAO  implements ResultSetExtractor<List<Borrower>>{

	
	public void insertBorrower(Borrower borrower) throws ClassNotFoundException, SQLException{
		template.update("insert into tbl_borrower (name,address,phone) values (?,?,?)", new Object[] {borrower.getName(),borrower.getAddress(),borrower.getPhone()});
	}
	
	public List<Borrower> readAll() throws ClassNotFoundException, SQLException{
		return template.query("select * from tbl_borrower", this);
	}
	
	public Borrower getBorrowerone(Borrower borrower) throws ClassNotFoundException, SQLException{
		List<Borrower> borrowers = template.query("select * from tbl_borrower where cardNo =?", new Object[] {borrower.getCardNo()},this);
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

	
	
}
