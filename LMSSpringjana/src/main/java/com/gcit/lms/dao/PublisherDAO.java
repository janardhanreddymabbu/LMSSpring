package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Publisher;

public class PublisherDAO extends BaseDAO implements ResultSetExtractor<List<Publisher>>{
	
	

	public List<Publisher> readAll() throws ClassNotFoundException, SQLException{
		return template.query("select * from tbl_publisher", this);
	}
	public void insertPublisher(Publisher publisher) throws ClassNotFoundException, SQLException{
		template.update("insert into tbl_publisher (publisherName,publisherAddress,publisherPhone) values (?,?,?)", new Object[] {publisher.getPublisherName(),publisher.getPublisherAddress(),publisher.getPublisherPhone()});
	}
	public List<Publisher> readAllbyId(Publisher publisher) throws ClassNotFoundException, SQLException{
		return	 template.query("select * from tbl_publisher where publisherId=?", new Object[] {publisher.getPublisherId()},this);
	}
	
	
	
	public Publisher readOne(Publisher publisher) throws ClassNotFoundException, SQLException{
		List<Publisher> publishers = template.query("select * from tbl_publisher where publisherId =?", new Object[] {publisher.getPublisherId()},this);
		for(Publisher p: publishers){
			
			return p;
		}
		return null;
	}
	
	public void editPublisher(Publisher publisher) throws ClassNotFoundException, SQLException{
		template.update("UPDATE `library`.`tbl_publisher` SET `publisherName`=?, `publisherAddress`=?, `publisherPhone`=? WHERE `publisherId`=?;", new Object[] {publisher.getPublisherName(),publisher.getPublisherAddress(),publisher.getPublisherPhone(),publisher.getPublisherId()});
	}
	
	public void deletePublisher(Publisher publisher) throws ClassNotFoundException, SQLException{
		template.update("delete from tbl_publisher where publisherId=?", new Object[] {publisher.getPublisherId()});
	}

	@Override
	public List<Publisher> extractData(ResultSet rs) throws SQLException {
		List<Publisher> publishers = new ArrayList<Publisher>();
		while(rs.next()){
			Publisher p = new Publisher();
			p.setPublisherId(rs.getInt("publisherId"));
			p.setPublisherName(rs.getString("publisherName"));
			p.setPublisherAddress(rs.getString("publisherAddress"));
			p.setPublisherPhone(rs.getString("publisherPhone"));
			publishers.add(p);
		}
		return publishers;
	}

	

}
