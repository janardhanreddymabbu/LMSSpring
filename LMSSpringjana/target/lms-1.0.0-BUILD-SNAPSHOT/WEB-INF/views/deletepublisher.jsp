<script>
$(document).ready(function()
		{
		    $('.modal').on('hidden.bs.modal', function(e)
		    { 
		        $(this).removeData();
		    }) ;
		});
</script>


<%@page import="com.gcit.lms.domain.Publisher"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="com.gcit.lms.service.AdministrativeService" %>
   
    <%@ page import="com.gcit.lms.domain.Author" %>
    
    <%
    Publisher p = (Publisher) request.getAttribute("publisher");
    %>
    
    

<%@ include file="include.html" %>
<h3>Hello Admin! Delete Publisher details</h3>
<body>
<form action="deletePublisher" method="post">


<input type="hidden"
			name="publisherId" value=<%=p.getPublisherId()%>>

Warning!! you are about to delete publisher:  <b><%= p.getPublisherName()%></b><br/>
<button type="submit">Delete Publisher</button>
 
</form>
</body>
</html>