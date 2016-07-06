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
    <%@ page import="com.gcit.lms.domain.BookCopies" %>
    <%AdministrativeService service = new AdministrativeService(); 
    String BranchId = request.getParameter("BranchId");
    
    List<BookCopies> bookcopies = new ArrayList<BookCopies>();
    bookcopies = service.viewBookCopiesbybranchId(Integer.parseInt(BranchId));
    
    %>
<%@ include file="include.html" %>
<h3>List of Books  <a href="viewlibrarybranch.jsp"><input type="button" value="Cancel" name="cancel"/></a></h3>
<div class="row">
	<div class="col-md-9">
		<table class="table" id="editbookCopiesTable">
			<thead>
				<tr>
					
	<th>Title</th>
	<th>Branch ID</th>
	<th>Branch Name</th>
	<th>Number of Copies</th>
	
	<th>Update Copies</th>
				</tr>
			</thead>
			<tbody>
			<%
					for (BookCopies b: bookcopies) {
				%>
				<tr>
					
					
			<td ><%=b.getBookTitle() %></td>
			<td ><%=b.getBranchId() %></td>
			<td ><%=b.getBranchName() %></td>
			<td ><%=b.getNoOfCopies() %></td>
					<td><button type="button" class="btn btn-sm btn-primary"  data-toggle="modal" data-target="#myModal1"
							href='managecopies.jsp?bookId=<%=b.getBookId()%>&branchId=<%=b.getBranchId()%>&noofCopies=<%=b.getNoOfCopies()%>'">Update Copies</button></td>
						
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
		
<div id="myModal1" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg">
		<div class="modal-content"></div>
	</div>
</div>





</html>