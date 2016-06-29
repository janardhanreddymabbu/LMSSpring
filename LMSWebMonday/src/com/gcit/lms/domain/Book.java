package com.gcit.lms.domain;

import java.util.Arrays;
import java.util.List;

public class Book {
	private Integer bookId;
	private String title;
	private Publisher publisher;
	private List<Author> authors;
	private List<Genre> genres;
	private String publishername;
	private String[] authors1;
	private String[] genres1;
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(authors1);
		result = prime * result + Arrays.hashCode(genres1);
		result = prime * result + ((publishername == null) ? 0 : publishername.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (!Arrays.equals(authors1, other.authors1))
			return false;
		if (!Arrays.equals(genres1, other.genres1))
			return false;
		if (publishername == null) {
			if (other.publishername != null)
				return false;
		} else if (!publishername.equals(other.publishername))
			return false;
		return true;
	}
	public String getPublishername() {
		return publishername;
	}
	public void setPublishername(String publishername) {
		this.publishername = publishername;
	}
	public String[] getAuthors1() {
		return authors1;
	}
	public void setAuthors1(String[] authors1) {
		this.authors1 = authors1;
	}
	public String[] getGenres1() {
		return genres1;
	}
	public void setGenres1(String[] genres1) {
		this.genres1 = genres1;
	}
	/**
	 * @return the bookId
	 */
	public Integer getBookId() {
		return bookId;
	}
	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the publisher
	 */
	public Publisher getPublisher() {
		return publisher;
	}
	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	/**
	 * @return the authors
	 */
	public List<Author> getAuthors() {
		return authors;
	}
	/**
	 * @param authors the authors to set
	 */
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	/**
	 * @return the genres
	 */
	public List<Genre> getGenres() {
		return genres;
	}
	/**
	 * @param genres the genres to set
	 */
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
	
	
}
