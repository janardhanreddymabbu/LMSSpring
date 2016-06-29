package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Publisher;

public class PublisherDAO extends BaseDAO{
	
	public PublisherDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public List<Publisher> readAll() throws ClassNotFoundException, SQLException{
		return read("select * from tbl_publisher", null);
	}
	public void insertPublisher(Publisher publisher) throws ClassNotFoundException, SQLException{
		save("insert into tbl_publisher (publisherName,publisherAddress,publisherPhone) values (?,?,?)", new Object[] {publisher.getPublisherName(),publisher.getPublisherAddress(),publisher.getPublisherPhone()});
	}
	public List<Publisher> readAllbyId(Publisher publisher) throws ClassNotFoundException, SQLException{
		return	 read("select * from tbl_publisher where publisherId=?", new Object[] {publisher.getPublisherId()});
	}
	
	public Publisher readOne(Publisher publisher) throws ClassNotFoundException, SQLException{
		List<Publisher> publishers = read("select * from tbl_publisher where publisherId =?", new Object[] {publisher.getPublisherId()});
		for(Publisher p: publishers){
			
			return p;
		}
		return null;
	}
	
	public void editPublisher(Publisher publisher) throws ClassNotFoundException, SQLException{
		save("UPDATE `library`.`tbl_publisher` SET `publisherName`=?, `publisherAddress`=?, `publisherPhone`=? WHERE `publisherId`=?;", new Object[] {publisher.getPublisherName(),publisher.getPublisherAddress(),publisher.getPublisherPhone(),publisher.getPublisherId()});
	}
	
	public void deletePublisher(Publisher publisher) throws ClassNotFoundException, SQLException{
		save("delete from tbl_publisher where publisherId=?", new Object[] {publisher.getPublisherId()});
	}

	@Override
	public List<?> extractData(ResultSet rs) throws SQLException {
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

	@Override
	public List<?> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<Publisher> publishers = new ArrayList<Publisher>();
		while(rs.next()){
			Publisher p = new Publisher();
			p.setPublisherId(rs.getInt("publisherId"));
			p.setPublisherName(rs.getString("publisherName"));
			publishers.add(p);
		}
		return publishers;
	}

}
