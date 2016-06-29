package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Genre;

public class GenreDAO extends BaseDAO {
	public GenreDAO(Connection conn) {
		super(conn);
	}
	
	public void insertGenre(Genre genre) throws ClassNotFoundException, SQLException{
		save("insert into tbl_genre (genre_name) values (?)", new Object[] {genre.getGenre_name()});
	}
	
	public void deleteGenre(Genre genre) throws ClassNotFoundException, SQLException{
		save("delete from tbl_genre where genre_id=?", new Object[] {genre.getGenre_id()});
	}
	
	public void deleteAll() throws ClassNotFoundException, SQLException{
		save("delete * from tbl_genre", null);
	}
	
	public void updateGenre(Genre genre) throws ClassNotFoundException, SQLException{
		save("update  tbl_genre set genre_name = ? where genre_id = ?", new Object[] {genre.getGenre_name(), genre.getGenre_id()});
	}
	
	public List<Genre> readAll() throws ClassNotFoundException, SQLException{
		return	 read("select * from tbl_genre", null);
	}
	
	
	

	@Override
	public List<?> extractData(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		List<Genre> genres = new ArrayList<Genre>();
		while(rs.next()){
			Genre g = new Genre();
			g.setGenre_id(rs.getInt("genre_id"));
			g.setGenre_name(rs.getString("genre_name"));
			
			genres.add(g);
		}
		return genres;
	}

	@Override
	public List<?> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<Genre> genres = new ArrayList<Genre>();
		while(rs.next()){
			Genre g = new Genre();
			g.setGenre_id(rs.getInt("genre_id"));
			g.setGenre_name(rs.getString("genre_name"));
			
			genres.add(g);
		}
		return genres;
	}

}
