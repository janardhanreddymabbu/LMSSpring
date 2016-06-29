<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="com.gcit.lms.service.AdministrativeService" %>
    <%@ page import="com.gcit.lms.domain.Book" %>
    <%@ page import="com.gcit.lms.domain.Author" %>
    <%@ page import="com.gcit.lms.domain.Genre" %>
    <%@ page import="com.gcit.lms.domain.Publisher" %>
    <%AdministrativeService service = new AdministrativeService(); 
    List<Book> books = new ArrayList<Book>();
    books= service.viewBooks();
    List<Publisher> publishers =  new ArrayList<Publisher>();
    publishers= service.viewPublishers();
    List<Author> authors =  new ArrayList<Author>();
    authors= service.viewAuthors();
    List<Genre> genres =  new ArrayList<Genre>();
    genres= service.viewGenres();
    %>
<jsp:include page='include.html'></jsp:include>
<h3>Hello Admin! Enter Book details</h3>
<body>
<form action="addBook" method="post">
Enter Book Name: <input type="text" name="bookName"required></br>



Select Publisher Name:<select name="selectedPublisherName"required>
  <%for (Publisher p: publishers) {%>
	  
	  <option value="<%=p.getPublisherId()%>"><%=p.getPublisherName()%></option>
	  <%} %>
</select></br>



Select Author's :<select multiple="multiple" name="selectedAuthors"required>
	<%for (Author a: authors) {%>
	  
	  <option value="<%=a.getAuthorId()%>" > <%=a.getAuthorName()%></option>
	  <%} %>
</select></br>



Select Genre's :<select multiple="multiple" name="selectedGenres"required>
	<%for (Genre g: genres) {%>
	  
	  <option value="<%=g.getGenre_id()%>"><%=g.getGenre_name()%></option>
	  <%} %>
</select></br>

<button type="submit">Add Book</button></br>



</form>
</body>
</html>