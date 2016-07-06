<script>
$(document).ready(function()
		{
		    $('.modal').on('hidden.bs.modal', function(e)
		    { 
		        $(this).removeData();
		    }) ;
		});
</script>


<%@ page import="com.gcit.lms.domain.Author"%>
<%
	Author a = (Author) request.getAttribute("author");

%>
<%@ include file="include.html" %>


<h3>Hello Admin! Edit Author details</h3>
<body>
	<form action="EditAuthor" method="post">
	
Edit Author Name: <input type="text" name="authorName" value="<%=a.getAuthorName()%>"> 
<input type="hidden" name="authorId" value=<%=a.getAuthorId()%>>
<button type="submit">Edit Author</button>
	</form>
	
	
</body>


</html>