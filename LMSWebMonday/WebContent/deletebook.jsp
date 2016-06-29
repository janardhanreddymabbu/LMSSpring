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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="com.gcit.lms.service.AdministrativeService" %>
   
    <%@ page import="com.gcit.lms.domain.Author" %>
    
    <%
    Book b = (Book) request.getAttribute("book");
    %>
    
    

<jsp:include page='include.html'></jsp:include>
<h3>Hello Admin! Delete Book details</h3>
<body>
<form action="deleteBook" method="post">


<input type="hidden"
			name="bookId" value=<%=b.getBookId()%>>

Warning!! you are about to delete author:  <b><%= b.getTitle()%></b><br/>
<button type="submit">Delete Book</button>
 
</form>
</body>
</html>