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

<%
	Publisher p = (Publisher) request.getAttribute("publisher");
%>


<%@ include file="include.html" %>
<h3>Hello Admin! Enter Publisher details</h3>
<body>
<form action="editPublisher" method="post">
<input type="hidden" name="publisherId" value=<%=p.getPublisherId()%>>
Enter Publisher Name: <input type="text" name="publisherName"required value=<%=p.getPublisherName()%>><br/>
Enter Publisher Address: <input type="text" name="publisherAddress"required value=<%=p.getPublisherAddress()%>><br/>
Enter Publisher Phone: <input type="text" name="publisherPhone"required value=<%=p.getPublisherPhone()%>><br/>

<button type="submit">Edit Publisher</button>

</form>
</body>
</html>