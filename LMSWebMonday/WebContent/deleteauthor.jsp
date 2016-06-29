<script>
$(document).ready(function()
		{
		    $('.modal').on('hidden.bs.modal', function(e)
		    { 
		        $(this).removeData();
		    }) ;
		});
</script>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="com.gcit.lms.service.AdministrativeService" %>
   
    <%@ page import="com.gcit.lms.domain.Author" %>
    
    <%
    Author a = (Author) request.getAttribute("author");
    %>
    
    

<jsp:include page='include.html'></jsp:include>
<h3>Hello Admin! Delete Author details</h3>
<body>
<form action="DeleteAuthor" method="post">
<input type="hidden"
			name="authorId" value=<%=a.getAuthorId()%>>

Warning!! you are about to delete author:  <b><%= a.getAuthorName()%></b><br/>
<button type="submit">Delete Author</button>
 
</form>

<script>
$(document).ready(function()
		{
		    $('.modal').on('hidden.bs.modal', function(e)
		    { 
		        $(this).removeData();
		    }) ;
		});
</script>
</body>
</html>