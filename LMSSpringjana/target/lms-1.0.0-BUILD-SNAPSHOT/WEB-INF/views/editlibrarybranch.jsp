<script>
$(document).ready(function()
		{
		    $('.modal').on('hidden.bs.modal', function(e)
		    { 
		        $(this).removeData();
		    }) ;
		});
</script>


<%@page import="com.gcit.lms.domain.LibraryBranch"%>
<%@ page import="com.gcit.lms.domain.Author"%>
<%
	LibraryBranch lb = (LibraryBranch) request.getAttribute("librarybranch");
%>
<%@ include file="include.html" %>
<h3>Hello Librarian! Edit Library branch details</h3>
<body>
	<form action="editLibrarybranch" method="post">
		<input type="hidden"name="branchId" value=<%=lb.getBranchId()%>>
		
		Edit Branch Name: <input type="text" name="branchName"value="<%=lb.getBranchName()%>"> 
		Edit Branch Address: <input type="text" name="branchAddress"value="<%=lb.getBranchAddress()%>">

		<button type="submit">Edit Library Branch</button>
	</form>
