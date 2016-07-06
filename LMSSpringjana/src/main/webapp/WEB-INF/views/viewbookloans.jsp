<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="com.gcit.lms.service.AdministrativeService" %>
    <%@ page import="com.gcit.lms.domain.BookLoans" %>
    <%AdministrativeService service = (AdministrativeService) request.getAttribute("service"); 
    List<BookLoans> bookloans = new ArrayList<BookLoans>();
    
    bookloans = service.viewBookLoans();
    %>
<%@ include file="include.html" %>
<h3>List of books loaned</h3> 

<div class="row">
	<div class="col-md-9">
		<table class="table" id="bookLoansTable">
			<thead>
				<tr>
					<th>Library ID</th>
					<th>Library  Name</th>
					<th>Book ID</th>
					<th>Book  Title</th>
					<th>card  No</th>
					<th>Check out date</th>
					<th>Due date</th>
					<th>Change Due date</th>
				</tr>
			</thead>
			<tbody>
			<%
					for (BookLoans b: bookloans) {
				%>
				<tr>
					
					<td ><%=b.getBranchId() %></td>
					<td ><%=b.getBranchname() %></td>
					<td ><%=b.getBookId()%></td>
					<td ><%=b.getBooktitle() %></td>
					<td ><%=b.getCardNo() %></td>
					<td ><%=b.getDateOut() %></td>
					<td ><%=b.getDueDate() %></td>
					<td><button type="button" class="btn btn-sm btn-primary"  data-toggle="modal" data-target="#myModal1"
							href='editbookloans?branchId=<%=b.getBranchId()%>&bookId=<%=b.getBookId()%>&cardNo=<%=b.getCardNo()%>&dueDate=<%=b.getDueDate()%>'">Extend Due</button></td>
						
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