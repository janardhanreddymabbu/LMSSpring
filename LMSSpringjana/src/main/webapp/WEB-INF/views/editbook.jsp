<script>
$(document).ready(function()
		{
		    $('.modal').on('hidden.bs.modal', function(e)
		    { 
		        $(this).removeData();
		    }) ;
		});
</script>

<%@page import="com.gcit.lms.domain.Book"%>

<%
	Book b = (Book) request.getAttribute("book");

%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="com.gcit.lms.service.AdministrativeService" %>
    <%@ page import="com.gcit.lms.domain.Book" %>
    <%@ page import="com.gcit.lms.domain.Author" %>
    <%@ page import="com.gcit.lms.domain.Genre" %>
    <%@ page import="com.gcit.lms.domain.Publisher" %>
    <%AdministrativeService service = (AdministrativeService) request.getAttribute("service"); 
    List<Book> books = new ArrayList<Book>();
    books= service.viewBooks();
    List<Publisher> publishers =  new ArrayList<Publisher>();
    publishers= service.viewPublishers();
    List<Author> authors =  new ArrayList<Author>();
    authors= service.viewAuthors();
    List<Genre> genres =  new ArrayList<Genre>();
    genres= service.viewGenres();
    %>
<%@ include file="include.html" %>
<h3>Hello Admin! Enter Book details</h3>
<body>
<form action="editBook" method="post">
Enter Book Name: <input type="text" name="bookName"required value="<%=b.getTitle()%>"></br>
<input type="hidden" name="bookId"required value=<%=b.getBookId()%>>


Select Publisher Name:<select name="selectedPublisherName"required>
  
 <option value="<%=b.getPublisher().getPublisherId()%>" selected="selected"><%=b.getPublisher().getPublisherName()%></option>
	 
	   
	  
	 <%for (Publisher p: publishers) {%>
	 
	   
	  <option value="<%=p.getPublisherId()%>" ><%=p.getPublisherName()%></option>
	  
	  <%} %>
	  
</select></br>



Select Author's :<select multiple="multiple" name="selectedAuthors"required>
	<%for (Author a: b.getAuthors()) {%>
	
	
	 
	  <option value="<%=a.getAuthorId()%>"selected="selected" > <%=a.getAuthorName()%></option>
	  <%} %>
	
	
	<%for (Author a: authors) {%>
	
	
	 
	  <option value="<%=a.getAuthorId()%>" > <%=a.getAuthorName()%></option>
	  <%} %>
</select></br>




Select Genre's :<select multiple="multiple" name="selectedGenres"required>
	<%for (Genre g: b.getGenres()) {%>
	  
	  <option value="<%=g.getGenre_id()%>" selected="selected"><%=g.getGenre_name()%></option>
	  <%} %>
	
	<%for (Genre g: genres) {%>
	  
	  <option value="<%=g.getGenre_id()%>"><%=g.getGenre_name()%></option>
	  <%} %>
</select></br>

<button type="submit">Edit Book</button></br>



</form>
</body>
</html>
